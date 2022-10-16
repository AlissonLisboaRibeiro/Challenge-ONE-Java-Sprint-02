package modelo;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Reserva {

	private Integer id;
	private Date dataEntrada;
	private Date dataSaida;
	private Double valor;
	private Integer formaPagamento;
	private String descricaoFormaPagamento;
	private static Integer status = 0;
	private List<Hospede> hospedes = new ArrayList<Hospede>();

	@SuppressWarnings("static-access")
	public Reserva(Date dataEntrada, Date dataSaida, double valor, Integer formaPagamento) {
		this.id = null;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.valor = valor;
		this.formaPagamento = formaPagamento;
		this.status = 0; // Aberta
	}
	
	public Reserva(Integer id, Date dataentrada, Date datasaida, double valor, Integer idformaPagamento, String formaPagamento) {
		this.id = id;
		this.dataEntrada = dataentrada;
		this.dataSaida = datasaida;
		this.valor = valor;
		this.formaPagamento = idformaPagamento;
		this.descricaoFormaPagamento = formaPagamento;
		//this.status = 0; // Aberta
	}	
	
	public Reserva(Integer id, Date dataentrada, Date datasaida, double valor, Integer formapagamento) {
		this.id = id;
		this.dataEntrada = dataentrada;
		this.dataSaida = datasaida;
		this.valor = valor;
		this.formaPagamento = formapagamento;
		//this.status = 0; // Aberta
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataentrada() {
		return dataEntrada;
	}

	public Date getDatasaida() {
		return dataSaida;
	}

	public Integer getFormapagamento() {
		return formaPagamento;
	}

	public String getDescricaoFormaPagamento() {
		return descricaoFormaPagamento;
	}

	public Double getValor() {
		return valor;
	}

	public Integer getStatus() {
		return status;
	}

	public void adicionar(Hospede hospede) {
		hospedes.add(hospede);
	}

	public List<Hospede> getHospedes() {
		return hospedes;
	}

	@Override
	public String toString() {
		return String.format("A reserva é: %d de %s até %s", this.id, this.dataEntrada, this.dataSaida);
	}
}
