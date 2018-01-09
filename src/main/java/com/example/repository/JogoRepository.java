package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.Jogo;

@Repository("jogoRepository")
public interface JogoRepository extends JpaRepository<Jogo, Long> {

	//void save(List<JogoPorData> jogos);
	
//	Empresa findByEmail(String email);
	// custom query example and return a stream
    @Query("SELECT p  FROM UserJogo2 u, Jogo p WHERE u.user_id=p.user_id and p.user_id= :email")
    List<Jogo> findJogoByUser(@Param("email") Integer user);
	//List<Jogo> findJogoByUser(User user);
}
