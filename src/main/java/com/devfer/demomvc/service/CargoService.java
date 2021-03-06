package com.devfer.demomvc.service;

import com.devfer.demomvc.domain.Cargo;
import com.devfer.demomvc.util.PaginacaoUtil;

import java.util.List;


public interface CargoService {

    void salvar(Cargo cargo);

    void editar(Cargo cargo);

    void excluir(Long id);

    Cargo buscarPorId(Long id);

    List<Cargo> buscarTodos();

    boolean hasFuncionario(Long id);

    PaginacaoUtil<Cargo> buscaPorPagina(int pagina, String ordenacao);
}
