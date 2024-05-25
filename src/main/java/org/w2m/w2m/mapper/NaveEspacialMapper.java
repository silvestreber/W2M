package org.w2m.w2m.mapper;

import org.springframework.stereotype.Component;
import org.w2m.w2m.dto.NaveEspacialDTO;
import org.w2m.w2m.entity.NaveEspacial;

@Component
public class NaveEspacialMapper {

	public NaveEspacialDTO naveEspacialToDto(NaveEspacial nave) {
		return new NaveEspacialDTO(nave.getId(), nave.getNombre(), nave.getAparicion(), nave.getTipo());
	}

	public NaveEspacial dtoToNaveEspacial(NaveEspacialDTO dto) {
		return new NaveEspacial(dto.getId(), dto.getNombre(), dto.getAparicion(), dto.getTipo());
	}
}
