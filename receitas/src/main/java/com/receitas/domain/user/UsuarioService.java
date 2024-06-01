package com.receitas.domain.user;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // metado para salvar usuário
    public Usuario salvarUsuario(Usuario dados){

       return usuarioRepository.save(dados);
    }

    //metado para listar todos
    public List<Usuario> listarTodos(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios;
    }
    //metado para listar por id
    public Usuario listarId(Long id){
        var usuario = usuarioRepository.findById(id).get();
        return usuario;
    }
    //metado para atualizar
    public Usuario atualizar(Long id, Map<String,String> dados){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();
            if(dados.containsKey("email")){
                usuario.setEmail(dados.get("email"));
            }
            if(dados.containsKey("senha")){
                usuario.setSenha(dados.get("senha"));
            }
            usuarioRepository.save(usuario);
            return usuario;
        }else{
            throw new EntityNotFoundException();
        }
    }

    //metado para deletar
    public void excluir(Long id){
        Optional<Usuario> excluirUsuario = usuarioRepository.findById(id);

        if(excluirUsuario.isPresent()){
            Usuario usuario = excluirUsuario.get();
            usuarioRepository.delete(usuario);
        }else {
            throw new EntityNotFoundException();
        }
    }

}
