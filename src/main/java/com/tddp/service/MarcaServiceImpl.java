package com.tddp.service;

import com.tddp.exception.ResourceNotFoundException;
import com.tddp.model.Marca;
import com.tddp.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class MarcaServiceImpl implements MarcaService{

    @Autowired
    MarcaRepository marcaRepository;

    @Override
    public Marca createMarca(Marca marca) {
        return marcaRepository.save(marca); }

    @Override
    public Marca updateMarca(Marca marca) {
        return marcaRepository.save(marca); }

    @Override
    public List<Marca> GetMarcaAll() {
        return marcaRepository.findAll(); }

    @Override
    public Marca GetMarcaById(Integer marca_id) {
        Optional<Marca> marca = marcaRepository.findById(marca_id);
        if (marca.isPresent()){
            return  marca.get();
        }else {
            throw new ResourceNotFoundException("ERROR" + marca_id); } }


    @Override
    public void deleteMarca(Integer marca_id) {
        Optional<Marca> marca = marcaRepository.findById(marca_id);
        if (marca.isPresent()) {
            marcaRepository.delete(marca.get()); }
        else {
            throw new ResourceNotFoundException("ERROR" + marca_id); }
    }
}

