package com.receitas.domain.controller;

import com.receitas.domain.user.Usuario;
import com.receitas.domain.user.UsuarioRegistro;
import com.receitas.domain.user.UsuarioRepository;
import com.receitas.domain.user.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value= "/login")
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping(value= "/cadastro/usuario")
    public ResponseEntity cadastraLogin(@RequestBody UsuarioRegistro usuarioRegistro){
        Usuario usuario = new Usuario();
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping(value= "/usuario/autenticado")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
            if (usuarioService.atenticarUsuario(usuario.getEmail(), usuario.getSenhaHash())) {
                return ResponseEntity.ok("Login bem-sucedido");
            }
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }
    }

