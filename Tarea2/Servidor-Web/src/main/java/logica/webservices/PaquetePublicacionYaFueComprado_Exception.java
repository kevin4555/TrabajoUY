
package logica.webservices;

import jakarta.xml.ws.WebFault;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebFault(name = "PaquetePublicacionYaFueComprado", targetNamespace = "http://webServices.logica/")
public class PaquetePublicacionYaFueComprado_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private PaquetePublicacionYaFueComprado faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public PaquetePublicacionYaFueComprado_Exception(String message, PaquetePublicacionYaFueComprado faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param cause
     * @param faultInfo
     * @param message
     */
    public PaquetePublicacionYaFueComprado_Exception(String message, PaquetePublicacionYaFueComprado faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: logica.webservices.PaquetePublicacionYaFueComprado
     */
    public PaquetePublicacionYaFueComprado getFaultInfo() {
        return faultInfo;
    }

}
