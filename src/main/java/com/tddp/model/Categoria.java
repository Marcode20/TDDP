package com.tddp.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tddp.model.Producto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@DynamicUpdate
@Table(name = "tb_categoria")
public class Categoria {

    public enum Status { Pendiente, Aceptado, Rechazado }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoria_id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Status status;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("categoria")
    private Set<Producto> subcategoryGlobals = new HashSet<>();
}




