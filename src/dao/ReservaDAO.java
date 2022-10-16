package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Hospede;
import modelo.Reserva;

public class ReservaDAO {

	private Connection connection;

	public ReservaDAO(Connection connection) {
		this.connection = connection;
	}

	public void salvar(Reserva reserva) {
		try {
			String sql = "INSERT INTO RESERVAS (DATAENTRADA, DATASAIDA, VALOR, IDFORMAPAGAMENTO)"
					+ " VALUES (?, ?, ?, ?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				// Converter Date do java.Util.Date em java.Sql.Date

				pstm.setDate(1, new Date(reserva.getDataentrada().getTime()));
				pstm.setDate(2, new Date(reserva.getDatasaida().getTime()));
				pstm.setDouble(3, reserva.getValor());
				pstm.setInt(4, reserva.getFormapagamento());

				pstm.execute();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						reserva.setId(rst.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public List<Reserva> listar() {
		List<Reserva> reservas = new ArrayList<Reserva>();
		try {
			String sql = "SELECT R.ID, R.DATAENTRADA, R.DATASAIDA, R.VALOR, FP.ID, FP.DESCRICAO FROM RESERVAS R INNER JOIN FORMAPAGAMENTO FP ON R.IDFORMAPAGAMENTO = FP.ID";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();

				trasformarResultSetEmReserva(reservas, pstm);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Reserva> listarComHospede() {
		try {
			Reserva ultima = null;
			List<Reserva> reservas = new ArrayList<>();

			String sql = "SELECT R.ID, R.DATAENTRADA, R.DATASAIDA, R.VALOR, R.STATUS, H.ID, H.NOME, H.SOBRENOME "
					+ "FROM RESERVAS R INNER JOIN HOSPEDES H ON H.IDRESERVA = R.ID AND R.STATUS = 0"; // Só reservas
																										// ativas

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();

				try (ResultSet rst = pstm.getResultSet()) {
					while (rst.next()) {

						if (ultima == null || !ultima.getId().equals(rst.getString(1))) {
							Reserva reserva = new Reserva(rst.getInt(1), rst.getDate(2), rst.getDate(3),
									rst.getDouble(4), rst.getInt(5));
							reservas.add(reserva);
							ultima = reserva;
						}
						Hospede hospede = new Hospede(rst.getInt(6), rst.getString(7), rst.getString(8));
						ultima.adicionar(hospede);
					}
				}
				return reservas;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Reserva> buscar(Integer id) {
		List<Reserva> reservas = new ArrayList<Reserva>();
		try {

			String sql = "SELECT R.ID, R.DATAENTRADA, R.DATASAIDA, R.VALOR, FP.ID, FP.DESCRICAO"
					+ " FROM RESERVAS R INNER JOIN FORMAPAGAMENTO FP ON R.IDFORMAPAGAMENTO = FP.ID WHERE R.ID = ?";
			// Só reservas ativas

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setInt(1, id);
				pstm.execute();

				trasformarResultSetEmReserva(reservas, pstm);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void deletar(Integer id) {
		try (PreparedStatement stm = connection.prepareStatement("DELETE FROM RESERVAS WHERE ID = ?")) {
			stm.setInt(1, id);
			stm.execute();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void alterar(Reserva reserva) {

		try (PreparedStatement stm = connection
				//
				.prepareStatement("UPDATE RESERVAS R SET R.DATAENTRADA = ?, R.DATASAIDA = ?, R.VALOR = ?, "
						+ "R.IDFORMAPAGAMENTO = ?, R.STATUS = ? WHERE ID = ?")) {

			stm.setDate(1, new Date(reserva.getDataentrada().getTime()));
			stm.setDate(2, new Date(reserva.getDatasaida().getTime()));
			stm.setDouble(3, reserva.getValor());
			stm.setInt(4, reserva.getFormapagamento());
			stm.setInt(5, reserva.getStatus());
			stm.setInt(6, reserva.getId());

			stm.execute();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void trasformarResultSetEmReserva(List<Reserva> reservas, PreparedStatement pstm) {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				// "SELECT R.ID, R.DATAENTRADA, R.DATASAIDA, R.VALOR, FP.ID, FP.DESCRICAO FROM";
				Reserva reserva = new Reserva(rst.getInt(1), rst.getDate(2), rst.getDate(3), rst.getDouble(4),
						rst.getInt(5), rst.getString(6));
				// Reserva(Integer id, Date dataentrada, Date datasaida, double valor, Integer
				// formapagamento)
				reservas.add(reserva);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
