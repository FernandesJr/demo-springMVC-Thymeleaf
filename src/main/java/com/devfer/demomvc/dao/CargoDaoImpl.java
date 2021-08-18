package com.devfer.demomvc.dao;

import com.devfer.demomvc.domain.Cargo;
import com.devfer.demomvc.util.PaginacaoUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CargoDaoImpl extends AbstractDao<Cargo, Long> implements CargoDao{

    public PaginacaoUtil<Cargo> buscaPaginada(int pagina, String ordenacao){
        int tamanho = 5;
        int inicio = (pagina - 1) * tamanho; // pagina 1 - 1 = 0 * 5 = 0 || pagina 2 - 1 = 1 * 5 = 5 inicio
        List<Cargo> cargos = this.getEntityManager()
                .createQuery("select c from Cargo c order by c.nome " + ordenacao, Cargo.class)
                .setFirstResult(inicio)
                .setMaxResults(tamanho)
                .getResultList();
        long totalRegistros = count();
        long totalPaginas = (totalRegistros + (tamanho - 1)) / tamanho; //Para que sempre arredondar o divisão para cima
        return new PaginacaoUtil<Cargo>(pagina,tamanho, totalPaginas, cargos, ordenacao);
    }

    public long count(){
        return this.getEntityManager()
                .createQuery("select count(*) from Cargo", Long.class)
                .getSingleResult();
    }
}
