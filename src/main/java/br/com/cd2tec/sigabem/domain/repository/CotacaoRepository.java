package br.com.cd2tec.sigabem.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cd2tec.sigabem.domain.entity.Cotacao;

@Repository
public interface CotacaoRepository extends JpaRepository<Cotacao, Long> {

}
