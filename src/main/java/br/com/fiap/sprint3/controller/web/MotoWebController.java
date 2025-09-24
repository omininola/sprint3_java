package br.com.fiap.sprint3.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fiap.sprint3.dto.moto.MotoRequest;
import br.com.fiap.sprint3.entity.Usuario;
import br.com.fiap.sprint3.service.MotoService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/web/motos")
public class MotoWebController {

    @Autowired
    private MotoService service;

    @GetMapping("/new")
    public String create(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) auth.getPrincipal();

        MotoRequest moto = new MotoRequest();
        moto.setUsuarioId(usuario.getId());
        
        model.addAttribute("moto", new MotoRequest());
        return "moto/new";
    }

    @GetMapping("/new/filial/{filialId}")
    public String create(Model model, @PathVariable Long filialId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) auth.getPrincipal();

        MotoRequest moto = new MotoRequest();
        moto.setUsuarioId(usuario.getId());
        moto.setFilialId(filialId);

        model.addAttribute("moto", moto);
        return "moto/new";
    }

    @PostMapping("/new")
    public String create(@Valid MotoRequest request) {
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
        model.addAttribute("moto", service.findById(id));
        model.addAttribute("motoId", id);
        return "moto/update";
    }

    @PostMapping("/atualizar/{id}")
    public String update(@PathVariable Long id, @Valid MotoRequest request) {
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
