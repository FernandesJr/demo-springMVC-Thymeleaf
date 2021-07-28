package com.devfer.demomvc.domain;

import javax.persistence.*;

@Entity
@Table(name = "ENDERECOS")
public class Endereco extends AbstractyEntity<Long>{

    //Não pode ser null
    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false, length = 9)
    private String cep;

    @Column(nullable = false, length = 5)
    private Integer numero;

    @Column(nullable = false, length = 2)
    @Enumerated(EnumType.STRING) //Diziando ao DB que vai ser um Enum do tipo String
    private UF uf;

    @Column(nullable = false)
    private String logradouro; //Gerar com os valores default

    private String complemento;

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public UF getUf() {
        return uf;
    }

    public void setUf(UF uf) {
        this.uf = uf;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
