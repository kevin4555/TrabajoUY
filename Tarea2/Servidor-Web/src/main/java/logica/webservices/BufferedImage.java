
package logica.webservices;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for bufferedImage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="bufferedImage">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="data" type="{http://webServices.logica/}raster" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bufferedImage", propOrder = {
    "data"
})
public class BufferedImage {

    protected Raster data;

    /**
     * Gets the value of the data property.
     * 
     * @return
     *     possible object is
     *     {@link Raster }
     *     
     */
    public Raster getData() {
        return data;
    }

    /**
     * Sets the value of the data property.
     * 
     * @param value
     *     allowed object is
     *     {@link Raster }
     *     
     */
    public void setData(Raster value) {
        this.data = value;
    }

}
