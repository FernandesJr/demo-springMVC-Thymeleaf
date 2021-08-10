package com.devfer.demomvc.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "CARGOS")
public class Cargo extends AbstractEntity<Long>{

    //Lembrando que, como default a anotação replica o nome do atributo da class para o atributo da tabela também
    //Dexei apenas como exmplo
    @NotBlank(message = "Informe o nome, por favor.")
    @Size(max = 66, message = "O nome deve conter entre {min} e {max} de caracteres.")
    @Column(name = "nome", nullable = false, unique = true, length = 60)
    private String nome;

    //Temos desta forma, um departamento pra N Cargos
    //Cargos n x 1 Departamentos
    @NotNull(message = "Por favor, selecione o departamento relativo ao cargo.")
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