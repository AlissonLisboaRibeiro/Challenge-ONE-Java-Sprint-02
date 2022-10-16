package modelo;

import java.util.Date;

public class Hospede {

	private Integer id;
	private String nome;
	private String sobrenome;
	private Date nascimento;
	private String nacionalidade;
	private String telefone;
	private Integer reserva;
	private Boolean status;

	public Hospede(Integer id, String nome, String sobrenome, Date nascimento, String nacionalidade, String telefone, Integer reserva) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.nascimento = nascimento;
		this.nacionalidade = nacionalidade;
		this.telefone = telefone;
		this.reserva = reserva;
		this.status = true;
	}

	public Hospede(Integer id, String nome, String sobrenome, Date nascimento, String nacionalidade, String telefone) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.nascimento = nascimento;
		this.nacionalidade = nacionalidade;
		this.telefone = telefone;
		this.status = true;
	}
	public Hospede(String nome, String sobrenome, Date nascimento, String nacionalidade, String telefone, Integer reserva) {
		this.id = null;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.nascimento = nascimento;
		this.nacionalidade = nacionalidade;
		this.telefone = telefone;
		this.reserva = reserva;
		this.status = true;
	}
	
	public Hospede(int id, String nome, String sobrenome) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.nascimento = null;
		this.nacionalidade = null;
		this.telefone = null;
		this.status = true;
	}
	public Hospede() {

	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public Integer getReserva() {
		return reserva;
	}

	public Boolean getStatus() {
		return status;
	}

	public String getTelefone() {
		return telefone;
	}

	@Override
	public String toString() {
		return String.format("O hospede é: %d, %s, %s", this.id, this.nome, this.sobrenome);
	}
}
