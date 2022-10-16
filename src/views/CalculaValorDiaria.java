package views;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

public class CalculaValorDiaria {

	private Date dataInicial;
	private Date dataFinal;
	private static Double valorDiaria = (double) 20;
	private Double valorTotal = (double) 0;

	public CalculaValorDiaria(Date dataIni, Date dataFin) {
		if (dataIni != null && dataFin != null) {
			dataInicial = dataIni;
			dataFinal = dataFin;
			CalculaEstadia();
		}
	}
	
	public Double getValorDiaria() {
		return valorTotal;
	}

	private void CalculaEstadia() {
		Long qtdeDias = (long) 0;

		try {
			qtdeDias = DiferencaDatas();
			
			valorTotal = qtdeDias * valorDiaria;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Houve um erro ao calcular o valor da diária, verifique as datas!");
		}
	}

	private long DiferencaDatas() {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		//Converte Date em String
		String dataIni = formato.format(dataInicial);
		String dataFin = formato.format(dataFinal);

		c1.set(Integer.parseInt(dataIni.substring(0, 4)), Integer.parseInt(dataIni.substring(5, 7)),
				Integer.parseInt(dataIni.substring(8, 10)));
		// Pega a segunda data
		c2.set(Integer.parseInt(dataFin.substring(0, 4)), Integer.parseInt(dataFin.substring(5, 7)),
				Integer.parseInt(dataFin.substring(8, 10)));
		
		return ((c2.getTimeInMillis() - c1.getTimeInMillis()) / 1000 / 60 / 60 / 24);
	}
}
