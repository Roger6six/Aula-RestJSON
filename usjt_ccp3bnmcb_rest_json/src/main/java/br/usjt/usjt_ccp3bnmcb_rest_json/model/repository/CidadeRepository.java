package br.usjt.usjt_ccp3bnmcb_rest_json.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.usjt.usjt_ccp3bnmcb_rest_json.model.Cidade;

public interface CidadeRepository extends JpaRepository <Cidade, Long> {
	public List<Cidade> findByNomeStartingWith(char c);
	
	public Cidade findOneByLatitudeAndLongitude(double lat, double lon);
}
