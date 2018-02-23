package br.com.byamada.teste;

import java.util.Date;

import org.junit.Test;

import br.com.byamada.bean.Printer;
import br.com.byamada.dao.PrinterDAO;

public class PrinterDAOTest {
	
	@Test
	public void salvar() {
		
		Printer printer = new Printer();
		printer.setContador("31400");
		printer.setIp("192.168.10.71");
		printer.setNivelColor("20%");
		printer.setNivelToner("80%");
		printer.setNome("Lexmark M640");
		printer.setSerie("35980-rl");
		printer.setUltimaLeitura(new Date());
		
		PrinterDAO printerDAO = new PrinterDAO();
				printerDAO.salvar(printer);
		
	}

}
