package com.devfer.demomvc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "DEPARTAMENTOS")
public class Departamento extends AbstractEntity<Long> {


    @NotBlank(message = "Informe um nome, por favor.") //Anotações de validação do formulário de cadastro
    @Size(min = 3, max = 60, message = "O nome deve conter entre {min} e {max} caracteres.")
    @Column(name = "nome", nullable = false, unique = true, length = 60) //Nome único, e não pode ser null
    private String nome;

    //Tenho N Cargos para 1 Departamento, 'direita to esquerda OneToMany'
    @OneToMany(mappedBy = "departamento") //Por ser bi-direcional é necessário informar o lado forte do relacionamento
    private List<Cargo> cargos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Cargo> getCargos() {
        return cargos;
    }

    public void setCargos(List<Cargo> cargos) {
        this.cargos = cargos;
    }
}
