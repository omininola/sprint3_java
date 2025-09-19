package br.com.fiap.sprint3.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UsuarioRole {
    ADMIN("admin"),
    USER("user");

    private String role;
}
