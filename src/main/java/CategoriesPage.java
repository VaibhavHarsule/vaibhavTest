import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.*;

public class CategoriesPage {
    private static WebDriver driver;

    //By optionList = By.xpath("//h2[@class='product-title']");
    public static void addItemsTOCard(WebDriver driver, String object) throws InterruptedException {
        CategoriesPage.driver = driver;
        List<WebElement> getItems = driver.findElements(By.xpath("//h2[@class='product-title']"));
        for (WebElement product : getItems) {
            // Get the product title
            WebElement titleElement = product.findElement(By.tagName("a"));
            String title = titleElement.getText();

            // Check if the title contains "Lenovo"
            if (title.contains("Lenovo")) {
                // Click on the Lenovo product
                titleElement.click();
                break; // Exit the loop since Lenovo product is found
            }
            Thread.sleep(3000);
        }

        WebElement addItemCart =  driver.findElement(By.xpath("//input[@class='qty-input']/following::button[1]"));
        if(addItemCart.isDisplayed())
        {
            addItemCart.click();
            Thread.sleep(1000);
        }
        else {
            System.out.print("Incurred some problem in find item");
        }
    }
}
