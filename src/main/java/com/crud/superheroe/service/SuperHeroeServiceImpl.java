package com.crud.superheroe.service;

import com.crud.superheroe.model.dto.SuperHeroeDTO;
import com.crud.superheroe.model.entities.SuperHeroe;
import com.crud.superheroe.model.mapper.SuperHeroeMapper;
import com.crud.superheroe.repository.SuperHeroeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import springfox.documentation.annotations.Cacheable;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SuperHeroeServiceImpl implements SuperHeroeService {

	@Autowired
	private SuperHeroeRepository superHeroeRepository;
	@Autowired
	private SuperHeroeMapper superHeroeMapper;
	@Cacheable("superheroes")
	public List<SuperHeroeDTO> getAllSuperHeroes()
	{
		return superHeroeMapper.toListSuperHeroeDto(superHeroeRepository.findAll());
	}
	public SuperHeroeDTO getById(String id) {
		Optional<SuperHeroe> opt = superHeroeRepository.findById(Long.parseLong(id));
		if (opt.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe Superhéroe para el Id solicitado");
		}
		return superHeroeMapper.toSuperHeroeDto(opt.get());

	}

	public List<SuperHeroeDTO> getByNombre(String nombre) {

		List<SuperHeroe> superHeroes = superHeroeRepository.findAllByNombreContainingIgnoreCase(nombre);//new ArrayList<Superheroe>();

		if (superHeroes.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe(n) SuperHeroe(s) con el nombre solicitado");
		}
		return superHeroeMapper.toListSuperHeroeDto(superHeroes);

	}

	@CacheEvict(value = "superheroes", allEntries = true)
	public ResponseEntity<?> save(SuperHeroeDTO heroe)
	{
		SuperHeroe save = superHeroeRepository.save(superHeroeMapper.toSuperHeroe(heroe));
        return ResponseEntity.ok(save);
	}

	@CacheEvict(value = "superheroes", allEntries = true)
	public void update(SuperHeroeDTO input) {
		Optional<SuperHeroe> superHeroe = superHeroeRepository.findById(input.getId());
		if (superHeroe.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El Superhéroe a actualizar no existe.");
		}
		superHeroeRepository.save(superHeroeMapper.toSuperHeroe(input));
	}

	@CacheEvict(value = "superheroes")
	public void delete(String id)
	{
		if (superHeroeRepository.findById(Long.parseLong(id)).isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El Superhéroe a eliminar no existe.");
		}
		superHeroeRepository.deleteById(Long.parseLong(id));
	}

}


