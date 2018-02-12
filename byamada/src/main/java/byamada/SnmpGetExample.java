

/**
 * Copyright 2010 TechDive.in
 *  
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
 *
 * <a href="http://www.apache.org/licenses/LICENSE-2.0" title="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a> 
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.  
 *   
 */

 /**
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS''
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
//LEXMARK
//(Host name) lexdhcpHostname = 1.3.6.1.4.1.641.1.5.7.6.0
//(Login Name)lexipxLoginName = 1.3.6.1.4.1.641.1.3.2.0
//(Count) prtMarkerLifeCount = 1.3.6.1.2.1.43.10.2.1.4.1.1
//1.3.6.1.2.1.1.1.0	sysDescr	OCTET-STRING	"IBM Infoprint 1130 version 54.10.21 kernel 2.4.0-test6 All-N-1"	
//1.3.6.1.2.1.1.2.0	sysObjectID	OID	1.3.6.1.4.1.641.1	
//1.3.6.1.2.1.1.3.0	sysUpTime	TIMETICKS	"19/09/2007 21:55:45"	
//1.3.6.1.2.1.1.4.0	sysContact	OCTET-STRING		
//1.3.6.1.2.1.1.5.0	sysName	OCTET-STRING	PREXP3	
//1.3.6.1.2.1.1.6.0	sysLocation	OCTET-STRING		
//1.3.6.1.2.1.1.7.0	sysServices	INTEGER	72
//
 package byamada;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class SnmpGetExample
{
  private static String  ipAddress  = "192.168.10.71";

  private static String  port    = "161";

  // OID of MIB RFC 1213; Scalar Object = .iso.org.dod.intrnet.mgmt.mib-2.system.sysDescr.0
  private static String  oidValue  = "1.3.6.1.2.1.43.10.2.1.4.1.1";  // ends with 0 for scalar object

  private static int    snmpVersion  = SnmpConstants.version1;

  private static String  community  = "public";

  public static void main(String[] args) throws Exception
  {
    System.out.println("SNMP GET Demo");

    // Create TransportMapping and Listen
    TransportMapping transport = new DefaultUdpTransportMapping();
    transport.listen();

    // Create Target Address object
    CommunityTarget comtarget = new CommunityTarget();
    comtarget.setCommunity(new OctetString(community));
    comtarget.setVersion(snmpVersion);
    comtarget.setAddress(new UdpAddress(ipAddress + "/" + port));
    comtarget.setRetries(2);
    comtarget.setTimeout(1000);

    // Create the PDU object
    PDU pdu = new PDU();
    pdu.add(new VariableBinding(new OID(oidValue)));
    pdu.setType(PDU.GET);
    pdu.setRequestID(new Integer32(1));

    // Create Snmp object for sending data to Agent
    Snmp snmp = new Snmp(transport);

    System.out.println("Sending Request to Agent...");
    ResponseEvent response = snmp.get(pdu, comtarget);

    // Process Agent Response
    if (response != null)
    {
      System.out.println("Got Response from Agent");
      PDU responsePDU = response.getResponse();

      if (responsePDU != null)
      {
        int errorStatus = responsePDU.getErrorStatus();
        int errorIndex = responsePDU.getErrorIndex();
        String errorStatusText = responsePDU.getErrorStatusText();

        if (errorStatus == PDU.noError)
        {
          System.out.println("Snmp Get Response = " + responsePDU.getVariableBindings());
        }
        else
        {
          System.out.println("Error: Request Failed");
          System.out.println("Error Status = " + errorStatus);
          System.out.println("Error Index = " + errorIndex);
          System.out.println("Error Status Text = " + errorStatusText);
        }
      }
      else
      {
        System.out.println("Error: Response PDU is null");
      }
    }
    else
    {
      System.out.println("Error: Agent Timeout... ");
    }
    snmp.close();
  }
}