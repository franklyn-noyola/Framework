package ventanasComunes;

import elementosBase.*;
import unidadesGraficas.*;

public class FormRadioScreen extends FormScreen {
	
	public FormRadioScreen(TipoBy i_titulo, String str_titulo, TipoBy i_subtitulo, String str_subtitulo, 
			 TipoBy[] i_botones_negros, String[] str_botones_negros, TipoBy i_msgerror,
				String str_msgerror, TipoBy i_encabezado, String str_encabezado,
				int num_criterios_campo,int num_criterios_desplegable,int num_criterios_fecha,
			TipoBy[] i_label, String[] str_label, TipoBy[] i_field, String[] str_field, TipoBy[][] i_tooltipcampo,
			String[][] str_tooltipcampo, TipoBy[][][] i_tooltipfecha, String[][][] str_tooltipfecha,
			TipoBy[] i_botones_crud, String[] str_botones_crud, TipoBy i_cabecera, String str_cabecera, 
			TipoBy i_leyendamostrado, String str_leyendamostrado, TipoBy[] i_botones_grid,
			String[] str_botones_grid, int num_columnas) {
		

		super(i_titulo, str_titulo, i_subtitulo, str_subtitulo, 
			  i_botones_negros, str_botones_negros,  i_msgerror,
			  str_msgerror,  i_encabezado,  str_encabezado, num_criterios_campo, num_criterios_desplegable, num_criterios_fecha,
			 i_label,  str_label,  i_field,  str_field,  i_tooltipcampo,  str_tooltipcampo, i_tooltipfecha,  str_tooltipfecha,
			  i_botones_crud,  str_botones_crud,  i_cabecera,  str_cabecera, 
			 i_leyendamostrado,  str_leyendamostrado,  i_botones_grid,
			 str_botones_grid,  num_columnas,  1);
		
		try {
			tabla=new TablaRadioButton(num_columnas, i_cabecera, str_cabecera, i_leyendamostrado, str_leyendamostrado, 
				 i_botones_crud,str_botones_crud, i_botones_grid, str_botones_grid);
		}
		catch (RuntimeException e) {
			System.exit(0);
		}
	}
	
	public void seleccionarFila(int indice) {
		((TablaRadioButton) tabla).seleccionarFila(indice);
	}
	
	public void recargarTabla() {
		super.recargarTabla();
		((TablaRadioButton) tabla).recargarGridRadioButton();
	}

}