package com.devfer.demomvc.web.controller;

import com.devfer.demomvc.domain.Departamento;
import com.devfer.demomvc.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {

    //Injetar percistência
    @Autowired
    private DepartamentoService service;

    @GetMapping("/cadastrar")
    public String cadastrar(Departamento departamento){
        return"/departamento/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model){
        model.addAttribute("departamentos",service.buscarTodos());
        return "/departamento/lista";
    }

    @PostMapping("/salvar")
    public String salvar(Departamento departamento){
        service.salvar(departamento);
        return "redirect:/departamentos/cadastrar";
    }

    @GetMapping("/preEditar/{id}")
    public String preEditar(@PathVariable Long id, ModelMap model){
        //Recuperando informações do departamento e enviando para onde poderá editar
        model.addAttribute("departamento",service.buscarPorId(id));
        return "departamento/cadastro";
    }

    @PostMapping("/editar")
    public String editar(Departamento departamento){
        service.editar(departamento);
        //O redirect envia destruindo a instância de departamento
        return "redirect:/departamentos/cadastrar";
    }
}
