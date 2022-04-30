package com.licenta.repository;

import com.licenta.model.Display;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisplayRepository extends JpaRepository<Display, Long> {
    @Override
    Display getById(Long aLong);
}
