package E_ShopProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.testng.Assert;

import java.util.List;
import java.util.Set;



public class ElectronicsCategoryPage{
    private final WebDriver driver;
    private static final By ELECTRONICS = By.linkText("Electronics");
    private static final By SEARCH_BOX = By.xpath("//*[@id=\"twotabsearchtextbox\"]");
    private static final By SEARCH_SUBMIT_BTN = By.xpath("//*[@id=\"nav-search-submit-button\"]");
    private static final By SELECT_SORT = By.xpath("//*[@id=\"a-autoid-0-announce\"]");
    private static final By DROP_DOWN_HIGH_LOW = By.xpath("//*[@id=\"s-result-sort-select_2\"]");
    private static final By ITEMS = By.cssSelector(".s-result-list .s-result-item.s-asin");
    private static final By CART_COUNT= By.id("nav-cart-count");
    private static final By NAV_CART = By.id("nav-cart");


    public ElectronicsCategoryPage(WebDriver driver) {
         this.driver = driver;
    }

    public void clickElectronicsCategory() {
        driver.findElement(ELECTRONICS).click();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void searchItem(String item){
        driver.findElement(SEARCH_BOX).sendKeys(item);
        driver.findElement(SEARCH_SUBMIT_BTN).click();
    }

    public void sortByPriceHighToLow() {
        // Find the sort dropdown and select "Price: High to Low"
        WebElement sortDropdown = driver.findElement(SELECT_SORT);
        sortDropdown.click();
        WebElement highToLowOption = driver.findElement(DROP_DOWN_HIGH_LOW);
        highToLowOption.click();
    }

    public void addMostExpensiveItemsToCart() throws InterruptedException {
        // Find and add the top 'numberOfItems' most expensive items to the cart
        List<WebElement> webelements = driver.findElements(ITEMS);
        for(int i = 0;i<2;i++) {
        webelements.get(i).click();
        switchToNewWindow();
        addToCart();
        driver.switchTo().defaultContent();
        }
    }

    private void switchToNewWindow() {
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            driver.switchTo().window(handle);
        }
    }

    public void addToCart() {
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-button"));
        addToCartButton.click();
    }

    private void switchToOriginalWindow() {
        String originalHandle = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                driver.close();
                break;
            }
        }
        driver.switchTo().window(originalHandle);
    }

    public void navigateBackToElectronicsCategory() {
        // Switch back to the original window or tab
        switchToOriginalWindow();
    }

    public boolean isCartContains(int expectedItemCount) {
        WebElement cartCount = driver.findElement(CART_COUNT);
        int actualItemCount = Integer.parseInt(cartCount.getText());
        return actualItemCount == expectedItemCount;
    }
    public void assertItemsInCart(String[] expectedItemNames) {
        WebElement cartIcon = driver.findElement(NAV_CART);
        cartIcon.click();
            for (String expectedItem : expectedItemNames) {
            WebElement item = driver.findElement(By.xpath("//span[contains(text(), '" + expectedItem + "')]"));
            Assert.assertNotNull(item, "Item '" + expectedItem + "' is not found in the cart.");
        }
    }
}