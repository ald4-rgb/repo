package com.bolsadeideas.springboot.backend.apirest2.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.backend.apirest2.models.entity.Producto;

public interface IProductoDao  extends CrudRepository<Producto, Long>{

	/*Implementamos un metodo que busque que filtre  a los productos por 
	 * ejemplo utilizando el wherelike y que retrone una lista  de productos
	 * encontrados, imprementamos el query la consulta q jpa  utilizando 
	 * la notacion query pero tenemos que pasar el termino el texto 
	 * por el cual vamos a buscar.
	 * el porcentaje al final ($) un append va a buscar al comienzo de la cadena
	 * por ejemplo si buscamos por la letra a bva a  buscar todo lo referene 
	 * a la letra a una frase o alguna palabra   tambien si queremos que 
	 * cimince al principio o  al final */
	 
	@Query("select p  from  Producto  p where p.nombre like %?1%")
	public List<Producto> findByNombre(String term);

	/*Consulta por jpa Contaigneincase equivale  hacer select p  from  Producto  o where p.nombre like %?1%
	 *  IgnoreCase -> ignora no importando si esmayusacula o minuscula */
	public List<Producto> findByNombreContainingIgnoreCase(String term);
	
	public List<Producto> findByNombreStartingWithIgnoreCase(String term);
	
}
