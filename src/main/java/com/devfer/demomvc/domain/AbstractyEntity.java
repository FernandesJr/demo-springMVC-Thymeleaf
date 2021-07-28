package com.devfer.demomvc.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

public abstract class AbstractyEntity<ID extends Serializable> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Segundo o professor Identity é apropiado para o MySql
    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractyEntity<?> that = (AbstractyEntity<?>) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "id = " + id;
    }
}
