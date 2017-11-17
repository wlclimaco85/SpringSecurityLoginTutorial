package com.example.service;

import java.util.List;

import com.example.model.Empresa;



public interface EmpresaService {
	public Empresa findEmpresaByEmail(String email);
	public void saveEmpresa(Empresa empresa);
	public void updateEmpresa(Empresa empresa);
	public void deleteEmpresa(Empresa empresa);
	public List<Empresa> findEmpresaByUser(Empresa empresa);
	public List<Empresa> findAllEmpresa();
}
