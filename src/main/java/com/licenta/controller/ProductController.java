package com.licenta.controller;

import com.licenta.dto.*;
import com.licenta.model.Laptop;
import com.licenta.repository.LaptopRepository;
import com.licenta.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping()
    public List<ProductDTO> loadProducts() {
        return productService.getAllProducts();
    }
    @GetMapping("/get-displays")
    public List<ProductDTO> getDisplays() {
        return productService.getDisplays();
    }

    @GetMapping("/get-processors")
    public List<ProductDTO> getProcessors() {
        return productService.getProcessors();
    }

    @PostMapping("/get-laptop")
    public LaptopDTO getLaptop(@RequestBody Long id) {
        return productService.getLaptop(id);
    }

    @PostMapping("/save-laptop")
    void saveLaptop(@RequestBody SaveLaptopModel laptop) throws SQLException {
        productService.saveLaptop(laptop);
    }

    @PostMapping("/save-graphics-card")
    void saveGraphicsCard(@RequestBody SaveGraphicsCardDTO graphicsCardDTO) {
        productService.saveGraphicsCard(graphicsCardDTO);
    }

    @PostMapping("/save-processor")
    void saveProcessor(@RequestBody SaveProcessorDTO processorDTO) {
        productService.saveProcessor(processorDTO);
    }

    @PostMapping("/save-display")
    void saveDisplay(@RequestBody SaveDisplayDTO displayDTO) {
        productService.saveDisplay(displayDTO);
    }
}
