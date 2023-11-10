package com.receitas.domain.user;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private String email;
    private String senhaHash;
    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository){
        this.repository = repository;
    }

    public String getEmail() {
        return email;
    }
    public Boolean verifaSenha(String senha){
        return BCrypt.checkpw(senha, senhaHash);
    }

    public String hashSenha(String senha){
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(senha, salt);
    }

    public Boolean atenticarUsuario(String email, String senhaHash){
        Usuario usuario = repository.findByEmail(email);
        return usuario !=null && usuario.getSenhaHash().equals(senhaHash);
    }
}
