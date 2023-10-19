import E_ShopProject.BasePage;
import E_ShopProject.ElectronicsCategoryPage;
import E_ShopProject.HomePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AmazonCategoryNavigationTest extends BasePage{

    private HomePage homePage;
    private ElectronicsCategoryPage electronicsCategoryPage;



    @BeforeClass
    public void initializePageObjects() {

        homePage = new HomePage(driver);
        electronicsCategoryPage = new ElectronicsCategoryPage(driver);
    }
    @Test
    public void navigateToAmazonAndSelectCategoryTest() {
        homePage.navigateToAmazon();
        String ActualPage = homePage.getPageTitle();
        homePage.clickShopByCategory();
        electronicsCategoryPage.clickElectronicsCategory();
        String Expected_pageTitle = "Electronics Store: Buy Electronics products Online at Best Prices in India at Amazon.in";
        String Actual_pageTitle = electronicsCategoryPage.getPageTitle();
        System.out.println("Page Title: " + Actual_pageTitle);
        SoftAssert softAssert = new SoftAssert();
        System.out.println(ActualPage);
        softAssert.assertTrue(ActualPage.toLowerCase().contains("amazon.in"), "Amazon.in did not open");
        softAssert.assertEquals(Actual_pageTitle,Expected_pageTitle,"Page title after navigating landed on" + Actual_pageTitle);
        softAssert.assertAll();
    }

    @Test
    public void addItemsToCartTest() throws InterruptedException {
        String searchItem = "Apple Watch";
        String[] expectedNames = {"Apple-Cellular-Stainless","Apple-Cellular-Stainless--Milanese-Resistant"};
        homePage.navigateToAmazon();
        homePage.clickShopByCategory();

        electronicsCategoryPage.clickElectronicsCategory();
        electronicsCategoryPage.searchItem(searchItem);
        electronicsCategoryPage.sortByPriceHighToLow();
        electronicsCategoryPage.addMostExpensiveItemsToCart();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(electronicsCategoryPage.isCartContains(2), "Shopping cart should contain 2 items.");

    }


}
