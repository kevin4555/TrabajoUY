
package main.java.webservices;

import jakarta.xml.ws.WebFault;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebFault(name = "PaquetePublicacionNoExisteException", targetNamespace = "http://webservices.java.main/")
public class PaquetePublicacionNoExisteException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private PaquetePublicacionNoExisteException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public PaquetePublicacionNoExisteException_Exception(String message, PaquetePublicacionNoExisteException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param cause
     * @param faultInfo
     * @param message
     */
    public PaquetePublicacionNoExisteException_Exception(String message, PaquetePublicacionNoExisteException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: main.java.webservices.PaquetePublicacionNoExisteException
     */
    public PaquetePublicacionNoExisteException getFaultInfo() {
        return faultInfo;
    }

}
