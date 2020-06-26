package com.tddp.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tb_mensaje")
public class Mensaje {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(nullable = false)
    private int idAuthor;

    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "idListaProducto")
    @JsonIgnoreProperties({"mensajes"})
    private ListaProducto listaProducto= new ListaProducto();
}
