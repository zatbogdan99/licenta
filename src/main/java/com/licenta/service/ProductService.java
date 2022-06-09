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
import java.util.*;

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
    MotherboardRepository motherboardRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PhotosRepository photosRepository;

    @Autowired
    StorageRepository storageRepository;

    public List<ProductDTO> getAllProducts() {
        List<Laptop> laptops = laptopRepository.getAllLaptops();
        List<GraphicsCard> graphicsCards = graphicsCardRepository.findAll();
        List<Processor> processors = processorRepository.findAll();
        List<Ram> rams = ramRepository.findAll();
        List<Storage> storages = storageRepository.findAll();
        List<Motherboard> motherboards = motherboardRepository.findAll();
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

        graphicsCards.stream().filter(graphicsCard -> graphicsCard.getForLaptop() == 0).forEach(graphicsCard -> {
            GraphicsCardDTO graphicsCardDTO = modelMapper.map(graphicsCard, GraphicsCardDTO.class);
            if (graphicsCard.getPhoto() != null) {
                byte[] imageByte = new byte[0];
                try {
                    imageByte = graphicsCard.getPhoto().getBinaryStream().readAllBytes();
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
                graphicsCardDTO.setPhoto(Base64.getEncoder().encodeToString(imageByte));
            }
            products.add(GraphicsCardDTO.toProduct(graphicsCardDTO));
        });

        processors.stream().filter(processor -> processor.getForLaptop() == 0).forEach(processor -> {
            ProcessorDTO processorDTO = modelMapper.map(processor, ProcessorDTO.class);
            if (processorDTO.getPhoto() != null) {
                byte[] imageByte = new byte[0];
                try {
                    imageByte = processor.getPhoto().getBinaryStream().readAllBytes();
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
                processorDTO.setPhoto(Base64.getEncoder().encodeToString(imageByte));
            }

            products.add(ProcessorDTO.toProduct(processorDTO));
        });

        rams.forEach(ram -> {
            RamDTO ramDTO = modelMapper.map(ram, RamDTO.class);
            if (ram.getPhoto() != null) {
                byte[] imageByte = new byte[0];
                try {
                    imageByte = ram.getPhoto().getBinaryStream().readAllBytes();
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
                ramDTO.setPhoto(Base64.getEncoder().encodeToString(imageByte));
            }
            products.add(RamDTO.toProduct(ramDTO));
        });

        storages.forEach(storage -> {
            StorageDTO storageDTO = modelMapper.map(storage, StorageDTO.class);
            if (storage.getPhoto() != null) {
                byte[] imageByte = new byte[0];
                try {
                    imageByte = storage.getPhoto().getBinaryStream().readAllBytes();
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
                storageDTO.setPhoto(Base64.getEncoder().encodeToString(imageByte));
            }
            products.add(StorageDTO.toProduct(storageDTO));
        });

        motherboards.forEach(motherboard -> {
            MotherboardDTO motherboardDTO = modelMapper.map(motherboard, MotherboardDTO.class);
            if (motherboard.getPhoto() != null) {
                byte[] imageByte = new byte[0];
                try {
                    imageByte = motherboard.getPhoto().getBinaryStream().readAllBytes();
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
                motherboardDTO.setPhoto(Base64.getEncoder().encodeToString(imageByte));
            }
            products.add(MotherboardDTO.toProduct(motherboardDTO));
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

    public void saveStorage(SaveStorageDTO saveStorageDTO) throws SQLException {
        Storage storage = modelMapper.map(saveStorageDTO, Storage.class);
        String partSeparator = ",";
        byte[] decodedBytes = new byte[0];
        if (saveStorageDTO.getPhotos()[0].contains(partSeparator)) {
            String encodedImg = saveStorageDTO.getPhotos()[0].split(partSeparator)[1];
            decodedBytes = Base64.getDecoder().decode(encodedImg);
        } else {
            decodedBytes = Base64.getDecoder().decode(saveStorageDTO.getPhotos()[0]);
        }
        Blob blob = new SerialBlob(decodedBytes);
        storage.setPhoto(blob);

        Storage storage1 = storageRepository.save(storage);
        savePhotos(saveStorageDTO.getPhotos(), storage1.getId(), partSeparator, Utils.ProductTypes.STORAGE.getValue());
    }

    public void saveRam(SaveRamDTO saveRamDTO) throws SQLException {
        Ram ram = modelMapper.map(saveRamDTO, Ram.class);
        String partSeparator = ",";
        byte[] decodedBytes = new byte[0];
        if (saveRamDTO.getPhotos()[0].contains(partSeparator)) {
            String encodedImg = saveRamDTO.getPhotos()[0].split(partSeparator)[1];
            decodedBytes = Base64.getDecoder().decode(encodedImg);
        } else {
            decodedBytes = Base64.getDecoder().decode(saveRamDTO.getPhotos()[0]);
        }
        Blob blob = new SerialBlob(decodedBytes);
        ram.setPhoto(blob);

        Ram newRam = ramRepository.save(ram);
        savePhotos(saveRamDTO.getPhotos(), newRam.getId(), partSeparator, Utils.ProductTypes.RAM.getValue());
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

        savePhotos(saveLaptopModel.getPhotos(), newLaptop.getId(), partSeparator, Utils.ProductTypes.LAPTOP.getValue());
    }

    void savePhotos(String[] photosArray, Long laptopId, String partSeparator, String productType) throws SQLException {
        Blob blob;
        byte[] decodedBytes;
        for (String photo : photosArray) {
            Photos photos = new Photos();
            photos.setProductId(laptopId);
            photos.setProductType(productType);

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

    public void saveGraphicsCard(SaveGraphicsCardDTO graphicsCardDTO) throws SQLException {
        GraphicsCard graphicsCard = new GraphicsCard();
        graphicsCard.setChipset(graphicsCardDTO.getChipset());
        graphicsCard.setCapacity(graphicsCardDTO.getCapacity());
        graphicsCard.setModel(graphicsCardDTO.getModel());
        graphicsCard.setTechnology(graphicsCardDTO.getTechnology());
        graphicsCard.setType(graphicsCardDTO.getType());
        graphicsCard.setName(graphicsCardDTO.getName());

        String partSeparator = ",";
        byte[] decodedBytes = new byte[0];
        if (graphicsCardDTO.getPhotos()[0].contains(partSeparator)) {
            String encodedImg = graphicsCardDTO.getPhotos()[0].split(partSeparator)[1];
            decodedBytes = Base64.getDecoder().decode(encodedImg);
        } else {
            decodedBytes = Base64.getDecoder().decode(graphicsCardDTO.getPhotos()[0]);
        }
        Blob blob = new SerialBlob(decodedBytes);
        graphicsCard.setPhoto(blob);

        GraphicsCard newGraphicsCard = graphicsCardRepository.save(graphicsCard);

        savePhotos(graphicsCardDTO.getPhotos(), newGraphicsCard.getId(), partSeparator, Utils.ProductTypes.GRAPHICS_CARD.getValue());

        graphicsCardRepository.save(graphicsCard);
    }

    public void saveProcessor(SaveProcessorDTO processorDTO) throws SQLException {
        Processor processor = modelMapper.map(processorDTO, Processor.class);
        String partSeparator = ",";
        byte[] decodedBytes = new byte[0];
        if (processorDTO.getPhotos()[0].contains(partSeparator)) {
            String encodedImg = processorDTO.getPhotos()[0].split(partSeparator)[1];
            decodedBytes = Base64.getDecoder().decode(encodedImg);
        } else {
            decodedBytes = Base64.getDecoder().decode(processorDTO.getPhotos()[0]);
        }
        Blob blob = new SerialBlob(decodedBytes);
        processor.setPhoto(blob);

        Processor newProcessor = processorRepository.save(processor);

        savePhotos(processorDTO.getPhotos(), newProcessor.getId(), partSeparator, Utils.ProductTypes.PROCESSOR.getValue());
    }

    public GraphicsCardDTO getGraphicsCard(Long id) {
        GraphicsCard graphicsCard = graphicsCardRepository.getById(id);
        return modelMapper.map(graphicsCard, GraphicsCardDTO.class);
    }

    public LaptopDTO getLaptop(Long id) {
        Laptop laptop = laptopRepository.getById(id);
        return modelMapper.map(laptop, LaptopDTO.class);
    }

    public StorageDTO getStorageById(Long id) {
        Storage storage = storageRepository.getById(id);
        return modelMapper.map(storage, StorageDTO.class);
    }

    public MotherboardDTO getMotherboardById(Long id) {
        Motherboard motherboard = motherboardRepository.getById(id);
        return modelMapper.map(motherboard, MotherboardDTO.class);
    }

    public RamDTO getRamById(Long id) {
        Ram ram = ramRepository.getById(id);
        return modelMapper.map(ram, RamDTO.class);
    }

    public void saveMotherboard(SaveMotherboardDTO motherboardDTO) throws SQLException {
        Motherboard motherboard = modelMapper.map(motherboardDTO, Motherboard.class);
        String partSeparator = ",";
        byte[] decodedBytes = new byte[0];
        if (motherboardDTO.getPhotos()[0].contains(partSeparator)) {
            String encodedImg = motherboardDTO.getPhotos()[0].split(partSeparator)[1];
            decodedBytes = Base64.getDecoder().decode(encodedImg);
        } else {
            decodedBytes = Base64.getDecoder().decode(motherboardDTO.getPhotos()[0]);
        }
        Blob blob = new SerialBlob(decodedBytes);
        motherboard.setPhoto(blob);

        Motherboard newMotherboard = motherboardRepository.save(motherboard);

        savePhotos(motherboardDTO.getPhotos(), newMotherboard.getId(), partSeparator, Utils.ProductTypes.MOTHERBOARD.getValue());
    }

    public void saveDisplay(SaveDisplayDTO displayDTO) {
        Display display = modelMapper.map(displayDTO, Display.class);
        displayRepository.save(display);
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

    public PhotosDto getPhotos(PhotosModelDto photosModelDto) {
        Set<Photos> photos = photosRepository.findAllByProductIdAndProductType(photosModelDto.getId(), photosModelDto.getProductType());
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
