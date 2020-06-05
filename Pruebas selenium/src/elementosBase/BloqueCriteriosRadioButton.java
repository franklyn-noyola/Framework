package elementosBase;

import org.apache.log4j.Logger;
import unidadesGraficas.*;

public class BloqueCriteriosRadioButton {

	private LabelRadioButtonLabels[] criterios_radioButton;
	private static int num_criterios_radiobutton;
	private final static Logger logger = Logger.getLogger(BloqueCriteriosRadioButton.class);
	
	public BloqueCriteriosRadioButton(int numero_criterios_radiobutton, int[] num_checks, TipoBy[][] i_label, String[][] str_label,
			TipoBy[][] i_field, String[][] str_field) {
		
		num_criterios_radiobutton=0;
		criterios_radioButton=new LabelRadioButtonLabels[numero_criterios_radiobutton];
		logger.debug("Criterios check");
		for (int i=0; i<numero_criterios_radiobutton; i++) {
			addCriteriosCheck(i_label[i], str_label[i], i_field[i], str_field[i]);
		}
	}
	
	private void addCriteriosCheck(TipoBy[] i_label, String[] str_label, TipoBy[] i_field, String[] str_field) {
		criterios_radioButton[num_criterios_radiobutton++]=new LabelRadioButtonLabels(i_label, str_label, i_field, str_field);
	}
	
	public String leerCriterioRadioButton(int i) {
		return criterios_radioButton[i].leerRadioButton();
	}
	
	public void clickCriterioRadioButton(int i, int check) {
		criterios_radioButton[i].click(check);
	}
	
	public boolean sintacticAnalysis(String[][] labelCriteriosBusqueda) {
		boolean analisisCriteriosBuqueda=true;
		
		for (int i=0; (i<criterios_radioButton.length); i++) {
			analisisCriteriosBuqueda=criterios_radioButton[i].sintacticAnalysis(labelCriteriosBusqueda[i]) && analisisCriteriosBuqueda;
		}
		return analisisCriteriosBuqueda;
	}

}
