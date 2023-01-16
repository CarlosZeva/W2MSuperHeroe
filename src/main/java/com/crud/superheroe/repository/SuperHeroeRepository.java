package com.crud.superheroe.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.crud.superheroe.model.entities.SuperHeroe;

public interface SuperHeroeRepository extends JpaRepository<SuperHeroe, Long> {

	 List<SuperHeroe> findAllByNombreContainingIgnoreCase(String parametro);

}
