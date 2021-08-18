package com.devfer.demomvc.validator;

import com.devfer.demomvc.domain.Funcionario;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

public class FuncionarioValidator implements Validator {
    //Outro exemplo de validação, poderia aqui validar todos os campos do form de cadastro de Funcionario

    @Override
    public boolean supports(Class<?> clazz) {
        //return Funcionario.class.equals(clazz);
        return true;
    }

    @Override
    public void validate(Object object, Errors errors) {

        Funcionario f = (Funcionario) object;
        LocalDate entrada = f.getDataEntrada();
        if(f.getDataSaida() != null){
            if(f.getDataSaida().isBefore(entrada)){
                errors.rejectValue("dataSaida", "PosteriorDataEntrada.funcionario.dataSaida");
                //A mensagem a ser enviada para o front está em messages.properties
                //Default do Validator
            }
        }
    }
}
