import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.*;

public class CartPage {
public static WebElement getElement (WebDriver driver, String idValue)
{
    WebElement findEle = driver.findElement(By.id(String.format("BillingNewAddress_%s",idValue)));
    return findEle;
}
    public static WebElement onClick (WebDriver driver, String idValue)
    {
        WebElement findEle = driver.findElement(By.xpath(String.format("//button[@onClick='%s.save()']",idValue)));
        return findEle;
    }

    public static void validateCartItem(WebDriver driver, String object, Boolean state) throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/cart");
        WebElement checkItem = driver.findElement(By.className("product-name"));
        Assert.assertTrue(checkItem.getText().trim().contains(object));
        WebElement checkbox = driver.findElement(By.id("termsofservice"));
        boolean isSelected = checkbox.isSelected();//div[@class='product']//div[@class='name']
        if(!(isSelected==state))
        {
            checkbox.click();
            Thread.sleep(2000);
        }
        WebElement checkout = driver.findElement(By.id("checkout"));
        checkout.click();
        Thread.sleep(2000);
    }
    public static void checkoutAsGuest(WebDriver driver, HashMap getAddress) throws InterruptedException {

        WebElement checkItem = driver.findElement(By.cssSelector(".button-1.checkout-as-guest-button"));
        checkItem.click();
        if(getAddress.containsKey("FirstName"))
        {
            String value = getAddress.get("FirstName").toString();
            getElement(driver,"FirstName").sendKeys(value);
        }
        if(getAddress.containsKey("LastName"))
        {
            String value = getAddress.get("LastName").toString();
            getElement(driver,"LastName").sendKeys(value);
        }
        if(getAddress.containsKey("Email"))
        {
            String value = getAddress.get("Email").toString();
            getElement(driver,"Email").sendKeys(value);
        }
        if(getAddress.containsKey("City"))
        {
            String value = getAddress.get("City").toString();
            getElement(driver,"City").sendKeys(value);
        }
        if(getAddress.containsKey("Address1"))
        {
            String value = getAddress.get("Address1").toString();
            getElement(driver,"Address1").sendKeys(value);
        }
        if(getAddress.containsKey("ZipPostalCode"))
        {
            String value = getAddress.get("ZipPostalCode").toString();
            getElement(driver,"ZipPostalCode").sendKeys(value);
        }
        if(getAddress.containsKey("PhoneNumber"))
        {
            String value = getAddress.get("PhoneNumber").toString();
            getElement(driver,"PhoneNumber").sendKeys(value);
        }
        if(getAddress.containsKey("CountryId"))
        {
            String value = getAddress.get("CountryId").toString();
            Select select = new Select(getElement(driver,"CountryId"));
            select.selectByVisibleText(value);
            Thread.sleep(5000);
        }
        if(getAddress.containsKey("StateProvinceId"))
        {
            String value = getAddress.get("StateProvinceId").toString();
            Select select = new Select(getElement(driver,"StateProvinceId"));
            select.selectByVisibleText(value);
        }
    }
    public static void continueTillCHK(WebDriver driver, String value) throws InterruptedException {
         onClick(driver,value).isDisplayed();
        onClick(driver,value).click();
        Thread.sleep(2000);
    }
    public static void thankUPage(WebDriver driver)
    {
        WebElement thankU = driver.findElement(By.className("page-title"));
        Assert.assertEquals(thankU.getText().trim(),"Thank you");
        WebElement thankInfo = driver.findElement(By.xpath("//div[@class='section order-completed']//div[@class='title']"));
        Assert.assertEquals(thankInfo.getText().trim(),"Your order has been successfully processed!");
    }
}
