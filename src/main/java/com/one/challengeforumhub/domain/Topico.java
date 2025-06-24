package com.one.challengeforumhub.domain;

import com.one.challengeforumhub.dto.CriarTopicoRequestDto;
import com.one.challengeforumhub.enums.EnumEstado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "topico")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String mensagem;

    @CreationTimestamp
    @Column(name = "data_criacao", insertable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    private EnumEstado estado;

    private String autor;

    private String curso;

    public Topico(CriarTopicoRequestDto dto) {
        this.titulo = dto.titulo();
        this.mensagem = dto.mensagem();
        this.estado = EnumEstado.ABERTO;
        this.autor = dto.autor();
        this.curso = dto.curso();
    }
}
