package elementosBase;

import org.apache.log4j.Logger;
import procesosComunes.Error;
import unidadesGraficas.*;

public class BloqueCriteriosCheckLabelDrodown {

	private LabelCheckDropdown[] criterios_check;
	private static int num_criterios_checklabeldropdown;
	private final static Logger logger = Logger.getLogger(BloqueCriteriosCheckLabelDrodown.class);
	
	public BloqueCriteriosCheckLabelDrodown(int numero_criterios_check, TipoBy[] i_label, String[] str_label,
			TipoBy[] i_check, String[] str_check, TipoBy[] i_dropdown, String[] str_dropdown) {
		
		num_criterios_checklabeldropdown=0;
		criterios_check=new LabelCheckDropdown[numero_criterios_check];
		logger.debug("Criterios checkLabelDropdown");
		for (int i=0; i<numero_criterios_check; i++) {
			addCriteriosCheck(i_label[i], str_label[i], i_check[i], str_check[i], i_dropdown[i], str_dropdown[i]);
		}
	}
	
	private void addCriteriosCheck(TipoBy i_label, String str_label, TipoBy i_check, String str_check, TipoBy i_dropdown,
			String str_dropdown) {
		criterios_check[num_criterios_checklabeldropdown++]=new LabelCheckDropdown(i_label, str_label, i_check, str_check,
				i_dropdown, str_dropdown);
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
	
	public Error seleccionarOpcion(int i, String valor) {
		return criterios_check[i].seleccionarOpcion(valor);
	}
	
	public boolean sintacticAnalysis(String[] labelCriteriosBusqueda) {
		boolean analisisCriteriosBuqueda=true;
		
		for (int i=0; (i<criterios_check.length); i++) {
			analisisCriteriosBuqueda=criterios_check[i].sintacticAnalysis(labelCriteriosBusqueda[i]) && analisisCriteriosBuqueda;
		}
		return analisisCriteriosBuqueda;
	}

}
