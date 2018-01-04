package com.example.service;

import java.util.List;

import com.example.model.UserJogo2;



public interface JogoUserService {
	public void updateJogoUser(UserJogo2 empresa);
	public void deleteJogoUser(UserJogo2 empresa);
	void saveUserJogo(List<UserJogo2> jogos);

}
