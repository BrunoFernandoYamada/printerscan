package byamada;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.JOptionPane;

public class BFPrinter {
	private PrintService[] services = null;	
	public PrintService[] getPrinters(){		
		services = PrintServiceLookup.lookupPrintServices(null, null);
		//debug code
		for(PrintService ps : services){
			System.out.println(ps.getName());
			JOptionPane.showMessageDialog(null, ps.getName());
		}
		//end of debug code
		return services;		
	}
	public PrintService[] getInstalledPrinters(){
		return services;		
	}
	public static void main(String args[]){
		BFPrinter pLister = new BFPrinter();
		pLister.getPrinters();
	}
}
