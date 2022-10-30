package br.com.cd2tec.sigabem.domain.service.business;

import java.math.BigDecimal;

import br.com.cd2tec.sigabem.api.dto.EncomendaDTO;
import br.com.cd2tec.sigabem.api.dto.EnderecoDTO;
import br.com.cd2tec.sigabem.domain.entity.Cotacao;

public class CotacaoBusiness {

	private EncomendaDTO encomenda;
	private CalculoFreteInterface calculoFrete;
	private BigDecimal valorKilo;

	public CotacaoBusiness(EncomendaDTO encomenda, EnderecoDTO enderecoOrigem, EnderecoDTO enderecoDestino,
			BigDecimal valorKilo) {
		
		this.encomenda = encomenda;
		this.valorKilo = valorKilo;
		
		if (enderecoOrigem.getDdd().equals(enderecoDestino.getDdd())) {
			calculoFrete = new CalculoFreteMesmoDDD();
		} else if (enderecoOrigem.getUf().equals(enderecoDestino.getUf())) {
			calculoFrete = new CalculoFreteMesmaUF();
		} else {
			calculoFrete = new CalculoFreteSemDesconto();
		}
	}
	
	public Cotacao gerarCotacao() {
		return calculoFrete.gerarCotacao(encomenda, valorKilo);
	}
}
