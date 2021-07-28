package com.devfer.demomvc.domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "FUNCIONARIOS")
public class Funcionario extends AbstractyEntity<Long>{

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
    private BigDecimal salario;

    @Column(name= "data_entrada", nullable = false, columnDefinition = "DATE")
    private LocalDate dataEntrada;

    @Column(name = "data_saida",columnDefinition = "DATE")
    private LocalDate dataSaida;

    //Funcionario n x 1 Cargo
    @ManyToOne()
    @JoinColumn(name = "cargo_id_fk")
    private Cargo cargo; //Esse atributo é o lado forte do relacionamento, por conter a FK

    //Esta sendo mapeado apenas desse lado do relacionamento porque nesse projeto somente
    //Sera pesquisado por funcionario, assim virá o enderenço
    //Caso também tivesse uma busca de Endereços teriamos que mapear na class Endereco também aula 19
    @OneToOne(cascade = CascadeType.ALL) //Quando buscar por Funcionário ele trará por cascata o endereço
    @JoinColumn(name = "endereco_id_fk")
    private Endereco endereco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
