package com.tddp.service;

import com.tddp.model.Categoria;

import java.util.List;

public interface CategoriaService {

    Categoria createCategoria(Categoria categoria);
    Categoria updateCategoria(Categoria categoria);
    List<Categoria> getCategoriaAll();
    Categoria getCategoriaById(Integer categoria_id);
    void deleteCategoria(Integer categoria_id);
}
