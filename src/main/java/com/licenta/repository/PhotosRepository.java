package com.licenta.repository;

import com.licenta.model.Display;
import com.licenta.model.Photos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotosRepository extends JpaRepository<Photos, Long> {
}
