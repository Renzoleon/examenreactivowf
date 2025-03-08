package com.rlb.examenreactivospringwf.service;

import com.rlb.examenreactivospringwf.model.Mensaje;
import com.rlb.examenreactivospringwf.model.Producto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductoService {
    Flux<Producto> findAllWithMinPriceAndCaps(Float minPrice);
    Mono<Producto> save(Producto producto);
    Mono<Producto> findByIdWithErrors(String id);
    Flux<Mensaje> mensajeDelay();
}
