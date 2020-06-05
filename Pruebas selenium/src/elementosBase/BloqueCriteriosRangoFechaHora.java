package elementosBase;

import org.apache.log4j.Logger;
import unidadesGraficas.*;

public class BloqueCriteriosRangoFechaHora {

	private RangoFechaHora[] criterios_rangofechahora;
	private static int num_criterios_rangofechjahora;
	private final static Logger logger = Logger.getLogger(BloqueCriteriosRangoFechaHora.class);
	
	public BloqueCriteriosRangoFechaHora(int numero_criterios_rangofechahora, TipoBy[][] i_label, String[][] str_label,
			TipoBy[][] i_field, String[][] str_field, TipoBy[][][][] i_tooltip, String[][][][] str_tooltip) {
		
		num_criterios_rangofechjahora=0;
		criterios_rangofechahora=new RangoFechaHora[numero_criterios_rangofechahora];
		logger.debug("Criterios RangofechaHora");
		for (int i=0; i<numero_criterios_rangofechahora; i++) {
			if (i_label[i].length!=str_label[i].length) {
				String error="BloqueCriteriosRangoFechaHora: i_label.length="+i_label.length+"<>str_label.length="+str_label.length;
				logger.fatal(error);
				throw new RuntimeException(error);
			}
			if (i_field[i].length!=str_field[i].length) {
				String error="BloqueCriteriosRangoFechaHora: i_field.length="+i_field.length+"<>str_field.length="+str_field.length;
				logger.fatal(error);
				throw new RuntimeException(error);
			}
			addCriteriosRangoFechaHora(i_label[i], str_label[i], i_field[i], str_field[i], i_tooltip[i], str_tooltip[i]);
		}
	}
	
	private void addCriteriosRangoFechaHora(TipoBy[] i_label, String[] str_label, TipoBy[] i_field, String[] str_field,
			TipoBy[][][] i_tooltip, String[][][] str_tooltip) {
		criterios_rangofechahora[num_criterios_rangofechjahora++]=new RangoFechaHora(i_label, str_label, i_field, str_field,
				i_tooltip[0], str_tooltip[0], i_tooltip[1], str_tooltip[1]);
	}
	
	public boolean estaChequeadoCriterioRangoFechaHora(int i) {
		return criterios_rangofechahora[i].isChecked();
	}
	
	public void clickCriterioRangoFechaHora(int i) {
		criterios_rangofechahora[i].click();
	}
	
	public String leerFechaDesdeFecha(int i) {
		return criterios_rangofechahora[i].getFechaDesde();
	}
	
	public String leerFechaHastaFecha(int i) {
		return criterios_rangofechahora[i].getFechaHasta();
	}
	
	public String leerFechaDesdeHora(int i) {
		return criterios_rangofechahora[i].getHoraDesde();
	}
	
	public String leerFechaHastaHora(int i) {
		return criterios_rangofechahora[i].getHoraHasta();
	}
	
	public void escribirFechaDesdeFecha(int i, String fecha) {
		criterios_rangofechahora[i].writeFechaDesde(fecha);
	}
	
	public void escribirFechaHastaFecha(int i, String fecha) {
		criterios_rangofechahora[i].writeFechaHasta(fecha);
	}
	
	public void escribirFechaDesdeHora(int i, String hora) {
		criterios_rangofechahora[i].writeHoraDesde(hora);
	}
	
	public void escribirFechaHastaHora(int i, String hora) {
		criterios_rangofechahora[i].writeHoraHasta(hora);
	}
	
	public void borrarFechaDesdeFecha(int i) {
		criterios_rangofechahora[i].clearFechaDesde();
	}
	
	public void borrarFechaHastaFecha(int i) {
		criterios_rangofechahora[i].clearFechaHasta();
	}
	
	public void borrarFechaDesdeHora(int i) {
		criterios_rangofechahora[i].clearHoraDesde();
	}
	
	public void borrarFechaHastaHora(int i) {
		criterios_rangofechahora[i].clearHoraHasta();
	}
	
	public boolean hayErrorValidacionFechaDesde(int opcion, int tipoerror) {
		return criterios_rangofechahora[opcion].hayErrorValidacionFechaDesde(tipoerror);
	}
	
	public boolean hayErrorValidacionFechaHasta(int opcion, int tipoerror) {
		return criterios_rangofechahora[opcion].hayErrorValidacionFechaHasta(tipoerror);
	}
	
	public boolean hayErrorValidacionHoraDesde(int opcion, int tipoerror) {
		return criterios_rangofechahora[opcion].hayErrorValidacionHoraDesde(tipoerror);
	}
	
	public boolean hayErrorValidacionHoraHasta(int opcion, int tipoerror) {
		return criterios_rangofechahora[opcion].hayErrorValidacionHoraHasta(tipoerror);
	}
	
	public boolean sintacticAnalysis(String[][] labels) {
		boolean analisisCriteriosBuqueda=true;
		
		for (int i=0; (i<criterios_rangofechahora.length); i++) {
			analisisCriteriosBuqueda=criterios_rangofechahora[i].sintacticAnalysis(labels[i]) && analisisCriteriosBuqueda;
		}
		return analisisCriteriosBuqueda;
	}

}
