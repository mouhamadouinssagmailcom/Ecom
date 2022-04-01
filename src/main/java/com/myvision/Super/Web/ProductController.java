package com.myvision.Super.Web;

import com.myvision.Super.Entity.Product;
import com.myvision.Super.Entity.User;
import com.myvision.Super.Repository.ProductRepository;
import com.myvision.Super.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.net.URLConnection;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;


    @GetMapping("/")
    public String Accueil(Model model,
                          @RequestParam(name = "page", defaultValue = "0") int page,
                          @RequestParam(name = "size", defaultValue = "12") int size,
                          @RequestParam(name = "keyword", defaultValue = "") String mc
    ) {
        Page<Product> productPage = productRepository.findBycategoryContains(mc, PageRequest.of(page, size));
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("pages", new int[productPage.getTotalPages()]);
        model.addAttribute("size", size);
        model.addAttribute("keyword", mc);
        model.addAttribute("curentpage", page);

        return "index";
    }

    @GetMapping("/products")
    public String getAllProducts(Model model,
                                 @RequestParam(name = "page", defaultValue = "0") int page,
                                 @RequestParam(name = "size", defaultValue = "12") int size,
                                 @RequestParam(name = "keyword", defaultValue = "") String mc
    ) {
        Page<Product> productPage = productRepository.findBycategoryContains(mc, PageRequest.of(page, size));
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("pages", new int[productPage.getTotalPages()]);
        model.addAttribute("size", size);
        model.addAttribute("keyword", mc);
        model.addAttribute("curentpage", page);

        return "product";
    }
    @GetMapping("/myproducts")
    public String FinAllProducts(Model model,
                                 @RequestParam(name = "page", defaultValue = "0") int page,
                                 @RequestParam(name = "size", defaultValue = "12") int size,
                                 @RequestParam(name = "keyword", defaultValue = "") String mc
    ) {
        Page<Product> productPage = productRepository.findBycategoryContains(mc, PageRequest.of(page, size));
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("pages", new int[productPage.getTotalPages()]);
        model.addAttribute("size", size);
        model.addAttribute("keyword", mc);
        model.addAttribute("curentpage", page);

        return "nouveauproduct";
    }


    @GetMapping("/adminisproducts")
    public String getAllProductsBydesign(Model model,
                                         @RequestParam(name = "page", defaultValue = "0") int page,
                                         @RequestParam(name = "size", defaultValue = "5") int size,
                                         @RequestParam(name = "keyword", defaultValue = "") String mc
    ) {
        Page<Product> productPage = productRepository.findByDesignContains(mc, PageRequest.of(page, size));
        List<User> user = userRepository.findAll();
        model.addAttribute("listUser", user);
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("pages", new int[productPage.getTotalPages()]);
        model.addAttribute("size", size);
        model.addAttribute("keyword", mc);
        model.addAttribute("curentpage", page);

        return "pit";
    }

    @GetMapping("/produitAdd")
    public String showproduitAdd(Product product) {
        return "ajoutproduct";
    }

    @PostMapping("/saveproduct")
    public String saveOneproduct(@RequestParam("myfile") MultipartFile myfile,
                                 @Valid Product product, BindingResult result, Model model) throws IOException {

        product.setName(myfile.getOriginalFilename());
        product.setImage(myfile.getBytes());
        if (result.hasErrors()) {
            return "ajoutproduct";
        }
        productRepository.save(product);
        return "redirect:/products";
    }


    @PostMapping("/updateproduct/{id}")
    public String UpdateOneProduct(@RequestParam("myfile") MultipartFile myfile,
                                   @PathVariable("id") long id, @Valid Product product,
                                   BindingResult result, Model model) throws IOException {
        product.setName(myfile.getOriginalFilename());
        product.setImage(myfile.getBytes());
        if (result.hasErrors()) {
            product.getName();
            product.setId(id);
            return "productUpdate";
        }

        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/suppression")
    public String delete(Model model, Long id, String keyword, int page, int size) {
        productRepository.deleteById(id);
        return "redirect:/adminisproducts?page =" + page + "&size=" + size + "&keyword=" + keyword;
    }

    @GetMapping("/Edition")
    public String edit(Model model, Long id) {
        Product product = productRepository.findById(id).get();

        model.addAttribute("product", product);
        return "Edition";
    }

    @GetMapping("/details")
    public String showDetails(Model model, Long id) {
        Product product = productRepository.findById(id).get();
        model.addAttribute("product", product);
        return "mydetails";
    }

    @GetMapping("/delete")
    public String deleteProduit(@PathVariable("id") long id, String keyword, int page, int size, Model model) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid produit Id:" + id));
        productRepository.delete(product);
        return "redirect:/adminisproducts?page =" + page + "&size=" + size + "&keyword=" + keyword;
    }


    @GetMapping("/images/{nameImage}")
    public void getImage(@PathVariable("nameImage") String nameImage, HttpServletResponse response) throws Exception {

        Optional<Product> img = productRepository.findByName(nameImage);
        if (img.isPresent()) {

            //System.out.println("returning file:"+nameImage);
            byte[] bytes = img.get().getImage();
            InputStream is = new BufferedInputStream(new ByteArrayInputStream(bytes));
            String mimeType = URLConnection.guessContentTypeFromStream(is);
            response.setContentType(mimeType);

            OutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
        }
    }

    /////////
}
