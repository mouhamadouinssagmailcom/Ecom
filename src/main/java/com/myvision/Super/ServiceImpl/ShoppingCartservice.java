package com.myvision.Super.ServiceImpl;

import com.myvision.Super.Entity.Order;
import com.myvision.Super.Entity.Product;
import com.myvision.Super.Entity.User;
import com.myvision.Super.Repository.OrderRepository;
import com.myvision.Super.Repository.ProductRepository;
import com.myvision.Super.Repository.UserRepository;
import com.myvision.Super.Services.ShoppingCartService;
import com.myvision.Super.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ShoppingCartservice implements ShoppingCartService {
    private Map<Product, Integer> cart = new LinkedHashMap<>();
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ShoppingCartService shoppingCartService;
    @Autowired
    ProductRepository productRepository;

    @Override
    public void addProduct(Product product) {
        if (cart.containsKey(product)) {

            cart.replace(product, cart.get(product) + 1);
        } else {
            cart.put(product, 1);
        }

    }

    @Override
    public void removeProduct(Product product) {
        if (cart.containsKey(product)) {
            if (cart.get(product) > 1)
                cart.replace(product, cart.get(product) - 1);
            else if (cart.get(product) == 1) {
                cart.put(product, 1);
            }
        }

    }

    @Override
    public void clearProducts() {
        cart.clear();

    }

    @Override
    public Map<Product, Integer> productsInCart() {
        return Collections.unmodifiableMap(cart);
    }

    @Override
    public BigDecimal totalPrice() {
        return cart.entrySet().stream()
                .map(k -> k.getKey().getPrix().multiply(BigDecimal.valueOf(k.getValue()))).sorted()
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

    }

    @Override
    public void cartCheckout() {
        cart.clear();

    }

    @Override
    public void DeleteOneProductToCart(Product product) {
        if (cart.containsKey(product)) {
            cart.remove(product);
        }
    }



}
