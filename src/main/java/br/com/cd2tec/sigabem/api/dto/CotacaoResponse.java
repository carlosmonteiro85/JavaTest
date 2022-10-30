package br.com.cd2tec.sigabem.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.cd2tec.sigabem.core.config.moneySerializer.MoneySerializer;
import lombok.Data;

@Data
public class CotacaoResponse {
	private BigDecimal peso;
	private String cepOrigem;
	private String cepDestino;
	private String nomeDestinatario;
    @JsonSerialize(using = MoneySerializer.class)
	private BigDecimal vlTotalFrete;
	private LocalDate dataPrevistaEntrega;
	private LocalDate dataConsulta;
}