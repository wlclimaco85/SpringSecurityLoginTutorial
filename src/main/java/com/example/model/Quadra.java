package com.example.model;

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

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "quadra")
public class Quadra{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "quadra_id")
	private int id;
	
	@Column(name = "nome")
	@NotEmpty(message = "*Please provide an cep")
	private String nome;
	
	@Column(name = "descricao")
	private String descricao;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "horario_quadra", joinColumns = @JoinColumn(name="parent_id", referencedColumnName="quadra_id"), inverseJoinColumns = @JoinColumn(name = "horarios_id"))
	private List<Horarios> horarioAberto;
	
	@Column(name = "valor")
	private Float valor;
	
	@Column(name = "comBola")
	private int comBola;
	
	@Column(name = "valorBola")
	private Float valorBola;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Horarios> getHorarioAberto() {
		return horarioAberto;
	}

	public void setHorarioAberto(List<Horarios> horarioAberto) {
		this.horarioAberto = horarioAberto;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public int getComBola() {
		return comBola;
	}

	public void setComBola(int comBola) {
		this.comBola = comBola;
	}

	public Float getValorBola() {
		return valorBola;
	}

	public void setValorBola(Float valorBola) {
		this.valorBola = valorBola;
	}

	

	
}