package com.licenta.repository;

import com.licenta.model.GraphicsCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GraphicsCardRepository extends JpaRepository<GraphicsCard, Long> {
    @Override
    GraphicsCard getById(Long aLong);
}
