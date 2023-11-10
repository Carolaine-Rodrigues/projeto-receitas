package com.receitas.domain.receita;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "receita")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String imagem;
    private String modoPreparo;
    private String descricao;

    public Receita(ReceitaResquestDTO dados){
        this.titulo = dados.titulo();
        this.imagem = dados.imagem();
        this.modoPreparo = dados.modoPreparo();
        this.descricao = dados.descricao();
    }

}
