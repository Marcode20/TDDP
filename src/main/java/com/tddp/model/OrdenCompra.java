package com.tddp.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tb_orden_compra")
public class OrdenCompra {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer ordenCompra_id;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime fechaOrden;

    @Column
    private Double total;



}
