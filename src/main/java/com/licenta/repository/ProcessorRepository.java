package com.licenta.repository;

import com.licenta.model.Processor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessorRepository extends JpaRepository<Processor, Long> {
    @Override
    Processor getById(Long aLong);
}
