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
import br.com.fiap.sprint3.entity.Usuario;
import br.com.fiap.sprint3.service.UsuarioService;
import jakarta.servlet.http.Cookie;
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

        Cookie cookie = new Cookie("JWT", authResponse.getToken());
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60); // 1 hour
        response.addCookie(cookie);

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

        Cookie cookie = new Cookie("JWT", authResponse.getToken());
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60); // 1 hour
        response.addCookie(cookie);

        return "redirect:/web/filiais";
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("JWT", null);
        response.addCookie(cookie);
        return "redirect:/web/usuarios/login";
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("usuarios", service.findAll());
        return "usuario/list";
    }

    @GetMapping("/atualizar/{id}")
    public String update(Model model, @PathVariable Long id) {
        model.addAttribute("usuario", service.findById(id));
        model.addAttribute("usuarioId", id);
        return "usuario/update";
    }

    @PostMapping("/atualizar/{id}")
    public String update(@PathVariable Long id, @Valid UsuarioRequest request) {
        service.update(request, id);
        return "redirect:/web/usuarios";
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
        return "redirect:/web/usuarios";
    }
}