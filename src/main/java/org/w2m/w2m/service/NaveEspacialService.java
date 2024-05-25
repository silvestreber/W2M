package org.w2m.w2m.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.w2m.w2m.dto.NaveEspacialDTO;
import org.w2m.w2m.entity.NaveEspacial;
import org.w2m.w2m.mapper.NaveEspacialMapper;
import org.w2m.w2m.repository.NaveEspacialRepository;

@Service
public class NaveEspacialService {

	@Autowired
	private NaveEspacialRepository repository;

	@Autowired
	private NaveEspacialMapper mapper;

	public Page<NaveEspacialDTO> consultarNaves(int pagina, int tamano) {
		return repository.findAll(PageRequest.of(pagina, tamano))
					.map(mapper::naveEspacialToDto);
	}

	public NaveEspacialDTO consultarNaveId(Long id) {
		Optional<NaveEspacial> naveEspacial = repository.findById(id);
		return naveEspacial
				.map(mapper::naveEspacialToDto)
				.orElse(null);
	}

	public List<NaveEspacialDTO> consultarNaveNombre(String nombre) {
		List<NaveEspacial> naves = repository.findByNombreContainingIgnoreCase(nombre);
		return naves.stream()
				.map(mapper::naveEspacialToDto)
				.collect(Collectors.toList());
	}
	
	public void crearNave(NaveEspacialDTO dto) {
		repository.save(mapper.dtoToNaveEspacial(dto));
	}
	
	public void modificarNave(NaveEspacialDTO dto) {
		Optional<NaveEspacial> naveOriginal = repository.findById(dto.getId());
		if (naveOriginal.isPresent()) {
			repository.save(mapper.dtoToNaveEspacial(dto));
		}
	}
	
	public void eliminarNave(Long id) {
		repository.deleteById(id);
	}
	
	public void crearListaNaves(List<NaveEspacialDTO> listaNaves) {
		List<NaveEspacial> naves = listaNaves.stream()
                .map(mapper::dtoToNaveEspacial)
                .collect(Collectors.toList());
		repository.saveAll(naves);
	}
}
