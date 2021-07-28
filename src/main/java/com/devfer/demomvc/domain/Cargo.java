package com.devfer.demomvc.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CARGOS")
public class Cargo extends AbstractyEntity<Long>{

    //Lembrando que, como default a anotação replica o nome do atributo da class para o atributo da tabela também
    //Dexei apenas como exmplo
    @Column(name = "nome", nullable = false, unique = true, length = 60)
    private String nome;

    //Temos desta forma, um departamento pra N Cargos
    //Cargos n x 1 Departamentos
    @ManyToOne
    @JoinColumn(name = "id_departamento_fk") //Nome do atributo na entidade, Chave Estrangeira
    private Departamento departamento;

    //Cargo 1 x n Funcionario
    @OneToMany(mappedBy = "cargo")
    private List<Funcionario> funcionarios;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Departamento getDepartamento() { return departamento; }

    public void setDepartamento(Departamento departamento) { this.departamento = departamento; }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

}
