package br.com.cd2tec.sigabem.domain.service.business;

import java.math.BigDecimal;

import br.com.cd2tec.sigabem.api.dto.EncomendaDTO;
import br.com.cd2tec.sigabem.domain.entity.Cotacao;

public interface CalculoFreteInterface {
	
	public Cotacao gerarCotacao(EncomendaDTO encomendaDTO , BigDecimal valorPorKilo);

}
