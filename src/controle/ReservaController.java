package controle;

import java.sql.Connection;
import java.util.List;

import conexão.ConnectionFactory;
import dao.ReservaDAO;
import modelo.Reserva;

public class ReservaController {
	private ReservaDAO reservaDAO;

	public ReservaController() {
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.reservaDAO = new ReservaDAO(connection);
	}

	public void deletar(Integer id) {
		this.reservaDAO.deletar(id);
	}

	public void salvar(Reserva reserva) {
		this.reservaDAO.salvar(reserva);
	}

	public List<Reserva> listar() {
		return this.reservaDAO.listar();
	}

	public List<Reserva> buscar(Integer id) {
		return this.reservaDAO.buscar(id);
	}

	public void alterar(Reserva reserva) {
		this.reservaDAO.alterar(reserva);
	}
}
