package com.bolsadeideas.springboot.backend.apirest2.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bolsadeideas.springboot.backend.apirest2.models.entity.Cliente;
import com.bolsadeideas.springboot.backend.apirest2.models.entity.Region;

/*usamos todo lo que es jpa para paginacion vamos a agregar un nuevo metodo 
 * para obetneer el listado de  regiones  o la otra oopcion es crear 
 * otro dao e inyectarla en la clase service.
 * Pero como solo vamos a tener una sola consulta para la region la podriamos
 *  agregar dentro del ICienteDao sin la necesidad de crear  una clase separada.
 *  Aho por ejemplo si tenemos  un crud  para  crear actualizar,modificar & eliminar 
 *  regiones  y varios tipos de consulta ahi si  valdria la pena crear un compoenenete dao separado
 *  con su propia interface jpaRepository.
 *  creamos un lista y va a retornar un List de Region un listado que llamaremos findallRegiones
 *  pero solo tenemos la frma de la interface a qui tenemos que mpaear este metodo a una consulta jpa
 *  que es la forma de jpa(JPAQuery) que es  la forma de usar repositorys
 *  para eso usamos la notacion, en esta notacion podemos personalizar nuestra consulta @Query("")
 *  @Query("from Region") => no es la tabla por que seguimos trabajando con objetos de esta forma 
 *  se peude abreviar from region una consulta jpa para que retorne todas a consultas 
 *  
 *  compiar el contrato findallRegiones() */
public interface IClienteDao extends JpaRepository<Cliente, Long> {

	@Query("from Region")
	public List<Region>findAllRegiones();
	
}
