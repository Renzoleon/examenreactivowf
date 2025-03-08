package com.rlb.examenreactivospringwf.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "productos")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Producto {
    @Id
    @EqualsAndHashCode.Include
    private String id;
    @Field
    private String name;
    @Field
    private Float price;
}
