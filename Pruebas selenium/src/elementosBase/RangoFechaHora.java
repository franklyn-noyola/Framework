package elementosBase;

import org.apache.log4j.Logger;

import unidadesGraficas.*;

public class RangoFechaHora {

	private CheckLabel activado=null;
	private FechaHora fechaHoraDesde;
	private FechaHora fechaHoraHasta;
	private final static Logger logger = Logger.getLogger(RangoFechaHora.class);
	
	public RangoFechaHora(TipoBy[] i_label, String[] str_label, TipoBy[] i_field, String[] str_field,
			TipoBy[][] i_tooltipdesde, String[][] str_tooltipdesde, TipoBy[][] i_tooltiphasta, String[][] str_tooltiphasta) {
		
		activado=new CheckLabel(i_label[0],str_label[0], i_field[0], str_field[0]);
		fechaHoraDesde=new FechaHora(i_label[1],str_label[1],i_field[1],str_field[1],i_field[2],str_field[2],
				i_tooltipdesde[0], str_tooltipdesde[0], i_tooltipdesde[1], str_tooltipdesde[1]);
		fechaHoraHasta=new FechaHora(i_label[2],str_label[2],i_field[3],str_field[3],i_field[4],str_field[4],
				i_tooltiphasta[0], str_tooltiphasta[0], i_tooltiphasta[1], str_tooltiphasta[1]);
	}
	
	public boolean isChecked() {
		return activado.isChecked();
	}
	
	public void click() {
		activado.click();
	}
	
	public String getFechaDesde() {
		if (activado.isChecked()) {
			return fechaHoraDesde.getFecha();
		}
		else {
			return null;
		}
	}
	
	public String getFechaHasta() {
		if (activado.isChecked()) {
			return fechaHoraHasta.getFecha();
		}
		else {
			return null;
		}
	}
	
	public void writeFechaDesde(String fecha) {
		if (activado.isChecked()) {
			fechaHoraDesde.clearFecha();
			fechaHoraDesde.writeFecha(fecha);
		}
	}
	
	public void writeFechaHasta(String fecha) {
		if (activado.isChecked()) {
			fechaHoraHasta.clearFecha();
			fechaHoraHasta.writeFecha(fecha);
		}
	}
	
	public void clearFechaDesde() {
		if (activado.isChecked()) {
			fechaHoraDesde.clearFecha();
		}
	}
	
	public void clearFechaHasta() {
		if (activado.isChecked()) {
			fechaHoraHasta.clearFecha();
		}
	}
	
	public String getHoraDesde() {
		if (activado.isChecked()) {
			return fechaHoraDesde.getHora();
		}
		else {
			return null;
		}
	}
	
	public String getHoraHasta() {
		if (activado.isChecked()) {
			return fechaHoraHasta.getHora();
		}
		else {
			return null;
		}
	}
	
	public void writeHoraDesde(String fecha) {
		if (activado.isChecked()) {
			fechaHoraDesde.clearHora();
			fechaHoraDesde.writeHora(fecha);
		}
	}
	
	public void writeHoraHasta(String fecha) {
		if (activado.isChecked()) {
			fechaHoraHasta.clearHora();
			fechaHoraHasta.writeHora(fecha);
		}
	}
	
	public void clearHoraDesde() {
		if (activado.isChecked()) {
			fechaHoraDesde.clearHora();
		}
	}
	
	public void clearHoraHasta() {
		if (activado.isChecked()) {
			fechaHoraHasta.clearHora();
		}
	}
	
	public boolean hayErrorValidacionFechaDesde(int tipoerror) {
		return fechaHoraDesde.hayErrorValidacionFecha(tipoerror);
	}
	
	public boolean hayErrorValidacionHoraDesde(int tipoerror) {
		return fechaHoraDesde.hayErrorValidacionHora(tipoerror);
	}
	
	public boolean hayErrorValidacionFechaHasta(int tipoerror) {
		return fechaHoraHasta.hayErrorValidacionFecha(tipoerror);
	}
	
	public boolean hayErrorValidacionHoraHasta(int tipoerror) {
		return fechaHoraHasta.hayErrorValidacionHora(tipoerror);
	}
	
	public boolean sintacticAnalysis(String[] labels) {
		logger.debug("Inicio analisis sintactico RangoFechaHora");
		boolean resultado=activado.sintacticAnalysis(labels[0]);
		return fechaHoraDesde.sintacticAnalysis(labels[1]) && fechaHoraHasta.sintacticAnalysis(labels[2]) && resultado;
	}
}
