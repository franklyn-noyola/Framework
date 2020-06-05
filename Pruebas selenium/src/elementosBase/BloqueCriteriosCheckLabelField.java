package elementosBase;

import org.apache.log4j.Logger;
import unidadesGraficas.*;

public class BloqueCriteriosCheckLabelField {

	private LabelCheckField[] criterios_check;
	private static int num_criterios_checklabelfield;
	private final static Logger logger = Logger.getLogger(BloqueCriteriosCheckLabelField.class);
	
	public BloqueCriteriosCheckLabelField(int numero_criterios_check, TipoBy[] i_label, String[] str_label,
			TipoBy[] i_check, String[] str_check, TipoBy[] i_field, String[] str_field, TipoBy[][] i_tooltip,
			String[][] str_tooltip) {
		
		num_criterios_checklabelfield=0;
		criterios_check=new LabelCheckField[numero_criterios_check];
		logger.debug("Criterios LabelCheckField");
		for (int i=0; i<numero_criterios_check; i++) {
			addCriteriosCheck(i_label[i], str_label[i], i_check[i], str_check[i], i_field[i], str_field[i], i_tooltip[i],
					str_tooltip[i]);
		}
	}
	
	private void addCriteriosCheck(TipoBy i_label, String str_label, TipoBy i_check, String str_check, TipoBy i_field, String str_field,
			TipoBy[] i_tooltip, String[] str_tooltip) {
		criterios_check[num_criterios_checklabelfield++]=new LabelCheckField(i_label, str_label, i_check, str_check,
				i_field, str_field, i_tooltip, str_tooltip);
	}
	
	public boolean estaChequeadoCriterioCheck(int i) {
		return criterios_check[i].isChecked();
	}
	
	public void clickCriterioCheck(int i) {
		criterios_check[i].click();
	}
	
	public String leerOpcion(int i) {
		return criterios_check[i].leerOpcion();
	}

	public void escribirOpcion(int i, String valor) {
		criterios_check[i].escribirOpcion(valor);
	}

	public void borrarOpcion(int i) {
		criterios_check[i].borrarOpcion();
	}
	
	public boolean hayErrorValidacionOpcion(int i, int tipoerror) {
		return criterios_check[i].hayErrorValidacionOpcion(tipoerror);
	}
	
	public boolean sintacticAnalysis(String[] labelCriteriosBusqueda) {
		boolean analisisCriteriosBuqueda=true;
		
		for (int i=0; (i<criterios_check.length); i++) {
			analisisCriteriosBuqueda=criterios_check[i].sintacticAnalysis(labelCriteriosBusqueda[i]) && analisisCriteriosBuqueda;
		}
		return analisisCriteriosBuqueda;
	}

}
