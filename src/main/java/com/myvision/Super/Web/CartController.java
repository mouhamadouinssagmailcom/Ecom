package com.myvision.Super.Web;

import com.myvision.Super.Entity.Order;
import com.myvision.Super.Entity.Product;
import com.myvision.Super.Entity.User;
import com.myvision.Super.Repository.OrderRepository;
import com.myvision.Super.Repository.ProductRepository;
import com.myvision.Super.Repository.UserRepository;
import com.myvision.Super.Services.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

@Controller
public class CartController {
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    private ShoppingCartService shoppingCartService;
    private ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    public CartController(ShoppingCartService shoppingCartService, ProductRepository productRepository) {
        this.shoppingCartService = shoppingCartService;
        this.productRepository = productRepository;
    }

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("products", shoppingCartService.productsInCart());
        model.addAttribute("totalPrice", shoppingCartService.totalPrice());

        return "cart";
    }

    @GetMapping("/cart/add/{id}")
    public String addProductToCart(@PathVariable("id") long id) {
        Product product = productRepository.findById(id).get();
        if (product != null) {
            shoppingCartService.addProduct(product);
            logger.debug(String.format("Product with id: %s added to shopping cart.", id));
        }
        return "redirect:/products";
    }

    @GetMapping("/cart/nouveauadd/{id}")
    public String addnouveauProductToCart(@PathVariable("id") long id) {
        Product product = productRepository.findById(id).get();
        if (product != null) {
            shoppingCartService.addProduct(product);
            logger.debug(String.format("Product with id: %s added to shopping cart.", id));
        }
        return "redirect:/cart";
    }

    @GetMapping("/check")
    public String formOrder(Order order) {
        return "check";
    }


    @GetMapping("/formckeck")
    public String formorder(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("products", shoppingCartService.productsInCart());
        model.addAttribute("totalPrice", shoppingCartService.totalPrice());
        List<User> user = userRepository.findAll();
        model.addAttribute("listUser", user);

        return "check";
    }

    @PostMapping("/savecheck")
    public String save(Model model, @Validated Order order, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "check";
        orderRepository.save(order);
        model.addAttribute("order", order);
        model.addAttribute("standardDate", new Date());


        model.addAttribute("products", shoppingCartService.productsInCart());
        model.addAttribute("totalPrice", shoppingCartService.totalPrice());
        List<User> user = userRepository.findAll();
        model.addAttribute("listUser", user);

        return "invoice";
    }

    @GetMapping("/mycom")
    public String getOneCommande(Model model) {
        List<Order> order = orderRepository.findAll();
        model.addAttribute("order", order);
        model.addAttribute("standardDate", new Date());


        model.addAttribute("products", shoppingCartService.productsInCart());
        model.addAttribute("totalPrice", shoppingCartService.totalPrice());
        List<User> user = userRepository.findAll();
        model.addAttribute("listUser", user);

        return "mycom";
    }


    @GetMapping("/cart/remove/{id}")
    public String removeProductFromCart(@PathVariable("id") long id) {
        Product product = productRepository.findById(id).get();
        if (product != null) {
            shoppingCartService.removeProduct(product);
            logger.debug(String.format("Product with id: %s removed from shopping cart.", id));
        }
        return "redirect:/cart";
    }

    @GetMapping("/deletecart/remove/{id}")
    public String deletefromcart(@PathVariable("id") long id) {
        Product product = productRepository.findById(id).get();
        if (product != null) {
            shoppingCartService.DeleteOneProductToCart(product);
            logger.debug(String.format("Product with id: %s removed from shopping cart.", id));

        }
        return "redirect:/cart";

    }

    @GetMapping("/suppcart")
    public String Supprimer(Long id, Model model) {
        productRepository.deleteById(id);
        return "redirect:/cart";
    }

    @GetMapping("/cart/clear")
    public String clearProductsInCart() {
        shoppingCartService.clearProducts();

        return "redirect:/cart";
    }

    @GetMapping("/cart/checkout")
    public String cartCheckout() {
        shoppingCartService.cartCheckout();

        return "redirect:/cart";
    }
}


