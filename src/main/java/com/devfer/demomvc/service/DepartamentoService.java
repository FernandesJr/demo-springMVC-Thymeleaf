package com.devfer.demomvc.service;



import com.devfer.demomvc.domain.Departamento;

import java.util.List;

public interface DepartamentoService {
    void salvar(Departamento departamento);

    void editar(Departamento departamento);

    void excluir(Long id);

    Departamento buscarPorId(Long id);

    List<Departamento> buscarTodos();
}