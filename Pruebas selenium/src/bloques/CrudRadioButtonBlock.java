package bloques;

import elementosBase.*;
import unidadesGraficas.*;

public class CrudRadioButtonBlock extends CrudBlock {

	private BloqueCriteriosRadioButton bloque_criterios_radiobutton=null;
	
	public CrudRadioButtonBlock(TipoBy i_encabezado, String str_encabezado,int num_criterios_campo, int num_criterios_desplegable,
			int num_criterios_fecha, int numero_criterios_check, int[] num_checks, TipoBy[] i_label, String[] str_label,
			TipoBy[] i_field, String[] str_field, TipoBy[][] i_tooltipcampo, String[][] str_tooltipcampo, 
			TipoBy[][][] i_tooltipfecha, String[][][] str_tooltipfecha, 
			TipoBy[][] i_labelradiobutton, String[][] str_labelradiobutton, TipoBy[][] i_radiobuttons, String[][] str_radiobuttons) {
		
		super(i_encabezado,  str_encabezado, num_criterios_campo,  num_criterios_desplegable,
				 num_criterios_fecha,i_label,str_label, i_field, str_field, i_tooltipcampo,str_tooltipcampo,i_tooltipfecha,str_tooltipfecha);
		bloque_criterios_radiobutton=new BloqueCriteriosRadioButton(numero_criterios_check, num_checks, i_labelradiobutton, str_labelradiobutton, i_radiobuttons, str_radiobuttons);
	}
	
	public String leerCriterioRadioButton(int opcion) {
		return bloque_criterios_radiobutton.leerCriterioRadioButton(opcion);
	}
	
	public void clickCriterioRadioButton(int i, int check) {
		bloque_criterios_radiobutton.clickCriterioRadioButton(i, check);
	}
	
	
	public boolean sintacticAnalysis(String encabezado, String[] labelCriteriosBusqueda, String[][] labelCriteriosRadioButton) {
		
		boolean resultado=true;
		
		if (encabezado!=null) {
			resultado=this.encabezado.sintacticAnalysis(encabezado);
		}
		if (labelCriteriosBusqueda!=null) {
			resultado=bloque_criterios.sintacticAnalysis(labelCriteriosBusqueda) && resultado;
			resultado=bloque_criterios_radiobutton.sintacticAnalysis(labelCriteriosRadioButton) && resultado;
		}
		return resultado;
	}
}
