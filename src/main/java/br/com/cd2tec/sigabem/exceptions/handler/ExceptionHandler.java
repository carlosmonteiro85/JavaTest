package br.com.cd2tec.sigabem.exceptions.handler;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.cd2tec.sigabem.exceptions.NegocioException;
import br.com.cd2tec.sigabem.exceptions.problemas.ProblemType;
import br.com.cd2tec.sigabem.exceptions.problemas.Problema;
import br.com.cd2tec.sigabem.exceptions.problemas.ProblemaTipoBean;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	public static final String MSG_ERRO_GENETICA = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se"
			+ " o problema persistir, entre em contato com o administrador do sistema";

	@Value("${problemType.url}")
	private String url;
	
    @org.springframework.web.bind.annotation.ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemaTipoBean problemaTipo = new ProblemaTipoBean(ProblemType.ERRO_NEGOCIO, url);
        String detalhe = ex.getMessage();

        Problema problem = criarProblemaBuilder(status, problemaTipo, detalhe)
        		.menssagemUsuario(detalhe)
                .build();
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }
	
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers
            , HttpStatus status, WebRequest request) {
        if(body == null) {
        	body = Problema.builder()
        				.status(status.value())
        				.titulo(status.getReasonPhrase())
        				.menssagemUsuario(MSG_ERRO_GENETICA)
        				.data(LocalDate.now())        			
        		   .build();
        } else if (body instanceof String) {
        	body = Problema.builder()
        				.data(LocalDate.now())
        				.titulo((String) body)
        				.status(status.value())
        				.menssagemUsuario(MSG_ERRO_GENETICA)
        			.build();
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
    
	private Problema.ProblemaBuilder criarProblemaBuilder(HttpStatus status, ProblemaTipoBean tipoProblema, String detalhe) {
		return Problema.builder()
				.status(status.value())
				.tipo(tipoProblema.getUrl())
				.titulo(tipoProblema.getTitlo())
				.detalhe(detalhe)
				.data(LocalDate.now());
	}
}
