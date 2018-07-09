package com.example.challenge6.repository;

import com.example.challenge6.model.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {
}
