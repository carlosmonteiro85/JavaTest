package br.com.cd2tec.sigabem.service.business;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.cd2tec.sigabem.dto.EncomendaDTO;
import br.com.cd2tec.sigabem.entity.Cotacao;

public class CalculoFreteSemDesconto implements CalculoFreteInterface {

	private static Integer DEZ_DIAS = 10;

	@Override
	public Cotacao gerarCotacao(EncomendaDTO encomendaDTO, BigDecimal valorPorKilo) {
		Cotacao cotacao = encomendaDTO.toCotacao();

		BigDecimal valorBrutoFrete = encomendaDTO.getPeso().multiply(valorPorKilo);

		cotacao.setDataConsulta(LocalDate.now());
		LocalDate dataPrevistaEntrega = cotacao.getDataConsulta().plusDays(DEZ_DIAS);
		cotacao.setDataPrevistaEntrega(dataPrevistaEntrega);

		cotacao.setVlTotalFrete(valorBrutoFrete);
		return cotacao;
	}
}
