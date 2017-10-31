package SlotM;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base {

    protected static WebDriver driver = null;

    @Before
    public void setUp() throws Exception {
        System.out.print("\nBrowser open");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("http://slotmachinescript.com/");
        Assert.assertEquals("Incorrect title", "Add a HTML5 Slot Machine to your Site", driver.getTitle());
    }

    @After
    public void tearDown() throws Exception {
        System.out.print("\nBrowser close");
        driver.close();
        driver.quit();
    }

}
