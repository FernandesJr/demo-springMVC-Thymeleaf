package com.devfer.demomvc.web.controller;

import com.devfer.demomvc.domain.Cargo;
import com.devfer.demomvc.domain.Funcionario;
import com.devfer.demomvc.domain.UF;
import com.devfer.demomvc.service.CargoService;
import com.devfer.demomvc.service.FuncionarioService;
import com.devfer.demomvc.util.PaginacaoUtil;
import com.devfer.demomvc.validator.FuncionarioValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private static String caminhoPastaImg = "B:\\ESTUDOS EXT\\SpringBoot\\Udemy\\Thymeleaf\\demo-mvc\\imageFuncionarios\\";

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

    @GetMapping("/listar")
    public String listar(ModelMap model,
                         @RequestParam("page") Optional<Integer> page,
                         @RequestParam("ord") Optional<String> ord){
        int pagina = page.orElse(1);
        String ordenacao = ord.orElse("asc");
        PaginacaoUtil<Funcionario> paginacao = funcionarioService.buscaPaginada(pagina, ordenacao);
        model.addAttribute("pageFuncionario", paginacao);
        model.addAttribute("funcionarios", paginacao.getRegistros());
        model.addAttribute("cargos", cargoService.buscarTodos());
        return "funcionario/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Funcionario funcionario, BindingResult result,
                         RedirectAttributes attr,@RequestParam("file") MultipartFile img){
        if(result.hasErrors()){
            return "funcionario/cadastro";
        }
        funcionarioService.salvar(funcionario);
        long id = funcionarioService.buscarPorNome(funcionario.getNome()).get(0).getId();
        if(!img.isEmpty()){
            try {
                //Salvando img na pasta de arquivos
                String nomeImg = id + img.getContentType().replace("image/", ".");
                byte[] imgBytes = img.getBytes();
                Path caminho = Paths.get(caminhoPastaImg + nomeImg);
                Files.write(caminho,imgBytes);

                //salvando nome dela na db
                funcionario.setImg(nomeImg);
                funcionarioService.salvar(funcionario);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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
        return "redirect:/funcionarios/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes attr){
        this.funcionarioService.excluir(id);
        attr.addFlashAttribute("success", "Funcionário excluído com sucesso.");
        return "redirect:/funcionarios/listar";
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
        if(this.funcionarioService.buscarPorData(entrada, saida) == null){
            model.addAttribute("fail", "A data de demissão não pode ser superior a admissão.");
        }else{
            model.addAttribute("funcionarios", this.funcionarioService.buscarPorData(entrada, saida));
        }
        return "funcionario/lista";
    }

    @GetMapping("/buscarImg/{image}")
    @ResponseBody
    public byte[] buscarImg(@PathVariable("image") String nomeImg) throws IOException {
        if (nomeImg != null){
            File imgFile = new File(caminhoPastaImg + nomeImg);
            byte[] bytes = Files.readAllBytes(imgFile.toPath());
            return bytes;
        }
        return null;
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
