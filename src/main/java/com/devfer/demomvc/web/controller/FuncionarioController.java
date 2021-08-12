package com.devfer.demomvc.web.controller;

import com.devfer.demomvc.domain.Cargo;
import com.devfer.demomvc.domain.Funcionario;
import com.devfer.demomvc.domain.UF;
import com.devfer.demomvc.service.CargoService;
import com.devfer.demomvc.service.FuncionarioService;
import com.devfer.demomvc.validator.FuncionarioValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private CargoService cargoService;

    //Primeiro método a ser execultado ao receber requisição
    @InitBinder
    public void initBindier(WebDataBinder binder){
        //Chamando o validator
        binder.addValidators(new FuncionarioValidator());
    }

    @GetMapping("/cadastrar")
    public String cadastrar(Funcionario funcionario){
        return"funcionario/cadastro";
    }

    @GetMapping
    public String listar(ModelMap model){
        model.addAttribute("funcionarios", funcionarioService.buscarTodos());
        model.addAttribute("cargos", cargoService.buscarTodos());
        return "funcionario/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attr){
        if(result.hasErrors()){
            return "funcionario/cadastro";
        }
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
    public String editar(@Valid Funcionario funcionario, BindingResult result ,RedirectAttributes attr){
        if(result.hasErrors()){
            return "funcionario/cadastro";
        }
        this.funcionarioService.editar(funcionario);
        attr.addFlashAttribute("success", "Funcionário editado com sucesso.");
        return "redirect:/funcionarios";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes attr){
        this.funcionarioService.excluir(id);
        attr.addFlashAttribute("success", "Funcionário excluído com sucesso.");
        return "redirect:/funcionarios";
    }

    @GetMapping("/buscar/nome")
    public String buscarNome(@RequestParam String nome, ModelMap model){
        model.addAttribute("funcionarios", this.funcionarioService.buscarPorNome(nome));
        return "funcionario/lista";
    }

    @GetMapping("/buscar/cargo")
    public String buscarCargo(@RequestParam Long id, ModelMap model){
        model.addAttribute("funcionarios", funcionarioService.buscarPorCargo(id));
        return "funcionario/lista";
    }

    @GetMapping("/buscar/data")
    public String buscarData(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                 @RequestParam(name = "entrada", required = false) LocalDate entrada ,
                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                             @RequestParam(name = "saida", required = false) LocalDate saida,
                             ModelMap model){
        model.addAttribute("funcionarios", this.funcionarioService.buscarPorData(entrada, saida));
        return "funcionario/lista";
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
