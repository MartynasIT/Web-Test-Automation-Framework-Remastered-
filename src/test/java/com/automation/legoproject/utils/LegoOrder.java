package com.automation.legoproject.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class LegoOrder {
    private static final NumberFormat CURRENCY_FORMAT = NumberFormat.getCurrencyInstance(Locale.US);

    public static synchronized boolean isFilterSatisfied(String price, String[][] products) {
        BigDecimal filterPrice = parsePrice(price);
        for (String[] product : products) {
            BigDecimal productPrice = parsePrice(product[1]);
            if (productPrice.compareTo(filterPrice) > 0) {
                return false;
            }
        }
        return true;
    }

    public static BigDecimal parsePrice(String priceString) {
        String productPrice = priceString.
                replace("Price", "").
                replace("Sale Price", "").replace("Sale", "").
                replace("$", "").
                trim();
        return new BigDecimal(productPrice);
    }

    public static synchronized boolean areAllProductsAddedOnce(Integer[] data) {
        for (Integer datum : data) {
            if (datum > 1) {
                return false;
            }
        }
        return true;
    }

    public static synchronized String calculateOrderTotalOfItemsAdded(String[][] products, double shipping) {
        BigDecimal total = BigDecimal.ZERO;
        for (String[] product : products) {
            BigDecimal productPrice = parsePrice(product[1]);
            total = total.add(productPrice);
        }
        total = total.add(BigDecimal.valueOf(shipping));
        total = total.setScale(2, RoundingMode.HALF_UP);
        return CURRENCY_FORMAT.format(total);
    }
}
