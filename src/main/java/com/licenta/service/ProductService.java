package com.licenta.service;

import com.licenta.dto.LaptopDTO;
import com.licenta.dto.ProductDTO;
import com.licenta.dto.SaveGraphicsCardDTO;
import com.licenta.dto.SaveLaptopModel;
import com.licenta.model.GraphicsCard;
import com.licenta.model.Laptop;
import com.licenta.model.Processor;
import com.licenta.repository.DisplayRepository;
import com.licenta.repository.GraphicsCardRepository;
import com.licenta.repository.LaptopRepository;
import com.licenta.repository.ProcessorRepository;
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
    DisplayRepository displayRepository;

    @Autowired
    ProcessorRepository processorRepository;

    @Autowired
    GraphicsCardRepository graphicsCardRepository;

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

    public void saveLaptop(SaveLaptopModel saveLaptopModel) {
        Laptop laptop = new Laptop();
        laptop.setName(saveLaptopModel.getName());
        laptop.setPrice(saveLaptopModel.getPrice());
        laptop.setDisplay(displayRepository.getById(saveLaptopModel.getDisplay()));
        laptop.setGuarantee(saveLaptopModel.getWaranty());
        laptop.setProcessor(processorRepository.getById(saveLaptopModel.getProcessor()));
        laptop.setRamFrequency(saveLaptopModel.getRamFrequency());
        laptop.setRamSlots(saveLaptopModel.getRamSlots());
        laptop.setRamFrequency(saveLaptopModel.getRamFrequency());
        laptop.setRamSlots(saveLaptopModel.getRamSlots());
        laptop.setRamTotal(saveLaptopModel.getRamTotal());
        laptop.setRamType(saveLaptopModel.getRamType());
        laptop.setStorage(saveLaptopModel.getStorage());
        laptop.setStorageCapacity(saveLaptopModel.getStorageCapacity());
        laptop.setStorageFormFactor(saveLaptopModel.getStorageFormFactor());
        laptop.setStorageInterface(laptop.getStorageInterface());
        laptop.setGraphicsCard(graphicsCardRepository.getById(saveLaptopModel.getGraphicsCard()));
        laptop.setStorageInterface(saveLaptopModel.getStorageInterface());

        laptopRepository.save(laptop);
    }

    public void saveGraphicsCard(SaveGraphicsCardDTO graphicsCardDTO) {
        GraphicsCard graphicsCard = new GraphicsCard();
        graphicsCard.setChipset(graphicsCardDTO.getChipset());
        graphicsCard.setCapacity(graphicsCardDTO.getCapacity());
        graphicsCard.setModel(graphicsCardDTO.getModel());
        graphicsCard.setTechnology(graphicsCardDTO.getTechnology());
        graphicsCard.setType(graphicsCardDTO.getType());
        graphicsCard.setName(graphicsCardDTO.getName());

        graphicsCardRepository.save(graphicsCard);
    }
}
