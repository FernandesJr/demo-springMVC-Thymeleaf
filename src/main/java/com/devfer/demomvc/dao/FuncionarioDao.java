package com.devfer.demomvc.dao;

import com.devfer.demomvc.domain.Funcionario;

import java.util.List;

public interface FuncionarioDao {

    void save(Funcionario f);

    void update(Funcionario f);

    void delete(Long id);

    Funcionario findById(Long id);

    List<Funcionario> findAll();
}
