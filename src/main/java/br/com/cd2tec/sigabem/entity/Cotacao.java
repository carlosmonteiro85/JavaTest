package br.com.cd2tec.sigabem.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.cd2tec.sigabem.config.MoneySerializer;
import lombok.Data;

@Entity
@Table(name = "COTACOES")
@Data
public class Cotacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	private BigDecimal peso;
	
	@Column(name = "CEP_ORIGEM",  length = 8)
	private String cepOrigem;

	@Column(length = 8)
	private String cepDestino;
	private String nomeDestinatario;
	
    @JsonSerialize(using = MoneySerializer.class)
	private BigDecimal vlTotalFrete;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dataPrevistaEntrega;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dataConsulta;
}