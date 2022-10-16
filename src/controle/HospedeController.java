package controle;

import java.sql.Connection;
import java.util.List;

import conexão.ConnectionFactory;
import dao.HospedeDAO;
import modelo.Hospede;

public class HospedeController {
	private HospedeDAO hospedeDAO;

	public HospedeController() {
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.hospedeDAO = new HospedeDAO(connection);
	}

	public void deletar(Integer id) {
		this.hospedeDAO.deletar(id);
	}

	public void salvar(Hospede hospede) {
		this.hospedeDAO.salvar(hospede);
	}

	public List<Hospede> listar() {
		return this.hospedeDAO.listar();
	}

	public List<Hospede> buscar(Integer id) {
		return this.hospedeDAO.buscar(id);
	}

	public void alterar(Hospede hospede) {
		this.hospedeDAO.alterar(hospede);
	}
}
