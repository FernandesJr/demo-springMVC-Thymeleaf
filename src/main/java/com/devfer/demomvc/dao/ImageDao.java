package com.devfer.demomvc.dao;

import com.devfer.demomvc.domain.Image;

import java.util.List;

public interface ImageDao {

    void save(Image i);

    void update(Image i);

    void delete(Long id);

    Image findById(Long id);

    List<Image> findAll();
}
