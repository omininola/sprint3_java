package br.com.fiap.sprint3.controller.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fiap.sprint3.dto.moto.MotoRequest;
import br.com.fiap.sprint3.service.FilialService;
import br.com.fiap.sprint3.service.MotoService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/web/motos")
public class MotoWebController {

    @Autowired
    private MotoService service;

    @Autowired
    private FilialService filialService;

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("filiais", filialService.findAll());
        model.addAttribute("moto", service.fillUserIdByContext());
        return "moto/new";
    }

    @GetMapping("/new/filial/{filialId}")
    public String create(Model model, @PathVariable Long filialId) {
        model.addAttribute("filiais", filialService.findAll());
        model.addAttribute("moto", service.fillUserIdByContext(filialId));
        return "moto/new";
    }

    @PostMapping("/new")
    public String create(@Valid @ModelAttribute("moto") MotoRequest request, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("filiais", filialService.findAll());
            model.addAttribute("moto", request);
            return "moto/new";
        }

        service.save(request);
        return "redirect:/web/motos";
    }

    @GetMapping
    public String readAll(Model model) {
        model.addAttribute("motos", service.findAll());
        return "moto/list";
    }

    @GetMapping("/atualizar/{id}")
    public String update(Model model, @PathVariable Long id) {
        Optional<MotoRequest> moto = service.findRequestById(id);
        if (moto.isEmpty()) return "redirect:/web/motos";

        model.addAttribute("filiais", filialService.findAll());
        model.addAttribute("moto", moto.get());
        model.addAttribute("motoId", id);
        return "moto/update";
    }

    @PostMapping("/atualizar/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute("moto") MotoRequest request, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("filiais", filialService.findAll());
            model.addAttribute("moto", request);
            model.addAttribute("motoId", id);
            return "moto/update";
        }

        service.update(id, request);
        return "redirect:/web/motos";
    }

    @GetMapping("/deletar/{id}")
    public String delete(Model model, @PathVariable Long id) {
        model.addAttribute("motoPlaca", service.findById(id).getPlaca());
        model.addAttribute("motoId", id);
        return "moto/delete";
    }

    @PostMapping("/deletar/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/web/motos";
    }
}
