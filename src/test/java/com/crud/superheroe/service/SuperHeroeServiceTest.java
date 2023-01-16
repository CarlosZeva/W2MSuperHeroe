package com.crud.superheroe.service;

import com.crud.superheroe.model.dto.SuperHeroeDTO;
import com.crud.superheroe.model.entities.SuperHeroe;
import com.crud.superheroe.model.mapper.SuperHeroeMapper;
import com.crud.superheroe.repository.SuperHeroeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SuperHeroeServiceTest {


    private final static String ID_PARAM = "1";

    @InjectMocks
    private SuperHeroeServiceImpl superHeroeService;

    @Mock
    private SuperHeroeRepository superHeroeRepository;

    @Mock
    private SuperHeroeMapper superHeroeMapper;

    @Test
    void getAllSuperHeroes_shouldReturnNotEmpty() {
        List<SuperHeroe> list = new ArrayList<>();
        list.add(SuperHeroe.builder()
                                .id(Long.parseLong("1"))
                                .nombre("Spiderman")
                                .tipo("Marvel")
                                .build());

        list.add(SuperHeroe.builder()
                .id(Long.parseLong("2"))
                .nombre("Aquaman")
                .tipo("DC Comic")
                .build());

        List<SuperHeroeDTO> response = new ArrayList<>();
        response.add(SuperHeroeDTO.builder()
                .id(Long.parseLong("1"))
                .nombre("Spiderman")
                .tipo("Marvel")
                .build());

        response.add(SuperHeroeDTO.builder()
                .id(Long.parseLong("2"))
                .nombre("Aquaman")
                .tipo("DC Comic")
                .build());

        when(superHeroeRepository.findAll()).thenReturn(list);
        when(superHeroeMapper.toListSuperHeroeDto(any())).thenAnswer(invocationOnMock -> {return response;});
        assertThat(superHeroeService.getAllSuperHeroes()).isNotEmpty();
    }

    @Test
    void getAllSuperHeroes_shouldReturnEmpty() {

        List<SuperHeroe> list = new ArrayList<>();
        when(superHeroeRepository.findAll()).thenReturn(list);
        assertThat(superHeroeService.getAllSuperHeroes()).isEmpty();

    }

    @Test
    void getSuperHeroeById_shouldReturnNotNull() {
        SuperHeroe superHeroe = SuperHeroe.builder()
                .id(Long.parseLong("1"))
                .nombre("Spiderman")
                .tipo("Marvel")
                .build();

        SuperHeroeDTO response = SuperHeroeDTO.builder()
                .id(Long.parseLong("1"))
                .nombre("Spiderman")
                .tipo("Marvel")
                .build();

        when(superHeroeRepository.findById(any(Long.class))).thenReturn(Optional.of(superHeroe));
        when(superHeroeMapper.toSuperHeroeDto(any())).thenAnswer(invocationOnMock -> {return response;});
        SuperHeroeDTO superHeroeDTO = superHeroeService.getById(String.valueOf(ID_PARAM));
        assertThat(superHeroeDTO).isNotNull();

    }

    @Test
    void getSuperHeroeById_shouldReturnResponseStatusExceptionNotFound() {

        when(superHeroeRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        ResponseStatusException exception =
                assertThrows(ResponseStatusException.class, () -> superHeroeService.getById(ID_PARAM));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    void getSuperHeroeByNombre_shouldReturnNotEmpty() {
        List<SuperHeroe> list = new ArrayList<>();

        list.add(SuperHeroe.builder()
                .id(Long.parseLong("1"))
                .nombre("Spiderman")
                .tipo("Marvel")
                .build());

        list.add(SuperHeroe.builder()
                .id(Long.parseLong("2"))
                .nombre("Aquaman")
                .tipo("DC Comic")
                .build());

        list.add(SuperHeroe.builder()
                .id(Long.parseLong("3"))
                .nombre("Robin")
                .tipo("DC Comic")
                .build());

        when(superHeroeRepository.findAllByNombreContainingIgnoreCase(any(String.class))).thenReturn(list);

        List<SuperHeroe> resultados = superHeroeRepository.findAllByNombreContainingIgnoreCase("man");
        assertThat(resultados).hasAtLeastOneElementOfType(SuperHeroe.class);

    }

}
