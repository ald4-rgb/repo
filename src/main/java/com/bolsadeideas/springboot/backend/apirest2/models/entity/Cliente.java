package com.bolsadeideas.springboot.backend.apirest2.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*vamos a realcionar en la bd es por llave foranea a qui es por atributo
 * por lo que debemos agregar una region cliente contiene una region como tenemos 
 * el atributo tambien tenemos que tener los getters and setters  respectivo una 
 * vez generamos mapeamos dentro del atributo un cliente va tener una region
 * una region en particular entocnes la relacion seria manytoone por que many
 * po que son muchos clientes en una solaregion configuramos el fetch la forma 
 * en que se obtienen los datos  las realaciones es del tio lizy que significa
 * carga floja utilizamos lizy para  ccarga floja  por ejemplo  cada vez que
 * invoquemos el metodo o el atributo region a travez del metodo get  cuando 
 * se llame a ese metodo  o se invoque hay recien se indique la carga
 * por eso es carga floja cuando se le llama al atributo si estamos 
 * trabajando con api rest todo el listado de cliente se va a transformar un json
 * de forma automatica por cada atributo se va invocar los metodos get 
 * pero en el caso de region es otro objeto por lo tento va a generar otro json
 * anidado dentro del json principal cuando se invoca este metodo get regiones 
 * hay recien  va a realizar  la consulta a estos objetos  relacionados
 * y por debajo de forma transparente por que en realidad se va a generar 
 * un objeto region que es un proxy  es como un puente  para poder accseder 
 * a estos datos  el siguiente paso es indicar nuestra llave foranear el id en 
 * la tabla clientes que va tener relacion con la tabla regiones.
 * Otra cosa que hay que tener en cuenta es que con LAZY se genera un proxy
 * hacia Region entity este proxy va a generar otros atributos adicionales
 * que son propios del framework entonces esos atributos que genera en esta
 * clase proxy de region hay que omitir del json por lo tanto vamos a 
 * excluir estos atributos esto no tiene que ver con jpa de prescistence 
 * a qui vamos a tener como valor una arreglo => @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
 * y vamos a omitir 2 atributos si no ignoramos estos atributos lanzara
 * un erro estos atributos son propios de hibernate del objeto proxy que esta relacionado al bojeto Region y 
 * esto seria todo vamos a dejar los atributos propios de la clase Region.
 * 
*/


@Entity
@Table(name = "clientes")
public class Cliente implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "no puede estar vacio")
	@Size(min=4, max=12, message = "el tamaño tiene que estar entre 4 y 12")
	@Column(nullable = false)
	private String nombre;
	@NotEmpty(message = "no puede estar vacio")
	private String apellido;
	@NotEmpty(message = "no puede estar vacio")
	@Email(message = "no es un correo valido ")
	@Column(nullable = false , unique = true )
	private String email; 
	
	@NotNull(message = "no puede estar vacio")
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	private String foto;
	
	@NotNull(message = "la region  no puede ser vacia")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "region_id" )
	@JsonIgnoreProperties(value= {"hibernateLazyInitializer","handler"} )
	private Region region;
	
	/*Recordemso que el cliente puede tener muchas facturas del tipo
	 * factura */

	@JsonIgnoreProperties(value={"cliente" ,"hibernateLazyInitializer","handler"}, allowSetters = true)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
	private List<Factura> facturas ;
	
	
	
	
	//@PrePersist
	/*public void prePersist() {
		
		createAt = new Date();
		
	}*/
	
	
	public Cliente() {
		
		this.facturas = new  ArrayList<Factura>();
	}
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
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
						
	
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	

	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}

	

	public List<Factura> getFacturas() {
		return facturas;
	}
	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}



	private static final long serialVersionUID = 1L;


}
