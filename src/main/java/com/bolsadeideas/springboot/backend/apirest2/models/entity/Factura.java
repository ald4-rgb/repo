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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
 
@Entity
@Table(name = "facturas")
public class Factura implements Serializable{

		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private String descripcion;
		private String observacion;
		//especificamos el nombre de la tabla 
		@Column(name="create_at")
		@Temporal(TemporalType.DATE)
		private Date createAt;
	
		/*importante para la realcion, muchas facturas estan realcionadas a un solo cliente*/
		@JsonIgnoreProperties(value ={"facturas",	"hibernateLazyInitializer","handler"},allowSetters = true )
		@ManyToOne(fetch =  FetchType.LAZY)
		private Cliente cliente;
		
		/*a qui necesitamos la clase relacion con la clase facturas con la clase items
		 * con las lineas, cremos la realcacion utilizando la notacion one
		 * muchas  items  @OneToMany*/
		@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
		@OneToMany(fetch = FetchType.LAZY)
		@JoinColumn(name = "factura_id")
		private List<ItemFactura> items;
		
		
		
		
		public Factura() {
		 
			items = new ArrayList<>();
		
		}




		@PrePersist
		public void prePersist() {
			
			this.createAt =  new Date();
			
		}
		
		
		

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}


		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public String getObservacion() {
			return observacion;
		}

		public void setObservacion(String observacion) {
			this.observacion = observacion;
		}
		
		
		public Date getCreateAt() {
			return createAt;
		}

		public void setCreateAt(Date createAt) {
			this.createAt = createAt;
		}

		public Cliente getCliente() {
			return cliente;
		}

		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
		}
		
		
		
		public List<ItemFactura> getItems() {
			return items;
		}




		public void setItems(List<ItemFactura> items) {
			this.items = items;
		}

		/*entonces la idea es reccorres tosos los items
		 * de la factuea e ir sumando*/
		public Double getTotal() {
			
			Double total = 0.00;
			for(ItemFactura item: items) {
				
				total += item.getImporte();
				
			}
			
			return total;
		} 





		private static final long serialVersionUID = 1L;
		
		
}
