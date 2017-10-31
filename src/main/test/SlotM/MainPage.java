package SlotM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPage {

    public WebDriver driver;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(how = How.CLASS_NAME, using = "btnChangeBackground")
    private WebElement btnChangeBkgr;

    @FindBy(how = How.CLASS_NAME, using = "btnChangeReels")
    private WebElement btnChangeIcons;

    @FindBy(how = How.CLASS_NAME, using = "btnChangeMachine")
    private WebElement btnChangeMachine;

    @FindBy(how = How.ID, using = "spinButton")
    private WebElement btnSpin;

    @FindBy(how = How.ID, using = "lastWin")
    private WebElement lastWinBox;

    @FindBy(how = How.ID, using = "credits")
    private WebElement creditsBox;

    @FindBy(how = How.ID, using = "bet")
    private WebElement betBox;

    @FindBy(how = How.ID, using = "dayWinnings")
    private WebElement dayWinningsBox;

    @FindBy(how = How.ID, using = "lifetimeWinnings")
    private WebElement lifetimeWinningsBox;

    @FindBy(how = How.ID, using = "betSpinUp")
    private WebElement btnBetSpinUp;

    @FindBy(how = How.ID, using = "betSpinDown")
    private WebElement btnBetSpinDown;

    @FindBy(how = How.CLASS_NAME, using = "tdPayout")
    public WebElement payoutBox;

    @FindBy(how = How.XPATH, using = "//div[@class='won']")
    public WebElement wonView;

    @FindBy(how = How.XPATH, using = "//div[@class='trPrize won']")
    public WebElement wonLine;

    @FindBy(how = How.XPATH, using = "//div[@class='trPrize won']/descendant::span[@class='tdPayout']")
    public WebElement wonValue;

    public float getWonValue() {
        return Float.parseFloat(wonValue.getText());
    }

    public float getCreditValue() {
        return Float.parseFloat(creditsBox.getText());
    }

    public float getLastWinValue() {
        return Float.parseFloat(lastWinBox.getText());
    }

    public float getActualValue(WebElement element) {
        return Float.parseFloat(element.getAttribute("textContent"));
    }

    public float getBaseValue(WebElement element) {
        return Float.parseFloat(element.getAttribute("data-basePayout"));
    }

    public void clickOnChangeBkgr() throws InterruptedException {
        btnChangeBkgr.click();
        Thread.sleep(3000);
    }

    public void clickOnChangeIcon() throws InterruptedException {
        btnChangeIcons.click();
        Thread.sleep(3000);
    }

    public void clickOnChangeMachine() throws InterruptedException {
        btnChangeMachine.click();
        Thread.sleep(3000);
    }

    public void clickSpinBtn() throws InterruptedException {
        btnSpin.click();
        Thread.sleep(5000);
    }

    public void clickBetSpinUp() {
        btnBetSpinUp.click();
    }

    public void clickBetSpinDown() {
        btnBetSpinDown.click();
    }

    public int actualBetValue() {
        return Integer.parseInt(betBox.getText());
    }

    public List<WebElement> listOfElements() {
        return driver.findElements(By.xpath("//span[@class='tdPayout']"));
    }

    public WebElement backgroundElement(int i) {
        return driver.findElement(By.xpath("//div[@id='changeable_background_" + i + "']"));
    }

    public WebElement iconElement(int i) {
        return driver.findElement(By.xpath("//div[contains(@class, 'reelSet" + i + "')]"));
    }

    public WebElement machineElement(int i) {
        return driver.findElement(By.xpath("//div[contains(@class, 'slotMachine" + i + "')]"));
    }

}
