package org.w2m.w2m.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w2m.w2m.dto.NaveEspacialDTO;
import org.w2m.w2m.exception.NaveNoEncontradaException;
import org.w2m.w2m.service.NaveEspacialService;

@RestController
@RequestMapping("/api/naves")
public class NaveEspacialController {

	@Autowired
	private NaveEspacialService service;

	@GetMapping
	public Page<NaveEspacialDTO> consultarNaves(
			@RequestParam(value = "pagina", defaultValue = "0") int pagina,
			@RequestParam(value = "tamano", defaultValue = "10") int tamano) {
		return service.consultarNaves(pagina, tamano);
	}
	
	@GetMapping("/{id}")
    public NaveEspacialDTO consultarNaveId(@PathVariable Long id) throws NaveNoEncontradaException {
        return service.consultarNaveId(id);
    }
	
	@GetMapping("/buscar")
    public List<NaveEspacialDTO> consultarNaveNombre(@RequestParam String nombre) throws NaveNoEncontradaException {
        return service.consultarNaveNombre(nombre);
    }
	
	@PostMapping
    public void crearNave(@RequestBody NaveEspacialDTO dto) {
        service.crearNave(dto);
    }
	
	@PutMapping
    public void modificarNave(@RequestBody NaveEspacialDTO dto) throws NaveNoEncontradaException {
        service.modificarNave(dto);
    }

    @DeleteMapping("/{id}")
    public void eliminarNave(@PathVariable Long id) throws NaveNoEncontradaException {
        service.eliminarNave(id);
    }
    
    @PostMapping("/list")
    public void crearListaNaves(@RequestBody List<NaveEspacialDTO> listaNaves) {
        service.crearListaNaves(listaNaves);
    }

}
