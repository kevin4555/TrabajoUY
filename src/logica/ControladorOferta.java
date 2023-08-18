package logica;

import java.sql.Date;
import java.util.ArrayList;

public class ControladorOferta implements IControladorOferta {

	@Override
	public ArrayList<String> listarTipoDePublicaciones() {
		ManejadorSettings ms = ManejadorSettings.getinstance();
		return ms.listarTipoDePublicaciones();
	}

	@Override
	public void altaOfertaLaboral(String nombre, String descrip, Date horaInicio, Date horaFin, double remuneracion,
			String ciudad, String departamento, Date fechaAlta, ArrayList<String> keywords) {
		// TODO Auto-generated method stub
		
	}

}
