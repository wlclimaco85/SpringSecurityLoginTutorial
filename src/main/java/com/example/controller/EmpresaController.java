package com.example.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.api.APIResponse;
import com.example.model.Empresa;
import com.example.model.User;
import com.example.service.EmpresaService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class EmpresaController {

	@Autowired
	private EmpresaService empresaService;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/empresa/insert", method = RequestMethod.POST)
	public @ResponseBody APIResponse createNewMensagem(@RequestBody String users)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Empresa user = mapper.readValue(users, Empresa.class);
		ModelAndView modelAndView = new ModelAndView();
		List<String> erros = new ArrayList<>();

		empresaService.saveEmpresa(user);
		modelAndView.addObject("successMessage", "User has been registered successfully");
		modelAndView.addObject("user", new Empresa());
		modelAndView.setViewName("registration");

		HashMap<String, Object> authResp = new HashMap<>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object token = auth.getCredentials();
		authResp.put("token", token);
		authResp.put("user", user);
		authResp.put("Error", erros);

		return APIResponse.toOkResponse(authResp);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/empresa/update", method = RequestMethod.POST)
	public @ResponseBody APIResponse updateMensagem(@Valid Empresa user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		List<String> erros = new ArrayList<>();
		Empresa userExists = empresaService.findEmpresaByEmail(user.getEmail());
		if (userExists != null) {
			erros.add("There is already a user registered with the email provided");
		}

		empresaService.updateEmpresa(user);
		modelAndView.addObject("successMessage", "User has been registered successfully");
		modelAndView.addObject("user", new Empresa());

		HashMap<String, Object> authResp = new HashMap<>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Object token = auth.getCredentials();
		authResp.put("token", token);
		authResp.put("user", user);
		authResp.put("Error", erros);

		return APIResponse.toOkResponse(authResp);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/empresa/delete", method = RequestMethod.POST)
	public @ResponseBody APIResponse deleteMensagem(@Valid Empresa user, BindingResult bindingResult) {

		List<String> erros = new ArrayList<>();

		empresaService.deleteEmpresa(user);

		HashMap<String, Object> authResp = new HashMap<>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Object token = auth.getCredentials();
		authResp.put("token", token);
		authResp.put("user", user);
		authResp.put("Error", erros);

		return APIResponse.toOkResponse(authResp);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/empresa/fetchByUser", method = RequestMethod.POST)
	public @ResponseBody APIResponse fetchByUser(@Valid Empresa user, BindingResult bindingResult) {

		List<String> erros = new ArrayList<>();

		List<Empresa> empresas = empresaService.findEmpresaByUser(user);

		HashMap<String, Object> authResp = new HashMap<>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Object token = auth.getCredentials();
		authResp.put("token", token);
		authResp.put("empresaList", empresas);
		authResp.put("Error", erros);

		return APIResponse.toOkResponse(authResp);
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/empresa/fetchAllEmpresa", method = RequestMethod.POST)
	public ResponseEntity<List<Empresa>> fetchAllEmpresa(@Valid Empresa user, BindingResult bindingResult) {

		List<String> erros = new ArrayList<>();

		List<Empresa> empresas = empresaService.findAllEmpresa();

		HashMap<String, Object> authResp = new HashMap<>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Object token = auth.getCredentials();
		authResp.put("token", token);
		authResp.put("empresaList", empresas);
		authResp.put("Error", erros);

		return new ResponseEntity<List<Empresa>>(empresas, HttpStatus.OK);
	}



}
