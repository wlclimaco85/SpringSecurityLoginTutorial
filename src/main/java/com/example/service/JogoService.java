package com.example.service;

import java.util.List;

import com.example.model.Jogo;



public interface JogoService {
	public void updateJogo(Jogo empresa);
	public void deleteJogo(Jogo empresa);
	public List<Jogo> findJogoByUser(Jogo empresa);
	public List<Jogo> findAllJogo();
	void saveJogo(List<Jogo> jogos);
	void saveUpdateJogo(Jogo jogos);
}
