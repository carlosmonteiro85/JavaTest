package br.com.cd2tec.sigabem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cd2tec.sigabem.dto.EncomendaDTO;
import br.com.cd2tec.sigabem.entity.Cotacao;
import br.com.cd2tec.sigabem.service.CotacaoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("cotacao-frete")
public class CotacaoFreteController {
	
	private CotacaoService cotacaoService;
	
	public CotacaoFreteController(CotacaoService cotacaoService) {
		this.cotacaoService = cotacaoService;
	}
	
	@PostMapping("/calcular")
	@ApiOperation(value = "Realiza o cálculo do frete")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Realiza o calculo da cotação"),
	    @ApiResponse(code = 403, message = "Sem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	public Cotacao calcularPrecoFrete(@RequestBody EncomendaDTO encomendaDTO) {
		return cotacaoService.gerarCotacaoFrete(encomendaDTO);
	}
	
	@GetMapping
	@ApiOperation(value = "Listagem das cotações realizadas")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna a lista de cotações"),
	    @ApiResponse(code = 403, message = "Sem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	public List<Cotacao> findAll(@RequestParam(defaultValue = "id,asc") String[] sort) {

		List<Order> orders = new ArrayList<>();

		if (sort[0].contains(",")) {
			// irá ordenar mais que dois campos
			// sortOrder="field, direction"
			for (String sortOrder : sort) {
				String[] _sort = sortOrder.split(",");
				orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
			}
		} else {
			// sort=[field, direction]
			orders.add(new Order(getSortDirection(sort[1]), sort[0]));
		}
		
		return cotacaoService.findAll(Sort.by(orders));
	}
	
	private Sort.Direction getSortDirection(String direction) {
		if (direction.equals("desc")) {
			return Sort.Direction.DESC;
		}
		return Sort.Direction.ASC;
	}

}
