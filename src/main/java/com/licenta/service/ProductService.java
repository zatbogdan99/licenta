package com.licenta.service;

import com.licenta.dto.LaptopDTO;
import com.licenta.dto.ProductDTO;
import com.licenta.model.Laptop;
import com.licenta.repository.LaptopRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    LaptopRepository laptopRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<ProductDTO> getAllProducts() {
        List<Laptop> laptops = laptopRepository.getAllLaptops();
        List<ProductDTO> products = new ArrayList<>();

        laptops.forEach(laptop -> {
            LaptopDTO laptopDTO = modelMapper.map(laptop, LaptopDTO.class);
            System.out.println(laptopDTO);
            products.add(LaptopDTO.toProduct(laptopDTO));
        });
        return products;
    }
}
