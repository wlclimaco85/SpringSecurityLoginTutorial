package com.example.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.example.model.Jogo.Dias;

@Entity
@Table(name = "jogoPorData")
public class JogoPorData{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "jogoPorData_id")
	private int id;
	
	@Column(name = "Data")
	private Date data;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "User_confirmar", joinColumns = @JoinColumn(name = "jogo_id"))
	private List<User> users;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_nota", joinColumns = @JoinColumn(name = "jogo_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<UserNota> usersNota;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_gols", joinColumns = @JoinColumn(name = "jogo_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<UserGol> usersGol;
	
	@Column(name = "quadra_id")
	private int quadraId;
	
	@Column(name = "horaInicial")
	private String horaInicial;
	
	@Column(name = "horaFinal")
	private String horaFinal;
	
	@Column(name = "dia")
	private Dias dia;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<UserNota> getUsersNota() {
		return usersNota;
	}
	public void setUsersNota(List<UserNota> usersNota) {
		this.usersNota = usersNota;
	}
	public List<UserGol> getUsersGol() {
		return usersGol;
	}
	public void setUsersGol(List<UserGol> usersGol) {
		this.usersGol = usersGol;
	}
	public int getQuadraId() {
		return quadraId;
	}
	public void setQuadraId(int quadraId) {
		this.quadraId = quadraId;
	}
	public String getHoraInicial() {
		return horaInicial;
	}
	public void setHoraInicial(String horaInicial) {
		this.horaInicial = horaInicial;
	}
	public String getHoraFinal() {
		return horaFinal;
	}
	public void setHoraFinal(String horaFinal) {
		this.horaFinal = horaFinal;
	}
	public Dias getDia() {
		return dia;
	}
	public void setDia(Dias dia) {
		this.dia = dia;
	}
		
}
