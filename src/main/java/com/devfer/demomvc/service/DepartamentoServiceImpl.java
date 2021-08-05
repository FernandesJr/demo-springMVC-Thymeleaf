package com.devfer.demomvc.service;

import com.devfer.demomvc.dao.DepartamentoDao;
import com.devfer.demomvc.domain.Departamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @Transactional(readOnly = false)
public class DepartamentoServiceImpl implements DepartamentoService{

    @Autowired
    private DepartamentoDao dao;

    @Override
    public void salvar(Departamento departamento) {
        dao.save(departamento);
    }

    @Override
    public void editar(Departamento departamento) {
        dao.update(departamento);
    }

    @Override
    public void excluir(Long id) {
        dao.delete(id);
    }

    //Não precisa abrir uma transação já que esta apenas lendo

    @Transactional(readOnly = true)
    @Override
    public Departamento buscarPorId(Long id) {
        return dao.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Departamento> buscarTodos() {
        return dao.findAll();
    }

    @Override
    public boolean temCargo(Long id) {
        if(this.buscarPorId(id).getCargos().isEmpty()){
            return false;
        }
        return true;
    }
}
