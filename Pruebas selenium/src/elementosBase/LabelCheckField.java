package elementosBase;

import org.apache.log4j.Logger;

import unidadesGraficas.*;

public class LabelCheckField {

	Label label;
	Check check;
	Field field;
	private Tooltip[] tooltip;
	private final static Logger logger = Logger.getLogger(LabelCheckField.class);
	
	public LabelCheckField(TipoBy i_label, String str_label, TipoBy i_check, String str_check, TipoBy i_field,
			String str_field, TipoBy[] i_tooltip, String[] str_tooltip) {
		label=new Label(i_label, str_label);
		check=new Check(i_check, str_check);
		field=new Field(i_field, str_field);
		tooltip=new Tooltip[i_tooltip.length];
		for (int i=0; i<i_tooltip.length; i++) {
			tooltip[i]=new Tooltip(i_tooltip[i], str_tooltip[i]);
		}
	}
	
	boolean isChecked() {
		return check.isChecked();
	}

	public void click() {
		check.click();
	}
	
	public String leerOpcion() {
		return field.getValue();
	}
	
	public void escribirOpcion(String valor) {
		field.writeText(valor);
	}
	
	public void borrarOpcion() {
		field.clear();
	}
	
	public boolean hayErrorValidacionOpcion(int tipoerror) {
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
	
	public boolean sintacticAnalysis(String labelcheck) {
		return label.sintacticAnalysis(labelcheck);
	}
}
