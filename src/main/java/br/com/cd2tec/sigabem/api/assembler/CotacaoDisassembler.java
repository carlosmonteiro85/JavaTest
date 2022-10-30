package br.com.cd2tec.sigabem.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cd2tec.sigabem.api.dto.CotacaoResponse;
import br.com.cd2tec.sigabem.domain.entity.Cotacao;

@Component
public class CotacaoDisassembler {
   
	@Autowired
    private ModelMapper mapper;

    public Cotacao toCotacao(CotacaoResponse cotacaoResponse) {
       return mapper.map(cotacaoResponse, Cotacao.class);
    }
    
    public List<Cotacao> toCollectionOutputDTO(List<CotacaoResponse> list) {
		return list.stream().map(this::toCotacao).collect(Collectors.toList());
	}
}
