package br.com.cd2tec.sigabem.api.dto;

import java.math.BigDecimal;

import br.com.cd2tec.sigabem.domain.entity.Cotacao;
import lombok.Data;

@Data
public class EncomendaDTO {

	private BigDecimal peso;
	private String cepOrigem;
	private String cepDestino;
	private String nomeDestinario;

	public Cotacao toCotacao() {
		Cotacao cotacao = new Cotacao();
		cotacao.setCepDestino(cepDestino);
		cotacao.setCepOrigem(cepOrigem);
		cotacao.setNomeDestinatario(nomeDestinario);
		cotacao.setPeso(peso);
		return cotacao;
	}

}
