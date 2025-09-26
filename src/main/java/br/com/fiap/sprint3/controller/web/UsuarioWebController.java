package br.com.fiap.sprint3.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fiap.sprint3.dto.auth.AuthResponse;
import br.com.fiap.sprint3.dto.usuario.UsuarioLoginRequest;
import br.com.fiap.sprint3.dto.usuario.UsuarioRequest;
import br.com.fiap.sprint3.dto.usuario.UsuarioResponse;
import br.com.fiap.sprint3.entity.Usuario;
import br.com.fiap.sprint3.service.UsuarioService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/web/usuarios")
public class UsuarioWebController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/register")
    public String create(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuario/register";
    }

    @PostMapping("/register")
    public String create(@Valid UsuarioRequest request, HttpServletResponse response) {
        AuthResponse authResponse = service.save(request);
        response.addCookie(service.createCookie(authResponse.getToken()));

        return "redirect:/web/filiais";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuario/login";
    }

    @PostMapping("/login")
    public String login(@Valid UsuarioLoginRequest request, HttpServletResponse response) {
        AuthResponse authResponse = service.login(request);
        response.addCookie(service.createCookie(authResponse.getToken()));

        return "redirect:/web/filiais";
    }

    @GetMapping("/me")
    public String findByContext(Model model) {
        model.addAttribute("usuario", service.findByContext());
        return "usuario/index";
    }

    @GetMapping("/atualizar/{id}")
    public String update(Model model, @PathVariable Long id) {
        UsuarioResponse usuario = service.findById(id);
        UsuarioRequest usuarioRequest = new UsuarioRequest();
        usuarioRequest.setEmail(usuario.getEmail());
        usuarioRequest.setRole(usuario.getRole());

        model.addAttribute("usuario", usuarioRequest);
        model.addAttribute("usuarioId", id);
        return "usuario/update";
    }

    @PostMapping("/atualizar/{id}")
    public String update(@PathVariable Long id, @Valid UsuarioRequest request) {
        service.update(request, id);
        return "redirect:/web/usuarios/me";
    }

    @GetMapping("/deletar/{id}")
    public String delete(Model model, @PathVariable Long id) {
        model.addAttribute("usuarioEmail", service.findById(id).getEmail());
        model.addAttribute("usuarioId", id);
        return "usuario/delete";
    }

    @PostMapping("/deletar/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/web/usuarios/me";
    }
}