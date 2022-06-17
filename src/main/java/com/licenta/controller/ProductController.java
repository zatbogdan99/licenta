package com.licenta.controller;

import com.licenta.dto.*;
import com.licenta.model.ConfiguratorDTO;
import com.licenta.model.Desktop;
import com.licenta.model.Laptop;
import com.licenta.repository.LaptopRepository;
import com.licenta.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("load-products")
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

    @PostMapping("/get-compatible-processors")
    public List<ProductDTO> getCompatibleProcessors(@RequestBody ConfiguratorDTO configuratorDTO) {
        return productService.getCompatibleProcessors(configuratorDTO);
    }

    @PostMapping("/get-compatible-graphics-cards")
    public List<ProductDTO> getCompatibleGraphicsCards(@RequestBody ConfiguratorDTO configuratorDTO) {
        return productService.getCompatibleGraphicsCards(configuratorDTO);
    }

    @PostMapping("/get-compatible-rams")
    public List<ProductDTO> getCompatibleRams(@RequestBody ConfiguratorDTO configuratorDTO) {
        return productService.getCompatibleRams(configuratorDTO);
    }

    @PostMapping("/get-compatible-storages")
    public List<ProductDTO> getCompatibleStorages(@RequestBody ConfiguratorDTO configuratorDTO) {
        return productService.getCompatibleStorages(configuratorDTO);
    }

    @PostMapping("/get-compatible-motherboards")
    public List<ProductDTO> getCompatibleMotherboards(@RequestBody ConfiguratorDTO configuratorDTO) {
        return productService.getCompatibleMotherboards(configuratorDTO);
    }

    @GetMapping("/get-graphic-cards")
    public List<ProductDTO> getGraphicCards() {
        return productService.getGraphicCards();
    }

    @GetMapping("/get-storage")
    public List<ProductDTO> getStorage() {
        return productService.getStorage();
    }

    @GetMapping("/get-ram")
    public List<ProductDTO> getRam() {
        return productService.getRam();
    }

    @GetMapping("/get-desktops")
    public List<ProductDTO> getDesktops(@RequestBody Long id) {
        return productService.getDesktops(id);
    }

    @PostMapping("/get-laptop")
    public LaptopDTO getLaptop(@RequestBody Long id) {
        return productService.getLaptop(id);
    }

    @PostMapping("/get-graphics-card")
    public GraphicsCardDTO getGraphicsCard(@RequestBody Long id) {
        return productService.getGraphicsCard(id);
    }

    @PostMapping("/get-processor")
    public ResponseEntity<ProcessorDTO> getProcessor(@RequestBody Long id) {
        return ResponseEntity.ok(productService.getProcessor(id));
    }

    @PostMapping("/get-storage-by-id")
    public StorageDTO getStorageById(@RequestBody Long id) {
        return productService.getStorageById(id);
    }

    @PostMapping("/get-ram-by-id")
    public RamDTO getRamById(@RequestBody Long id) {
        return productService.getRamById(id);
    }

    @PostMapping("/get-motherboard-by-id")
    public MotherboardDTO getMotherboardById(@RequestBody Long id) {
        return productService.getMotherboardById(id);
    }

    @PostMapping("/get-desktop")
    public DesktopDTO getDesktop(@RequestBody Long id) {
        return productService.getDesktop(id);
    }

    @PostMapping("/get-photos")
    public PhotosDto getPhotos(@RequestBody PhotosModelDto model) {
        return productService.getPhotos(model);
    }

    @PostMapping("/save-laptop")
    void saveLaptop(@RequestBody SaveLaptopModel laptop) throws SQLException {
        productService.saveLaptop(laptop);
    }

    @PostMapping("/save-motherboard")
    void saveMotherboard(@RequestBody SaveMotherboardDTO motherboardDTO) throws SQLException {
        productService.saveMotherboard(motherboardDTO);
    }

    @PostMapping("/save-graphics-card")
    void saveGraphicsCard(@RequestBody SaveGraphicsCardDTO graphicsCardDTO) throws SQLException {
        productService.saveGraphicsCard(graphicsCardDTO);
    }

    @PostMapping("/save-processor")
    void saveProcessor(@RequestBody SaveProcessorDTO processorDTO) throws SQLException {
        productService.saveProcessor(processorDTO);
    }

    @PostMapping("/save-display")
    void saveDisplay(@RequestBody SaveDisplayDTO displayDTO) {
        productService.saveDisplay(displayDTO);
    }

    @PostMapping("/save-desktop")
    void saveDesktop(@RequestBody SaveDesktopDTO saveDesktopDTO) throws SQLException {
        productService.saveDesktop(saveDesktopDTO);
    }

    @PostMapping("update-product-stock")
    void updateProductStock(@RequestBody UpdateProductStock updateProductStock) {
        productService.updateStock(updateProductStock);
    }

    @PostMapping("/save-storage")
    void saveStorage(@RequestBody SaveStorageDTO saveStorageDTO) throws SQLException {
        productService.saveStorage(saveStorageDTO);
    }

    @PostMapping("/save-ram")
    void saveRam(@RequestBody SaveRamDTO saveRamDTO) throws SQLException {
        productService.saveRam(saveRamDTO);
    }

    @PostMapping("/update-products")
    public List<ProductDTO> updateProducts(@RequestBody FilterDTO filterDTO) {
        return productService.updateProducts(filterDTO);
    }
}
