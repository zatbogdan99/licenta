package com.licenta.controller;

import com.licenta.dto.ProductDTO;
import com.licenta.model.Laptop;
import com.licenta.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping()
    @CrossOrigin(origins = "http://localhost:4200")
    public List<ProductDTO> loadProducts() {
        return productService.getAllProducts();
    }
}
