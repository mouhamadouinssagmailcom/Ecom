package com.myvision.Super.Services;

import com.myvision.Super.Entity.Order;
import com.myvision.Super.Entity.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public interface ShoppingCartService {
    void addProduct(Product product);
    void removeProduct(Product product);
    void clearProducts();
    Map<Product, Integer> productsInCart();
    BigDecimal totalPrice();
    void cartCheckout();
    void DeleteOneProductToCart(Product product);
}
