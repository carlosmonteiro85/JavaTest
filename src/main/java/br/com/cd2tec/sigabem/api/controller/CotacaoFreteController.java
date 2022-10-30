package br.com.cd2tec.sigabem.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cd2tec.sigabem.api.assembler.CotacaoAssembler;
import br.com.cd2tec.sigabem.api.dto.CotacaoResponse;
import br.com.cd2tec.sigabem.api.dto.EncomendaDTO;
import br.com.cd2tec.sigabem.domain.entity.Cotacao;
import br.com.cd2tec.sigabem.domain.service.CotacaoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("cotacao-frete")
@RequiredArgsConstructor
public class CotacaoFreteController {
	
	private final CotacaoService cotacaoService;
	
	@Autowired
	private CotacaoAssembler mapper;
	
	@PostMapping("/calcular")
	public ResponseEntity<CotacaoResponse> calcularPrecoFrete(@RequestBody EncomendaDTO encomendaDTO) {
		Cotacao gerarCotacaoFrete = cotacaoService.gerarCotacaoFrete(encomendaDTO);
		return ResponseEntity.status(HttpStatus.OK).body(mapper.toOutputDTO(gerarCotacaoFrete));
	}
	
	@GetMapping
	public ResponseEntity<Page<CotacaoResponse>> findAll(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size,
			@RequestParam(name = "sort", defaultValue = "descricao") String sort) {

		Pageable pagina = PageRequest.of(page, size, Sort.by(sort));
		List<CotacaoResponse> cotacoes = mapper.toCollectionOutputDTO(cotacaoService.findAll());
		Page<CotacaoResponse> pageCotacoes = new PageImpl<>(cotacoes, pagina, cotacoes.size());

		if (cotacoes.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}

		return ResponseEntity.status(HttpStatus.OK).body(pageCotacoes);
	}
}
