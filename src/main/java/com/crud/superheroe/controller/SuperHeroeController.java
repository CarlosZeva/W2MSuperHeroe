package com.crud.superheroe.controller;
import com.crud.superheroe.annotations.TimeRequest;
import com.crud.superheroe.model.dto.SuperHeroeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.crud.superheroe.service.SuperHeroeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Super Heroe API")
@RestController
@RequestMapping("/superHeroe")
@CrossOrigin(origins = "*")
public class SuperHeroeController {
	
	@Autowired  
	SuperHeroeService superHeroeService;  
    
    @ApiOperation(value = "Retorna todos los superheroes de la base de datos como Respuesta", notes = "Retorna 204 si no encuentra data")
    @ApiResponses(value = {
    @ApiResponse(code = 204, message = "No existen registros"),
    @ApiResponse(code = 500, message = "Internal error")})
    @GetMapping()
    @TimeRequest
    public ResponseEntity<?> getAllSuperHeroes() {
        return ResponseEntity.ok(superHeroeService.getAllSuperHeroes());
    }
    
    @GetMapping("/{id}")
    @TimeRequest
    public ResponseEntity<?> getById(@PathVariable String id) {
        return ResponseEntity.ok(superHeroeService.getById(id));
    }

    @GetMapping("/ByParams")
    @TimeRequest
    public ResponseEntity<?> getByNombre(@RequestParam(value="nombre") String nombre) {
    	return ResponseEntity.ok(superHeroeService.getByNombre(nombre));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody SuperHeroeDTO input) {
        superHeroeService.update(input);
        return ResponseEntity.noContent().build();
     }
    
    @PostMapping
    public ResponseEntity<?> insert(@RequestBody SuperHeroeDTO input) {
        superHeroeService.save(input);
        return ResponseEntity.noContent().build();

    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
    	 superHeroeService.delete(id);
         return ResponseEntity.noContent().build();
    }
	
}
