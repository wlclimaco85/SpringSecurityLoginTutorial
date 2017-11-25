package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Notificacoes;

@Repository("notificacoesRepository")
public interface NotificacoesRepository extends JpaRepository<Notificacoes, Long> {
	
	//List<Notificacoes> findNotificacoesByEmail(String email);
}
