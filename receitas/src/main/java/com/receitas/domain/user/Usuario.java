package com.receitas.domain.user;

import jakarta.persistence.*;
import lombok.*;
import org.mindrot.jbcrypt.BCrypt;


@Table(name= "usuario")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String senhaHash;

    public Usuario(UsuarioRegistro dados){
       this.email = dados.email();
       this.senhaHash = dados.senha();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public Boolean verifaSenha(String senha){
        return BCrypt.checkpw(senha, senhaHash);
    }

    public String hashSenha(String senha){
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(senha, salt);
    }
}
