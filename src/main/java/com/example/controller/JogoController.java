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

import com.example.api.APIResponse;
import com.example.model.Jogo;
import com.example.model.User;
import com.example.service.JogoService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
public class JogoController {

	@Autowired
	private JogoService jogoService;


	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/jogo/update", method = RequestMethod.POST)
	public @ResponseBody APIResponse createNewMensagem(@RequestBody String users)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Jogo user = mapper.readValue(users, Jogo.class);
		ModelAndView modelAndView = new ModelAndView();
		List<String> erros = new ArrayList<>();

		jogoService.saveUpdateJogo(user);
		
		HashMap<String, Object> authResp = new HashMap<>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object token = auth.getCredentials();
		authResp.put("token", token);
		authResp.put("user", user);
		authResp.put("Error", erros);

		return APIResponse.toOkResponse(authResp);
	}
//
//	//@CrossOrigin(origins = "*")
//	@RequestMapping(value = "/raxa/delete", method = RequestMethod.POST)
//	public @ResponseBody APIResponse deleteMensagem(@Valid User user, BindingResult bindingResult) {
//		ModelAndView modelAndView = new ModelAndView();
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
//
//
//	HashMap<String, Object> authResp = new HashMap<>();
//	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//	Object token = auth.getCredentials();
//	authResp.put("token", token);
//	authResp.put("user", user);
//	authResp.put("Error", erros);
//
//
//    return APIResponse.toOkResponse(authResp);
//	}
//
//	//@CrossOrigin(origins = "*")
//	@RequestMapping(value = "/raxa/fetchByUser", method = RequestMethod.POST)
//	public @ResponseBody APIResponse fetchByUser(@Valid User user, BindingResult bindingResult) {
//		ModelAndView modelAndView = new ModelAndView();
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
//
//
//	HashMap<String, Object> authResp = new HashMap<>();
//	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//	Object token = auth.getCredentials();
//	authResp.put("token", token);
//	authResp.put("user", user);
//	authResp.put("Error", erros);
//
//
//    return APIResponse.toOkResponse(authResp);
//	}
//
//	private void createAuthResponse(User user, HashMap<String, Object> authResp,ArrayList<String> erros) {
//        String token = "";
//        		//Jwts.builder().setSubject(user.getEmail())
//               // .claim("role", user.getRole().name()).setIssuedAt(new Date())
//              // .signWith(SignatureAlgorithm.HS256, JWTTokenAuthFilter.JWT_KEY).compact();
//        authResp.put("token", token);
//        authResp.put("user", user);
//        authResp.put("Error", erros);
//    }


}
