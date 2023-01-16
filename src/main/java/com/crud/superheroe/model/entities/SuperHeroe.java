package com.crud.superheroe.model.entities;

import javax.persistence.*;

import lombok.*;


@Entity 
@Data
@Getter 
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "superheroe")
public class SuperHeroe {

	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 	
	private long id;
	@Column(name = "NOMBRE", nullable = false, length = 50)
	private String nombre;
	@Column(name = "TIPO", nullable = true, length = 50)
	private String tipo;

}
