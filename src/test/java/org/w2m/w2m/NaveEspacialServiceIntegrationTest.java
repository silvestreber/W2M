package org.w2m.w2m;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.w2m.w2m.dto.NaveEspacialDTO;
import org.w2m.w2m.exception.NaveNoEncontradaException;
import org.w2m.w2m.service.NaveEspacialService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class NaveEspacialServiceIntegrationTest {

    @Autowired
    private NaveEspacialService naveEspacialService;

    @BeforeEach
    public void setUp() {
    	NaveEspacialDTO nave1 = new NaveEspacialDTO();
        nave1.setNombre("Halcón Milenario");
        nave1.setAparicion("Star Wars");
        nave1.setTipo("Película");

        NaveEspacialDTO nave2 = new NaveEspacialDTO();
        nave2.setNombre("Endurance");
        nave2.setAparicion("Interstellar");
        nave2.setTipo("Película");

        naveEspacialService.crearNave(nave1);
        naveEspacialService.crearNave(nave2);
    }

    @Test
    public void testConsultarNaveId() throws NaveNoEncontradaException {
        NaveEspacialDTO nave = naveEspacialService.consultarNaveId(1L);
        assertThat(nave).isNotNull();
        assertThat(nave.getNombre()).isEqualTo("Halcón Milenario");
    }

    @Test
    public void testConsultarNaveNombre() throws NaveNoEncontradaException {
        List<NaveEspacialDTO> naves = naveEspacialService.consultarNaveNombre("Halcón");
        assertThat(naves).isNotEmpty();
        assertThat(naves.get(0).getNombre()).isEqualTo("Halcón Milenario");
    }

    @Test
    public void testEliminarNave() throws NaveNoEncontradaException {
        naveEspacialService.eliminarNave(2L);
        assertThrows(NaveNoEncontradaException.class, () -> {
            naveEspacialService.consultarNaveId(2L);
        });
    }
}
