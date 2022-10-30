package br.com.cd2tec.sigabem.domain.service.business;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.cd2tec.sigabem.api.dto.EncomendaDTO;
import br.com.cd2tec.sigabem.domain.entity.Cotacao;

public class CalculoFreteMesmaUF implements CalculoFreteInterface {

	private static Integer TRES_DIAS = 3;

	@Override
	public Cotacao gerarCotacao(EncomendaDTO encomendaDTO, BigDecimal valorPorKilo) {
		Cotacao cotacao = encomendaDTO.toCotacao();

		BigDecimal valorBrutoFrete = encomendaDTO.getPeso().multiply(valorPorKilo);

		cotacao.setDataConsulta(LocalDate.now());
		LocalDate dataPrevistaEntrega = cotacao.getDataConsulta().plusDays(TRES_DIAS);
		cotacao.setDataPrevistaEntrega(dataPrevistaEntrega);

		cotacao.setVlTotalFrete(desconto75Porcento(valorBrutoFrete));
		return cotacao;
	}

	private BigDecimal desconto75Porcento(BigDecimal valorBrutoFrete) {
		return valorBrutoFrete.subtract(valorBrutoFrete.multiply(new BigDecimal(0.75)));
	}

}
