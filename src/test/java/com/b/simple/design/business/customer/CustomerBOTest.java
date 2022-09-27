package com.b.simple.design.business.customer;

import com.b.simple.design.business.exception.DifferentCurrenciesException;
import com.b.simple.design.model.customer.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerBOTest {

    public static final int DEFAULT_PRODUCT_ID = 100;
    public static final String DEFAULT_PRODUCT_NAME = "Product 15";

    private final CustomerBO customerBO = new CustomerBOImpl();

    @Test
    void testCustomerProductSum_TwoProductsSameCurrencies()
            throws DifferentCurrenciesException {

        List<Amount> amounts = List.of(new AmountImpl(new BigDecimal("5.0"), Currency.EURO),
                new AmountImpl(new BigDecimal("6.0"), Currency.EURO));
        List<Product> products = createProducts(amounts);


        Amount expected = new AmountImpl(new BigDecimal("11.0"), Currency.EURO);
        Amount result = customerBO.getCustomerProductsSum(products);

        assertAmount(expected, result);
    }

    @Test
    void testCustomerProductSum_twoProductsWithDifferentCurrency() {

        List<Amount> amounts = List.of(new AmountImpl(new BigDecimal("5.0"), Currency.INDIAN_RUPEE),
                new AmountImpl(new BigDecimal("6.0"), Currency.EURO));

        List<Product> products = createProducts(amounts);

        assertThrows(DifferentCurrenciesException.class, () -> customerBO.getCustomerProductsSum(products));
    }

    @Test
    void testCustomerProductSum_emptyProductList() throws DifferentCurrenciesException {
        List<Product> products = new ArrayList<>();

        Amount expected = new AmountImpl(BigDecimal.ZERO, Currency.EURO);
        Amount result = customerBO.getCustomerProductsSum(products);

        assertAmount(expected, result);
    }

    private List<Product> createProducts(List<Amount> amounts) {
        List<Product> products = new ArrayList<>();
        amounts.forEach(amount -> products.add(new ProductImpl(DEFAULT_PRODUCT_ID, DEFAULT_PRODUCT_NAME,
                ProductType.BANK_GUARANTEE,
                amount)));
        return products;
    }

    private void assertAmount(Amount expected, Amount result) {
        assertEquals(expected.getCurrency(), result.getCurrency());
        assertEquals(expected.getValue(), result.getValue());
    }
}