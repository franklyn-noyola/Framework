package elementosBase;

import unidadesGraficas.*;

public class TablaButton extends Tabla {

	public TablaButton(int num_columnas, TipoBy i_cabecera, String str_cabecera, TipoBy i_leyendamostrado,
			String str_leyendamostrado, TipoBy[] i_botonescrud, String[] str_botonescrud, 
			TipoBy[] i_botonesgrid, String[] str_botonesgrid) {
		
		super(num_columnas, i_cabecera,  str_cabecera,  i_leyendamostrado,
				 str_leyendamostrado,  i_botonescrud,  str_botonescrud, 
				 i_botonesgrid,  str_botonesgrid, 1);
		
		grid=new GridButton(num_columnas,i_leyendamostrado,str_leyendamostrado, i_cabecera,str_cabecera,
				i_botonesgrid, str_botonesgrid);

	}
	
	
	public void recargarGridButton() {
		GridButton gridbutton=(GridButton)grid;
		gridbutton.recargarGridButton(grid.i_cabecera, grid.str_cabecera);
	}
	
	public void pulsarFila(int indice) {
		GridButton gridbutton=(GridButton)grid;
		gridbutton.pulsarFila(indice);
	}
	
}
