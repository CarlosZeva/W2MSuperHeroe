package com.crud.superheroe.model.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class SuperHeroeDTO {

    private Long id;
    private String nombre;
    private String tipo;
}
