package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import controle.HospedeController;
import controle.ReservaController;
import modelo.Hospede;
import modelo.Reserva;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class Buscar extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHospedes;
	private JTable tbReservas;
	private DefaultTableModel modeloReservas;
	private DefaultTableModel modeloHospedes;
	private JLabel labelAtras;
	private JLabel labelExit;
	private ReservaController reservaController = new ReservaController();
	private HospedeController hospedeController = new HospedeController();
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscar frame = new Buscar();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Buscar() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("/imagenes/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		txtBuscar = new JTextField();
		txtBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblTitulo = new JLabel("SISTEMA DE BUSCA");
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblTitulo.setBounds(331, 62, 280, 42);
		contentPane.add(lblTitulo);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		// Alinhamento do conteudo da célula
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		tbReservas = new JTable();

		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Reservas", new ImageIcon(Buscar.class.getResource("/imagenes/reservado.png")), tbReservas, null);

		modeloReservas = (DefaultTableModel) tbReservas.getModel();
		modeloReservas.addColumn("Numero de Reserva");
		modeloReservas.addColumn("Data Check In");
		modeloReservas.addColumn("Data Check Out");
		modeloReservas.addColumn("Valor");
		modeloReservas.addColumn("Codigo do Pagto");
		modeloReservas.addColumn("Forma de Pagto");

		tbReservas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		tbReservas.getColumnModel().getColumn(0).setPreferredWidth(50);
		tbReservas.getColumnModel().getColumn(1).setPreferredWidth(100);
		tbReservas.getColumnModel().getColumn(2).setPreferredWidth(100);
		tbReservas.getColumnModel().getColumn(3).setPreferredWidth(100);
		tbReservas.getColumnModel().getColumn(4).setMinWidth(0);
		tbReservas.getColumnModel().getColumn(4).setMaxWidth(0);
		tbReservas.getColumnModel().getColumn(5).setPreferredWidth(100);

		// Aplica a celula escolhida
		tbReservas.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tbReservas.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		tbReservas.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		tbReservas.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		tbReservas.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

		tbHospedes = new JTable();
		tbHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		tbHospedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Hóspedes", new ImageIcon(Buscar.class.getResource("/imagenes/pessoas.png")), tbHospedes, null);

		modeloHospedes = (DefaultTableModel) tbHospedes.getModel();
		modeloHospedes.addColumn("Numero de Hóspede");
		modeloHospedes.addColumn("Nome");
		modeloHospedes.addColumn("Sobrenome");
		modeloHospedes.addColumn("Data de Nascimento");
		modeloHospedes.addColumn("Nacionalidade");
		modeloHospedes.addColumn("Telefone");
		modeloHospedes.addColumn("Numero de Reserva");

		tbHospedes.getColumnModel().getColumn(0).setPreferredWidth(30);
		tbHospedes.getColumnModel().getColumn(1).setPreferredWidth(100);
		tbHospedes.getColumnModel().getColumn(2).setPreferredWidth(150);
		tbHospedes.getColumnModel().getColumn(3).setPreferredWidth(50);
		tbHospedes.getColumnModel().getColumn(4).setPreferredWidth(90);
		tbHospedes.getColumnModel().getColumn(5).setPreferredWidth(90);
		tbHospedes.getColumnModel().getColumn(6).setPreferredWidth(20);
		// Aplica a celula escolhida
		tbHospedes.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tbHospedes.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Buscar.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Quando o usuário passa o mouse sobre o botão, ele muda de cor
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Quando o usuário remove o mouse do botão, ele retornará ao estado
													// original
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Integer painelSelecionado = panel.getSelectedIndex();
				if (painelSelecionado == 0) {// Reserva
					if (!txtBuscar.getText().isEmpty()) {
						preencherTabelaReservaBusca(); // Busca o parametro informado
					} else {
						preencherTabelaReserva(); // Busca todos os registros
					}
				}
				if (painelSelecionado == 1) {// Hospede
					if (!txtBuscar.getText().isEmpty()) {
						preencherTabelaHospedeBusca(); // Busca o parametro informado
					} else {
						preencherTabelaHospede(); // Busca todos os registros
					}
				}

			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Integer painelSelecionado = panel.getSelectedIndex();

				if (painelSelecionado == 0) {// Reserva

					Object objetoDaLinha = (Object) tbReservas.getValueAt(tbReservas.getSelectedRow(),
							tbReservas.getSelectedColumn());
					if (objetoDaLinha instanceof Integer) {
						Integer id = (Integer) objetoDaLinha;

						try {
							String dataEntrada = tbReservas.getValueAt(tbReservas.getSelectedRow(), 1).toString();
							String dataSaida = tbReservas.getValueAt(tbReservas.getSelectedRow(), 2).toString();

							SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

							CalculaValorDiaria calculo = new CalculaValorDiaria(formato.parse(dataEntrada),
									formato.parse(dataSaida));

							Double valor = calculo.getValorDiaria();
							Integer codigoPagto = Integer
									.parseInt(tbReservas.getValueAt(tbReservas.getSelectedRow(), 4).toString());

							Reserva reserva = new Reserva(id, formato.parse(dataEntrada), formato.parse(dataSaida),
									valor, codigoPagto);

							reservaController.alterar(reserva);

							JOptionPane.showMessageDialog(null, "Dados gravados com sucesso.");

							LimparTabelaReserva();
							preencherTabelaReserva();

						} catch (ParseException e1) {
							JOptionPane.showMessageDialog(null,
									"Não foi posssível gravar, os dados informados são inválidos.");
						}

					} else {
						JOptionPane.showMessageDialog(null, "Por favor, selecionar o ID");
					}
				}
				if (painelSelecionado == 1) {

					Object objetoDaLinha = (Object) tbHospedes.getValueAt(tbHospedes.getSelectedRow(),
							tbHospedes.getSelectedColumn());
					if (objetoDaLinha instanceof Integer) {
						Integer id = (Integer) objetoDaLinha;

						try {
							SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

							String nome = tbHospedes.getValueAt(tbHospedes.getSelectedRow(), 1).toString();
							String sobrenome = tbHospedes.getValueAt(tbHospedes.getSelectedRow(), 2).toString();
							Date dataNascimento = formato
									.parse(tbHospedes.getValueAt(tbHospedes.getSelectedRow(), 3).toString());
							String nacionalidade = tbHospedes.getValueAt(tbHospedes.getSelectedRow(), 4).toString();
							String telefone = tbHospedes.getValueAt(tbHospedes.getSelectedRow(), 5).toString();
							Integer idreserva = Integer
									.parseInt(tbHospedes.getValueAt(tbHospedes.getSelectedRow(), 6).toString());

							Hospede hospede = new Hospede(id, nome, sobrenome, dataNascimento, nacionalidade, telefone,
									idreserva);

							hospedeController.alterar(hospede);

							JOptionPane.showMessageDialog(null, "Dados gravados com sucesso.");

							LimparTabelaHospedes();
							preencherTabelaHospede();

						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null,
									"Não foi posssível gravar, os dados informados são inválidos.");
						}

					} else {
						JOptionPane.showMessageDialog(null, "Por favor, selecionar o ID");
					}
				}
			}
		});
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		JPanel btnDeletar = new JPanel();
		btnDeletar.setLayout(null);
		btnDeletar.setBackground(new Color(12, 138, 199));
		btnDeletar.setBounds(767, 508, 122, 35);
		btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnDeletar);

		JLabel lblExcluir = new JLabel("DELETAR");
		lblExcluir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Integer painelSelecionado = panel.getSelectedIndex();

				if (painelSelecionado == 0) {// Reserva

					Object objetoDaLinha = (Object) tbReservas.getValueAt(tbReservas.getSelectedRow(),
							tbReservas.getSelectedColumn());
					if (objetoDaLinha instanceof Integer) {
						Integer id = (Integer) objetoDaLinha;

						try {
							reservaController.deletar(id);
							JOptionPane.showMessageDialog(null, "Dados excluídos com sucesso.");
							LimparTabelaReserva();
							preencherTabelaReserva();

						} catch (RuntimeException e1) {
							JOptionPane.showMessageDialog(null,
									"Não foi posssível gravar, os dados informados são inválidos.");
						}

					} else {
						JOptionPane.showMessageDialog(null, "Por favor, selecionar o ID");
					}
				}
				if (painelSelecionado == 1) {

					Object objetoDaLinha = (Object) tbHospedes.getValueAt(tbHospedes.getSelectedRow(),
							tbHospedes.getSelectedColumn());
					if (objetoDaLinha instanceof Integer) {
						Integer id = (Integer) objetoDaLinha;

						try {
							hospedeController.deletar(id);
							JOptionPane.showMessageDialog(null, "Dados excluídos com sucesso.");
							LimparTabelaHospedes();
							preencherTabelaHospede();

						} catch (RuntimeException e1) {
							JOptionPane.showMessageDialog(null,
									"Não foi posssível gravar, os dados informados são inválidos.");
						}

					} else {
						JOptionPane.showMessageDialog(null, "Por favor, selecionar o ID");
					}
				}
			}
		});
		lblExcluir.setHorizontalAlignment(SwingConstants.CENTER);
		lblExcluir.setForeground(Color.WHITE);
		lblExcluir.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblExcluir.setBounds(0, 0, 122, 35);
		btnDeletar.add(lblExcluir);
		setResizable(false);

		preencherTabelaReserva();
		preencherTabelaHospede();
	}

	private void LimparTabelaReserva() {
		while (modeloReservas.getRowCount() > 0) {
			modeloReservas.removeRow(0);
		}
	}

	private void LimparTabelaHospedes() {
		while (modeloHospedes.getRowCount() > 0) {
			modeloHospedes.removeRow(0);
		}
	}

	private void preencherTabelaReserva() {
		LimparTabelaReserva();
		List<Reserva> reservas = listarReserva();
		try {
			for (Reserva reserva : reservas) {
				modeloReservas.addRow(new Object[] { reserva.getId(), reserva.getDataentrada(), reserva.getDatasaida(),
						reserva.getValor(), reserva.getFormapagamento(), reserva.getDescricaoFormaPagamento() });
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private void preencherTabelaReservaBusca() {
		LimparTabelaReserva();
		List<Reserva> reservas = buscarReserva();

		try {
			for (Reserva reserva : reservas) {
				modeloReservas.addRow(new Object[] { reserva.getId(), reserva.getDataentrada(), reserva.getDatasaida(),
						reserva.getValor(), reserva.getFormapagamento(), reserva.getDescricaoFormaPagamento() });
			}
		} catch (Exception e) {

		}
	}

	private void preencherTabelaHospede() {
		LimparTabelaHospedes();
		List<Hospede> hospedes = listarHospede();

		try {

			for (Hospede hospede : hospedes) {
				modeloHospedes.addRow(new Object[] { hospede.getId(), hospede.getNome(), hospede.getSobrenome(),
						hospede.getNascimento(), hospede.getNacionalidade(), hospede.getTelefone(),
						hospede.getReserva() });
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private void preencherTabelaHospedeBusca() {
		LimparTabelaHospedes();
		List<Hospede> hospedes = buscarHospede();

		try {

			for (Hospede hospede : hospedes) {
				modeloHospedes.addRow(new Object[] { hospede.getId(), hospede.getNome(), hospede.getSobrenome(),
						hospede.getNascimento(), hospede.getNacionalidade(), hospede.getTelefone(),
						hospede.getReserva() });
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private List<Reserva> listarReserva() {
		return reservaController.listar();
	}

	private List<Reserva> buscarReserva() {
		String buscaId = txtBuscar.getText().trim();
		if (!txtBuscar.getText().isEmpty()) {
			Integer id = Integer.parseInt(buscaId);
			return reservaController.buscar(id);
		} else {
			List<Reserva> reserva = new ArrayList<Reserva>();
			return reserva;
		}
	}

	private List<Hospede> buscarHospede() {
		String buscaId = txtBuscar.getText().trim();
		if (!txtBuscar.getText().isEmpty()) {
			Integer id = Integer.parseInt(buscaId);
			return hospedeController.buscar(id);
		} else {
			List<Hospede> hospede = new ArrayList<Hospede>();
			return hospede;
		}
	}

	private List<Hospede> listarHospede() {
		return hospedeController.listar();
	}

	// Código que permite movimentar a janela pela tela seguindo a posição de "x" e
	// "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}
}
