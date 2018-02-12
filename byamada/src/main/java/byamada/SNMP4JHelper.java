package byamada;

import javax.swing.JOptionPane;


import org.snmp4j.CommunityTarget; 
import org.snmp4j.PDU; 
import org.snmp4j.Snmp; 
import org.snmp4j.TransportMapping; 
import org.snmp4j.event.ResponseEvent; 
import org.snmp4j.mp.SnmpConstants; 
import org.snmp4j.smi.*; 
import org.snmp4j.transport.DefaultUdpTransportMapping; 


public class SNMP4JHelper {
	
	 public static final String READ_COMMUNITY = "public";       
	 public static final String WRITE_COMMUNITY= "private";       
	 public static final int mSNMPVersion = 0; // 0 represents SNMP version=1           
	 public static final String OID_COUNT_PAGE = "1.3.6.1.2.1.43.13.4.1.10";
	 private static final String SNMP_PORT = "161";
	 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try   {                    
			String strIPAddress = JOptionPane.showInputDialog("Digite o IP da Impressora!");          
			SNMP4JHelper objSNMP = new SNMP4JHelper();          
			         
		//////////////////////////////////////////////////////////         
		//Recebe quantidade de copias      
		/////////////////////////////////////////////////////////           
		String leitura = objSNMP.snmpGet(strIPAddress, READ_COMMUNITY, OID_COUNT_PAGE); 
		 
		
		
		JOptionPane.showMessageDialog(null, "Total de Cópias: "+ leitura);
		
		}      
		catch (Exception e){            
			e.printStackTrace();      
		}  

	}
	
	  public String snmpGet(String strAddress, String commun, String strOID){           
		  String str="";           
		  
		  try{      
			  OctetString community = new OctetString(commun);      
			  strAddress= strAddress+"/"+ SNMP_PORT;      
			  Address targetaddress = new UdpAddress(strAddress);      
			  TransportMapping transport = new DefaultUdpTransportMapping();      
			  transport.listen();             
			  CommunityTarget comtarget = new CommunityTarget();       
			  comtarget.setCommunity(community);       
			  comtarget.setVersion(SnmpConstants.version1);       
			  comtarget.setAddress(targetaddress);       
			  comtarget.setRetries(2);       
			  comtarget.setTimeout(5000);             
			  PDU pdu = new PDU();       
			  ResponseEvent response;       
			  Snmp snmp;       
			  pdu.add(new VariableBinding(new OID(strOID)));       
			  pdu.setType(PDU.GET);       
			  snmp = new Snmp(transport); 
              response = snmp.get(pdu,comtarget);       
              if(response != null){        
            	  if(response.getResponse().getErrorStatusText().
            			  equalsIgnoreCase("Success")){       
            		  		PDU pduresponse=response.getResponse();       
            		  		str=pduresponse.getVariableBindings().firstElement().toString();       
            		  		if(str.contains("=")){            
            		  			int len = str.indexOf("=");            
            		  			str=str.substring(len+1, str.length());     
            		  		}         
            	   }   
              }else{            
            	   		System.out.println("Feeling like a TimeOut occured ");    
            	   }    
              snmp.close();     
          }catch(Exception e){  
        	  e.printStackTrace();    
          }      
		  
		  System.out.println("Response="+str);      
		  return str;    
		  } 
	  
	  
	 

}
