package com.devfer.demomvc.web.conversor;

import com.devfer.demomvc.domain.Departamento;
import com.devfer.demomvc.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToDepartamento implements Converter<String, Departamento> {

    @Autowired
    private DepartamentoService service;

    @Override
    public Departamento convert(String text) {
        if(text.isEmpty()){
            return null;
        }
        //Buscar o departamento por Id
        Long id = Long.valueOf(text);
        return service.buscarPorId(id);
    }
}
