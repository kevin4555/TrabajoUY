
package main.java.webservices;

import jakarta.xml.ws.WebFault;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebFault(name = "PaquetePublicacionYaExisteException", targetNamespace = "http://webservices.java.main/")
public class PaquetePublicacionYaExisteException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private PaquetePublicacionYaExisteException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public PaquetePublicacionYaExisteException_Exception(String message, PaquetePublicacionYaExisteException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param cause
     * @param faultInfo
     * @param message
     */
    public PaquetePublicacionYaExisteException_Exception(String message, PaquetePublicacionYaExisteException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: main.java.webservices.PaquetePublicacionYaExisteException
     */
    public PaquetePublicacionYaExisteException getFaultInfo() {
        return faultInfo;
    }

}
