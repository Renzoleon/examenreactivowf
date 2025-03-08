package com.rlb.examenreactivospringwf.service.impl;

import com.rlb.examenreactivospringwf.exception.ProductoNoEncontradoException;
import com.rlb.examenreactivospringwf.model.Mensaje;
import com.rlb.examenreactivospringwf.model.Producto;
import com.rlb.examenreactivospringwf.repository.ProductoRepository;
import com.rlb.examenreactivospringwf.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    @Override
    public Flux<Producto> findAllWithMinPriceAndCaps(Float minPrice) {
        Flux<Producto> allProductosFlux = productoRepository.findAll();
        Flux<Producto> filterProducts = allProductosFlux.filter(e -> e.getPrice() > minPrice).map(e -> {
            Producto p = new Producto();
            p.setId(e.getId());
            p.setName(e.getName().toUpperCase());
            p.setPrice(e.getPrice());
            return p;
        });

        return filterProducts;
    }

    @Override
    public Mono<Producto> save(Producto producto) {
        producto.setName(producto.getName().toUpperCase());
        return productoRepository.save(producto);
    }

    @Override
    public Mono<Producto> findByIdWithErrors(String id) {
        return productoRepository.findById(id)
                .switchIfEmpty(Mono.error(new ProductoNoEncontradoException("El producto con ID "+ id +" no fue encontrado")));

    }

    @Override
    public Flux<Mensaje> mensajeDelay() {
        return Flux.concat(
                Mono.just(new Mensaje("Hola después de 3 segundos!"))
                        .delayElement(Duration.ofSeconds(3)),
                Mono.just(new Mensaje("Este es un segundo mensaje!"))
                        .delayElement(Duration.ofSeconds(2))
        );
    }
}
