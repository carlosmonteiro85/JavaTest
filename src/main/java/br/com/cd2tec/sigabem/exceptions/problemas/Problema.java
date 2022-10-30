package br.com.cd2tec.sigabem.exceptions.problemas;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class Problema {

    private Integer status;
    private String tipo;
    private String titulo;
    private String detalhe;
    private String menssagemUsuario;
    private LocalDate data;

    private List<Objecto> objects;

    @Builder
    @Getter
    @Setter
    public static class Objecto {
        private String nome;
        private String menssagemUsuario;
    }
}
