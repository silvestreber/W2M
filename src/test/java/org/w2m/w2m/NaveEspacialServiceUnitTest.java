package org.w2m.w2m;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w2m.w2m.dto.NaveEspacialDTO;
import org.w2m.w2m.entity.NaveEspacial;
import org.w2m.w2m.exception.NaveNoEncontradaException;
import org.w2m.w2m.repository.NaveEspacialRepository;
import org.w2m.w2m.service.NaveEspacialService;

public class NaveEspacialServiceUnitTest {

	private NaveEspacialRepository repositoryMock;
	private NaveEspacialService naveEspacialService;

	@BeforeEach
	public void setUp() {
		repositoryMock = mock(NaveEspacialRepository.class);
		naveEspacialService = new NaveEspacialService(repositoryMock);
	}

	@Test
	public void testConsultarNaveId() throws NaveNoEncontradaException {
		NaveEspacialDTO result = obtenerDatos();
		assertEquals(1L, result.getId());
		assertEquals("Halcón Milenario", result.getNombre());
		assertEquals("Star Wars", result.getAparicion());
		assertEquals("Película", result.getTipo());
	}
	
	@Test
	public void testConsultarNaveIdFailNombre() throws NaveNoEncontradaException {
		NaveEspacialDTO result = obtenerDatos();
		assertEquals(1L, result.getId());
		assertEquals("Halcón", result.getNombre());
		assertEquals("Star Wars", result.getAparicion());
		assertEquals("Película", result.getTipo());
	}
	
	@Test
	public void testConsultarNaveIdFailAparicion() throws NaveNoEncontradaException {
		NaveEspacialDTO result = obtenerDatos();
		assertEquals(1L, result.getId());
		assertEquals("Halcón Milenario", result.getNombre());
		assertEquals("Star Trek", result.getAparicion());
		assertEquals("Película", result.getTipo());
	}
	
	@Test
	public void testConsultarNaveIdFailTipo() throws NaveNoEncontradaException {
		NaveEspacialDTO result = obtenerDatos();
		assertEquals(1L, result.getId());
		assertEquals("Halcón Milenario", result.getNombre());
		assertEquals("Star Wars", result.getAparicion());
		assertEquals("Serie", result.getTipo());
	}

	public NaveEspacialDTO obtenerDatos() throws NaveNoEncontradaException {
		when(repositoryMock.findById(1L))
				.thenReturn(Optional.of(new NaveEspacial(1L, "Halcón Milenario", "Star Wars", "Película")));
		NaveEspacialDTO result = naveEspacialService.consultarNaveId(1L);
		return result;
	}

}
