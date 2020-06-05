package elementosBase;

import unidadesGraficas.*;

public class TablaRadioButton extends Tabla {

	public TablaRadioButton(int num_columnas, TipoBy i_cabecera, String str_cabecera, TipoBy i_leyendamostrado,
			String str_leyendamostrado, TipoBy[] i_botonescrud, String[] str_botonescrud, 
			TipoBy[] i_botonesgrid, String[] str_botonesgrid) {
		
		super(num_columnas, i_cabecera,  str_cabecera,  i_leyendamostrado,
				 str_leyendamostrado,  i_botonescrud,  str_botonescrud, 
				 i_botonesgrid,  str_botonesgrid, 1);
		try {
			grid=new GridRadioButton(num_columnas,i_leyendamostrado,str_leyendamostrado, i_cabecera,str_cabecera,
				i_botonesgrid, str_botonesgrid);
		}
		catch (RuntimeException e) {
			System.exit(0);
		}
	}
	
	
	public void recargarGridRadioButton() {
		GridRadioButton gridradio=(GridRadioButton)grid;
		gridradio.recargarGridRadioButton(grid.i_cabecera, grid.str_cabecera);
	}
	
	public void seleccionarFila(int numfila) {
		GridRadioButton gridradio=(GridRadioButton)grid;
		gridradio.seleccionarFila(numfila);
	}
	
}
