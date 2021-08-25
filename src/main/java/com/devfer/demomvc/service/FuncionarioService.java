package com.devfer.demomvc.service;


import com.devfer.demomvc.domain.Funcionario;
import com.devfer.demomvc.util.PaginacaoUtil;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface FuncionarioService {
    void salvar(Funcionario funcionario);

    void editar(Funcionario funcionario);

    void excluir(Long id);

    Funcionario buscarPorId(Long id);

    List<Funcionario> buscarTodos();

    List<Funcionario> buscarPorNome(String nome);

    List<Funcionario> buscarPorCargo(Long id);

    List<Funcionario> buscarPorData(LocalDate entrada, LocalDate saida);

    PaginacaoUtil<Funcionario> buscaPaginada(int pagina, String ordenacao);

    void salvarImg(Funcionario funcionario, MultipartFile img);

    void editarFoto(Long id, MultipartFile img);
}
