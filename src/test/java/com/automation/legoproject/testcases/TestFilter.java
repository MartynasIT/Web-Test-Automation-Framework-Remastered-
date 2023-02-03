package com.automation.legoproject.testcases;

import com.automation.framework.loging.Log4jLogger;
import com.automation.legoproject.base.BaseTest;
import com.automation.legoproject.base.PageNavigator;
import com.automation.legoproject.pageobjects.CartPage;
import com.automation.legoproject.pageobjects.MainPage;
import com.automation.legoproject.pageobjects.ProductSearchResultPage;
import com.automation.legoproject.utils.LegoOrder;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TestFilter extends BaseTest {
    private PageNavigator navigator;
    private ProductSearchResultPage results;
    private MainPage mainPage;
    private CartPage cart;

    @Test
    public void testFilterAndBasket() {
        mainPage = new MainPage(selenium, true);
        navigator = new PageNavigator(selenium);
        results = navigator.navigateToProductsKeychains();
        String item = getJsonReader().getValue("Search.Item");
        String age = getJsonReader().getValue("Search.Age");
        results.selectProductType(item, "Filtering by " + item);
        results.ExpandFilters();
        results.selectAge(age, "Filtering by " + age);
        selenium.waitTwoSeconds();
        Integer resultCount = Integer.parseInt(results.getResultCount());
        results.loadProducts(resultCount);
        Assert.assertEquals(resultCount, results.getCountOfActualLoadedItems(),
                "Page should have loaded exact amount of products");
        Log4jLogger.log("Correct amount of products was loaded");
        Assert.assertTrue(LegoOrder.isFilterSatisfied(getJsonReader().getValue("Search.Max_Price"),
                results.getAllProductData()), "Filter did not work correctly");
        Log4jLogger.log("All prices are below filter and products are " + item);
        String[][] addToBagProducts = results.addToBasket(3);
        cart = navigator.navigateToCart();
        Assert.assertTrue(LegoOrder.areAllProductsAddedOnce(cart.getAllQuantitiesInCart()), "Items" +
                "should have been only once");
        Log4jLogger.log("All items were added once");
        Assert.assertEquals(cart.getTotal(), LegoOrder.calculateOrderTotalOfItemsAdded(addToBagProducts,
                cart.getShippingPrice()), "Total of order is not correct");
        Log4jLogger.log("Total of order is correct");
    }

}
