package E_ShopProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage{
    public  WebDriver driver;
    public static final By BTN_OPEN_CATEGORY = By.id("nav-hamburger-menu");
    public static final String URL = "https://www.amazon.in/";
       private static final By LOGO = By.xpath("//*[@id=\"nav-logo-sprites\"]");

    public void navigateToAmazon() {
        driver.get(URL);
    }

        public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        System.out.println(driver.getTitle());
        return driver.getTitle();
    }

    public void clickShopByCategory() {
        driver.findElement(BTN_OPEN_CATEGORY).click();
    }
}
