package com.devfer.demomvc.dao;

import com.devfer.demomvc.domain.Departamento;


import java.util.List;

public interface DepartamentoDao {

    void save(Departamento d);

    void update(Departamento d);

    void delete(Long id);

    Departamento findById(Long id);

    List<Departamento> findAll();
}
