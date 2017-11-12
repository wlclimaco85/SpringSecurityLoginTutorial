package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "horarios")
public class Horarios{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "horarios_id")
	private int id;
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="doisValor_id")
	private DoisValores dia;
	@Column(name = "hora_inicial")
	private String horaInicial;
	@Column(name = "hora_final")
	private String hora_final;
	@Column(name = "parent_id")
	private String ParentId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public DoisValores getDia() {
		return dia;
	}
	public void setDia(DoisValores dia) {
		this.dia = dia;
	}
	public String getHoraInicial() {
		return horaInicial;
	}
	public void setHoraInicial(String horaInicial) {
		this.horaInicial = horaInicial;
	}
	public String getHora_final() {
		return hora_final;
	}
	public void setHora_final(String hora_final) {
		this.hora_final = hora_final;
	}
	public String getParentId() {
		return ParentId;
	}
	public void setParentId(String parentId) {
		ParentId = parentId;
	}
	
}
