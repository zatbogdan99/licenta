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
    DesktopRepository desktopRepository;

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

    public List<ProductDTO> getCartProducts(List<CartDTO> cartDTO) {
        List<ProductDTO> productDTOS = new ArrayList<>();
        cartDTO.forEach(dto -> {
            switch (dto.getProductType()) {
                case "Laptop": {
                    Optional<Laptop> laptopOptional = laptopRepository.findById(dto.getId());
                    if (laptopOptional.isPresent()) {
                        Laptop laptop = laptopOptional.get();
                        LaptopDTO laptopDTO = modelMapper.map(laptop, LaptopDTO.class);
                        try {
                            if (laptop.getPhoto() != null) {
                                byte[] imageByte = laptop.getPhoto().getBinaryStream().readAllBytes();
                                laptopDTO.setPhoto(Base64.getEncoder().encodeToString(imageByte));
                            }
                        } catch (IOException | SQLException e) {
                            e.printStackTrace();
                        }
                        productDTOS.add(LaptopDTO.toProduct(laptopDTO));
                    }
                    break;
                }
                case "Desktop": {
                    Optional<Desktop> desktopOptional = desktopRepository.findById(dto.getId());
                    if (desktopOptional.isPresent()) {
                        Desktop desktop = desktopOptional.get();
                        DesktopDTO desktopDTO = modelMapper.map(desktop, DesktopDTO.class);
                        if (desktop.getPhoto() != null) {
                            byte[] imageByte = new byte[0];
                            try {
                                imageByte = desktop.getPhoto().getBinaryStream().readAllBytes();
                            } catch (IOException | SQLException e) {
                                e.printStackTrace();
                            }
                            desktopDTO.setPhoto(Base64.getEncoder().encodeToString(imageByte));
                        }
                        productDTOS.add(DesktopDTO.toProduct(desktopDTO));
                    }
                    break;
                }
                case "Placă video":
                case "GraphicsCard": {
                    Optional<GraphicsCard> graphicsCardOptional = graphicsCardRepository.findById(dto.getId());
                    if (graphicsCardOptional.isPresent()) {
                        GraphicsCard graphicsCard = graphicsCardOptional.get();
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
                        productDTOS.add(GraphicsCardDTO.toProduct(graphicsCardDTO));
                    }
                    break;
                }
                case "Procesor":
                case "Processor": {
                    Optional<Processor> processorOptional = processorRepository.findById(dto.getId());
                    if (processorOptional.isPresent()) {
                        Processor processor = processorOptional.get();
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

                        productDTOS.add(ProcessorDTO.toProduct(processorDTO));
                    }
                    break;
                }
                case "Memorie RAM":
                case "RAM": {
                    Optional<Ram> ramOptional = ramRepository.findById(dto.getId());
                    if (ramOptional.isPresent()) {
                        Ram ram = ramOptional.get();
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
                        productDTOS.add(RamDTO.toProduct(ramDTO));
                    }
                    break;
                }
                case "Stocare":
                case "Storage": {
                    Optional<Storage> storageOptional = storageRepository.findById(dto.getId());
                    if (storageOptional.isPresent()) {
                        Storage storage = storageOptional.get();
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
                        productDTOS.add(StorageDTO.toProduct(storageDTO));
                    }
                    break;
                }
                case "Placă de bază":
                case "Motherboard": {
                    Optional<Motherboard> motherboardOptional = motherboardRepository.findById(dto.getId());
                    if (motherboardOptional.isPresent()) {
                        Motherboard motherboard = motherboardOptional.get();
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
                        productDTOS.add(MotherboardDTO.toProduct(motherboardDTO));
                    }
                    break;
                }
            }
        });
        return productDTOS;
    }

    public List<ProductDTO> getAllProducts() {
        List<Laptop> laptops = laptopRepository.getAllLaptops();
        List<GraphicsCard> graphicsCards = graphicsCardRepository.findAll();
        List<Processor> processors = processorRepository.findAll();
        List<Ram> rams = ramRepository.findAll();
        List<Storage> storages = storageRepository.findAll();
        List<Motherboard> motherboards = motherboardRepository.findAll();
        List<Desktop> desktops = desktopRepository.findAll();
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

        desktops.forEach(desktop -> {
            DesktopDTO desktopDTO = modelMapper.map(desktop, DesktopDTO.class);
            if (desktop.getPhoto() != null) {
                byte[] imageByte = new byte[0];
                try {
                    imageByte = desktop.getPhoto().getBinaryStream().readAllBytes();
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
                desktopDTO.setPhoto(Base64.getEncoder().encodeToString(imageByte));
            }
            products.add(DesktopDTO.toProduct(desktopDTO));
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

    public List<ProductDTO> getCompatibleProcessors(ConfiguratorDTO configuratorDTO) {
        List<Processor> processors = processorRepository.findAll();
        List<ProductDTO> products = new ArrayList<>();

        processors.stream().filter(processor -> {
            if (configuratorDTO.getMotherboard() != null) {
                Optional<Motherboard> motherboard = motherboardRepository.findById(configuratorDTO.getMotherboard());
                if (processor.getSocket() == null) {
                    return false;
                } else {
                    return motherboard.map(value -> processor.getSocket().equals(value.getCpuSocket())).orElse(true);
                }
            } else if (processor.getForLaptop() != null && processor.getForLaptop() == 1){
                return false;
            } else {
                return true;
            }
        }).forEach(processor -> {
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

        return products;
    }

    public List<ProductDTO> getCompatibleGraphicsCards(ConfiguratorDTO configuratorDTO) {
        List<GraphicsCard> graphicsCards = graphicsCardRepository.findAll();
        List<ProductDTO> products = new ArrayList<>();

        graphicsCards.stream().filter(graphicsCard -> {
            if (configuratorDTO.getMotherboard() != null) {
                Optional<Motherboard> motherboard = motherboardRepository.findById(configuratorDTO.getMotherboard());
                return motherboard.map(value -> value.getGraphicalInterface().equals(graphicsCard.getGraphicsCardInterface())).orElse(false);
            } else if (graphicsCard.getForLaptop() != null && graphicsCard.getForLaptop() ==1) {
                return false;
            } else {
                return true;
            }
        }).forEach(graphicsCard -> {
            GraphicsCardDTO graphicsCardDTO = modelMapper.map(graphicsCard, GraphicsCardDTO.class);

            if (graphicsCardDTO.getPhoto() != null) {
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

        return products;
    }

    public List<ProductDTO> getCompatibleRams(ConfiguratorDTO configuratorDTO) {
        List<Ram> rams = ramRepository.findAll();
        List<ProductDTO> products = new ArrayList<>();

        rams.stream().filter(ram -> {
            if (ram.getForLaptop() != null && ram.getForLaptop() == 1 && configuratorDTO.getLaptop() == null) {
                return false;
            }
            if (configuratorDTO.getMotherboard() != null) {
                Optional<Motherboard> motherboard = motherboardRepository.findById(configuratorDTO.getMotherboard());
                boolean ramType = motherboard.map(value -> ram.getType().equals(value.getRamType())).orElse(false);
                String[] frequencies = motherboard.get().getSupportedFrequencies().split("/");
                boolean frequency = Arrays.asList(frequencies).contains(ram.getFrequency().toString());
                return ramType && frequency;
            } else if (configuratorDTO.getLaptop() != null) {
                Optional<Laptop> laptop = laptopRepository.findById(configuratorDTO.getLaptop());
                if (laptop.isPresent()) {
                    if (laptop.get().getRam().getFrequency() != null && laptop.get().getRam().getType() != null) {
                        boolean ramType = laptop.map(value -> ram.getType().equals(value.getAdditionalRamType())).orElse(false);
                        boolean frequency = laptop.get().getAdditionalRamFrequency().equals(ram.getFrequency().toString());
                        boolean isForLaptop = ram.getForLaptop() == 1;
                        return ramType && frequency && isForLaptop;
                    }
                }
            } else {
                return true;
            }
            return false;
        }).forEach(ram -> {
            RamDTO ramDTO = modelMapper.map(ram, RamDTO.class);

            if (ramDTO.getPhoto() != null) {
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

        return products;
    }

    public List<ProductDTO> getCompatibleStorages(ConfiguratorDTO configuratorDTO) {
        List<Storage> storages = storageRepository.findAll();
        List<ProductDTO> products = new ArrayList<>();

        storages.stream().filter(storage -> {
            if (configuratorDTO.getMotherboard() != null) {
                Optional<Motherboard> motherboard = motherboardRepository.findById(configuratorDTO.getMotherboard());
                if (motherboard.isPresent()) {
                    if (storage.getStorage_interface().equals("SATA III")) {
                        return motherboard.get().getSata3Slots() > 1;
                    } else if (storage.getStorage_interface().equals("M.2 PCIe")) {
                        //TODO de adaugat si ferificarea dupa form factor
                        return motherboard.get().getM2Ports() > 1;
                    } else {
                        return true;
                    }
                } else {
                    return true;
                }
            } else if (configuratorDTO.getLaptop() != null) {
                Optional<Laptop> laptop = laptopRepository.findById(configuratorDTO.getLaptop());
                if (laptop.isPresent()) {
                    if (laptop.get().getAdditionalSataSlots() != null && laptop.get().getAdditionalSataSlots() > 0) {
                        return laptop.get().getAdditionalSataType().equals(storage.getStorage_interface());
                    } else if(laptop.get().getAdditionalM2Slots() != null && laptop.get().getAdditionalM2Slots() > 0) {
                        if (storage.getForm_factor() != null) {
                            return laptop.get().getAdditionalM2Interface().equals(storage.getStorage_interface()) &&
                                    laptop.get().getAdditionalM2FormFactor().equals(storage.getForm_factor().toString());
                        } else {
                            return false;
                        }
                    }
                }
            } else {
                return true;
            }
            return false;
        }).forEach(storage -> {
            StorageDTO storageDTO = modelMapper.map(storage, StorageDTO.class);

            if (storageDTO.getPhoto() != null) {
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

        return products;
    }

    public List<ProductDTO> getCompatibleMotherboards(ConfiguratorDTO configuratorDTO) {
        List<Motherboard> motherboards = motherboardRepository.findAll();
        List<ProductDTO> products = new ArrayList<>();

        motherboards.stream().filter(motherboard -> {
            boolean socket = true;
            boolean graphicsCardInterface = true;
            boolean ramBoolean = true;
            if (configuratorDTO.getProcessor() != null) {
                Optional<Processor> processor = processorRepository.findById(configuratorDTO.getProcessor());
                if (processor.isPresent()) {
                    socket = processor.get().getSocket().equals(motherboard.getCpuSocket());
                }
            }
            if (configuratorDTO.getGraphicsCard() != null) {
                Optional<GraphicsCard> graphicsCard = graphicsCardRepository.findById(configuratorDTO.getGraphicsCard());
                if (graphicsCard.isPresent()) {
                    graphicsCardInterface = graphicsCard.get().getGraphicsCardInterface().equals(motherboard.getGraphicalInterface());
                }
            }
            if (configuratorDTO.getRam() != null) {
                Optional<Ram> ram = ramRepository.findById(configuratorDTO.getRam());
                boolean ramType = ram.get().getType().equals(motherboard.getRamType());
                String[] frequencies = motherboard.getSupportedFrequencies().split("/");
                boolean frequency = Arrays.asList(frequencies).contains(ram.get().getFrequency().toString());
                ramBoolean = ramType && frequency;
            }
            boolean storageBoolean = true;
            if (configuratorDTO.getStorage() != null) {
                Optional<Storage> storage = storageRepository.findById(configuratorDTO.getStorage());
                if (storage.isPresent()) {
                    if (storage.get().getStorage_interface().equals("SATA III")) {
                        storageBoolean = motherboard.getSata3Slots() > 1;
                    } else if (storage.get().getStorage_interface().equals("M.2 PCIe")) {
                        storageBoolean = motherboard.getM2Ports() > 1;
                    }
                }
            }
            return socket && graphicsCardInterface && ramBoolean && storageBoolean;
        }).forEach(motherboard -> {
            MotherboardDTO motherboardDTO = modelMapper.map(motherboard, MotherboardDTO.class);

            if (motherboardDTO.getPhoto() != null) {
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

    public void saveDesktop(SaveDesktopDTO saveDesktopDTO) throws SQLException {
        Desktop desktop = new Desktop();
        desktop.setName(saveDesktopDTO.getName());
        desktop.setWarranty(saveDesktopDTO.getWarranty());
        desktop.setProcessor(processorRepository.getById(saveDesktopDTO.getProcessor()));
        desktop.setGraphicsCard(graphicsCardRepository.getById(saveDesktopDTO.getGraphicsCard()));
        desktop.setStorage(storageRepository.getById(saveDesktopDTO.getStorage()));
        desktop.setRam(ramRepository.getById(saveDesktopDTO.getRam()));
        desktop.setType(saveDesktopDTO.getType());
        desktop.setStock(saveDesktopDTO.getStock());

        String partSeparator = ",";
        byte[] decodedBytes = new byte[0];
        if (saveDesktopDTO.getPhotos()[0].contains(partSeparator)) {
            String encodedImg = saveDesktopDTO.getPhotos()[0].split(partSeparator)[1];
            decodedBytes = Base64.getDecoder().decode(encodedImg);
        } else {
            decodedBytes = Base64.getDecoder().decode(saveDesktopDTO.getPhotos()[0]);
        }
        Blob blob = new SerialBlob(decodedBytes);
        desktop.setPhoto(blob);

        Desktop newDesktop = desktopRepository.save(desktop);

        savePhotos(saveDesktopDTO.getPhotos(), newDesktop.getId(), partSeparator, Utils.ProductTypes.DESKTOP.getValue());
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
        laptop.setStock(saveLaptopModel.getStock());

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
        graphicsCard.setStock(graphicsCardDTO.getStock());
        graphicsCard.setPrice(graphicsCardDTO.getPrice());
        graphicsCard.setWarranty(graphicsCardDTO.getWarranty());
        graphicsCard.setForLaptop(graphicsCardDTO.getForLaptop());

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
        byte[] decodedBytes;
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

    public ProcessorDTO getProcessor(Long id) {
        Processor processor = processorRepository.getById(id);
        return modelMapper.map(processor, ProcessorDTO.class);
    }

    public LaptopDTO getLaptop(Long id) {
        Laptop laptop = laptopRepository.getById(id);
        return modelMapper.map(laptop, LaptopDTO.class);
    }

    public StorageDTO getStorageById(Long id) {
        Optional<Storage> storage = storageRepository.findById(id);
        return modelMapper.map(storage.get(), StorageDTO.class);
    }

    public MotherboardDTO getMotherboardById(Long id) {
        Motherboard motherboard = motherboardRepository.getById(id);
        return modelMapper.map(motherboard, MotherboardDTO.class);
    }

    public DesktopDTO getDesktop(Long id) {
        Desktop desktop = desktopRepository.getById(id);
        return modelMapper.map(desktop, DesktopDTO.class);
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
        List<Ram> rams = ramRepository.findAll();
        List<Storage> storages = storageRepository.findAll();
        List<GraphicsCard> graphicsCards = graphicsCardRepository.findAll();
        List<Processor> processors = processorRepository.findAll();
        List<Motherboard> motherboards = motherboardRepository.findAll();
        List<Desktop> desktops = desktopRepository.findAll();
        List<ProductDTO> products = new ArrayList<>();

        if (filterDTO.getProductType() != null) {
            switch (filterDTO.getProductType()) {
                case "Laptop": {
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
                    break;
                }
                case "Desktop": {
                    desktops.stream().filter(desktop -> applyDesktopFilters(filterDTO, desktop)).forEach(desktop -> {
                        DesktopDTO desktopDTO = modelMapper.map(desktop, DesktopDTO.class);
                        try {
                            if (desktop.getPhoto() != null) {
                                byte[] imageByte = desktop.getPhoto().getBinaryStream().readAllBytes();
                                desktopDTO.setPhoto(Base64.getEncoder().encodeToString(imageByte));
                            }
                        } catch (IOException | SQLException e) {
                            e.printStackTrace();
                        }
                        System.out.println(desktopDTO);
                        products.add(DesktopDTO.toProduct(desktopDTO));
                    });
                    break;
                }
                case "Placă video":
                case "GraphicsCard": {
                    graphicsCards.stream().filter(graphicsCard -> applyGraphicsCardFilters(filterDTO, graphicsCard)).forEach(graphicsCard -> {
                        GraphicsCardDTO graphicsCardDTO = modelMapper.map(graphicsCard, GraphicsCardDTO.class);
                        try {
                            if (graphicsCard.getPhoto() != null) {
                                byte[] imageByte = graphicsCard.getPhoto().getBinaryStream().readAllBytes();
                                graphicsCardDTO.setPhoto(Base64.getEncoder().encodeToString(imageByte));
                            }
                        } catch (IOException | SQLException e) {
                            e.printStackTrace();
                        }
                        System.out.println(graphicsCardDTO);
                        products.add(GraphicsCardDTO.toProduct(graphicsCardDTO));
                    });
                    break;
                }
                case "Procesor":
                case "Processor": {
                    processors.stream().filter(processor -> applyProcessorFilters(filterDTO, processor)).forEach(processor -> {
                        ProcessorDTO processorDTO = modelMapper.map(processor, ProcessorDTO.class);
                        try {
                            if (processor.getPhoto() != null) {
                                byte[] imageByte = processor.getPhoto().getBinaryStream().readAllBytes();
                                processorDTO.setPhoto(Base64.getEncoder().encodeToString(imageByte));
                            }
                        } catch (IOException | SQLException e) {
                            e.printStackTrace();
                        }
                        System.out.println(processorDTO);
                        products.add(ProcessorDTO.toProduct(processorDTO));
                    });
                    break;
                }
                case "Memorie RAM":
                case "RAM": {
                    rams.stream().filter(ram -> applyRamFilters(filterDTO, ram)).forEach(ram -> {
                        RamDTO ramDTO = modelMapper.map(ram, RamDTO.class);
                        try {
                            if (ram.getPhoto() != null) {
                                byte[] imageByte = ram.getPhoto().getBinaryStream().readAllBytes();
                                ramDTO.setPhoto(Base64.getEncoder().encodeToString(imageByte));
                            }
                        } catch (IOException | SQLException e) {
                            e.printStackTrace();
                        }
                        System.out.println(ramDTO);
                        products.add(RamDTO.toProduct(ramDTO));
                    });
                    break;
                }
                case "Stocare":
                case "Storage": {
                    storages.stream().filter(storage -> applyStorageFilters(filterDTO, storage)).forEach(storage -> {
                        StorageDTO storageDTO = modelMapper.map(storage, StorageDTO.class);
                        try {
                            if (storage.getPhoto() != null) {
                                byte[] imageByte = storage.getPhoto().getBinaryStream().readAllBytes();
                                storageDTO.setPhoto(Base64.getEncoder().encodeToString(imageByte));
                            }
                        } catch (IOException | SQLException e) {
                            e.printStackTrace();
                        }
                        System.out.println(storageDTO);
                        products.add(StorageDTO.toProduct(storageDTO));
                    });
                    break;
                }
                case "Placă de bază":
                case "Motherboard": {
                    motherboards.stream().filter(motherboard -> applyMotherboardFilters(filterDTO, motherboard)).forEach(motherboard -> {
                        MotherboardDTO motherboardDTO = modelMapper.map(motherboard, MotherboardDTO.class);
                        try {
                            if (motherboard.getPhoto() != null) {
                                byte[] imageByte = motherboard.getPhoto().getBinaryStream().readAllBytes();
                                motherboardDTO.setPhoto(Base64.getEncoder().encodeToString(imageByte));
                            }
                        } catch (IOException | SQLException e) {
                            e.printStackTrace();
                        }
                        System.out.println(motherboardDTO);
                        products.add(MotherboardDTO.toProduct(motherboardDTO));
                    });
                    break;
                }
            }
        } else {
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
            graphicsCards.stream().filter(graphicsCard -> applyGraphicsCardFilters(filterDTO, graphicsCard)).forEach(graphicsCard -> {
                GraphicsCardDTO graphicsCardDTO = modelMapper.map(graphicsCard, GraphicsCardDTO.class);
                try {
                    if (graphicsCard.getPhoto() != null) {
                        byte[] imageByte = graphicsCard.getPhoto().getBinaryStream().readAllBytes();
                        graphicsCardDTO.setPhoto(Base64.getEncoder().encodeToString(imageByte));
                    }
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
                System.out.println(graphicsCardDTO);
                products.add(GraphicsCardDTO.toProduct(graphicsCardDTO));
            });
            processors.stream().filter(processor -> applyProcessorFilters(filterDTO, processor)).forEach(processor -> {
                ProcessorDTO processorDTO = modelMapper.map(processor, ProcessorDTO.class);
                try {
                    if (processor.getPhoto() != null) {
                        byte[] imageByte = processor.getPhoto().getBinaryStream().readAllBytes();
                        processorDTO.setPhoto(Base64.getEncoder().encodeToString(imageByte));
                    }
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
                System.out.println(processorDTO);
                products.add(ProcessorDTO.toProduct(processorDTO));
            });
            rams.stream().filter(ram -> applyRamFilters(filterDTO, ram)).forEach(ram -> {
                RamDTO ramDTO = modelMapper.map(ram, RamDTO.class);
                try {
                    if (ram.getPhoto() != null) {
                        byte[] imageByte = ram.getPhoto().getBinaryStream().readAllBytes();
                        ramDTO.setPhoto(Base64.getEncoder().encodeToString(imageByte));
                    }
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
                System.out.println(ramDTO);
                products.add(RamDTO.toProduct(ramDTO));
            });
            storages.stream().filter(storage -> applyStorageFilters(filterDTO, storage)).forEach(storage -> {
                StorageDTO storageDTO = modelMapper.map(storage, StorageDTO.class);
                try {
                    if (storage.getPhoto() != null) {
                        byte[] imageByte = storage.getPhoto().getBinaryStream().readAllBytes();
                        storageDTO.setPhoto(Base64.getEncoder().encodeToString(imageByte));
                    }
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
                System.out.println(storageDTO);
                products.add(StorageDTO.toProduct(storageDTO));
            });
        }

        return products;
    }

    private boolean applyDesktopFilters(FilterDTO filterDTO, Desktop desktop) {
        if (desktop.getPrice() != null) {
            if (desktop.getPrice() > filterDTO.getMaxRange() || desktop.getPrice() < filterDTO.getMinRange()) {
                return false;
            }
        }
        if (filterDTO.getRam() != null && filterDTO.getRam().length > 0) {
            if (Arrays.stream(filterDTO.getRam()).noneMatch(ram -> ram.equals(desktop.getRam().getType()))) {
                return false;
            }
        }
        if (filterDTO.getMemory() != null && filterDTO.getMemory().length > 0) {
            if (Arrays.stream(filterDTO.getMemory()).noneMatch(memory -> memory.equals(desktop.getStorage().getType()))) {
                return false;
            }
        }
        if (filterDTO.getProcessor() != null && filterDTO.getProcessor().length > 0) {
            if (Arrays.stream(filterDTO.getProcessor()).noneMatch(processor -> processor.equals(desktop.getProcessor().getProducer()))) {
                return false;
            }
        }
        if (filterDTO.getMemoryCapacity() != null && filterDTO.getMemoryCapacity().length > 0) {
            return Arrays.stream(filterDTO.getMemoryCapacity()).anyMatch(memoryCapacity -> memoryCapacity.equals(desktop.getStorage().getCapacity().toString()));
        }
        if (filterDTO.getRamCapacity() != null && filterDTO.getRamCapacity().length > 0) {
            return Arrays.stream(filterDTO.getRamCapacity()).anyMatch(ramCapacity -> ramCapacity.equals(desktop.getRam().getTotal().toString()));
        }
        return true;
    }

    private boolean applyMotherboardFilters(FilterDTO filterDTO, Motherboard motherboard) {
        if (motherboard.getPrice() != null) {
            if (motherboard.getPrice() > filterDTO.getMaxRange() || motherboard.getPrice() < filterDTO.getMinRange()) {
                return false;
            }
        }

        if (filterDTO.getSelectedGraphicalInterface() != null && filterDTO.getSelectedGraphicalInterface().length > 0) {
            if (Arrays.stream(filterDTO.getSelectedGraphicalInterface()).noneMatch(p -> p.equals(motherboard.getGraphicalInterface()))) {
                return false;
            }
        }

        return true;
    }

    private boolean applyProcessorFilters(FilterDTO filterDTO, Processor processor) {
        if (processor.getPrice() != null) {
            if (processor.getPrice() > filterDTO.getMaxRange() || processor.getPrice() < filterDTO.getMinRange()) {
                return false;
            }
        }

        if (processor.getForLaptop() != null && processor.getForLaptop() == 1) {
            return false;
        }

        if (filterDTO.getProcessor() != null && filterDTO.getProcessor().length > 0) {
            if (Arrays.stream(filterDTO.getProcessor()).noneMatch(p -> p.equals(processor.getProducer()))) {
                return false;
            }
        }
        return true;
    }

    private boolean applyGraphicsCardFilters(FilterDTO filterDTO, GraphicsCard graphicsCard) {
        if (graphicsCard.getPrice() != null) {
            if (graphicsCard.getPrice() > filterDTO.getMaxRange() || graphicsCard.getPrice() < filterDTO.getMinRange()) {
                return false;
            }
        }
        if (filterDTO.getSelectedVRAM() != null && filterDTO.getSelectedVRAM().length > 0) {
            if (Arrays.stream(filterDTO.getSelectedVRAM()).noneMatch(g -> g.equals(graphicsCard.getCapacity().toString()))) {
                return false;
            }
        }

        return true;
    }

    private boolean applyStorageFilters(FilterDTO filterDTO, Storage storage) {
        if (storage.getPrice() > filterDTO.getMaxRange() || storage.getPrice() < filterDTO.getMinRange()) {
            return false;
        }

        if (filterDTO.getMemory() != null && filterDTO.getMemory().length > 0) {
            if (Arrays.stream(filterDTO.getMemory()).noneMatch(memory -> memory.equals(storage.getType()))) {
                return false;
            }
        }

        if (filterDTO.getMemoryCapacity() != null && filterDTO.getMemoryCapacity().length > 0) {
            return Arrays.stream(filterDTO.getMemoryCapacity()).anyMatch(memoryCapacity -> memoryCapacity.equals(storage.getCapacity().toString()));
        }

        return true;
    }

    private boolean applyRamFilters(FilterDTO filterDTO, Ram ram) {
        if (ram.getPrice() > filterDTO.getMaxRange() || ram.getPrice() < filterDTO.getMinRange()) {
            return false;
        }

        if (filterDTO.getRam() != null && filterDTO.getRam().length > 0) {
            if (Arrays.stream(filterDTO.getRam()).noneMatch(r -> r.equals(ram.getType()))) {
                return false;
            }
        }

        if (filterDTO.getSelectedRAMFrequency() != null) {
            if (Arrays.stream(filterDTO.getSelectedRAMFrequency()).noneMatch(r -> r.equals(ram.getFrequency().toString()))) {
                return false;
            }
        }
        if (filterDTO.getRamCapacity() != null) {
            if (Arrays.stream(filterDTO.getRamCapacity()).noneMatch(r -> r.equals(ram.getTotal().toString()))) {
                return false;
            }
        }

        if (filterDTO.getSelectedRAMCAS() != null) {
            if (Arrays.stream(filterDTO.getSelectedRAMCAS()).noneMatch(r -> r.equals(ram.getFormat()))) {
                return false;
            }
        }

        return true;
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
            if (Arrays.stream(filterDTO.getMemory()).noneMatch(memory -> memory.equals(laptop.getStorage().getType()))) {
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

        if (filterDTO.getRamCapacity() != null && filterDTO.getRamCapacity().length > 0) {
            return Arrays.stream(filterDTO.getRamCapacity()).anyMatch(ramCapacity -> ramCapacity.equals(laptop.getRam().getTotal().toString()));
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

    public List<ProductDTO> getDesktops(Long id) {
        List<Desktop> desktops = desktopRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();

        desktops.forEach(desktop -> {
            DesktopDTO desktopDTO = modelMapper.map(desktop, DesktopDTO.class);

            productDTOS.add(DesktopDTO.toProduct(desktopDTO));
        });

        return productDTOS;
    }

    public List<ProductDTO> getLaptops() {
        List<Laptop> laptops = laptopRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();

        laptops.forEach(laptop -> {
            LaptopDTO laptopDTO = modelMapper.map(laptop, LaptopDTO.class);

            if (laptopDTO.getPhoto() != null) {
                byte[] imageByte = new byte[0];
                try {
                    imageByte = laptop.getPhoto().getBinaryStream().readAllBytes();
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
                laptopDTO.setPhoto(Base64.getEncoder().encodeToString(imageByte));
            }

            productDTOS.add(LaptopDTO.toProduct(laptopDTO));
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
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
                photosList.add(Base64.getEncoder().encodeToString(imageByte));
            }
        });

        photosDto.setPhotos(photosList);
        return photosDto;
    }

    @Transactional
    public void updateStock(UpdateProductStock updateProductStock) {
        switch (updateProductStock.getSelectedProductType()) {
            case "Laptop" -> {
                Laptop laptop = laptopRepository.getById(updateProductStock.getSelectedProduct());
                laptop.setStock(updateProductStock.getValue());
            }
            case "Desktop" -> {
                Desktop desktop = desktopRepository.getById(updateProductStock.getSelectedProduct());
                desktop.setStock(updateProductStock.getValue());
            }
            case "GraphicsCard" -> {
                GraphicsCard graphicsCard = graphicsCardRepository.getById(updateProductStock.getSelectedProduct());
                graphicsCard.setStock(updateProductStock.getValue());
            }
            case "Processor" -> {
                Processor processor = processorRepository.getById(updateProductStock.getSelectedProduct());
                processor.setStock(updateProductStock.getValue());
            }
            case "RAM" -> {
                Ram ram = ramRepository.getById(updateProductStock.getSelectedProduct());
                ram.setStock(updateProductStock.getValue());
            }
            case "Storage" -> {
                Storage storage = storageRepository.getById(updateProductStock.getSelectedProduct());
                storage.setStock(updateProductStock.getValue());
            }
            case "Motherboard" -> {
                Motherboard motherboard = motherboardRepository.getById(updateProductStock.getSelectedProduct());
                motherboard.setStock(updateProductStock.getValue());
            }
        }
    }

    public void removeProduct(RemoveProductDTO removeProductDTO) {
        switch (removeProductDTO.getProductType()) {
            case "Laptop" -> {
                laptopRepository.deleteById(removeProductDTO.getId());
            }
            case "Desktop" -> {
                desktopRepository.deleteById(removeProductDTO.getId());
            }
            case "GraphicsCard" -> {
                graphicsCardRepository.deleteById(removeProductDTO.getId());
            }
            case "Processor" -> {
                processorRepository.deleteById(removeProductDTO.getId());
            }
            case "RAM" -> {
                ramRepository.deleteById(removeProductDTO.getId());
            }
            case "Storage" -> {
                storageRepository.deleteById(removeProductDTO.getId());
            }
            case "Motherboard" -> {
                motherboardRepository.deleteById(removeProductDTO.getId());
            }
        }
    }
}
