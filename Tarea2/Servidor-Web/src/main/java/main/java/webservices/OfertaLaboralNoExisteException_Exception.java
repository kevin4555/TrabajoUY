
package main.java.webservices;

import jakarta.xml.ws.WebFault;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebFault(name = "OfertaLaboralNoExisteException", targetNamespace = "http://webservices.java.main/")
public class OfertaLaboralNoExisteException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private OfertaLaboralNoExisteException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public OfertaLaboralNoExisteException_Exception(String message, OfertaLaboralNoExisteException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param cause
     * @param faultInfo
     * @param message
     */
    public OfertaLaboralNoExisteException_Exception(String message, OfertaLaboralNoExisteException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: main.java.webservices.OfertaLaboralNoExisteException
     */
    public OfertaLaboralNoExisteException getFaultInfo() {
        return faultInfo;
    }

}
