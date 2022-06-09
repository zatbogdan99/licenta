package com.licenta.repository;

import com.licenta.model.Motherboard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotherboardRepository extends JpaRepository<Motherboard, Long> {
    @Override
    <S extends Motherboard> S save(S entity);
}
