package com.crud.superheroe.service;
import java.util.List;
import com.crud.superheroe.model.dto.SuperHeroeDTO;
import org.springframework.http.ResponseEntity;


public interface SuperHeroeService {

	List<SuperHeroeDTO> getAllSuperHeroes();
	SuperHeroeDTO getById(String id);
	List<SuperHeroeDTO> getByNombre(String nombre);
	ResponseEntity<?> save(SuperHeroeDTO superHeroe);
	void update(SuperHeroeDTO input);
	void delete(String id);

}


