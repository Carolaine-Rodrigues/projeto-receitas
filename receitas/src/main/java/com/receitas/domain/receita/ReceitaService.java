package com.receitas.domain.receita;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ReceitaService {
    @Autowired
    private ReceitaRepository receitaRepository;


    public Receita salvarReceita(ReceitaResquestDTO dados){
        var cadastrarReceita = new Receita(dados);
        receitaRepository.save(cadastrarReceita);
        return cadastrarReceita;
    }

    public List<Receita> listarReceitas(){
        List<Receita> lista = receitaRepository.findAll();
        return lista;
    }

    public Receita ListaReceitaId(Long id){
        var receitaId = receitaRepository.findById(id).get();
        return receitaId;
    }

    public Receita atualizaCampos(Long id, ReceitaResquestDTO dados){
        Optional<Receita> receitaOptional = receitaRepository.findById(id);

        if(receitaOptional.isPresent()){
            Receita receitaAtualiza = receitaOptional.get();
            if(dados.titulo()!= null){
                receitaAtualiza.setTitulo(dados.titulo());
            }
            if(dados.imagem()!= null){
                receitaAtualiza.setImagem(dados.imagem());
            }
            if(dados.modoPreparo()!= null){
                receitaAtualiza.setModoPreparo(dados.modoPreparo());
            }
            if(dados.descricao()!= null){
                receitaAtualiza.setDescricao(dados.descricao());
            }
           return receitaRepository.save(receitaAtualiza);
        }else {
            throw new EntityNotFoundException();
        }
    }

    public Receita atualizarCampoParcialmente(Long id, Map<String, String> dados){
        Optional<Receita> optionalReceita = receitaRepository.findById(id);
        if(optionalReceita.isPresent()){
            Receita receita = optionalReceita.get();
            for(Map.Entry<String, String> valorReceita : dados.entrySet()){
                String chave = valorReceita.getKey();
                String valor = valorReceita.getValue();
                if("titulo".equals(chave)  && valor instanceof String){
                    receita.setTitulo(valor);
                }
                if("imagem".equals(chave)  && valor instanceof String){
                    receita.setImagem(valor);
                }
                if("modoPreparo".equals(chave)  && valor instanceof String){
                    receita.setModoPreparo(valor);
                }
                if("descricao".equals(chave)  && valor instanceof String){
                    receita.setDescricao(valor);
                }
            }
            receitaRepository.save(receita);
            return receita;
        } else {
            throw new EntityNotFoundException();
        }
    }

    public void excluirReceitaId(Long id){
        Optional<Receita> excuirReceita = receitaRepository.findById(id);
        if(excuirReceita.isPresent()){
            Receita excluir = excuirReceita.get();
            receitaRepository.delete(excluir);
        } else {
            throw new EntityNotFoundException();
        }
    }

}
