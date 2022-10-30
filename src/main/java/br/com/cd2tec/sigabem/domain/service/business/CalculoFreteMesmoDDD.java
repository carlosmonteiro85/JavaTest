package br.com.cd2tec.sigabem.domain.service.business;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.cd2tec.sigabem.api.dto.EncomendaDTO;
import br.com.cd2tec.sigabem.domain.entity.Cotacao;

public class CalculoFreteMesmoDDD implements CalculoFreteInterface {

	private static Integer UM_DIA = 1;

	@Override
	public Cotacao gerarCotacao(EncomendaDTO encomendaDTO, BigDecimal valorPorKilo) {
		Cotacao cotacao = encomendaDTO.toCotacao();

		BigDecimal valorBrutoFrete = encomendaDTO.getPeso().multiply(valorPorKilo);

		cotacao.setDataConsulta(LocalDate.now());
		LocalDate dataPrevistaEntrega = cotacao.getDataConsulta().plusDays(UM_DIA);
		cotacao.setDataPrevistaEntrega(dataPrevistaEntrega);

		cotacao.setVlTotalFrete(desconto50Porcento(valorBrutoFrete));
		return cotacao;
	}

	private BigDecimal desconto50Porcento(BigDecimal valorBrutoFrete) {
		return valorBrutoFrete.divide(new BigDecimal(2));
	}

}
