package com.devfer.demomvc.service;

import com.devfer.demomvc.dao.FuncionarioDao;
import com.devfer.demomvc.domain.Funcionario;
import com.devfer.demomvc.domain.Image;
import com.devfer.demomvc.util.PaginacaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service @Transactional
public class FuncionarioServiceImpl implements FuncionarioService{

    //A @Transactional fornece outras configurações baseadas em atributos como de isolamento, comportamento de
    //roll-back, tempo para time-out, entre outras, o roll-back desfaz todas as transações caso alguma exceção aconteça

    @Autowired
    private FuncionarioDao dao;

    @Autowired
    private ImageService imageService;

    @Override
    public void salvar(Funcionario funcionario) {
        dao.save(funcionario);
    }

    @Override
    public void editar(Funcionario funcionario) {
        dao.update(funcionario);
    }

    @Override
    public void excluir(Long id) {
        dao.delete(id);
    }

    //Esse comportamento vai fazer com que não seja aberta uma transação quando o método de consulta for executado,
    //liberando assim, o acesso a tabela em questão para outras operações. Essa prática melhora a performance do banco de dados.

    @Transactional(readOnly = true)
    @Override
    public Funcionario buscarPorId(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Funcionario> buscarTodos() {
        return dao.findAll();
    }

    @Override
    public List<Funcionario> buscarPorNome(String nome) {
        return dao.findByName(nome);
    }

    @Override
    public List<Funcionario> buscarPorCargo(Long id) {
        return dao.findByCargo(id);
    }

    @Override
    public List<Funcionario> buscarPorData(LocalDate entrada, LocalDate saida) {

        if(entrada != null && saida != null){
            if (saida.isBefore(entrada)){
                return null;
            }
            return dao.findByDateInAndOut(entrada, saida);
        }else if(entrada != null){
            return dao.findByDateIn(entrada);
        }else if(saida != null){
            return dao.findByDateOut(saida);
        }
        return new ArrayList();
    }

    @Override
    public PaginacaoUtil<Funcionario> buscaPaginada(int pagina, String ordenacao) {
        return dao.findPaginada(pagina, ordenacao);
    }

    @Override
    public void salvarImg(Funcionario funcionario, MultipartFile img) {

        try {
            funcionario.setId(this.buscarPorNome(funcionario.getNome()).get(0).getId());;
            funcionario.setImg(true);
            this.editar(funcionario);

            Image foto = new Image();
            foto.setImgByte(img.getBytes());
            foto.setTipo(img.getContentType().replace("image/", "."));
            foto.setFuncionario(funcionario);
            imageService.salvar(foto);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
