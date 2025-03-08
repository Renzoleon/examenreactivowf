package com.rlb.examenreactivospringwf.repository;

import com.rlb.examenreactivospringwf.model.Producto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductoRepository extends ReactiveMongoRepository<Producto, String> {
}
