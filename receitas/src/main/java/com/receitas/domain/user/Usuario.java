package com.receitas.domain.user;

import jakarta.persistence.*;
import lombok.*;
import org.mindrot.jbcrypt.BCrypt;


@Table(name= "tb_usuario")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String senha;

    public Usuario(UsuarioLogin dados){
       this.email = dados.getEmail();
       this.senha = dados.getSenha();
    }

}
