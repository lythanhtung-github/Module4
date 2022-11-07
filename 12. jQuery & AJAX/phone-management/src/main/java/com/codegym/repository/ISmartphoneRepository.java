package com.codegym.repository;

import com.codegym.model.Smartphone;
import org.springframework.data.repository.CrudRepository;

public interface ISmartphoneRepository extends CrudRepository<Smartphone, Long> {
}
