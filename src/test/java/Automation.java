
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;


public class Automation {
    public static WebDriver driver;
    @BeforeTest
    public static void preSetUp()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public static void checkTest() throws IOException, InterruptedException {
        driver.get(ConstantsClass.homeUrl);
        HomePage.validateHeader(driver);
        HomePage.selectItem(driver,"Desktops");
        CategoriesPage.addItemsTOCard(driver,"Lenovo");
        CartPage.validateCartItem(driver,"Lenovo",true);
        HashMap<String, String>  billingData = new HashMap<>();
        billingData.put("FirstName","Vaibhav");
        billingData.put("LastName","Vaibhav");
        billingData.put("Email","Vaibhav@Assesment.com");
        billingData.put("City","Vaibhav");
        billingData.put("Address1","Vaibhav");
        billingData.put("ZipPostalCode","12345");
        billingData.put("PhoneNumber","1234567890");
        billingData.put("CountryId","United States");
        billingData.put("StateProvinceId","Alaska");
        CartPage.checkoutAsGuest(driver,billingData);
        CartPage.continueTillCHK(driver, "Billing");
        CartPage.continueTillCHK(driver, "ShippingMethod");
        CartPage.continueTillCHK(driver, "PaymentMethod");
        CartPage.continueTillCHK(driver, "PaymentInfo");
        CartPage.continueTillCHK(driver, "ConfirmOrder");
        CartPage.thankUPage(driver);
//        driver.quit();

    }
    @AfterTest
    public static void closeDriver()
    {
        driver.quit();
        System.out.print("The test was successful");
    }

}
