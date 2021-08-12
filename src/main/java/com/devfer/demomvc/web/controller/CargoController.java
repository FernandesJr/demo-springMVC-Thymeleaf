package com.devfer.demomvc.web.controller;

import com.devfer.demomvc.domain.Cargo;
import com.devfer.demomvc.domain.Departamento;
import com.devfer.demomvc.service.CargoService;
import com.devfer.demomvc.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/cargos")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping("/cadastrar")
    public String cadastrar(Cargo cargo){
        return"cargo/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model){
        model.addAttribute("cargos", cargoService.buscarTodos());
        return "cargo/lista";
    }


    @PostMapping("/salvar")
    public String salvar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr){
        //Lembrando..
        //Antes de Salvar o cargo o Spring converte o Id String que vem do form para um Departamento
        //De forma auto, apenas implementei o metódo que faz isso em StringToDepartamento, Ele chama esse class automaticamente
        //Já chega aqui pronto
        if(result.hasErrors()){
            return "cargo/cadastro";
        }
        this.cargoService.salvar(cargo);
        attr.addFlashAttribute("success", "Cargo inserido com sucesso.");
        return "redirect:/cargos/cadastrar";
    }

    //Model geral
    @ModelAttribute("departamentos")
    public List<Departamento> listarDepartamentos(){
        return this.departamentoService.buscarTodos();
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable long id, RedirectAttributes attr){
        attr.addFlashAttribute("cargo", cargoService.buscarPorId(id));
        attr.addFlashAttribute("editar", "editar");
        return "redirect:/cargos/cadastrar";
    }

    @PostMapping("/editar")
    public String editar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr){
        //Lembrando, Valid e o model devem está um ao lado do outro na assinatura do método
        if(result.hasErrors()){
            return "cargo/cadastro";
        }
        cargoService.editar(cargo);
        attr.addFlashAttribute("success", "Cargo editado com sucesso.");
        return "redirect:/cargos/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr){
        if(cargoService.hasFuncionario(id)){
            attr.addFlashAttribute("fail", "Existe funcionário(s) vinculado(s) a esse Cargo.");
        }else{
            cargoService.excluir(id);
            attr.addFlashAttribute("success", "Cargo excluído com sucesso.");
        }
        return "redirect:/cargos/listar";
    }
}
