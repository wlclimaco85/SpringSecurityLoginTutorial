package com.example.service;

import java.util.List;

import com.example.model.Notificacoes;



public interface NotificacoesService {
	public void insertNotificacoes(Notificacoes notificacoes);
	public void updateNotificacoes(Notificacoes notificacoes);
	public void deleteNotificacoes(Notificacoes notificacoes);
	public List<Notificacoes> findNotificacoesByUser(Integer email);
	public List<Notificacoes> findNotificacoesByEmpr(Integer email);
	public List<Notificacoes> findNotificacoesByJogo(Integer email);
	public List<Notificacoes> findAllNotificacoes();

}
