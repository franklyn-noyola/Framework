package elementosBase;

import org.apache.log4j.Logger;
import unidadesGraficas.*;

public class BloqueCriteriosLabelCheckLabels {

	private LabelCheckLabels[] criterios_check;
	private static int num_criterios_check;
	private final static Logger logger = Logger.getLogger(BloqueCriteriosLabelCheckLabels.class);
	
	public BloqueCriteriosLabelCheckLabels(int numero_criterios_check, int[] num_checks, TipoBy[][] i_label, String[][] str_label,
			TipoBy[][] i_field, String[][] str_field) {
		
		num_criterios_check=0;
		criterios_check=new LabelCheckLabels[numero_criterios_check];
		logger.debug("Criterios check");
		for (int i=0; i<numero_criterios_check; i++) {
			addCriteriosCheck(i_label[i], str_label[i], i_field[i], str_field[i]);
		}
	}
	
	private void addCriteriosCheck(TipoBy[] i_label, String[] str_label, TipoBy[] i_field, String[] str_field) {
		criterios_check[num_criterios_check++]=new LabelCheckLabels(i_label, str_label, i_field, str_field);
	}
	
	public String leerCriterioCheck(int i) {
		return criterios_check[i].leerCheck();
	}
	
	public void clickCriterioCheck(int i, int check) {
		criterios_check[i].click(check);
	}
	
	public boolean estaChequeadoCriterioCheck(int i, int check) {
		return criterios_check[i].isChecked(check);
	}
	
	public boolean sintacticAnalysis(String[][] labelCriteriosBusqueda) {
		boolean analisisCriteriosBuqueda=true;
		
		for (int i=0; (i<criterios_check.length); i++) {
			analisisCriteriosBuqueda=criterios_check[i].sintacticAnalysis(labelCriteriosBusqueda[i]) && analisisCriteriosBuqueda;
		}
		return analisisCriteriosBuqueda;
	}

}
