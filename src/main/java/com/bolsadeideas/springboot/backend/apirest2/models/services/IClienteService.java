package com.bolsadeideas.springboot.backend.apirest2.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.springboot.backend.apirest2.models.entity.Cliente;
import com.bolsadeideas.springboot.backend.apirest2.models.entity.Factura;
import com.bolsadeideas.springboot.backend.apirest2.models.entity.Producto;
import com.bolsadeideas.springboot.backend.apirest2.models.entity.Region;

/********************************************************************  	 
* Implementar el contrato del crud 									*
* el metodo save va a recibir el cliente que vamos a almacenar      *
* y va a retornar el cliente guardado el cliente que ya tiene el id *
* El delete va a retronar un void y vamos a pasar el id de tipo Long*
* del cliente que vamos eliminar									*
* Buscar por id findById va a retornar un cliente va a recibir el id*					*
********************************************************************/
/*aregamod otro  findAll(): que va a recibir un ojeto pageAble por argumento que es
 * una instancia de pageRequest que contiene el numero de pagina y la cantidad de 
 * elementos por paginaa mostrar */

/*primero la interface agregamos el metodo public List<Region>findallRegiones();*/
public interface IClienteService {

	
	public List<Cliente> findAll();
	
	public Page<Cliente> findAll(Pageable pegeable);

	public Cliente findById(Long id);

	public Cliente save(Cliente cliente);
	
	public void deleted(Long id);
	
	public List<Region>findAllRegiones();
	
	public Factura findFacturaById(Long id);	 
	
	public Factura saveFactura(Factura factura);
	
	public void deleteFacturaById(Long id);
	
	public List<Producto> findProductoByNombre(String term);


}
