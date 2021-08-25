package com.devfer.demomvc.service;

import com.devfer.demomvc.dao.ImageDao;
import com.devfer.demomvc.domain.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService{

    @Autowired
    ImageDao dao;

    @Override
    public void salvar(Image img) {
        dao.save(img);
    }

    @Override
    public void editar(Image img) {
        dao.update(img);
    }

    @Override
    public void excluir(Long id) {
        dao.delete(id);
    }

    @Override
    public Image buscarPorId(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Image> buscarTodos() {
        return dao.findAll();
    }

    @Override
    public List<Image> buscarPorByte(byte[] bytes) {
        return dao.findByBytes(bytes);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Image> buscarPorFuncionario(Long id) {
        return dao.findByFuncionario(id);
    }

}
