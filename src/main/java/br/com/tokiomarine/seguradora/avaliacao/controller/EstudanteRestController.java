package br.com.tokiomarine.seguradora.avaliacao.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.service.EstudanteRestService;

@RestController
@RequestMapping("/estudanteRest")
public class EstudanteRestController {
	
	@Autowired
	EstudanteRestService service;

	@GetMapping
	public List<Estudante> listarTodos(){
		return service.listarTodos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Estudante> getEstudantePorId(@PathVariable Long id){
		Estudante estudante = service.getEstudantePorId(id);
		return estudante == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(estudante);
	}
	
	@PostMapping
	public ResponseEntity<Estudante> criarEstudante(@Valid @RequestBody Estudante estudante, HttpServletResponse response) {
		Estudante estudanteSalvo = service.criarEstudante(estudante);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(estudanteSalvo.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(estudanteSalvo);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deletarCategoria(@PathVariable Long id) {
		service.deletarCategoria(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Estudante> atualizarEstudante(@PathVariable Long id, @Valid @RequestBody Estudante estudante){
		Estudante estudanteAtualizado = service.atualizarEstudante(id, estudante);
		return ResponseEntity.ok(estudanteAtualizado);
	}
	
}
