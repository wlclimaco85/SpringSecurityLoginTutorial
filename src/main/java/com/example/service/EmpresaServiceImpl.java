package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Empresa;
import com.example.repository.EmpresaRepository;

@Service("empresaService")
public class EmpresaServiceImpl implements EmpresaService{

	@Autowired
	private EmpresaRepository userRepository;

	@Override
	
	public Empresa findEmpresaByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	@Override
	public void saveEmpresa(Empresa user) {
		
		userRepository.save(user);
		
	}
	@Override
	public void updateEmpresa(Empresa user) {
		userRepository.save(user);
		
	}
	@Override
	public void deleteEmpresa(Empresa user) {
		userRepository.delete(user);
	}
	@Override
	public List<Empresa> findEmpresa(Empresa user) {
		return userRepository.findAll();
		
	}
	
	

}
