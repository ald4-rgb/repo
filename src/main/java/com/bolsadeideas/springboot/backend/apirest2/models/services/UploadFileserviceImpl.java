package com.bolsadeideas.springboot.backend.apirest2.models.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileserviceImpl implements IUploadFileService {
	private final static String DIRECTORIO_UPLOAD = "uploads";
	
	private final Logger log =  LoggerFactory.getLogger(UploadFileserviceImpl.class); 

	@Override									//este emtodo lanza la expception por lo tanto borramos try catch
	public Resource cargar(String nombreFoto) throws MalformedURLException {
		//entonces a qui tendremos que usar el metodo get y le pasamos el nombre de la foto  
		Path rutaArchivo =  getPath(nombreFoto);
		//mostramos la ruta archivo en la conosla en el log
		log.info(rutaArchivo.toString());
		
		//ahora a partir de la ruta que contiene la imagen creamos el recurso
		//se creo el recurso como nulo tatamos de crear la instancia como nulo convertida a una url
		Resource recurso = new UrlResource(rutaArchivo.toUri());
		
		/*y a qui tenemos la validacion con el if en caso  de que no exista la imagen fisicamente en el directorio	 */
		if(!recurso.exists() &&	!recurso.isReadable()  ) {
			
			rutaArchivo =  Paths.get("src/main/resources/static/images").resolve("no-user.png").toAbsolutePath();
		
			
				recurso = new UrlResource(rutaArchivo.toUri());
			
			log.error("No se pudo cargar la imgane :C"+ nombreFoto);
			}
		return recurso;
	}
												//quitamos el try catch por que estamos lanzando directamente la exception
	@Override			
	public String copiar(MultipartFile archivo) throws IOException {
		//el controlador solo se encarga de utilizar el try catch 
		String	nombreArchivo = UUID.randomUUID().toString()+ "_"+	archivo.getOriginalFilename().replace(" ","");
		
		Path rutaArchivo =getPath(nombreArchivo);
		
		log.info(rutaArchivo.toString());
		
		Files.copy(archivo.getInputStream(), rutaArchivo);
		
		
		return nombreArchivo;
	}

	@Override
	public boolean eliminar(String nombreFoto) {
		if (nombreFoto != null && nombreFoto.length() > 0) {
			
			Path rutaFotoAnterior =  Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
			//convertir este path a un archivo tipo file
			File archivoFotoAnterior = rutaFotoAnterior.toFile(); 
			//validamos con tor archivo que este se pueda leer y se elimina
			if(archivoFotoAnterior.exists()	&& archivoFotoAnterior.canRead()) {
				
				archivoFotoAnterior.delete();
				//entonces si se borra retornamos un true si todo sale bien 
				return true;
				
			}
		}
		//de lo contrario vamos a retornar un false 
		return false;
	}

	@Override
	public Path getPath(String nombreFoto) {
		/*este meotodo se encarga de eso de construir el path y retornarlo*/
		return Paths.get(DIRECTORIO_UPLOAD).resolve(nombreFoto).toAbsolutePath();
	}

}
