
package logica.datatypes;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Arrays;
import java.util.List;

public class ListAdapter extends XmlAdapter<String, List<Object>> {
  @Override
  public List<Object> unmarshal(String v) {
    if (v == null || v.isEmpty()) {
      return null;
    }
    String[] values = v.split(" ");
    return Arrays.asList(values);
  }
  
  @Override
  public String marshal(List<Object> v) {
    if (v == null || v.isEmpty()) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    for (Object item : v) {
      sb.append(item.toString()).append(" ");
    }
    return sb.toString().trim();
  }
}
