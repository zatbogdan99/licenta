package com.licenta.service;

import com.licenta.dto.*;
import com.licenta.model.*;
import com.licenta.repository.*;
import com.licenta.utils.Utils;
import org.aspectj.util.FileUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.SequenceGenerator;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    LaptopRepository laptopRepository;

    @Autowired
    DisplayRepository displayRepository;

    @Autowired
    RamRepository ramRepository;

    @Autowired
    ProcessorRepository processorRepository;

    @Autowired
    GraphicsCardRepository graphicsCardRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PhotosRepository photosRepository;

    @Autowired
    StorageRepository storageRepository;

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
        laptop.setWarranty(saveLaptopModel.getWarranty());
        laptop.setProcessor(processorRepository.getById(saveLaptopModel.getProcessor()));
        laptop.setRam(ramRepository.getById(saveLaptopModel.getRam()));
        laptop.setStorage(storageRepository.getById(saveLaptopModel.getStorage()));
        laptop.setGraphicsCard(graphicsCardRepository.getById(saveLaptopModel.getGraphicsCard()));
        String partSeparator = ",";
        byte[] decodedBytes = new byte[0];
        if (saveLaptopModel.getPhotos()[0].contains(partSeparator)) {
            String encodedImg = saveLaptopModel.getPhotos()[0].split(partSeparator)[1];
            decodedBytes = Base64.getDecoder().decode(encodedImg);
        } else {
            decodedBytes = Base64.getDecoder().decode(saveLaptopModel.getPhotos()[0]);
        }
        Blob blob = new SerialBlob(decodedBytes);
        laptop.setPhoto(blob);

        Laptop newLaptop = laptopRepository.save(laptop);

        savePhotos(saveLaptopModel.getPhotos(), newLaptop.getId(), partSeparator);
    }

    void savePhotos(String[] photosArray, Long laptopId, String partSeparator) throws SQLException {
        Blob blob;
        byte[] decodedBytes;
        for (String photo : photosArray) {
            Photos photos = new Photos();
            photos.setProductId(laptopId);
            photos.setProductType(Utils.ProductTypes.LAPTOP.getValue());

            if (photo.contains(partSeparator)) {
                String encodedImg = photo.split(partSeparator)[1];
                decodedBytes = Base64.getDecoder().decode(encodedImg);
            } else {
                decodedBytes = Base64.getDecoder().decode(photo);
            }
            blob = new SerialBlob(decodedBytes);
            photos.setPhoto(blob);
            photosRepository.save(photos);
        }
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

    public void saveStorage(SaveStorageDTO saveStorageDTO) {
        Storage storage = modelMapper.map(saveStorageDTO, Storage.class);
        storageRepository.save(storage);
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

    public void saveRam(SaveRamDTO saveRamDTO) {
        Ram ram = modelMapper.map(saveRamDTO, Ram.class);
        ramRepository.save(ram);
    }

    public List<ProductDTO> updateProducts(FilterDTO filterDTO) {
        List<Laptop> laptops = laptopRepository.getAllLaptops();
        List<ProductDTO> products = new ArrayList<>();

        laptops.stream().filter(laptop -> applyFilters(filterDTO, laptop)).forEach(laptop -> {
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

    private boolean applyFilters(FilterDTO filterDTO, Laptop laptop) {
        if (laptop.getPrice() > filterDTO.getMaxRange() || laptop.getPrice() < filterDTO.getMinRange()) {
            return false;
        }
        if (filterDTO.getRam() != null && filterDTO.getRam().length > 0) {
            if (Arrays.stream(filterDTO.getRam()).noneMatch(ram -> ram.equals(laptop.getRam().getType()))) {
                return false;
            }
        }
        if (filterDTO.getMemory() != null && filterDTO.getMemory().length > 0) {
            if (Arrays.stream(filterDTO.getMemory()).noneMatch(memory -> memory.equals(laptop.getStorage()))) {
                return false;
            }
        }
        if (filterDTO.getProcessor() != null && filterDTO.getProcessor().length > 0) {
            if (Arrays.stream(filterDTO.getProcessor()).noneMatch(processor -> processor.equals(laptop.getProcessor().getProducer()))) {
                return false;
            }
        }

        if (filterDTO.getMemoryCapacity() != null && filterDTO.getMemoryCapacity().length > 0) {
            return Arrays.stream(filterDTO.getMemoryCapacity()).anyMatch(memoryCapacity -> memoryCapacity.equals(laptop.getStorage().getCapacity().toString()));
        }
        return true;
    }

    public List<ProductDTO> getGraphicCards() {
        List<GraphicsCard> graphicsCards = graphicsCardRepository.findAll();
        List<ProductDTO> products = new ArrayList<>();

        graphicsCards.forEach(graphicsCard -> {
            GraphicsCardDTO graphicsCardDTO = modelMapper.map(graphicsCard, GraphicsCardDTO.class);

            products.add(GraphicsCardDTO.toProduct(graphicsCardDTO));
        });

        return products;
    }

    public List<ProductDTO> getStorage() {
        List<Storage> storages = storageRepository.findAll();
        List<ProductDTO> products = new ArrayList<>();

        storages.forEach(storage -> {
            StorageDTO storageDTO = modelMapper.map(storage, StorageDTO.class);

            products.add(StorageDTO.toProduct(storageDTO));
        });

        return products;
    }

    public List<ProductDTO> getRam() {
        List<Ram> rams = ramRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();

        rams.forEach(ram -> {
            RamDTO ramDTO = modelMapper.map(ram, RamDTO.class);

            productDTOS.add(RamDTO.toProduct(ramDTO));
        });

        return productDTOS;
    }

    public PhotosDto getPhotos(Long id) {
        List<Photos> photos = photosRepository.findAll();
        PhotosDto photosDto = new PhotosDto();
        List<String> photosList = new ArrayList<>();
        photos.forEach(photo -> {
            if (photo.getPhoto() != null) {
                byte[] imageByte = new byte[0];
                try {
                    imageByte = photo.getPhoto().getBinaryStream().readAllBytes();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                photosList.add(Base64.getEncoder().encodeToString(imageByte));
            }
        });

        photosDto.setPhotos(photosList);
        return photosDto;
    }
}
