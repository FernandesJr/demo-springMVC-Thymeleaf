package com.devfer.demomvc.service;

import com.devfer.demomvc.domain.Image;

import java.util.List;

public interface ImageService {

    void salvar(Image img);

    void editar(Image img);

    void excluir(Long id);

    Image buscarPorId(Long id);

    List<Image> buscarTodos();

    List<Image> buscarPorByte(byte[] bytes);

    List<Image> buscarPorFuncionario(Long id);
}
