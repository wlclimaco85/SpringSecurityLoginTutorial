package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Empresa;
import com.example.model.Jogo;
import com.example.repository.JogoRepository;

@Service("jogoService")
public class JogoServiceImpl implements JogoService{

	@Autowired
	private JogoRepository jogoRepository;


	@Override
	public void updateJogo(Jogo user) {
		jogoRepository.save(user);
		
	}
	@Override
	public void deleteJogo(Jogo user) {
		jogoRepository.delete(user);
	}
	//@Override
	//public List<Jogo> findJogoByUser(Jogo user) {
	//	return jogoRepository.findJogoByUser(user);
		
	//}

	@Override
	public List<Jogo> findAllJogo() {
		return jogoRepository.findAll();
	}
	@Override
	public List<Jogo> findJogoByUser(Jogo jogo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveJogo(List<Jogo> jogos) {
		jogoRepository.save(jogos);
	}
	
	

}
