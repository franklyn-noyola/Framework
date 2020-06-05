package bloques;

import elementosBase.*;
import unidadesGraficas.*;

public class CrudRangoFechaHoraBlock extends CrudBlock {

	private BloqueCriteriosRangoFechaHora bloque_criterios_rangofechahora=null;
	
	public CrudRangoFechaHoraBlock(TipoBy i_encabezado, String str_encabezado,int num_criterios_campo, int num_criterios_desplegable,
			int num_criterios_fecha, int num_criterios_rangofechahora, TipoBy[] i_label, String[] str_label,
			TipoBy[] i_field, String[] str_field, TipoBy[][] i_tooltipcampo, String[][] str_tooltipcampo, 
			TipoBy[][][] i_tooltipfecha, String[][][] str_tooltipfecha, 
			TipoBy[][] i_labelrango, String[][] str_labelrango, TipoBy[][] i_fieldrango, String[][] str_fieldrango,
			TipoBy[][][][] i_tooltiprango, String[][][][] str_tooltiprango) {
		
		super(i_encabezado,  str_encabezado, num_criterios_campo,  num_criterios_desplegable,
				 num_criterios_fecha,i_label,str_label, i_field, str_field, i_tooltipcampo,str_tooltipcampo,i_tooltipfecha,str_tooltipfecha);
		// La validación de las longitudes de labels y fields se hace en BloqueCriteriosRangoFechaHora
		bloque_criterios_rangofechahora=new BloqueCriteriosRangoFechaHora(num_criterios_rangofechahora, i_labelrango,
				str_labelrango, i_fieldrango, str_fieldrango, i_tooltiprango, str_tooltiprango);
	}
	
	public boolean estaChequeadoCriterioRangoFechaHora(int i) {
		return bloque_criterios_rangofechahora.estaChequeadoCriterioRangoFechaHora(i);
	}
	
	public void clickCriterioCheck(int i) {
		bloque_criterios_rangofechahora.clickCriterioRangoFechaHora(i);
	}
	
	public String leerFechaDesdeFechaCriterio(int i) {
		return bloque_criterios_rangofechahora.leerFechaDesdeFecha(i);
	}
	
	public String leerFechaHastaFechaCriterio(int i) {
		return bloque_criterios_rangofechahora.leerFechaHastaFecha(i);
	}
	
	public String leerFechaDesdeHoraCriterio(int i) {
		return bloque_criterios_rangofechahora.leerFechaDesdeHora(i);
	}
	
	public String leerFechaHastaHoraCriterio(int i) {
		return bloque_criterios_rangofechahora.leerFechaHastaHora(i);
	}
	
	public void escribirFechaDesdeFechaCriterio(int i, String fecha) {
		 bloque_criterios_rangofechahora.escribirFechaDesdeFecha(i, fecha);
	}
	
	public void escribirFechaHastaFechaCriterio(int i, String fecha) {
		 bloque_criterios_rangofechahora.escribirFechaHastaFecha(i, fecha);
	}
	
	public void escribirFechaDesdeHoraCriterio(int i, String hora) {
		 bloque_criterios_rangofechahora.escribirFechaDesdeHora(i, hora);
	}
	
	public void escribirFechaHastaHoraCriterio(int i, String hora) {
		 bloque_criterios_rangofechahora.escribirFechaHastaHora(i, hora);
	}
	
	public void borrarFechaDesdeFechaCriterio(int i) {
		 bloque_criterios_rangofechahora.borrarFechaDesdeFecha(i);
	}
	
	public void borrarFechaHastaFechaCriterio(int i) {
		 bloque_criterios_rangofechahora.borrarFechaHastaFecha(i);
	}
	
	public void borrarFechaDesdeHoraCriterio(int i) {
		 bloque_criterios_rangofechahora.borrarFechaDesdeHora(i);
	}
	
	public void borrarFechaHastaHoraCriterio(int i) {
		 bloque_criterios_rangofechahora.borrarFechaHastaHora(i);
	}
	
	public boolean hayErrorValidacionFechaDesde(int i, int tipoerror) {
		return bloque_criterios_rangofechahora.hayErrorValidacionFechaDesde(i,tipoerror);
	}
	
	public boolean hayErrorValidacionFechaHasta(int i, int tipoerror) {
		return bloque_criterios_rangofechahora.hayErrorValidacionFechaHasta(i,tipoerror);
	}
	
	public boolean hayErrorValidacionHoraDesde(int i, int tipoerror) {
		return bloque_criterios_rangofechahora.hayErrorValidacionHoraDesde(i,tipoerror);
	}
	
	public boolean hayErrorValidacionHoraHasta(int i, int tipoerror) {
		return bloque_criterios_rangofechahora.hayErrorValidacionHoraHasta(i,tipoerror);
	}
	
	public boolean sintacticAnalysis(String encabezado, String[] labelCriteriosBusqueda, String[][] labelRangoFecha) {
		
		boolean resultado=true;

		if (labelCriteriosBusqueda!=null) {
			resultado=super.sintacticAnalysis(encabezado, labelCriteriosBusqueda) && resultado;
		}
		return bloque_criterios_rangofechahora.sintacticAnalysis(labelRangoFecha) && resultado;
	}
}
