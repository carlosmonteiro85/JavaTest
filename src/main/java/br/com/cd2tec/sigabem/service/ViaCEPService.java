package br.com.cd2tec.sigabem.service;

import java.net.URL;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cd2tec.sigabem.dto.EnderecoDTO;

@Service
public class ViaCEPService {

	private static final String END_POINT_VIA_CEP = "https://viacep.com.br/ws/%s/json/";

	public EnderecoDTO getEndereco(String cep) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readValue(new URL(String.format(END_POINT_VIA_CEP, cep)), EnderecoDTO.class);
		} catch (Exception e) {
			//TODO Colocar isto na documentação do swagger quando for consumir a api
			throw new IllegalStateException(String.format("Foi gerado o seguinte erro %s ao consultar o CEP %s", e.getMessage(), cep));
		}
	}

}
