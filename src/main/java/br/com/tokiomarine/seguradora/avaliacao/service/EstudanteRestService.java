package br.com.tokiomarine.seguradora.avaliacao.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.repository.EstudanteRepository;

@Service
public class EstudanteRestService {

	@Autowired
	EstudanteRepository repository;
	
	public List<Estudante> listarTodos(){
		return repository.findAll();
	}
	
	public Estudante getEstudantePorId(Long id){
		Estudante estudante = repository.findById(id).orElse(null);
		return estudante;
	}
	
	public Estudante criarEstudante(Estudante estudante) {
		Estudante estudanteSalvo = repository.save(estudante);
		return estudanteSalvo;
	}
	
	public void deletarCategoria(Long id) {
		repository.deleteById(id);
	}
	
	public Estudante atualizarEstudante(Long id, Estudante estudante){
		Estudante estudanteSalvo = buscarEstudantePeloId(id);
		BeanUtils.copyProperties(estudante, estudanteSalvo, "id");
		repository.save(estudanteSalvo);
		
		return estudanteSalvo;
	}
	
	private Estudante buscarEstudantePeloId(Long id) {
		Estudante estudanteSalvo = repository.findById(id).orElse(null);
		if(estudanteSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return estudanteSalvo;
	}
	
}
