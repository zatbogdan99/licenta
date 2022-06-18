package com.licenta.repository;

import com.licenta.model.Photos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PhotosRepository extends JpaRepository<Photos, Long> {
    Set<Photos> findAllByProductIdAndProductType(Long id, String productType);
}
