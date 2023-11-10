
package main.java.webservices;

import jakarta.xml.ws.WebFault;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebFault(name = "UsuarioEmailRepetidoException", targetNamespace = "http://webservices.java.main/")
public class UsuarioEmailRepetidoException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private UsuarioEmailRepetidoException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public UsuarioEmailRepetidoException_Exception(String message, UsuarioEmailRepetidoException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param cause
     * @param faultInfo
     * @param message
     */
    public UsuarioEmailRepetidoException_Exception(String message, UsuarioEmailRepetidoException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: main.java.webservices.UsuarioEmailRepetidoException
     */
    public UsuarioEmailRepetidoException getFaultInfo() {
        return faultInfo;
    }

}