package com.receitas.domain.controller;

import com.receitas.domain.user.Usuario;
import com.receitas.domain.user.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;



@RestController
@RequestMapping("/login")
public class LoginController {

    private final PasswordEncoder encoder;
    @Autowired
    private UsuarioService usuarioService;

    public LoginController(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity salvar(@RequestBody Usuario dados){

        dados.setSenha(encoder.encode(dados.getSenha()));

        var usuarioSalvo = usuarioService.salvarUsuario(dados);
        return ResponseEntity.ok().body(usuarioSalvo);
    }

    @GetMapping("/usuarios")
    public ResponseEntity listarUsuarios(){
        var usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok().body(usuarios);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity listarId(@PathVariable Long id){
        var usuario = usuarioService.listarId(id);
        return ResponseEntity.ok().body(usuario);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity excluirId(@PathVariable Long id){
        usuarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/atualizar/{id}")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody Usuario dados){
        var atualizarUsuario = usuarioService.atualizar(id,(Map<String, String>) dados);
        return ResponseEntity.ok().body(atualizarUsuario);
    }
}

