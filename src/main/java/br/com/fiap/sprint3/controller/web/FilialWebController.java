package br.com.fiap.sprint3.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fiap.sprint3.dto.filial.FilialRequest;
import br.com.fiap.sprint3.entity.Filial;
import br.com.fiap.sprint3.service.FilialService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/web/filiais")
public class FilialWebController {

    @Autowired
    private FilialService service;

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("filial", new Filial());
        return "filial/new";
    }

    @PostMapping("/new")
    public String create(@Valid @ModelAttribute("filial") FilialRequest request, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("filial", request);
            return "filial/new";
        }

        service.save(request);
        return "redirect:/web/filiais";
    }

    @GetMapping
    public String readAll(Model model) {
        model.addAttribute("filiais", service.findAll());
        return "filial/list";
    }

    @GetMapping("/atualizar/{id}")
    public String update(Model model, @PathVariable Long id) {
        model.addAttribute("filial", service.findById(id));
        model.addAttribute("filialId", id);
        return "filial/update";
    }

    @PostMapping("/atualizar/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute("filial") FilialRequest request, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("filial", request);
            model.addAttribute("filialId", id);
            return "filial/update";
        }

        service.update(id, request);
        return "redirect:/web/filiais";
    }

    @GetMapping("/deletar/{id}")
    public String delete(Model model, @PathVariable Long id) {
        model.addAttribute("filialNome", service.findById(id).getNome());
        model.addAttribute("filialId", id);
        return "filial/delete";
    }

    @PostMapping("/deletar/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/web/filiais";
    }
}
