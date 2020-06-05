package ventanasComunes;

import bloques.CrudBlock;
import procesosComunes.Error;
import unidadesGraficas.*;

public class CRUDScreen extends Screen {
	
	private CrudBlock crud;
	
	public CRUDScreen(TipoBy i_titulo, String str_titulo, TipoBy i_subtitulo, String str_subtitulo, 
			TipoBy[] i_botones_negros, String[] str_botones_negros,  TipoBy i_msgerror,
			String str_msgerror,int num_criterios_campo, int num_criterios_desplegable,
			int num_criterios_fecha, TipoBy[] i_label, String[] str_label, TipoBy[] i_field, String[] str_field,
			TipoBy i_encabezado, String str_encabezado, TipoBy[][] i_tooltipcampo, String[][] str_tooltipcampo,
			TipoBy[][][] i_tooltipfecha, String[][][] str_tooltipfecha) {
		
		super(i_titulo,str_titulo,i_subtitulo,str_subtitulo,i_botones_negros,str_botones_negros,i_msgerror,str_msgerror);
		try {
			crud=new CrudBlock(i_encabezado, str_encabezado, num_criterios_campo, num_criterios_desplegable,
				num_criterios_fecha,i_label, str_label, i_field, str_field, i_tooltipcampo, str_tooltipcampo,
				i_tooltipfecha, str_tooltipfecha);
		}
		catch (RuntimeException e) {
			System.exit(0);
		}
	}
	
	public String leerOpcionDesplegable(int desplegable) {
		return crud.leerCriterioDesplegable(desplegable);
	}
	
	public Error desplegarOpcion(int desplegable,String opcion) {
		return crud.desplegarCriterioDesplegable(desplegable,opcion);
	}
	
	public String leerOpcion(int opcion) {
		return crud.leerOpcion(opcion);
	}
	
	public void escribirOpcion(int opcion, String valor) {
		crud.escribirOpcion(opcion, valor);
	}
	
	public String leerFecha(int opcion) {
		return crud.leerFecha(opcion);
	}
	
	public String leerHora(int opcion) {
		return crud.leerHora(opcion);
	}
	
	public void escribirFecha(int opcion, String fecha, String hora) {
		crud.escribirFecha(opcion, fecha, hora);
	}
	
	public boolean hayErrorValidacionOpcion(int opcion, int tipoerror) {
		return crud.hayErrorValidacionOpcion(opcion, tipoerror);
	}
	
	public boolean hayErrorValidacionFechaFecha(int opcion, int tipoerror) {
		return crud.hayErrorValidacionFechaFecha(opcion, tipoerror);
	}
	
	public boolean hayErrorValidacionFechaHora(int opcion, int tipoerror) {
		return crud.hayErrorValidacionFechaHora(opcion, tipoerror);
	}
	
	public void borrarOpcion(int opcion) {
		crud.borrarOpcion(opcion);
	}
	
	public void borrarFecha(int opcion) {
		crud.borrarFecha(opcion);
	}
	
	public void borrarHora(int opcion) {
		crud.borrarHora(opcion);
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] labelBotonesNegros, String encabezado, 
			String[] labelCriteriosBusqueda) {
		
		return super.sintacticAnalysis(titulo, subtitulo,labelBotonesNegros) && crud.sintacticAnalysis(encabezado, labelCriteriosBusqueda);
	}
}
