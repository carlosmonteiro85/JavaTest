package br.com.cd2tec.sigabem.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.cd2tec.sigabem.dto.EncomendaDTO;
import br.com.cd2tec.sigabem.dto.EnderecoDTO;
import br.com.cd2tec.sigabem.entity.Cotacao;
import br.com.cd2tec.sigabem.repository.CotacaoRepository;
import br.com.cd2tec.sigabem.service.business.CotacaoBusiness;

@Service
public class CotacaoService {

	private CotacaoRepository cotacaoRepository;
	private ViaCEPService viaCEPService;

	public CotacaoService(CotacaoRepository cotacaoRepository, ViaCEPService viaCEPService) {
		this.cotacaoRepository = cotacaoRepository;
		this.viaCEPService = viaCEPService;
	}

	public Cotacao gerarCotacaoFrete(EncomendaDTO encomendaDTO) {

		Cotacao cotacao = encomendaDTO.toCotacao();
		EnderecoDTO enderecoOrigem = viaCEPService.getEndereco(cotacao.getCepOrigem());
		EnderecoDTO enderecoDestino = viaCEPService.getEndereco(cotacao.getCepDestino());
		BigDecimal valorKilo = new BigDecimal(1); //O valor do kg pode ser definido 

		CotacaoBusiness cotacaoBusiness = new CotacaoBusiness(encomendaDTO, enderecoOrigem, enderecoDestino, valorKilo);
		cotacao = cotacaoBusiness.gerarCotacao();

		cotacaoRepository.save(cotacao);

		return cotacao;
	}

	public List<Cotacao> findAll(Sort sort) {
		return cotacaoRepository.findAll(sort);
	}

	public void delete(Cotacao cotacao) {
		cotacaoRepository.delete(cotacao);
		
	}

}
