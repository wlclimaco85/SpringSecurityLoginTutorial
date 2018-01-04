package com.example.service;

import java.util.List;

import com.example.model.Jogo;
import com.example.model.JogoPorData;
import com.example.model.User;



public interface JogoService {
	public void updateJogo(Jogo empresa);
	public void deleteJogo(Jogo empresa);
	public List<Jogo> findJogoByUser(Jogo empresa);
	public List<Jogo> findAllJogo();
	public List<Jogo> findJogoByUser(User user);
	void saveJogo(List<Jogo> jogos);
	void saveUpdateJogo(Jogo jogos);
	void saveJogoPorData(List<JogoPorData> jogos);
}
