package com.tddp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.EnableMBeanExport;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tbl_orden_compra")
public class OrdenCompra {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer orden_compra_id;

    @Column
    private LocalDateTime fechaOrden;

    @Column
    private Double total;

    @OneToOne(mappedBy = "ordenCompra")
    private Carrito carrito;
}
