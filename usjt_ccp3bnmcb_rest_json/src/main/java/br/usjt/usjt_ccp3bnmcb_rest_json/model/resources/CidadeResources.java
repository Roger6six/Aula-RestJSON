package br.usjt.usjt_ccp3bnmcb_rest_json.model.resources;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.usjt.usjt_ccp3bnmcb_rest_json.model.Cidade;
import br.usjt.usjt_ccp3bnmcb_rest_json.model.repository.CidadeRepository;

@RestController
@RequestMapping("/cidade")
public class CidadeResources {
	@Autowired
	private CidadeRepository cidadeRepo;

	@GetMapping("/lista")
	public List<Cidade> todasAsCidades() {
		return cidadeRepo.findAll();
	}
	
	@GetMapping("/{letra}")
	public List<Cidade> buscarPorLetra(@PathVariable char letra) {
		return cidadeRepo.findByNomeStartingWith(letra);
	}
	
	@GetMapping("/{lat}/{lon}")
	public Cidade buscarPorLatitudeAndLongitude(@PathVariable double lat, @PathVariable double lon) {
		return cidadeRepo.findOneByLatitudeAndLongitude(lat, lon);
	}

	@PostMapping("/salvar")
	public ResponseEntity<Cidade> salvar(@RequestBody Cidade cidade, HttpServletResponse response) {
		Cidade c = cidadeRepo.save(cidade);
		URI uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/{id}").buildAndExpand(c.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		return ResponseEntity.created(uri).body(c);
	}
}
