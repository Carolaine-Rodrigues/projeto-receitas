package com.receitas.domain.controller;


import com.receitas.convert.ConvertMap;
import com.receitas.domain.receita.ReceitaResquestDTO;
import com.receitas.domain.receita.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/receita")
public class ReceitaController {
    @Autowired
   private ReceitaService receitaService;

    @PostMapping("/cadastrar")
    public ResponseEntity salvar(@RequestBody ReceitaResquestDTO dados){
        var receita = receitaService.salvarReceita(dados);
        return ResponseEntity.ok(receita);
    }

    @GetMapping("/lista")
    public ResponseEntity listarReceitas(){
        var receitas = receitaService.listarReceitas();
        return ResponseEntity.ok(receitas);
    }

    @GetMapping("/lista/{id}")
    public ResponseEntity listaReceitaId(@PathVariable Long id){
        var receita = receitaService.ListaReceitaId(id);
        return ResponseEntity.ok(receita);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity atualizarCampos(@PathVariable Long id, @RequestBody ReceitaResquestDTO dados){
        var campos = receitaService.atualizaCampos(id,dados);
        return ResponseEntity.ok(campos);
    }

    @PatchMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarCampoId(@PathVariable Long id, @RequestBody ReceitaResquestDTO dados){
        try {
            Map<String, String> atualizarMap = ConvertMap.convertDTOToMap(dados);
            var atualizarReceita = receitaService.atualizarCampoParcialmente(id,atualizarMap);

            return ResponseEntity.ok().body(atualizarReceita);

        } catch (IllegalAccessException e) {
            return ResponseEntity.status(500).body("Erro ao converter receita para Map: " + e.getMessage());
        }
    }
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity excluir(@PathVariable Long id){
        receitaService.excluirReceitaId(id);
        return ResponseEntity.noContent().build();
    }
}
