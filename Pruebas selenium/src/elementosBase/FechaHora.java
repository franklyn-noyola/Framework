package elementosBase;

import org.apache.log4j.Logger;

import unidadesGraficas.*;

public class FechaHora {

	private Label label;
	private Field fecha;
	private Field hora;
	private Tooltip[] tooltipfecha=null;
	private Tooltip[] tooltiphora=null;
	private final static Logger logger = Logger.getLogger(FechaHora.class);
	
	public FechaHora(TipoBy i_label, String str_label,TipoBy i_fecha, String str_fecha, TipoBy i_hora, String str_hora) {
		label=new Label(i_label, str_label);
		fecha=new Field(i_fecha, str_fecha);
		if (i_hora==null || str_hora==null) {
			hora=null;
		}
		else {
			hora=new Field(i_hora, str_hora);
		}
	}
	
	public FechaHora(TipoBy i_label, String str_label,TipoBy i_fecha, String str_fecha, TipoBy i_hora, String str_hora,
			TipoBy[] i_tooltipfecha, String[] str_tooltipfecha,
			TipoBy[] i_tooltiphora, String[] str_tooltiphora)  throws RuntimeException {
		
		if (i_tooltipfecha!=null && str_tooltipfecha!=null) {
			if (i_tooltipfecha.length!=str_tooltipfecha.length) {
				logger.fatal("FechaHora: i_tooltipfecha.length="+i_tooltipfecha.length+"<>str_tooltipfecha.length="+str_tooltipfecha.length);
				throw new RuntimeException("FechaHora: i_tooltipfecha.length="+i_tooltipfecha.length+"<>str_tooltipfecha.length="+str_tooltipfecha.length);
			}
		}
		if (i_tooltiphora!=null && str_tooltiphora!=null) {
			if (i_tooltiphora.length!=str_tooltiphora.length) {
				logger.fatal("FechaHora: i_tooltiphora.length="+i_tooltiphora.length+"<>str_tooltiphora.length="+str_tooltiphora.length);
				throw new RuntimeException("FechaHora: i_tooltiphora.length="+i_tooltiphora.length+"<>str_tooltiphora.length="+str_tooltiphora.length);
			}
		}
		label=new Label(i_label, str_label);
		fecha=new Field(i_fecha, str_fecha);
		if (i_hora==null || str_hora==null) {
			hora=null;
		}
		else {
			hora=new Field(i_hora, str_hora);
		}
		if (i_tooltipfecha!=null && str_tooltipfecha!=null) {
			tooltipfecha=new Tooltip[i_tooltipfecha.length];
			for (int i=0; i<i_tooltipfecha.length; i++) {
				tooltipfecha[i]=new Tooltip(i_tooltipfecha[i], str_tooltipfecha[i]);
			}
		}
		if (i_tooltiphora!=null && str_tooltiphora!=null) {
			tooltiphora=new Tooltip[i_tooltiphora.length];
			for (int i=0; i<i_tooltiphora.length; i++) {
				tooltiphora[i]=new Tooltip(i_tooltiphora[i], str_tooltiphora[i]);
			}
		}
	}
	
	public String getText() {
		return label.getText();
	}
	
	public String getFecha() {
		return fecha.getValue();
	}
	
	public String getHora() {
		return hora.getValue();
	}
	
	private Field fecha() {
		return fecha;
	}
	
	private Field hora() {
		return hora;
	}
	
	public void writeFecha(String fecha) {
		this.clearFecha();
		this.fecha.writeText(fecha);
	}
	
	public void clearFecha() {
		this.fecha().clear();
	}
	
	public void writeHora(String hora) {
		this.clearHora();
		this.hora.writeText(hora);
	}
	
	public void clearHora() {
		this.hora().clear();
	}
	
	public boolean hayErrorValidacionFecha(int tipoerror) {
		if (tooltipfecha!=null) {
			boolean hayError=false;
			if (tipoerror==0) {  // Corresponde al primer valor de cualquier enumerado de error de validación de fecha, que tiene que ser "Cualquiera"
				for (int i=0; i<tooltipfecha.length && !hayError; i++) {
					hayError=hayError || tooltipfecha[i].hayMensajeError();
					logger.trace("tooltipfecha["+i+"].hayMensajeError()="+tooltipfecha[i].hayMensajeError());
				}
				return hayError;
			}
			else {
				hayError= tooltipfecha[tipoerror-1].hayMensajeError();
				return hayError;
			}
				
		}
		else {
			return false;
		}
	}
	
	public boolean hayErrorValidacionHora(int tipoerror) {
		if (tooltiphora!=null) {
			boolean hayError=false;
			if (tipoerror==0) {  // Corresponde al primer valor de cualquier enumerado de error de validación de fecha, que tiene que ser "Cualquiera"
				for (int i=0; i<tooltiphora.length && !hayError; i++) {
					hayError=hayError || tooltiphora[i].hayMensajeError();
					logger.trace("tooltiphora["+i+"].hayMensajeError()="+tooltiphora[i].hayMensajeError());
				}
				return hayError;
			}
			else {
				hayError= tooltiphora[tipoerror-1].hayMensajeError();
				return hayError;
			}
				
		}
		else {
			return false;
		}
	}
	
	public boolean sintacticAnalysis(String label) {
		return this.label.sintacticAnalysis(label);
	}
}
