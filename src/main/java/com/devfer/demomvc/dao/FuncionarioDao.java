package com.devfer.demomvc.dao;

import com.devfer.demomvc.domain.Funcionario;
import com.devfer.demomvc.util.PaginacaoUtil;

import java.time.LocalDate;
import java.util.List;

public interface FuncionarioDao {

    void save(Funcionario f);

    void update(Funcionario f);

    void delete(Long id);

    Funcionario findById(Long id);

    List<Funcionario> findAll();

    List<Funcionario> findByName(String nome);

    List<Funcionario> findByCargo(Long id);

    List<Funcionario> findByDateInAndOut(LocalDate entrada, LocalDate saida);

    List<Funcionario> findByDateIn(LocalDate entrada);

    List<Funcionario> findByDateOut(LocalDate saida);

    PaginacaoUtil<Funcionario> findPaginada(int pagina, String ordenacao);
}
