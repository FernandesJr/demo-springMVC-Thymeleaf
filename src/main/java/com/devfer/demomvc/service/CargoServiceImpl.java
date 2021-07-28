package com.devfer.demomvc.service;

import com.devfer.demomvc.dao.CargoDao;
import com.devfer.demomvc.dao.CargoDaoImpl;
import com.devfer.demomvc.domain.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false) //Falso significa que a transação bloqueia o acesso a tabela em uso. ninguém mexe até terminar
public class CargoServiceImpl implements CargoService{

    @Autowired
    private  CargoDao dao; //Uma interface, pensei que seria usado o CargoDaoImpl os métodos estão implementados

    @Override
    public void salvar(Cargo cargo) {
        dao.save(cargo);
    }

    @Override
    public void editar(Cargo cargo) {
        dao.update(cargo);
    }

    @Override
    public void excluir(Long id) {
        dao.delete(id);
    }

    @Override @Transactional(readOnly = true)
    public Cargo buscarPorId(Long id) {
        return dao.findById(id);
    }

    @Override @Transactional(readOnly = true)
    public List<Cargo> buscarTodos() {
        return dao.findAll();
    }
}
