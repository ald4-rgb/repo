 package com.bolsadeideas.springboot.backend.apirest2.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.channels.AcceptPendingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.multipart.MultipartFile;

import com.bolsadeideas.springboot.backend.apirest2.models.services.IClienteService;
import com.bolsadeideas.springboot.backend.apirest2.models.services.IUploadFileService;
import com.bolsadeideas.springboot.backend.apirest2.models.entity.Cliente;
import com.bolsadeideas.springboot.backend.apirest2.models.entity.Region;

/*como vamos a tener un nuevo camos en el metodo update tenemos que agregar la 
 * region.
 * Vmoa anotar todos lo metodos a los cuales vamos a dar seguridad  roles  por 
 * lo tanto el index  y el  index  para paginar  no hay que hacer nada
 * pero el metodo show hay que mpdificar con la notacion @Secured(indicamos el roles)
 * , pero tenemos dos roles tanto el user como admin   por lo tanto
 * usamos las llaves para indicar que son mas de un role */
@SuppressWarnings("unused")
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClienteRestController {
	
	@Autowired
	private IClienteService clienteService;
	@Autowired
	private IUploadFileService  uploadService;
	
	private final Logger log =  LoggerFactory.getLogger(ClienteRestController.class); 
	
	
	@GetMapping("/clientes")
	public List<Cliente> index(){
		return  clienteService.findAll();
		
	}
	
	 @GetMapping("/clientes/page/{page}")
	public Page<Cliente> index(@PathVariable Integer page){
		
		Pageable pageable = PageRequest.of(page, 4);
		
		return  clienteService.findAll(pageable);
		
	}
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Cliente cliente  = null;
		Map<String, Object> response = new  HashMap<>();
		
		try {
			cliente = clienteService.findById(id);
			
			
		} catch (DataAccessException e) {
			
			response.put("Mensaje","Vuelve a intentar  error al realizar consulta ");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		 
		if(cliente == null) {
			
			response.put("mensaje","El cliente Id: ".concat(id.toString().concat(" no existe en la bd :C ")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		
		return new ResponseEntity<Cliente>(cliente,HttpStatus.OK); 
	}
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/clientes")
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result  ) {
		
		//	cliente.setCreateAt(new Date());
		Cliente clienteNew =  null;
		Map<String,Object> response = new HashMap<>();
		/*una vez obentino el resulta podemos validar
		 * si tiene errores lo manejamos dentro de este if
		 * si no continua su flujo normal*/
		if(result.hasErrors()) {
			//aqui tenemos que crear una lista del tipo string un list que contenga los mensajes de error
			/*a qui recibimos en faillderror lo convertimos a un string lo retornamos y ya
			 * tenemos un collect del tipo string*/
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err ->"El campo'"+err.getField()+"' "+err.getDefaultMessage())
					.collect(Collectors.toList());
			
			
			response.put("errors",errors);
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);

		}
		
		
		try {
			
			clienteNew = clienteService.save(cliente);
			
		
		}catch (DataAccessException e) {
			
			response.put("mensaje","Error al realizar el insert en la bd  ");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);

			
			
		}
		response.put("mensaje","El cliente ha sido creado con éxito!");
		response.put("cliente",clienteNew);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
		
		
	} 
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/clientes/{id}") 
	public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long id ) {
		//nota imprtante simepre fijarse con los terminos enre back end y front end 
		Cliente clienteActual =  clienteService.findById(id);
		Cliente clienteUpdated =	null;
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {
			//aqui tenemos que crear una lista del tipo string un list que contenga los mensajes de error
			/*a qui recibimos en faillderror lo convertimos a un string lo retornamos y ya
			 * tenemos un collect del tipo string*/
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err ->"El campo'"+err.getField()+"' "+err.getDefaultMessage())
					.collect(Collectors.toList());
			
			
			response.put("errors",errors);
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);

		}

		
		
		
		if(clienteActual == null) {
			
			response.put("mensaje","error: no se puede editar El cliente Id: ".concat(id.toString().concat(" no existe en la bd :C ")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		
		try {
			
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setApellido(cliente.getApellido());		
			clienteActual.setEmail(cliente.getEmail());
			clienteActual.setCreateAt(cliente.getCreateAt());
			clienteActual.setRegion(cliente.getRegion());
			
			clienteUpdated = clienteService.save(clienteActual);
			
			
		} catch (DataAccessException e) {
			
			response.put("mensaje","Error al actualizar en la bd  ");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		
		}
		response.put("mensaje"	, "Actualizado con exito");
		response.put("cliente",clienteUpdated);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
		
	}
	@Secured({"ROLE_ADMIN"})
	@DeleteMapping("/clientes/{id}")  
	public ResponseEntity<?> delete(@PathVariable Long id  ){
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			//borrar imagen, nececitamos el findbyid para obetener el cliente
			Cliente cliente =  clienteService.findById(id);
			
			String nombreFotoAnterior = cliente.getFoto();
			
			uploadService.eliminar(nombreFotoAnterior);
			
			clienteService.deleted(id);
			
		} catch (DataAccessException e) {
			
			response.put("mensaje","Error al eliminar al cliente en la bd  ");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		
			
		}
		response.put("mensaje","El cliente a sido eliminado con exito!");
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
		
	}
	//creamos un nuevo meotodo para subir la imagen ResponseEntity <?> puede ser cualquier cosa
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@PostMapping("/clientes/upload")
	public 	ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id ){
		Map<String, Object> response = new HashMap<>();
		//buscamos pro id para registrar nuestra imagen
		Cliente cliente =  clienteService.findById(id);
		/* y utilizando rl  archivo que contiene la foto  que se esta subiendo desde el cliente pero tenemos que validar
		 si se esta sbuneidno la imagen*/
		//ahora subiremos el archivo	
		if(!archivo.isEmpty()) {
			
			String nombreArchivo = null;
			try {
				
				nombreArchivo = uploadService.copiar(archivo);
			} catch (IOException e) {
				
				response.put("mensaje","error al subit la imagen ");
				response.put("error",e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			//borrar imagen
			String nombreFotoAnterior = cliente.getFoto();
			
			uploadService.eliminar(nombreFotoAnterior);
			 
			cliente.setFoto(nombreArchivo);
			
			clienteService.save(cliente);
			
			//por lo tanto siemrpe recordar que estamos accediendo a este atributo cliente que retorna un json
			//para convertir un obeserbable de cliente
			response.put("cliente", cliente);
			response.put("mensaje","Has subido correctamente la imagen:" + nombreArchivo);
		}
		
		
		
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	//mapeamos y damos una ruta como parametro de la ruta nuestro nombreFoto debe estar indicada
	//con una extencion ejemplo png,jpj,img 
	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable	String nombreFoto){
		//entonces retornamos el ResponseEntity<Resource> creamos una instancia y en el cuerpo el bojeto recurso

		
		//ahora a partir de la ruta que contiene la imagen creamos el recurso
		//se creo el recurso como nulo tatamos de crear la instancia como nulo convertida a una url
		Resource recurso = null;
		
		try {
			
			recurso =	uploadService.cargar(nombreFoto);
		
		} catch (MalformedURLException e) {
		
			e.printStackTrace();
		}	
			
		//y por ultimo tenemos que pasar las cabezeras de la respuesta para que este recurso o formcemos para que 
		//se pueda descargar
		
		HttpHeaders cabecera  = new HttpHeaders();
		// \-> esto es una escape para las comillas cuando son 3 comillas "\""
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;	filename=\" "+recurso.getFilename()+ "\"");
		
		//y si todo sale bien entonces guarcamos el recurso en la respuesta
		                     
		return new ResponseEntity<Resource>(recurso,cabecera ,HttpStatus.OK ); 
	}
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/clientes/regiones")
	public List<Region> listarRegiones(){
		
		//simplemente va a retornar la lista regiones a travez del service
		return clienteService.findAllRegiones(); 
		
	} 
}
