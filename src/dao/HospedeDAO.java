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

public class HospedeDAO {

	private Connection connection;

	public HospedeDAO(Connection connection) {
		this.connection = connection;
	}

	public void salvar(Hospede hospede) {
		try {
			String sql = "INSERT INTO HOSPEDES (NOME, SOBRENOME, DATANASCIMENTO, NACIONALIDADE, TELEFONE, IDRESERVA)"
					+ " VALUES (?, ?, ?, ?, ?, ?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstm.setString(1, hospede.getNome());
				pstm.setString(2, hospede.getSobrenome());
				pstm.setDate(3, new Date(hospede.getNascimento().getTime()));
				pstm.setString(4, hospede.getNacionalidade());
				pstm.setString(5, hospede.getTelefone());
				pstm.setInt(6, hospede.getReserva());

				pstm.execute();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						hospede.setId(rst.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public List<Hospede> listar() {
		List<Hospede> hospedes = new ArrayList<Hospede>();
		try {
			String sql = "SELECT ID, NOME, SOBRENOME, DATANASCIMENTO, NACIONALIDADE, TELEFONE, IDRESERVA FROM HOSPEDES WHERE STATUS = 1";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();

				trasformarResultSetEmHospede(hospedes, pstm);
			}
			return hospedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Hospede> buscar(Integer id) {
		List<Hospede> hospedes = new ArrayList<Hospede>();
		try {
			String sql = "SELECT ID, NOME, SOBRENOME, DATANASCIMENTO, NACIONALIDADE, TELEFONE, IDRESERVA, STATUS FROM HOSPEDES WHERE ID = ? OR SOBRENOME LIKE ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setInt(1, id);
				pstm.execute();

				trasformarResultSetEmHospedeCompleto(hospedes, pstm);
			}
			return hospedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void deletar(Integer id) {
		try (PreparedStatement stm = connection.prepareStatement("DELETE FROM HOSPEDES WHERE ID = ?")) {
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void alterar(Hospede hospede) {

		try (PreparedStatement stm = connection
				.prepareStatement("UPDATE HOSPEDES P SET P.NOME = ?, P.SOBRENOME = ?, P.DATANASCIMENTO = ?, "
						+ "P.NACIONALIDADE = ?, P.TELEFONE = ?, " + "P.IDRESERVA = ? WHERE ID = ?")) {

			stm.setString(1, hospede.getNome());
			stm.setString(2, hospede.getSobrenome());
			stm.setDate(3, new Date(hospede.getNascimento().getTime()));
			stm.setString(4, hospede.getNacionalidade()); 
			stm.setString(5, hospede.getTelefone());
			stm.setInt(6, hospede.getReserva());
			stm.setInt(7, hospede.getId());
			stm.execute();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void trasformarResultSetEmHospede(List<Hospede> hospedes, PreparedStatement pstm) {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Hospede hospede = new Hospede(rst.getInt(1), rst.getString(2), rst.getString(3),rst.getDate(4), rst.getString(5), rst.getString(6), rst.getInt(7));

				hospedes.add(hospede);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void trasformarResultSetEmHospedeCompleto(List<Hospede> hospedes, PreparedStatement pstm) {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Hospede hospede = new Hospede(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getDate(4),
						rst.getString(5), rst.getString(6));
				hospedes.add(hospede);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
