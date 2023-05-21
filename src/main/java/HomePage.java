import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;


public class HomePage {
    static By header = By.xpath("//div[@class='header-logo']");
    static By img = By.xpath("//img[@alt='nopCommerce demo store']");
    public static void validateHeader(WebDriver driver) throws IOException {
        driver.findElement(header);
        CloseableHttpClient client = HttpClientBuilder.create().build();
        String srcAttributeValue = driver.findElement(img).getAttribute("src");
        HttpGet request = new HttpGet(srcAttributeValue);
        CloseableHttpResponse response = client.execute(request);
        System.out.println(response.getCode());
        Assert.assertEquals(response.getCode(),200);

    }
    public static void selectItem(WebDriver driver, String option) throws IOException, InterruptedException {
        WebElement elementToHover = driver.findElement(By.xpath("//a[@href and contains(text(),'Computers')][1]"));

        Actions actions = new Actions(driver);

        actions.moveToElement(elementToHover).perform();
        Thread.sleep(3000);
        List<WebElement> dropdownElement = driver.findElements(By.xpath("(//div[@class='sublist-toggle'])[1]/following::ul[1]//li"));
        for(WebElement ele : dropdownElement)
        {
            if (ele.getText().trim().equals(option)) {
                ele.click();
                break;
            }
        }

    }
}
