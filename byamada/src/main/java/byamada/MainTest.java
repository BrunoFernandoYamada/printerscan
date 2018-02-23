package byamada;

import java.util.Date;

import org.apache.log4j.BasicConfigurator;

import br.com.byamada.bean.Printer;
import br.com.byamada.dao.PrinterDAO; 


public class MainTest {
	
	 public static final String READ_COMMUNITY = "public";       
	 public static final String WRITE_COMMUNITY= "private";       
	 public static final int mSNMPVersion = 0; // 0 represents SNMP version=1           
	 public static final String OID_COUNT_PAGE = "1.3.6.1.2.1.43.13.4.1.10";
	 private static final String SNMP_PORT = "161";
	 

	public static void main(String[] args) {
		
		BasicConfigurator.configure();
		
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
