package com.receitas.domain.controller;


import com.receitas.domain.receita.Receita;
import com.receitas.domain.receita.ReceitaRepository;
import com.receitas.domain.receita.ReceitaResquestDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/receita")
public class ReceitaController {
    @Autowired
    private ReceitaRepository repository;

    @PostMapping(value = "/cadastar")
    public ResponseEntity cadastraReceita(@RequestBody ReceitaResquestDTO dados){
        Receita receitaDados = new Receita(dados);
        repository.save(receitaDados);
        return ResponseEntity.ok().body(receitaDados);
    }

    @PutMapping(value = "/atualizar/{id}")
    public ResponseEntity atualizarReceita(@PathVariable Long id, @RequestBody ReceitaResquestDTO atualizaDados) {
        Optional<Receita> optionalReceita = repository.findById(id);

        if (optionalReceita.isPresent()) {
            Receita receitaAtualizada = optionalReceita.get();

            if (atualizaDados.titulo() != null) {
                receitaAtualizada.setTitulo(atualizaDados.titulo());
            }
            if (atualizaDados.imagem() != null) {
                receitaAtualizada.setImagem(atualizaDados.imagem());
            }
            if (atualizaDados.modoPreparo() != null) {
                receitaAtualizada.setModoPreparo(atualizaDados.modoPreparo());
            }
            if (atualizaDados.descricao() != null) {
                receitaAtualizada.setDescricao(atualizaDados.descricao());
            }

            repository.save(receitaAtualizada);
            return ResponseEntity.ok(receitaAtualizada);
        } else {
            throw new EntityNotFoundException();
        }
    }


    @GetMapping(value= "/lista")
    public ResponseEntity<List<Receita>> listaTodasReceitas(){
        List<Receita> lista = repository.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping(value = "/lista/{id}")
    public ResponseEntity<Receita> listaReceitaId(@PathVariable Long id){
        Receita receita = repository.findById(id).get();
        return ResponseEntity.ok(receita);
    }

    @DeleteMapping(value= "excluir/{id}")
    public ResponseEntity excluirReceita(@PathVariable Long id){
        Optional<Receita> excluiReceita = repository.findById(id);
        if(excluiReceita.isPresent()){
            Receita receita = excluiReceita.get();
            repository.delete(receita);
            return ResponseEntity.noContent().build();
        } else {
            // Lançar exceção ou lidar com o caso em que a receita não é encontrada
            throw new EntityNotFoundException();
        }
    }
}
