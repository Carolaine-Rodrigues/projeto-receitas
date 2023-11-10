package com.receitas.domain.user;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioLogin(String email, String senha) {
}
