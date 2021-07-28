package com.devfer.demomvc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "DEPARTAMENTOS")
public class Departamento extends AbstractyEntity<Long>{

    //Nome único, e não pode ser null
    @Column(name = "nome", unique = true, nullable = false)
    private String nome;

    //Tenho N Cargos para 1 Departamento, 'direita to esquerda OneToMany'
    @OneToMany(mappedBy = "departamento") //Por ser bi-direcional é necessário informar o lado forte do relacionamento
    private List<Cargo> cargos;

}
