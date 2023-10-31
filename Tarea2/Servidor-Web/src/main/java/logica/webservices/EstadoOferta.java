
package logica.webservices;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for estadoOferta.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>{@code
 * <simpleType name="estadoOferta">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="INGRESADA"/>
 *     <enumeration value="CONFIRMADA"/>
 *     <enumeration value="RECHAZADA"/>
 *     <enumeration value="FINALIZADA"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "estadoOferta")
@XmlEnum
public enum EstadoOferta {

    INGRESADA,
    CONFIRMADA,
    RECHAZADA,
    FINALIZADA;

    public String value() {
        return name();
    }

    public static EstadoOferta fromValue(String v) {
        return valueOf(v);
    }

}
