package br.com.cd2tec.sigabem.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cd2tec.sigabem.api.dto.CotacaoResponse;
import br.com.cd2tec.sigabem.domain.entity.Cotacao;

@Component
public class CotacaoAssembler {
	
    @Autowired
    private ModelMapper mapper;

    public CotacaoResponse toOutputDTO(Cotacao cotacao) {
       return mapper.map(cotacao, CotacaoResponse.class);
    }
    
    public List<CotacaoResponse> toCollectionOutputDTO(List<Cotacao> list) {
		return list.stream().map(this::toOutputDTO).collect(Collectors.toList());
	}
}
