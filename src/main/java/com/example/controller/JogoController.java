package com.example.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.framework.api.APIResponse;
import com.example.model.Jogo;
import com.example.model.Jogo.Dias;
import com.example.model.Jogo.Status;
import com.example.model.JogoPorData;
import com.example.model.JogoPorData.StatusJogoPorData;
import com.example.model.Notificacoes;
import com.example.model.Notificacoes.NotificacoesStatus;
import com.example.model.User;
import com.example.model.UserJogo2;
import com.example.model.UserJogo2.Admin;
import com.example.model.UserJogo2.StatusUser;
import com.example.service.JogoService;
import com.example.service.JogoUserService;
import com.example.service.NotificacoesService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class JogoController {

	@Autowired
	private JogoService jogoService;
	
	@Autowired
	private NotificacoesService notificacoesService;
	
	@Autowired
	private JogoUserService jogoUserService;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/jogo/update", method = RequestMethod.POST)
	public @ResponseBody APIResponse createNewMensagem(@RequestBody String users)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Jogo user = mapper.readValue(users, Jogo.class);
		ModelAndView modelAndView = new ModelAndView();
		List<String> erros = new ArrayList<>();

		
		Notificacoes notificacoes = new Notificacoes();
		switch (user.getStatus()) {
		case DISPONIVEL:
			jogoService.saveUpdateJogo(user);
			notificacoes = new Notificacoes("DISPONIVEL", new Date(), "Titulo DISPONIVEL", NotificacoesStatus.NAOLIDO, 10, 8);
			break;
		case ACONFIRMAR:
			jogoService.saveUpdateJogo(user);
			List<UserJogo2> userJogos = new ArrayList<>();
			userJogos.add(new UserJogo2(user.getUser_id(),user.getId(),StatusUser.CONFIRMADO,Admin.SIM));
			jogoUserService.saveUserJogo(userJogos);
			notificacoes = new Notificacoes("ACONFIRMAR", new Date(), "Titulo ACONFIRMAR", NotificacoesStatus.NAOLIDO, 10, 8);
			break;
		case OCUPADO:
			jogoService.saveUpdateJogo(user);
			notificacoes = new Notificacoes("OCUPADO", new Date(), "Titulo OCUPADO", NotificacoesStatus.NAOLIDO, 10,8);
			break;
		case INDISPONIVEL:
			jogoService.saveUpdateJogo(user);
			notificacoes = new Notificacoes("INDISPONIVEL", new Date(), "Titulo INDISPONIVEL", NotificacoesStatus.NAOLIDO, 10,8);
			break;
		case CONFIRMAR:
			jogoService.saveUpdateJogo(user);
			notificacoes = new Notificacoes("CONFIRMAR", new Date(), "Titulo CONFIRMAR", NotificacoesStatus.NAOLIDO, 10,8);
			break;
		case DESMARCAR:
			notificacoes = new Notificacoes("DESMARCAR", new Date(), "Titulo DESMARCAR", NotificacoesStatus.NAOLIDO, 10,8);
			break;
			
		case SOLICITAR:
			List<UserJogo2> userJogos1 = new ArrayList<>();
			userJogos1.add(new UserJogo2(user.getUser_id(),user.getId(),StatusUser.SOLICITADO,Admin.NAO));
			jogoUserService.saveUserJogo(userJogos1);
			notificacoes = new Notificacoes("SOLICITAR", new Date(), "Titulo SOLICITAR", NotificacoesStatus.NAOLIDO, 10,8);
			break;

		default:
			break;
		}
		notificacoesService.insertNotificacoes(notificacoes);
		HashMap<String, Object> authResp = new HashMap<>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object token = auth.getCredentials();
		authResp.put("token", token);
		authResp.put("user", user);
		authResp.put("Error", erros);

		return APIResponse.toOkResponse(authResp);
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/jogo/updateJogoPorData", method = RequestMethod.POST)
	public @ResponseBody APIResponse updateJogoPorData(@RequestBody String users)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		JogoPorData user = mapper.readValue(users, JogoPorData.class);
		ModelAndView modelAndView = new ModelAndView();
		List<String> erros = new ArrayList<>();

		
		Notificacoes notificacoes = new Notificacoes();
		switch (user.getStatus()) {
		case CONFIRMADO:
			jogoService.saveJogoPorData(user);
			notificacoes = new Notificacoes("CONFIRMADO", new Date(), "Titulo DISPONIVEL", NotificacoesStatus.NAOLIDO, 10, 8);
			break;
		case NAOVO:
			jogoService.saveJogoPorData(user);
			notificacoes = new Notificacoes("NAOVO", new Date(), "Titulo ACONFIRMAR", NotificacoesStatus.NAOLIDO, 10, 8);
			break;
		case TALVEZ:
			jogoService.saveJogoPorData(user);
			notificacoes = new Notificacoes("TALVEZ", new Date(), "Titulo OCUPADO", NotificacoesStatus.NAOLIDO, 10,8);
			break;

		default:
			break;
		}
		notificacoesService.insertNotificacoes(notificacoes);
		HashMap<String, Object> authResp = new HashMap<>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object token = auth.getCredentials();
		authResp.put("token", token);
		authResp.put("user", user);
		authResp.put("Error", erros);

		return APIResponse.toOkResponse(authResp);
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/jogo/aprovarJogador", method = RequestMethod.POST)
	public @ResponseBody APIResponse aprovarJogador(@RequestBody String users)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		UserJogo2 user = mapper.readValue(users, UserJogo2.class);
		ModelAndView modelAndView = new ModelAndView();
		List<String> erros = new ArrayList<>();

		
		Notificacoes notificacoes = new Notificacoes();
		switch (user.getStatus_user()) {
		case CONFIRMADO:
			jogoUserService.saveUserJogo(Arrays.asList(user));
			notificacoes = new Notificacoes("CONFIRMADO", new Date(), "Titulo DISPONIVEL", NotificacoesStatus.NAOLIDO, user.getUser_id(),user.getJogo_id());
			break;
		case RECUSADO:
			jogoUserService.saveUserJogo(Arrays.asList(user));
			notificacoes = new Notificacoes("NAOVO", new Date(), "Titulo ACONFIRMAR", NotificacoesStatus.NAOLIDO, user.getUser_id(),user.getJogo_id());
			break;
		default:
			break;
		}
		notificacoesService.insertNotificacoes(notificacoes);
		HashMap<String, Object> authResp = new HashMap<>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object token = auth.getCredentials();
		authResp.put("token", token);
		authResp.put("user", user);
		authResp.put("Error", erros);

		return APIResponse.toOkResponse(authResp);
	}
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/jogo/insertUserJogo", method = RequestMethod.POST)
	public @ResponseBody APIResponse insertJogoPorData(@RequestBody String users)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		UserJogo2 user = mapper.readValue(users, UserJogo2.class);
		ModelAndView modelAndView = new ModelAndView();
		List<String> erros = new ArrayList<>();

		
		Notificacoes notificacoes = new Notificacoes();
		switch (user.getStatus_user()) {
		case CONFIRMADO:
			notificacoes = new Notificacoes("CONFIRMADO", new Date(), "Titulo DISPONIVEL", NotificacoesStatus.NAOLIDO, user.getUser_id(),user.getJogo_id());
			break;
		case SOLICITADO:
			notificacoes = new Notificacoes("NAOVO", new Date(), "Titulo ACONFIRMAR", NotificacoesStatus.NAOLIDO, user.getUser_id(),user.getJogo_id());
			break;
		case RECUSADO:
			notificacoes = new Notificacoes("TALVEZ", new Date(), "Titulo OCUPADO", NotificacoesStatus.NAOLIDO, user.getUser_id(),user.getJogo_id());
			break;

		default:
			break;
		}
		notificacoesService.insertNotificacoes(notificacoes);
		jogoUserService.saveUserJogo(Arrays.asList(user));
		HashMap<String, Object> authResp = new HashMap<>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object token = auth.getCredentials();
		authResp.put("token", token);
		authResp.put("userJogo", user);
		authResp.put("Error", erros);

		return APIResponse.toOkResponse(authResp);
	}
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/jogo/createNovo", method = RequestMethod.POST)
	public @ResponseBody APIResponse createNovo(@RequestBody String jog)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
	//	Jogo user = mapper.readValue(jog, Jogo.class);
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // você pode usar outras máscaras
		
		List<Jogo> jogos = jogoService.findAllJogo();
		List<JogoPorData> jogosData = new ArrayList<JogoPorData>();
		for (Jogo jogo : jogos) {
			System.out.println(jogo.getStatus());
			if(jogo.getStatus().equals(Status.INDISPONIVEL))
			{
				for (UserJogo2 user : jogo.getUsersJogo2()) {
					if(user.getStatus_user().equals(StatusUser.CONFIRMADO))
					{
						GregorianCalendar gc = new GregorianCalendar();
				jogosData.add(new JogoPorData(shouldDownloadFile2(jogo.getDia(),gc,jogo.getHoraInicial()).getTime(),shouldDownloadFile2(jogo.getDia(),gc,jogo.getHoraFinal()).getTime(), jogo.getId(), user.getUser_id(), StatusJogoPorData.ACONFIRMAR, 0, 0,
						jogo.getQuadraId()));
					}
				}
			}
		}
		jogoService.saveJogoPorData(jogosData);
		HashMap<String, Object> authResp = new HashMap<>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object token = auth.getCredentials();
		authResp.put("token", token);
		authResp.put("jogo", jogos);
		authResp.put("Error", "");

		return APIResponse.toOkResponse(authResp);
	}
	//
	// //@CrossOrigin(origins = "*")
	// @RequestMapping(value = "/raxa/delete", method = RequestMethod.POST)
	// public @ResponseBody APIResponse deleteMensagem(@Valid User user,
	// BindingResult bindingResult) {
	// ModelAndView modelAndView = new ModelAndView();
	// List<String> erros = new ArrayList<>();
	// User userExists = userService.findUserByEmail(user.getEmail());
	// if (userExists != null) {
	// erros.add("There is already a user registered with the email provided");
	// }
	//
	// userService.saveUser(user);
	// modelAndView.addObject("successMessage", "User has been registered
	// successfully");
	// modelAndView.addObject("user", new User());
	// modelAndView.setViewName("registration");
	//
	//
	// HashMap<String, Object> authResp = new HashMap<>();
	// Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	//
	// Object token = auth.getCredentials();
	// authResp.put("token", token);
	// authResp.put("user", user);
	// authResp.put("Error", erros);
	//
	//
	// return APIResponse.toOkResponse(authResp);
	// }
	//
	// //@CrossOrigin(origins = "*")
	// @RequestMapping(value = "/raxa/fetchByUser", method = RequestMethod.POST)
	// public @ResponseBody APIResponse fetchByUser(@Valid User user, BindingResult
	// bindingResult) {
	// ModelAndView modelAndView = new ModelAndView();
	// List<String> erros = new ArrayList<>();
	// User userExists = userService.findUserByEmail(user.getEmail());
	// if (userExists != null) {
	// erros.add("There is already a user registered with the email provided");
	// }
	//
	// userService.saveUser(user);
	// modelAndView.addObject("successMessage", "User has been registered
	// successfully");
	// modelAndView.addObject("user", new User());
	// modelAndView.setViewName("registration");
	//
	//
	// HashMap<String, Object> authResp = new HashMap<>();
	// Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	//
	// Object token = auth.getCredentials();
	// authResp.put("token", token);
	// authResp.put("user", user);
	// authResp.put("Error", erros);
	//
	//
	// return APIResponse.toOkResponse(authResp);
	// }
	//
	// private void createAuthResponse(User user, HashMap<String, Object>
	// authResp,ArrayList<String> erros) {
	// String token = "";
	// //Jwts.builder().setSubject(user.getEmail())
	// // .claim("role", user.getRole().name()).setIssuedAt(new Date())
	// // .signWith(SignatureAlgorithm.HS256, JWTTokenAuthFilter.JWT_KEY).compact();
	// authResp.put("token", token);
	// authResp.put("user", user);
	// authResp.put("Error", erros);
	// }
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/jogo/findJogoByUser", method = RequestMethod.POST)
	public ResponseEntity<List<Jogo>> findAllQuadraById(@RequestBody String users)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(users, User.class);

		List<Jogo> quadra = jogoService.findJogoByUser(user);
		for (Jogo jogo : quadra) {
			HashMap<Integer, StatusJogoPorData> userConfirm = new HashMap<Integer, StatusJogoPorData>();
			for (JogoPorData jogoPorData : jogo.getJogos()) {
				List<JogoPorData> userConfirmDTO = jogoService.findJogoPorDataUserConfirmDTO(jogo.getId(),jogoPorData.getDataInicial());
				for (JogoPorData jogoPorData2 : userConfirmDTO) {
					userConfirm.put(jogoPorData2.getUser_id(), jogoPorData2.getStatus());
				}
				jogoPorData.setUserConfirm(userConfirm);
				//jogo.setUserConfirm(new ArrayList<>());
				//jogo.getUserConfirm().add(new UserConfirmDTO(userConfirmDTO));
		}}

		return new ResponseEntity<List<Jogo>>(quadra, HttpStatus.OK);
	}
	public GregorianCalendar shouldDownloadFile2(Dias dia,GregorianCalendar gc,String hInc)
	{
		Integer a= 0;
		if (gc.get(GregorianCalendar.DAY_OF_WEEK) == gc.SUNDAY) {
			switch (dia) { 
			case DOMINGO:
					a = 0;
				break;
			case SEGUNDA:
				a = 1;
				break;
			case TERCA:
				a = 2;
				break;
			case QUARTA:
				a = 3;
				break;
			case QUINTA:
				a = 4;
				break;
			case SEXTA:
				a = 5;
				break;
			case SABADO:
				a = 6;
				break;
			}
		}else
		if (gc.get(GregorianCalendar.DAY_OF_WEEK) == gc.MONDAY) {
			switch (dia) { 
			case DOMINGO:
					a = 6;
				break;
			case SEGUNDA:
				a = 0;
				break;
			case TERCA:
				a = 1;
				break;
			case QUARTA:
				a = 2;
				break;
			case QUINTA:
				a = 3;
				break;
			case SEXTA:
				a = 4;
				break;
			case SABADO:
				a = 5;
				break;
			}
		}else
		if (gc.get(GregorianCalendar.DAY_OF_WEEK) == gc.TUESDAY) {
			switch (dia) { 
			case DOMINGO:
					a = 5;
				break;
			case SEGUNDA:
				a = 6;
				break;
			case TERCA:
				a = 0;
				break;
			case QUARTA:
				a = 1;
				break;
			case QUINTA:
				a = 2;
				break;
			case SEXTA:
				a = 3;
				break;
			case SABADO:
				a = 4;
				break;
			}
		}else

		if (gc.get(GregorianCalendar.DAY_OF_WEEK) == gc.WEDNESDAY) {
			switch (dia) { 
			case DOMINGO:
					a = 4;
				break;
			case SEGUNDA:
				a = 5;
				break;
			case TERCA:
				a = 6;
				break;
			case QUARTA:
				a = 0;
				break;
			case QUINTA:
				a = 1;
				break;
			case SEXTA:
				a = 2;
				break;
			case SABADO:
				a = 3;
				break;
			}
		}else
		if (gc.get(GregorianCalendar.DAY_OF_WEEK) == gc.THURSDAY) {
			switch (dia) { 
			case DOMINGO:
					a = 3;
				break;
			case SEGUNDA:
				a = 4;
				break;
			case TERCA:
				a = 5;
				break;
			case QUARTA:
				a = 6;
				break;
			case QUINTA:
				a = 0;
				break;
			case SEXTA:
				a = 1;
				break;
			case SABADO:
				a = 2;
				break;
			}
		}else
		if (gc.get(GregorianCalendar.DAY_OF_WEEK) == gc.FRIDAY) {
			switch (dia) { 
			case DOMINGO:
					a = 2;
				break;
			case SEGUNDA:
				a = 3;
				break;
			case TERCA:
				a = 4;
				break;
			case QUARTA:
				a = 5;
				break;
			case QUINTA:
				a = 6;
				break;
			case SEXTA:
				a = 1;
				break;
			case SABADO:
				a = 2;
				break;
			}
		}else
		if (gc.get(GregorianCalendar.DAY_OF_WEEK) == gc.SATURDAY) {
			switch (dia) { 
			case DOMINGO:
					a = 1;
				break;
			case SEGUNDA:
				a = 2;
				break;
			case TERCA:
				a = 3;
				break;
			case QUARTA:
				a = 4;
				break;
			case QUINTA:
				a = 5;
				break;
			case SEXTA:
				a = 6;
				break;
			case SABADO:
				a = 7;
				break;
			}
		}
		
		gc.add(gc.DATE, a);
		String[] rabbitmqUserInfo = hInc.split(":");
		gc.set(GregorianCalendar.HOUR_OF_DAY,Integer.parseInt(rabbitmqUserInfo[0]));
		gc.set(GregorianCalendar.MINUTE,Integer.parseInt(rabbitmqUserInfo[1]));
		
		//gc.add(gc.HOUR, Integer.parseInt(rabbitmqUserInfo[0]));
	//	gc.add(gc.MINUTE, Integer.parseInt(rabbitmqUserInfo[0]));
		return gc;
	}
}
