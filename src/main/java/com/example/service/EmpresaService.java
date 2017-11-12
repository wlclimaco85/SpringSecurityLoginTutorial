package com.example.service;

import java.util.List;

import com.example.model.Empresa;



public interface EmpresaService {
	public Empresa findEmpresaByEmail(String email);
	public void saveEmpresa(Empresa user);
	public void updateEmpresa(Empresa user);
	public void deleteEmpresa(Empresa user);
	public List<Empresa> findEmpresa(Empresa user);
}
