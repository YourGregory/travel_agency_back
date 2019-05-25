package com.kursova.travel.repository;

import com.kursova.travel.entity.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Long> {
}
