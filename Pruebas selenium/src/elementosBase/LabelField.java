package elementosBase;

import org.apache.log4j.Logger;

import unidadesGraficas.*;

public class LabelField {

	private Label label;
	private Field field;
	private Tooltip[] tooltip;
	private final static Logger logger = Logger.getLogger(LabelField.class);
	
	public LabelField(TipoBy i_label, String str_label, TipoBy i_field, String str_field) {
		label=new Label(i_label, str_label);
		field=new Field(i_field, str_field);
		tooltip=null;
	}
	
	public LabelField(TipoBy i_label, String str_label, TipoBy i_field, String str_field,
			TipoBy[] i_tooltip, String[] str_tooltip) {
		label=new Label(i_label, str_label);
		field=new Field(i_field, str_field);
		tooltip=new Tooltip[i_tooltip.length];
		for (int i=0; i<i_tooltip.length; i++) {
			tooltip[i]=new Tooltip(i_tooltip[i], str_tooltip[i]);
		}
	}
	
	public Label getLabel() {
		return label;
	}
	
	public Field getField() {
		return field;
	}
	
	public String getLabelText() {
		return label.getText();
	}
	
	public void writeText(String text) {
		if (text!=null) {
			field.writeText(text);
		}
		else {
			logger.trace("Error: se intenta escribir en un Field con un valor nulo");
		}
	}
	
	public void clear() {
		field.clear();
	}
	
	public boolean hayErrorValidacion(int tipoerror) {
		if (tooltip!=null) {
			boolean hayError=false;
			if (tipoerror==0) {  // Corresponde al primer valor de cualquier enumerado de error de validación de fecha, que tiene que ser "Cualquiera"
				for (int i=0; i<tooltip.length && !hayError; i++) {
					hayError=hayError || tooltip[i].hayMensajeError();
					logger.trace("tooltip["+i+"].hayMensajeError()="+tooltip[i].hayMensajeError());
				}
				return hayError;
			}
			else {
				hayError= tooltip[tipoerror-1].hayMensajeError();
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
