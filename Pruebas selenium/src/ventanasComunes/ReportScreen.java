package ventanasComunes;

import unidadesGraficas.*;

public class ReportScreen extends CRUDScreen {
	
	public ReportScreen(TipoBy i_titulo, String str_titulo, TipoBy i_subtitulo, String str_subtitulo, 
			TipoBy[] i_botones_negros, String[] str_botones_negros,  TipoBy i_msgerror,
			String str_msgerror,int num_criterios_campo, int num_criterios_desplegable,
			int num_criterios_fecha, TipoBy[] i_label, String[] str_label, TipoBy[] i_field, String[] str_field,
			TipoBy i_encabezado, String str_encabezado, TipoBy[][] i_tooltipcampo, String[][] str_tooltipcampo,
			TipoBy[][][] i_tooltipfecha, String[][][] str_tooltipfecha) {
		
		super(i_titulo,  str_titulo,  i_subtitulo,  str_subtitulo, 
				 i_botones_negros, str_botones_negros,  i_msgerror,
				 str_msgerror, num_criterios_campo,  num_criterios_desplegable,
				 num_criterios_fecha,  i_label, str_label,  i_field,  str_field,
				 i_encabezado,  str_encabezado,  i_tooltipcampo,  str_tooltipcampo,i_tooltipfecha,str_tooltipfecha);

	}
}
