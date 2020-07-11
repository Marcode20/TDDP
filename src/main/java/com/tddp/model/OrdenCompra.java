package com.tddp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy/MM/dd hh:mm:ss")
    private LocalDateTime fechaOrden;

    @Column
    private String fileS3Key;

    @Column
    private Double total;

    @OneToOne(mappedBy = "ordenCompra")
    private Carrito carrito;


    public OrdenCompra( LocalDateTime fechaOrden, String fileS3Key,  Double total, Carrito carrito) {
        this.fechaOrden = fechaOrden;
        this.fileS3Key = fileS3Key;
        this.total = total;
        this.carrito = carrito;
    }

    @Override
    public String toString() {
        return "OrdenCompra\n" +
                "orden_compra_id=" + orden_compra_id + "\n" +
                ", fechaOrden =" + fechaOrden + "\n" +
                ", producto =" + carrito.getCarritoProducto().get(0).getProducto().getNombre() + "\n" +
                ", cantidad =" + carrito.getCarritoProducto().get(0).getCantidad()+ "\n" +
                ", precio =" + carrito.getCarritoProducto().get(0).getProducto().getPrecio()+ "\n" +
                ", descripcion =" + carrito.getCarritoProducto().get(0).getProducto().getDescripcion()+ "\n" +
                ", descuento = 3.00 " +
                ", total =" + total ;
    }

}
