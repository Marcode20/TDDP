package com.tddp.service;

import com.tddp.model.Marca;

import java.util.List;

public interface MarcaService {

    Marca createMarca(Marca marca);

    Marca updateMarca(Marca marca);

    List<Marca> GetMarcaAll();

    Marca GetMarcaById(Integer marca_id);

    void deleteMarca(Integer marca_id);
}
