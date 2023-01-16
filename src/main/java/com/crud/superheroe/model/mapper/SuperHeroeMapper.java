package com.crud.superheroe.model.mapper;
import com.crud.superheroe.model.dto.SuperHeroeDTO;
import com.crud.superheroe.model.entities.SuperHeroe;
import org.mapstruct.*;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface SuperHeroeMapper {

    SuperHeroeDTO toSuperHeroeDto(SuperHeroe superHeroe);
    SuperHeroe toSuperHeroe(SuperHeroeDTO superHeroeDTO);
    List<SuperHeroeDTO> toListSuperHeroeDto(List<SuperHeroe> superheroeList);
}
