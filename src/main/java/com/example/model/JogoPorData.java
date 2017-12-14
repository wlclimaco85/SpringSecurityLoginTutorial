package com.example.model;

import java.util.ArrayList;
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
import com.example.model.UserConfirmar.Status;

@Entity
@Table(name = "jogoPorData")
public class JogoPorData{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "jogoPorData_id")
	private Integer id;
	
	@Column(name = "Data")
	private Date data;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "User_confirmar", joinColumns = @JoinColumn(name = "jogo_id"))
	private List<UserConfirmar> users;
	
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
	
	
	
	
	public JogoPorData(Date data, List<User> users, int quadraId,
			String horaInicial, String horaFinal, Dias dia) {
		super();
		
		this.data = data;
		List<UserNota> usersNotas = new ArrayList<UserNota>();
		List<UserGol> userGols = new ArrayList<UserGol>();
		List<UserConfirmar> userConfirmars = new ArrayList<UserConfirmar>();
		for (User userList : users) {
			usersNotas.add(new UserNota(data,"0", userList.getId()));
			userGols.add(new UserGol(data,0, userList.getId()));
			userConfirmars.add(new UserConfirmar(data,Status.ACONFIRMAR, userList.getId()));
		}
		this.usersNota = usersNotas;
		this.usersGol = userGols;
		this.users = userConfirmars;
		this.quadraId = quadraId;
		this.horaInicial = horaInicial;
		this.horaFinal = horaFinal;
		this.dia = dia;
	}
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

	public List<UserConfirmar> getUsers() {
		return users;
	}
	public void setUsers(List<UserConfirmar> users) {
		this.users = users;
	}
	public void setId(Integer id) {
		this.id = id;
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
