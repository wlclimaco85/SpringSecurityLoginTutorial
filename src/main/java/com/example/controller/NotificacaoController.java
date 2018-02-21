package com.example.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.framework.api.APIResponse;
import com.example.model.NotificacaoRequest;
import com.example.model.Notificacoes;
import com.example.model.User;
import com.example.service.NotificacoesService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
public class NotificacaoController {

	@Autowired
	private NotificacoesService userService;




	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/notificacao/update", method = RequestMethod.POST)
	public @ResponseBody APIResponse updateMensagem(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
//		List<String> erros = new ArrayList<>();
//		User userExists = userService.findUserByEmail(user.getEmail());
//		if (userExists != null) {
//			erros.add("There is already a user registered with the email provided");
//		}
//
//			userService.saveUser(user);
//			modelAndView.addObject("successMessage", "User has been registered successfully");
//			modelAndView.addObject("user", new User());
//			modelAndView.setViewName("registration");


	HashMap<String, Object> authResp = new HashMap<>();
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	Object token = auth.getCredentials();
	authResp.put("token", token);
	authResp.put("user", user);
	authResp.put("Error", null);


    return APIResponse.toOkResponse(authResp);
	}

	//@CrossOrigin(origins = "*")
	@RequestMapping(value = "/notificacao/delete", method = RequestMethod.POST)
	public @ResponseBody APIResponse deleteMensagem(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		List<String> erros = new ArrayList<>();
//		User userExists = userService.findUserByEmail(user.getEmail());
//		if (userExists != null) {
//			erros.add("There is already a user registered with the email provided");
//		}
//
//			userService.saveUser(user);
//			modelAndView.addObject("successMessage", "User has been registered successfully");
//			modelAndView.addObject("user", new User());
//			modelAndView.setViewName("registration");


	HashMap<String, Object> authResp = new HashMap<>();
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	Object token = auth.getCredentials();
	authResp.put("token", token);
	authResp.put("user", user);
	authResp.put("Error", erros);


    return APIResponse.toOkResponse(authResp);
	}

	//@CrossOrigin(origins = "*")
	@RequestMapping(value = "/notificacao/fetchByUser", method = RequestMethod.POST)
	public @ResponseBody APIResponse fetchByUser(@RequestBody String users)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		NotificacaoRequest notificacaoRequest = mapper.readValue(users, NotificacaoRequest.class);
		List<String> erros = new ArrayList<>();
		List<Notificacoes> notificacaoList = new ArrayList<>();
		if(notificacaoRequest.getRole().getId() == 3)
		{
			notificacaoList = userService.findNotificacoesByEmpr(notificacaoRequest.getEmpresaId());
		}else {
			notificacaoList = userService.findNotificacoesByEmpr(notificacaoRequest.getUserId());
		}
	HashMap<String, Object> authResp = new HashMap<>();
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	Object token = auth.getCredentials();
	authResp.put("token", token);
	authResp.put("notificacaoList", notificacaoList);
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
