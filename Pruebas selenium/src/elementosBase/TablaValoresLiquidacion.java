package elementosBase;

import unidadesGraficas.*;

public class TablaValoresLiquidacion extends TablaEstatica {

	public TablaValoresLiquidacion(int num_filas, int num_columnas, TipoBy i_cabecera, String str_cabecera,int columna_inicial) throws RuntimeException {
		
		super(num_filas,  num_columnas,  i_cabecera,  str_cabecera, columna_inicial);
		try {
			grid=new GridValoresLiquidacion(num_filas, num_columnas,i_cabecera,str_cabecera, columna_inicial);
		}
		catch (RuntimeException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

}
