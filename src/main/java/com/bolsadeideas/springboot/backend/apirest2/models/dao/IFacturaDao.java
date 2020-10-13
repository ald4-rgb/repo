package com.bolsadeideas.springboot.backend.apirest2.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.backend.apirest2.models.entity.Factura;

public interface IFacturaDao extends CrudRepository<Factura, Long>{

}
