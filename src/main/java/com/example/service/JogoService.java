package com.example.service;

import java.util.Date;
import java.util.List;

import com.example.model.Jogo;
import com.example.model.Jogo.Status;
import com.example.model.JogoPorData;
import com.example.model.User;
import com.example.model.UserConfirmDTO;



public interface JogoService {
	public void updateJogo(Jogo empresa);
	public void deleteJogo(Jogo empresa);
	public List<Jogo> findJogoByUser(Jogo empresa);
	public Jogo findJogoById(Integer empresa);
	public List<Jogo> findAllJogo();
	public List<Jogo> findJogoByUser(User user);
	public List<JogoPorData> findJogoPorDataUserConfirmDTO(Integer JogoId, Date dataJogo);
	void saveJogo(List<Jogo> jogos);
	void saveUpdateJogo(Jogo jogos);
	void saveJogoPorData(List<JogoPorData> jogos);
	void saveJogoPorData(JogoPorData jogoPorData);
	public void updateStatus(Status indisponivel, Integer id);
}
