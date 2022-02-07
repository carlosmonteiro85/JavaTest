package br.com.cd2tec.sigabem.service.business;

import java.math.BigDecimal;

import br.com.cd2tec.sigabem.dto.EncomendaDTO;
import br.com.cd2tec.sigabem.entity.Cotacao;

public interface CalculoFreteInterface {
	
	public Cotacao gerarCotacao(EncomendaDTO encomendaDTO , BigDecimal valorPorKilo);

}
