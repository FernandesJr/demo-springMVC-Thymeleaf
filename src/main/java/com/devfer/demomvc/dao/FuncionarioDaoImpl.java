package com.devfer.demomvc.dao;

import com.devfer.demomvc.domain.Funcionario;
import com.devfer.demomvc.util.PaginacaoUtil;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

@Repository
public class FuncionarioDaoImpl extends AbstractDao<Funcionario, Long> implements FuncionarioDao{

    @Override
    public List<Funcionario> findByName(String nome) {
        return this.createQuery("select f from Funcionario f where f.nome like concat('%',?1,'%')", nome);
    }

    @Override
    public List<Funcionario> findByCargo(Long id) {
        return this.createQuery("select f from Funcionario f where f.cargo.id = ?1", id);
    }

    @Override
    public List<Funcionario> findByDateInAndOut(LocalDate entrada, LocalDate saida) {
        //Uma forma diferente de concatenar
        String jpql = new StringBuilder("select f from Funcionario f ")
                .append("where f.dataEntrada >= ?1 and f.dataSaida <= ?2 ")
                .append("order by f.dataEntrada asc")
                .toString();
        return this.createQuery(jpql, entrada, saida);
    }

    @Override
    public List<Funcionario> findByDateIn(LocalDate entrada) {
        String jpql = new StringBuilder("select f from Funcionario f ")
                .append("where f.dataEntrada = ?1 ")
                .append("order by f.dataEntrada asc")
                .toString();
        return this.createQuery(jpql, entrada);
    }

    @Override
    public List<Funcionario> findByDateOut(LocalDate saida) {
        String jpql = new StringBuilder("select f from Funcionario f ")
                .append("where f.dataSaida = ?1 ")
                .append("order by f.dataEntrada asc")
                .toString();
        return this.createQuery(jpql, saida);
    }

    /*
    //Normalmente seria feito a busca desta forma se não tivesse o método createQuery
    @Override
    public List<Funcionario> findByName(String nome) {
        TypedQuery<Funcionario> query = this.getEntityManager()
                .createQuery("select f from Funcionario f where f.nome like :nome", Funcionario.class);
        query.setParameter("nome", nome);
        return query.getResultList();
    }
    */

    //Paginação
    public PaginacaoUtil<Funcionario> findPaginada(int pagina, String ordenacao){
        int tamanho = 5;
        int inicio = (pagina - 1) * tamanho; //1-1=0*5=0, 2-1=1*5=5, 3-1=2*5=10
        List<Funcionario> registros = getEntityManager()
                .createQuery("select f from Funcionario f order by f.nome " + ordenacao, Funcionario.class)
                .setFirstResult(inicio)
                .setMaxResults(tamanho)
                .getResultList();
        long totpag = (countFuncionarios() + (tamanho - 1)) / tamanho; //6+4=10/5=2 17+4=21/5=4,2 como é long arredonda para baixo
        return new PaginacaoUtil<Funcionario>(pagina, tamanho, totpag, registros, ordenacao);
    }

    public long countFuncionarios(){
        return getEntityManager().createQuery("Select count(*) from Funcionario", Long.class).getSingleResult();
    }

}
