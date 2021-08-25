package com.devfer.demomvc.domain;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "IMAGE")
public class Image extends AbstractEntity<Long>{

    @Lob //representa um campo do tipo blob no bd
    private byte[] imgByte; //armazena arquivo como um byte array

    private String tipo;

    @OneToOne
    @JoinColumn(name = "funcionario_id_fk")
    private Funcionario funcionario;

    public byte[] getImgByte() {
        return imgByte;
    }

    public void setImgByte(byte[] imgByte) {
        this.imgByte = imgByte;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
