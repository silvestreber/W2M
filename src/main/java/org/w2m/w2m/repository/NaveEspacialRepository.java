package org.w2m.w2m.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.w2m.w2m.entity.NaveEspacial;

public interface NaveEspacialRepository extends JpaRepository<NaveEspacial, Long>{
	
	// "Nombre" para filtrar por dicho campo de la tabla
	// "Containing" para buscar cualquier resultado cuyo nombre contenga la cadena de entrada
	// "IgnoreCase" para que no diferencie mayúsculas de minúsculas
	List<NaveEspacial> findByNombreContainingIgnoreCase(String nombre);

}
