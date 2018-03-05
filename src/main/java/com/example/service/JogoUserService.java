package com.example.service;

import java.util.List;

import com.example.model.UserJogo2;
import com.example.model.UserJogo2.StatusUser;



public interface JogoUserService {
	public void updateStatus(StatusUser status,Integer empresa);
	public void updateJogoUser(UserJogo2 empresa);
	public void deleteJogoUser(UserJogo2 empresa);
	void saveUserJogo(List<UserJogo2> jogos);
	public List<UserJogo2> findJogoUserByJogoId(Integer jogoId);
}
