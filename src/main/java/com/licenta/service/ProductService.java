package com.licenta.service;

import com.licenta.dto.*;
import com.licenta.model.Display;
import com.licenta.model.GraphicsCard;
import com.licenta.model.Laptop;
import com.licenta.model.Processor;
import com.licenta.repository.DisplayRepository;
import com.licenta.repository.GraphicsCardRepository;
import com.licenta.repository.LaptopRepository;
import com.licenta.repository.ProcessorRepository;
import org.aspectj.util.FileUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
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
            try {
                if (laptop.getPhoto() != null) {
                    byte[] imageByte = laptop.getPhoto().getBinaryStream().readAllBytes();
                    laptopDTO.setPhoto(Base64.getEncoder().encodeToString(imageByte));
                }
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
            System.out.println(laptopDTO);
            products.add(LaptopDTO.toProduct(laptopDTO));
        });
        return products;
    }

    public List<ProductDTO> getDisplays() {
        List<Display> displays = displayRepository.findAll();
        List<ProductDTO> products = new ArrayList<>();

        displays.forEach(display -> {
            DisplayDTO displayDTO = modelMapper.map(display, DisplayDTO.class);

            products.add(DisplayDTO.toProduct(displayDTO));
        });
        return products;
    }

    public List<ProductDTO> getProcessors() {
        List<Processor> processors = processorRepository.findAll();
        List<ProductDTO> products = new ArrayList<>();

        processors.forEach(processor -> {
            ProcessorDTO processorDTO = modelMapper.map(processor, ProcessorDTO.class);

            products.add(ProcessorDTO.toProduct(processorDTO));
        });

        return products;
    }

    public void saveLaptop(SaveLaptopModel saveLaptopModel) throws SQLException {
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
        String partSeparator = ",";
        byte[] decodedBytes;
        if (saveLaptopModel.getPhotos()[0].contains(partSeparator)) {
            String encodedImg = saveLaptopModel.getPhotos()[0].split(partSeparator)[1];
            decodedBytes = Base64.getDecoder().decode(encodedImg);
        } else {
            decodedBytes = Base64.getDecoder().decode(saveLaptopModel.getPhotos()[0]);
        }
        Blob blob = new SerialBlob(decodedBytes);
        laptop.setPhoto(blob);

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

    public void saveProcessor(SaveProcessorDTO processorDTO) {
        Processor processor = modelMapper.map(processorDTO, Processor.class);
        processorRepository.save(processor);
    }

    public LaptopDTO getLaptop(Long id) {
        Laptop laptop = laptopRepository.getById(id);
        LaptopDTO laptopDTO = modelMapper.map(laptop, LaptopDTO.class);
        return laptopDTO;
    }

    public void saveDisplay(SaveDisplayDTO displayDTO) {
        Display display = modelMapper.map(displayDTO, Display.class);
        displayRepository.save(display);
    }
}
