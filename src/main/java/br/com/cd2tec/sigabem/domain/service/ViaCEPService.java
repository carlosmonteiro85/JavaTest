package br.com.cd2tec.sigabem.domain.service;

import java.net.URL;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cd2tec.sigabem.api.dto.EnderecoDTO;
import br.com.cd2tec.sigabem.exceptions.NegocioException;

@Service
public class ViaCEPService {

	private static final String END_POINT_VIA_CEP = "https://viacep.com.br/ws/%s/json/";

	public EnderecoDTO getEndereco(String cep) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readValue(new URL(String.format(END_POINT_VIA_CEP, cep)), EnderecoDTO.class);
		} catch (Exception e) {
			throw new NegocioException(String.format("Foi gerado o seguinte erro %s ao consultar o CEP %s", e.getMessage(), cep));
		}
	}

}
