package testsComunes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class RutinasTestComunes {

	private static Logger logger=Logger.getLogger(RutinasTestComunes.class);
	
	public static boolean assertArrayEquals(Object[] datosEntrada,Object[] datosSalida) {
		boolean resultado=true;
		
		if (datosEntrada.length!=datosSalida.length) {
			logger.debug("this.assertArrayEquals-Las longitudes de los dos parámetros no son iguales.");
			return false;
		}
		for (int i=0; i<datosEntrada.length && resultado; i++) {
			String dato1=((String)datosEntrada[i]).trim();
			String dato2=((String)datosSalida[i]).trim();
			if (!dato1.equals(dato2)) {
				logger.error("El elemento ["+i+"] no coincide para ambos arrays: '"+dato1+"' y '"+dato2+"'");
				resultado=false;
			}
		}
		return resultado;
	}
	
	
	public static int obtenerTamañoConsulta(ResultSet rs) {
		int contador = 0;
		try {
			while (rs.next()) {
			    contador++;
			}
			rs.beforeFirst();
		    return contador;
		} 
		catch (SQLException e) {
			logger.fatal("Error en acceso a BD");
			e.printStackTrace();
			return -1;
		}
	}
}
