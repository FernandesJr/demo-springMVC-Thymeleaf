package com.devfer.demomvc.dao;

import com.devfer.demomvc.domain.Image;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImageDaoImpl extends AbstractDao<Image, Long> implements ImageDao{
    @Override
    public List<Image> findByBytes(byte[] bytes) {
        String jpql = "select i from Image i where i.imgByte = ?1";
        return createQuery(jpql, bytes);
    }

    @Override
    public List<Image> findByFuncionario(Long id) {
        String jpql = "select i from Image i where i.funcionario.id = ?1";
        return createQuery(jpql, id);
    }
}
