package me.patrick.products.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.patrick.products.model.ProductModel;
import me.patrick.products.model.ResponseModel;
import me.patrick.products.service.ProductService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductsController {

    private final ProductService productService;

    @GetMapping()
    public String route() {
        return "API de produtos funcionando.";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody ProductModel productModel) {
        return productService.registerOrEdit(productModel, "register");
    }

    @PutMapping("/change")
    public ResponseEntity<?> change(@RequestBody ProductModel productModel) {
        return productService.registerOrEdit(productModel, "change");
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<ResponseModel> delete(@PathVariable Long id) {
        return productService.remove(id);
    }

    @GetMapping("/products")
    public Iterable<ProductModel> listAll() {
        return productService.listModels();
    }

}
