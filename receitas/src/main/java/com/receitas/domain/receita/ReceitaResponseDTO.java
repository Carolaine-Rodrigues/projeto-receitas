package com.receitas.domain.receita;

public record ReceitaResponseDTO(Long id, String titulo, String imagem, String modoPreparo, String descricao) {

    public ReceitaResponseDTO(Receita receita){
        this(receita.getId(), receita.getTitulo(), receita.getImagem(), receita.getModoPreparo(),receita.getDescricao());
    }
}
