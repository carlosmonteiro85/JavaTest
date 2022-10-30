package br.com.cd2tec.sigabem.exceptions.problemas;

import lombok.Getter;

@Getter
public class ProblemaTipoBean {
	private String titlo;
	private String url;

	public ProblemaTipoBean(ProblemType problemType, String url) {
		this.url = url + problemType.getPath();
		this.titlo = problemType.getTitle();
	}
}
