package com.devfer.demomvc.web.conversor;

import com.devfer.demomvc.domain.Cargo;
import com.devfer.demomvc.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToCargo implements Converter<String, Cargo> {

    //Por causa da anotação Component é um Beans
    //Lembrando que ao submenter o Form o Spring Chame essa class de forma automática, e retorna para o Controller.
    @Autowired
    private CargoService service;

    @Override
    public Cargo convert(String s) {
        if(s.isEmpty()){
            return null;
        }
        Long id = Long.valueOf(s);
        return service.buscarPorId(id);
    }
}
