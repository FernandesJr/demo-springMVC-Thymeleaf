package com.devfer.demomvc.web.controller;

import com.devfer.demomvc.domain.Cargo;
import com.devfer.demomvc.domain.Funcionario;
import com.devfer.demomvc.domain.UF;
import com.devfer.demomvc.service.CargoService;
import com.devfer.demomvc.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private CargoService cargoService;

    @GetMapping("/cadastrar")
    public String cadastrar(Funcionario funcionario){
        return"/funcionario/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model){
        model.addAttribute("funcionarios", funcionarioService.buscarTodos());
        return "/funcionario/lista";
    }

    @PostMapping("/salvar")
    public String salvar(Funcionario funcionario, RedirectAttributes attr){
        funcionarioService.salvar(funcionario);
        attr.addFlashAttribute("success","Funcionário cadastrado com sucesso.");
        return "redirect:/funcionarios/cadastrar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable Long id, ModelMap model){
        model.addAttribute("funcionario", this.funcionarioService.buscarPorId(id));
        return "funcionario/cadastro";
    }

    @PostMapping("/editar")
    public String editar(Funcionario funcionario, RedirectAttributes attr){
        this.funcionarioService.editar(funcionario);
        attr.addFlashAttribute("success", "Funcionário editado com sucesso.");
        return "redirect:/funcionarios/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes attr){
        this.funcionarioService.excluir(id);
        attr.addFlashAttribute("success", "Funcionário excluído com sucesso.");
        return "redirect:/funcionarios/listar";
    }

    @ModelAttribute("cargos")
    public List<Cargo> listarCargos(){
        return cargoService.buscarTodos();
    }

    @ModelAttribute("ufs")
    public UF[] listarUFS(){
        return UF.values();
    }
}
