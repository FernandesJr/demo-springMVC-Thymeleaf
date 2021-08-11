package com.devfer.demomvc.web.conversor;

import org.springframework.core.convert.converter.Converter;

public class StringToInteger implements Converter<String, Integer> {
    @Override
    public Integer convert(String s) {
        //Método que verifica se a String tem apenas caracteres entre 0-9
        //o + avisa que pode ocorrer de chegar outros caracteres
        if(s.matches("[0-9]+")){
            return Integer.valueOf(s);
        }
        return null;
    }
}
