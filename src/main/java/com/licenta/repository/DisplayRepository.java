package com.licenta.repository;

import com.licenta.model.Display;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisplayRepository extends JpaRepository<Display, Long> {
}
