package com.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.api.APIResponse;
import com.example.model.Empresa;
import com.example.model.Endereco;
import com.example.model.Estado;
import com.example.model.User;
import com.example.service.EmpresaService;


@Controller
public class EmpresaController {

	@Autowired
	private EmpresaService userService;


	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/empresa/insert", method = RequestMethod.POST)
	public @ResponseBody APIResponse createNewMensagem(@Valid Empresa user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		List<String> erros = new ArrayList<>();
		Endereco endereco = new Endereco();
		endereco.setCep("38082243");
		Estado estado = new Estado();
		estado.setId(1);
		endereco.setEstado(estado);
        endereco.setLogradouro("999999999");
        endereco.setNumero("999999999");
        endereco.setCidade("9999999");
		user.setEndereco(endereco);
//		Empresa userExists = userService.findEmpresaByEmail(user.getEmail());
//		if (userExists != null) {
//			erros.add("There is already a user registered with the email provided");
//		}

			userService.saveEmpresa(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
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
		Empresa userExists = userService.findEmpresaByEmail(user.getEmail());
		if (userExists != null) {
			erros.add("There is already a user registered with the email provided");
		}

			userService.updateEmpresa(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());


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
		

			userService.deleteEmpresa(user);



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
		

		List<Empresa> empresas = userService.findEmpresa(user);



	HashMap<String, Object> authResp = new HashMap<>();
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	Object token = auth.getCredentials();
	authResp.put("token", token);
	authResp.put("empresaList", empresas);
	authResp.put("Error", erros);


    return APIResponse.toOkResponse(authResp);
	}

	private void createAuthResponse(User user, HashMap<String, Object> authResp,ArrayList<String> erros) {
        String token = "";
        		//Jwts.builder().setSubject(user.getEmail())
               // .claim("role", user.getRole().name()).setIssuedAt(new Date())
              // .signWith(SignatureAlgorithm.HS256, JWTTokenAuthFilter.JWT_KEY).compact();
        authResp.put("token", token);
        authResp.put("user", user);
        authResp.put("Error", erros);
    }


}
