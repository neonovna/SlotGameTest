package SlotM;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestSuite extends Base {
    MainPage mainp;
    WebDriverWait wait;

    @Before
    public void setup2() throws Exception {
        mainp = new MainPage(driver);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void Test1_Change_Backgrounds() throws InterruptedException {
        for (int i = 1; i <= mainp.maxBackgrounds; i++) {
            wait.until(ExpectedConditions.visibilityOf(mainp.backgroundElement(i)));
            assertTrue("Background is not changed", mainp.backgroundElement(i).isDisplayed());
            mainp.clickOnChangeBkgr();
        }
    }

    @Test
    public void Test2_Change_Icons() throws InterruptedException {
        for (int i = 1; i <= mainp.maxIcons; i++) {
           WebElement element = wait.until
                (ExpectedConditions.presenceOfElementLocated(mainp.iconElement(i)));
            assertTrue("Icon is not changed", element.isDisplayed());
            mainp.clickOnChangeIcon();
        }
    }

    @Test
    public void Test3_Change_Machine() throws InterruptedException {
        for (int i = 1; i <= mainp.maxMachines; i++) {
            WebElement element = wait.until
                    (ExpectedConditions.presenceOfElementLocated(mainp.machineElement(i)));
            assertTrue("Slot Machines are not changed", element.isDisplayed());
            mainp.clickOnChangeMachine();
        }
    }

    @Test
    public void Test4_Change_Bet_Value() throws InterruptedException {
        for (int i = 1; i < mainp.maxBets; i++) {
            assertEquals("Bet value is not changed after spin up", mainp.actualBetValue(), i);
            mainp.clickBetSpinUp();
        }
        for (int i = mainp.actualBetValue(); i > 0; i--) {
            assertEquals("Bet value is not changed after spin down", mainp.actualBetValue(), i);
            mainp.clickBetSpinDown();
        }
    }

    @Test
    public void Test5_Change_Payout_Value() {
        for (int i = 2; i < mainp.maxBets; i++) {
            mainp.clickBetSpinUp();
            for (WebElement element : mainp.listOfElements()) {
                float baseValue = mainp.getBaseValue(element);
                float actualValue = mainp.getActualValue(element);
                assertEquals("Payout value is not increased after changing bet", actualValue, baseValue * i, 0.00);
            }
        }
    }

    @Test
    public void Test6_Won_Game() throws InterruptedException {
        boolean win = false;
        while (!win && mainp.maxTries > 0) {
            mainp.clickSpinBtn();
            wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBeNotEmpty(mainp.btnSpin,"class")));
            try {
                mainp.wonLine.isDisplayed();
                win = true;
            } catch (Exception e) {
                mainp.maxTries--;
            }
        }
        assertEquals("Last Won value is not changed", mainp.getWonValue(), mainp.getLastWinValue(), 0.00);
    }

    @Test
    public void Test7_Credit_Value_if_Win() throws InterruptedException {
        boolean win = false;
        float credits = mainp.getCreditValue();
        while (!win && mainp.maxTries > 0) {
            mainp.clickSpinBtn();
            wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBeNotEmpty(mainp.btnSpin,"class")));
            credits--;
            try {
                mainp.wonLine.isDisplayed();
                win = true;
            } catch (Exception e) {
                mainp.maxTries--;
            }
        }
        float currentCreditValue = mainp.getCreditValue();
        float expectedCreditValue = credits + mainp.getWonValue();
        assertEquals("Credit value is not changed after win", currentCreditValue, expectedCreditValue, 0.00);

    }

    @Test
    public void Test8_Credit_Value_if_Lost() throws InterruptedException {
        boolean lost = false;
        float credits;
        while (!lost && mainp.maxTries > 0) {
            credits = mainp.getCreditValue();
            mainp.clickSpinBtn();
            wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBeNotEmpty(mainp.btnSpin,"class")));
            credits--;
            try {
                mainp.wonLine.isDisplayed();
                mainp.maxTries--;
            } catch (Exception e) {
                assertEquals("Credit value is not reduced after losing game", credits, mainp.getCreditValue(), 0.00);
                lost = true;
            }
        }
    }
}





