package com.tddp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
    private String fileS3Key;

    @Column
    private Double total;

    @OneToOne(mappedBy = "ordenCompra")
    private Carrito carrito;

    @Override
    public String toString() {
        return "OrdenCompra\n" +
                "orden_compra_id=" + orden_compra_id + "\n" +
                ", fechaOrden =" + fechaOrden + "\n" +
                ", carrito =" + carrito + "\n" +
                ", total =" + total ;
    }

}
