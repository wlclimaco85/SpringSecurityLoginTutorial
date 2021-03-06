package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Empresa;
import com.example.model.Quadra;
import com.example.repository.QuadraRepository;
import com.example.service.QuadraService;

@Service("quadraService")
public class QuadraServiceImpl implements QuadraService{

	@Autowired
	private QuadraRepository quadraRepository;

	@Override
	public List<Quadra> findQuadraByUser(Integer userId) {

		return quadraRepository.findQuadraByUser(userId);
	}

	@Override
	public List<Quadra> findAllQuadra() {

		return quadraRepository.findAll();
	}

	@Override
	public List<Empresa> findAllQuadraByEmpresa(Integer empresaId) {

		return quadraRepository.findAllQuadraByEmpresa(empresaId);
	}

	@Override
	public Quadra findAllQuadraById(Integer id) {

		return quadraRepository.findAllQuadraById(id);
	}



}
