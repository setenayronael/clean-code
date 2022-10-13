package com.b.simple.design.business.customer;

import com.b.simple.design.business.exception.DifferentCurrenciesException;
import com.b.simple.design.model.customer.Amount;
import com.b.simple.design.model.customer.AmountImpl;
import com.b.simple.design.model.customer.Currency;
import com.b.simple.design.model.customer.Product;

import java.math.BigDecimal;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    @Override
    public Amount getCustomerProductsSum(List<Product> products)
            throws DifferentCurrenciesException {

        if (products.isEmpty())
            return new AmountImpl(BigDecimal.ZERO, Currency.EURO);

        Currency firstProductCurrency = products.get(0).getAmount()
                .getCurrency();

        boolean currencySameAsFirstProduct = doAllProductsHaveSameCurrency(products, firstProductCurrency);
        if (!currencySameAsFirstProduct) {
            throw new DifferentCurrenciesException();
        }

        BigDecimal totalAmount = calculateSumOfProducts(products);

        return new AmountImpl(totalAmount, firstProductCurrency);
    }

    private boolean doAllProductsHaveSameCurrency(List<Product> products, Currency firstProductCurrency) {
        return products.stream()
                .allMatch(product -> product.getAmount().getCurrency().equals(firstProductCurrency));
    }

    private BigDecimal calculateSumOfProducts(List<Product> products) {
        return products.stream()
                .map(product -> product.getAmount().getValue()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}