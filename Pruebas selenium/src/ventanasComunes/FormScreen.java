package ventanasComunes;

import elementosBase.*;
import unidadesGraficas.*;

public class FormScreen extends CRUDScreen {
	
	protected Tabla tabla;
	
	public FormScreen(TipoBy i_titulo, String str_titulo, TipoBy i_subtitulo, String str_subtitulo, 
			 TipoBy[] i_botones_negros, String[] str_botones_negros, TipoBy i_msgerror,
				String str_msgerror, TipoBy i_encabezado, String str_encabezado,
				int num_criterios_campo,int num_criterios_desplegable,int num_criterios_fecha,
			TipoBy[] i_label, String[] str_label, TipoBy[] i_field, String[] str_field, TipoBy[][] i_tooltipcampo,
			String[][] str_tooltipcampo, TipoBy[][][] i_tooltipfecha, String[][][] str_tooltipfecha,
			TipoBy[] i_botones_crud, String[] str_botones_crud, TipoBy i_cabecera, String str_cabecera, 
			TipoBy i_leyendamostrado, String str_leyendamostrado, TipoBy[] i_botones_grid,
			String[] str_botones_grid, int num_columnas, int columna_inicial) {
		
		super(i_titulo,str_titulo,i_subtitulo,str_subtitulo,i_botones_negros,str_botones_negros,i_msgerror,str_msgerror,
				num_criterios_campo,num_criterios_desplegable, num_criterios_fecha, i_label,
				str_label,i_field,str_field, i_encabezado, str_encabezado,i_tooltipcampo,str_tooltipcampo,
				i_tooltipfecha,str_tooltipfecha);
		tabla=new Tabla(num_columnas, i_cabecera, str_cabecera, i_leyendamostrado, str_leyendamostrado, 
				 i_botones_crud,str_botones_crud, i_botones_grid, str_botones_grid, columna_inicial);
	}

	public String obtenerValorColumna(int num_fila,String columna) {
		return tabla.obtenerValorColumna(num_fila,columna);
	}
	
	public String obtenerValorColumna(String columnaFinal, String columnaInicial, String valorInicial) {
		return tabla.obtenerValorColumna(columnaFinal,columnaInicial,valorInicial);
	}
	
	public String[] obtenerFilaTabla(int num_fila) {
		return tabla.obtenerDatosFilaTabla(num_fila);
	}
	
	public String[] obtenerFilaTabla(String[] columnas, String[] valores) {
		return tabla.obtenerDatosFilaTabla(columnas, valores);
	}
	
	public int obtenerNumFilaTabla(String[] columnas, String[] valores) {
		return tabla.obtenerNumFilaTabla(columnas, valores);
	}
	
	public boolean ultimaTabla() {
		//Devuelve true si la tabla mostrada por pantalla es la última y no hay más datos que mostrar
		return tabla.ultimaTabla();
	}
	
	public int numFilasTabla() {
		// Devuelve el número de filas de la tabla mostradas por pantalla
		return tabla.numFilas();
	}
	
	public int numFilasTotalTabla() {
		// Devuelve el número de filas total de la tabla
		return tabla.numFilasTotal();
	}
	
	public int numColumnasTabla() {
		return tabla.numColumnas();
	}
	
	public void clickBotonCrud(int indice) {
		tabla.pulsarBotonCrud(indice);
	}
	
	public void pulsarBotonTabla(int indice) {
		tabla.pulsarBotonTabla(indice);
	}
	
	public void recargarTabla() {
		tabla.recargarGrid();
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] labelBotonesNegros, String[] labelBotonesCRUD,
			String encabezado, String[] labelCriteriosBusqueda, String[] labelTitulosColumnas, String[] labelBotonesGrid) {
		
		return super.sintacticAnalysis(titulo, subtitulo,labelBotonesNegros,encabezado, labelCriteriosBusqueda)  &&
				tabla.sintacticAnalysis(labelBotonesCRUD,labelTitulosColumnas,labelBotonesGrid);
		
	}
}
