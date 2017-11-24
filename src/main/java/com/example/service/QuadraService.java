package com.example.service;

import java.util.List;

import com.example.model.Empresa;
import com.example.model.Quadra;



public interface QuadraService {

	public List<Quadra> findQuadraByUser(Integer userId);
	public List<Quadra> findAllQuadra();
	public List<Empresa> findAllQuadraByEmpresa(Integer empresaId);
	public List<Quadra> findAllQuadraById(Integer id);

}
