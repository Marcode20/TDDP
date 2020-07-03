package com.tddp.service;

import com.tddp.exception.ResourceNotFoundException;
import com.tddp.model.Categoria;
import com.tddp.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoriaServiceImp implements CategoriaService{

    @Autowired
    CategoriaRepository categoriaRepository;

    @Override
    public Categoria createCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria updateCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public List<Categoria> getCategoriaAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria getCategoriaById(Integer categoria_id) {
        Optional<Categoria> byId = categoriaRepository.findById(categoria_id);
        if(byId.isPresent()){return byId.get();
        }else {
            throw new ResourceNotFoundException("Error: " + categoria_id);
        }
    }

    @Override
    public void deleteCategoria(Integer categoria_id) {
        Optional<Categoria> byId = categoriaRepository.findById(categoria_id);
        if(byId.isPresent()){
            categoriaRepository.delete(byId.get());
        }else {
            throw new ResourceNotFoundException("Error: " + categoria_id);
        }
    }
}
