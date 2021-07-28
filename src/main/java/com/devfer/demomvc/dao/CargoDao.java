package com.devfer.demomvc.dao;


import com.devfer.demomvc.domain.Cargo;

import java.util.List;

public interface CargoDao {
    void save(Cargo c);

    void update(Cargo c);

    void delete(Long id);

    Cargo findById(Long id);

    List<Cargo> findAll();
}
