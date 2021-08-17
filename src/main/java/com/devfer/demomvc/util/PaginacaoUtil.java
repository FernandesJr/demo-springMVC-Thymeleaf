package com.devfer.demomvc.util;

import java.util.List;

public class PaginacaoUtil<T>{

    //Paginação BackEnd

    private int pagina;
    private int tamanho;
    private long totPaginas;
    private List<T> registros;
    private String ordenacao;

    public PaginacaoUtil(int pagina, int tamanho, long totPaginas, List<T> registros, String ordenacao) {
        this.pagina = pagina;
        this.tamanho = tamanho;
        this.totPaginas = totPaginas;
        this.registros = registros;
        this.ordenacao = ordenacao;
    }


    public int getPagina() {
        return pagina;
    }

    public int getTamanho() {
        return tamanho;
    }

    public long getTotPaginas() {
        return totPaginas;
    }

    public List<T> getRegistros() {
        return registros;
    }

    public String getOrdenacao(){return ordenacao;}
}
