package com.devfer.demomvc.domain;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ENDERECOS")
public class Endereco extends AbstractEntity<Long> {

    //Não pode ser null
    @NotNull
    @Size(min = 3, max = 255)
    @Column(nullable = false)
    private String cidade;

    @NotNull
    @Size(min = 3, max = 255)
    @Column(nullable = false)
    private String bairro;

    @NotBlank
    @Size(min = 9, max = 9, message = "{Size.endereco.cep}")
    @Column(nullable = false, length = 9)
    private String cep;

    @Column(nullable = false, length = 5)
    @NotNull(message = "{NotNull.endereco.numero}")
    @Digits(integer = 5, fraction = 1)
    private Integer numero;

    @NotNull(message = "{NotNull.endereco.uf}")
    @Column(nullable = false, length = 2)
    @Enumerated(EnumType.STRING) //Diziando ao DB que vai ser um Enum do tipo String
    private UF uf;

    @NotNull
    @Size(min = 3, max = 255)
    @Column(nullable = false)
    private String logradouro; //Gerar com os valores default

    @Size(max = 255)
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
