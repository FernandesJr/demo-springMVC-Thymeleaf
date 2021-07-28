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

    @Column(nullable = false, columnDefinition = "Decimal(7,2) DEFAULT 0.00")
    private BigDecimal salario;

    @Column(name = "data_entrada", columnDefinition = "DATE", nullable = false)
    private LocalDate DataEntrada;

    @Column(name = "data_saida",columnDefinition = "DATE")
    private LocalDate DataSaida;

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


}
