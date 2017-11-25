package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Notificacoes;
import com.example.repository.NotificacoesRepository;

@Service("notificacoesService")
public class NotificacoesServiceImpl implements NotificacoesService{

	@Autowired
	private NotificacoesRepository notificacoesRepository;

	@Override
	public void insertNotificacoes(Notificacoes notificacoes) {
		notificacoesRepository.save(notificacoes);
		
	}

	@Override
	public void updateNotificacoes(Notificacoes notificacoes) {
		notificacoesRepository.save(notificacoes);
		
	}

	@Override
	public void deleteNotificacoes(Notificacoes notificacoes) {
		notificacoesRepository.delete(notificacoes);
		
	}

	@Override
	public List<Notificacoes> findNotificacoesByUser(String email) {
		return null;//notificacoesRepository.findNotificacoesByEmail(email);
	}

	@Override
	public List<Notificacoes> findAllNotificacoes() {
		return notificacoesRepository.findAll();
	}

	

}
