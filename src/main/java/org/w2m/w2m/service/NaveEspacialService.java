package org.w2m.w2m.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.w2m.w2m.dto.NaveEspacialDTO;
import org.w2m.w2m.entity.NaveEspacial;
import org.w2m.w2m.exception.NaveNoEncontradaException;
import org.w2m.w2m.mapper.NaveEspacialMapper;
import org.w2m.w2m.repository.NaveEspacialRepository;
import org.w2m.w2m.util.ConstantesError;

@Service
public class NaveEspacialService {
	
	private static final Logger logger = LoggerFactory.getLogger(NaveEspacialService.class);

	@Autowired
	private NaveEspacialRepository repository;

	@Autowired
	private NaveEspacialMapper mapper;

	public NaveEspacialService(NaveEspacialRepository repositoryMock) {
		this.repository = repositoryMock;
		this.mapper = new NaveEspacialMapper();
	}

	public Page<NaveEspacialDTO> consultarNaves(int pagina, int tamano) {
		return repository.findAll(PageRequest.of(pagina, tamano))
				.map(mapper::naveEspacialToDto);
	}

	@Cacheable(value = "nave", key = "#id")
	public NaveEspacialDTO consultarNaveId(Long id) throws NaveNoEncontradaException {
		NaveEspacialDTO nave = null;
		Optional<NaveEspacial> naveEspacial = repository.findById(id);
		logger.info(this.getClass().getName() + "consultarNaveId(" + id + ") consultó a base de datos " + new Date());
		nave = naveEspacial
				.map(mapper::naveEspacialToDto)
				.orElse(null);
		if (nave == null) {
			throw new NaveNoEncontradaException(ConstantesError.SIN_RESULTADOS);
		}
		return nave;
	}

	public List<NaveEspacialDTO> consultarNaveNombre(String nombre) throws NaveNoEncontradaException {
		List<NaveEspacialDTO> navesDto = null;
		List<NaveEspacial> naves = repository.findByNombreContainingIgnoreCase(nombre);
		if (!naves.isEmpty()) {
			navesDto = naves.stream()
					.map(mapper::naveEspacialToDto)
					.collect(Collectors.toList());
		} else {
			throw new NaveNoEncontradaException(ConstantesError.SIN_RESULTADOS);
		}
		return navesDto;
	}
	
	public void crearNave(NaveEspacialDTO dto) {
		repository.save(mapper.dtoToNaveEspacial(dto));
	}
	
	public void modificarNave(NaveEspacialDTO dto) throws NaveNoEncontradaException {
		Optional<NaveEspacial> naveOriginal = repository.findById(dto.getId());
		if (naveOriginal.isPresent()) {
			repository.save(mapper.dtoToNaveEspacial(dto));
		} else {
			throw new NaveNoEncontradaException(ConstantesError.ERROR_ACTUALIZAR);
		}
	}
	
	public void eliminarNave(Long id) throws NaveNoEncontradaException {
		Optional<NaveEspacial> naveEliminar = repository.findById(id);
		if (naveEliminar.isPresent()) {
			repository.deleteById(id);
		} else {
			throw new NaveNoEncontradaException(ConstantesError.ERROR_ELIMINAR);
		}
	}
	
	public void crearListaNaves(List<NaveEspacialDTO> listaNaves) {
		List<NaveEspacial> naves = listaNaves.stream()
                .map(mapper::dtoToNaveEspacial)
                .collect(Collectors.toList());
		repository.saveAll(naves);
	}
}
