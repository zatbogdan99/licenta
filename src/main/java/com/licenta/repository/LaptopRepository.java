package com.licenta.repository;

import com.licenta.model.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LaptopRepository extends JpaRepository<Laptop, Long> {
    List<Laptop> getAllByIdNotNull();

    @Query("SELECT l from Laptop l")
    List<Laptop> getAllLaptops();
}
