package SlotM;


import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestSuite extends Base {

    @Test
    public void Test1_Change_Backgrounds() throws InterruptedException {
        MainPage mainp = new MainPage(driver);
        for (int i = 1; i < 5; i++) {
            assertTrue("Background is not changed",mainp.backgroundElement(i).isDisplayed());
            mainp.clickOnChangeBkgr();
        }

    }

    @Test
    public void Test2_Change_Icons() throws InterruptedException {
        MainPage mainp = new MainPage(driver);
        for (int i = 1; i < 4; i++) {
            assertTrue("Icons are not changed",mainp.iconElement(i).isDisplayed());
            mainp.clickOnChangeIcon();
        }

    }

    @Test
    public void Test3_Change_Machine() throws InterruptedException {
        MainPage mainp = new MainPage(driver);
        for (int i = 1; i < 6; i++) {
            assertTrue("Slot Machines are not changed",mainp.machineElement(i).isDisplayed());
            mainp.clickOnChangeMachine();
        }

    }


    @Test
    public void Test4_Change_Bet_Value() throws InterruptedException {
        MainPage mainp = new MainPage(driver);
        for (int i = 1; i < 9; i++) {
            assertEquals("Bet value is not changed after spin up",mainp.actualBetValue(), i);
            mainp.clickBetSpinUp();
        }
        for (int i = mainp.actualBetValue(); i > 0; i--) {
            assertEquals("Bet value is not changed after spin down",mainp.actualBetValue(), i);
            mainp.clickBetSpinDown();
        }
    }


    @Test
    public void Test5_Change_Payout_Value() {
        MainPage mainp = new MainPage(driver);
        for (int i = 2; i < 9; i++) {
            mainp.clickBetSpinUp();
            for (WebElement element : mainp.listOfElements()) {
                float baseValue = mainp.getBaseValue(element);
                float actualValue = mainp.getActualValue(element);
                assertEquals("Payout value is not increased after changing bet",actualValue, baseValue * i, 0.00);
            }
        }
    }

    @Test
    public void Test6_Won_Game() throws InterruptedException {
        MainPage mainp = new MainPage(driver);
        boolean win = false;
        int tries = 10;
        while (!win && tries > 0) {
            mainp.clickSpinBtn();
            try {
                mainp.wonLine.isDisplayed();
                win = true;
            } catch (Exception e) {
                tries--;
            }
        }
        assertEquals("Last Won value is not changed",mainp.getWonValue(), mainp.getLastWinValue(), 0.00);
    }


    @Test
    public void Test7_Credit_Value_if_Win() throws InterruptedException {
        MainPage mainp = new MainPage(driver);
        boolean win = false;
        int tries = 10;
        float credits = mainp.getCreditValue();
        while (!win && tries > 0) {
            mainp.clickSpinBtn();
            credits--;
            try {
                mainp.wonLine.isDisplayed();
                win = true;
            } catch (Exception e) {
                tries--;
            }
        }
        Thread.sleep(5000);
        float currentCreditValue = mainp.getCreditValue();
        float expectedCreditValue = credits + mainp.getWonValue();
        assertEquals("Credit value is not changed after win",currentCreditValue, expectedCreditValue, 0.00);

    }


    @Test
    public void Test8_Credit_Value_if_Lost() throws InterruptedException {
        MainPage mainp = new MainPage(driver);
        boolean lost = false;
        int tries = 5;
        float credits;
        while (!lost && tries > 0) {
            credits = mainp.getCreditValue();
            mainp.clickSpinBtn();
            credits--;
            try {
                mainp.wonLine.isDisplayed();
                tries--;
            } catch (Exception e) {
                assertEquals("Credit value is not reduced after losing game",credits, mainp.getCreditValue(), 0.00);
                lost = true;
            }
        }

    }

}





