package com.rlb.examenreactivospringwf.controller;

import com.rlb.examenreactivospringwf.model.Mensaje;
import com.rlb.examenreactivospringwf.model.Producto;
import com.rlb.examenreactivospringwf.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {
    private final ProductoService productoService;

    @GetMapping("/findfilter/{minPrice}")
    public Mono<ResponseEntity<Flux<Producto>>> findFilter(@PathVariable float minPrice){
        Flux<Producto> fx = productoService.findAllWithMinPriceAndCaps(minPrice);
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fx)
        ).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<Producto>> save(@RequestBody Producto producto){
        return productoService.save(producto)
                .map(e -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(e))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Producto>> findById(@PathVariable String id){
        return productoService.findByIdWithErrors(id)
                .map(e -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(e))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/delayed-message")
    public Flux<Mensaje> findDelayedMessage() {
        return productoService.mensajeDelay();
    }
}
