package com.api.casm.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "productos")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "the field 'name' cannot be blank")
    @Column(name = "nombre")
    private String name;
    @NotBlank(message = "the field 'description' cannot be blank")
    @Column(name = "descripcion")
    private String description;
    @NotNull(message = "the field 'price' cannot be null")
    @Column(name = "precio")
    private Double price;
    @NotNull(message = "the field 'stock' cannot be null")
    @Column(name = "stock")
    private Integer stock;
    @NotNull(message = "the field 'category' cannot be null")
    @JoinColumn(name = "categoria_id")
    @ManyToOne
    @JsonBackReference
    private Category category;
}
