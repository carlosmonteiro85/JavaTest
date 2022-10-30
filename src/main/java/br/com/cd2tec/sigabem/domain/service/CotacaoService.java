package br.com.cd2tec.sigabem.domain.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.cd2tec.sigabem.api.dto.EncomendaDTO;
import br.com.cd2tec.sigabem.api.dto.EnderecoDTO;
import br.com.cd2tec.sigabem.domain.entity.Cotacao;
import br.com.cd2tec.sigabem.domain.repository.CotacaoRepository;
import br.com.cd2tec.sigabem.domain.service.business.CotacaoBusiness;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CotacaoService {

	private final CotacaoRepository cotacaoRepository;
	private final ViaCEPService viaCEPService;

	public Cotacao gerarCotacaoFrete(EncomendaDTO encomendaDTO) {

		Cotacao cotacao = encomendaDTO.toCotacao();
		EnderecoDTO enderecoOrigem = viaCEPService.getEndereco(cotacao.getCepOrigem());
		EnderecoDTO enderecoDestino = viaCEPService.getEndereco(cotacao.getCepDestino());
		BigDecimal valorKilo = new BigDecimal(1); 

		CotacaoBusiness cotacaoBusiness = new CotacaoBusiness(encomendaDTO, enderecoOrigem, enderecoDestino, valorKilo);
		cotacao = cotacaoBusiness.gerarCotacao();

		cotacaoRepository.save(cotacao);

		return cotacao;
	}

	public List<Cotacao> findAll() {
		return cotacaoRepository.findAll();
	}

	public void delete(Cotacao cotacao) {
		cotacaoRepository.delete(cotacao);
	}
}
