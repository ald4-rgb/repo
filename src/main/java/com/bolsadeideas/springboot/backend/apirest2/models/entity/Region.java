package com.bolsadeideas.springboot.backend.apirest2.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*es importante implementar la interfas serielizable igual que cliente
 * ya que estamos trabajando con json y tambien es importante si
 * queremos guardar en secion http*/
/*lo que si es importante son los atributos tendremos el id y el nombre 
 * de la region.
 * Ahora tenemos que mapear esta clase  a un tabla  de la bd 
 * e indicar que es un entity con las anotaciones de jpa y tambien 
 * la tabla.
 * en la anotacions table la anotaciones de la tabla cominesan simepre
 * por estandar en plurar y las clases siempre en singular
 * entonces con la notacion table mapeamos nuestra clase Region entity a la tabla 
 * regiones.
 * tambien es importante tener los metodos accesores para obtener el emtodo get
 * y los modificadores los metodos se.
 * Lo siguente es mapear el id con la noctacion id  con esta notacion indicamos que 
 * es una llave primaria  luego tenemos el generate value  la estrategy en la estrategia 
 * seleccionamos GenerationType.IDENTITY una identidad autoincrementable que va de uno en uno */
@Entity
@Table(name = "regiones")
public class Region implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;

//simplemetne agregamos este valor por defecto 
	private static final long serialVersionUID = 1L;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
