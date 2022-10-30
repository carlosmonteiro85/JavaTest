package br.com.cd2tec.sigabem.api.dto;

import lombok.Data;

@Data
public class EnderecoDTO {
	
	private String cep;
	private String logradouro;
	private String complemento;
	private String bairro;
	private String localidade;
	private String uf;
	private String ddd;
	private String ibge;
	private String siafi;
	private String gia;

}
