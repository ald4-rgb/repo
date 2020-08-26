package com.bolsadeideas.springboot.backend.apirest2.models.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {

	// en la firma del metodo va a lanzar una excepcion 	
	public Resource cargar(String nombreFoto) throws MalformedURLException;
	//metodo para copiar la imagen como argumeto va a recivir el archivo de tipo multiparfile 
	public String copiar(MultipartFile archivo ) throws IOException;
	//metodo para eliminar
	public boolean eliminar(String nombreFoto);
	//el ultimo metodo es para obetener la ruta donde vamos a guardar el archivo el path
	public Path getPath(String nombreFoto);


}
