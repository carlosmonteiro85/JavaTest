package br.com.cd2tec.sigabem;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.cd2tec.sigabem.dto.EncomendaDTO;
import br.com.cd2tec.sigabem.dto.EnderecoDTO;
import br.com.cd2tec.sigabem.entity.Cotacao;
import br.com.cd2tec.sigabem.service.CotacaoService;
import br.com.cd2tec.sigabem.service.ViaCEPService;

@SpringBootTest
public class SigaBemApplicationTests {

	@Autowired
	private CotacaoService service;
	@Autowired
	private ViaCEPService viaCEPService;

	private static BigDecimal PESO_ENCOMENDA = new BigDecimal(10);
	private static BigDecimal VALOR_FRETE_MESMO_DDD = new BigDecimal(5.0);
	private static BigDecimal VALOR_FRETE_SEM_DESCONTO = new BigDecimal(10);

	EncomendaDTO encomenda;
	Cotacao cotacao;
	LocalDate dataEntregaComparada;
	EnderecoDTO enderecoDestino;
	EnderecoDTO enderecoOrigem;

	@BeforeEach
	public void criarEncomendaParaTeste() {
		encomenda = new EncomendaDTO();
		encomenda.setCepDestino("72231422");
		encomenda.setCepOrigem("72231804");
		encomenda.setNomeDestinario("Pessoa Teste");
		encomenda.setPeso(PESO_ENCOMENDA);
		enderecoOrigem = viaCEPService.getEndereco(encomenda.getCepOrigem());
		enderecoDestino = viaCEPService.getEndereco(encomenda.getCepDestino());
	}

	@Test
	public void testeCepComMesmo_DDD() {
		cotacao = service.gerarCotacaoFrete(encomenda);
		dataEntregaComparada = cotacao.getDataConsulta().plusDays(1);
		assertTrue(cotacao.getDataPrevistaEntrega().equals(dataEntregaComparada));
		assertTrue(cotacao.getVlTotalFrete().equals(VALOR_FRETE_MESMO_DDD));
		assertTrue(enderecoOrigem.getDdd().equals(enderecoDestino.getDdd()));
	}

	@Test
	public void testeCepComMesmo_estado() {
		encomenda.setCepOrigem("01009000"); // SAO PAULO - SP
		encomenda.setCepDestino("13015904");// CAMPINAS SP - SP
		cotacao = service.gerarCotacaoFrete(encomenda);
		dataEntregaComparada = cotacao.getDataConsulta().plusDays(3);
		assertTrue(cotacao.getDataPrevistaEntrega().equals(dataEntregaComparada));
		assertTrue(enderecoOrigem.getDdd() != enderecoDestino.getDdd());
		assertTrue(enderecoOrigem.getLocalidade().equals(enderecoDestino.getLocalidade()));
	}

	@Test
	public void testeCepComOutro_estado() {
		encomenda.setCepOrigem("72231804");
		encomenda.setCepDestino("01009907");
		cotacao = service.gerarCotacaoFrete(encomenda);
		dataEntregaComparada = cotacao.getDataConsulta().plusDays(10);
		assertTrue(cotacao.getDataPrevistaEntrega().equals(dataEntregaComparada));
		assertTrue(cotacao.getVlTotalFrete().equals(VALOR_FRETE_SEM_DESCONTO));
		assertTrue(enderecoOrigem.getLocalidade() != enderecoDestino.getLocalidade());
	}

	@AfterEach
	public void deletarCotacaoDeTeste() {
		service.delete(cotacao);
	}

}
