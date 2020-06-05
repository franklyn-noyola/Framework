package bloques;

import elementosBase.*;
import procesosComunes.Error;
import unidadesGraficas.*;

public class CrudCheckLabelDropdownBlock extends CrudBlock {

	private BloqueCriteriosCheckLabelDrodown bloque_criterios_check=null;
	
	public CrudCheckLabelDropdownBlock(TipoBy i_encabezado, String str_encabezado,int num_criterios_campo, int num_criterios_desplegable,
			int num_criterios_fecha, int numero_criterios_check, TipoBy[] i_label, String[] str_label,
			TipoBy[] i_field, String[] str_field, TipoBy[][] i_tooltipcampo, String[][] str_tooltipcampo, 
			TipoBy[][][] i_tooltipfecha, String[][][] str_tooltipfecha, 
			TipoBy[] i_labelcheck, String[] str_labelcheck, TipoBy[] i_check, String[] str_check,
			TipoBy[] i_dropdown, String[] str_dropdown) {
		
		super(i_encabezado,  str_encabezado, num_criterios_campo,  num_criterios_desplegable,
				 num_criterios_fecha,i_label,str_label, i_field, str_field, i_tooltipcampo, str_tooltipcampo,i_tooltipfecha,str_tooltipfecha);
		bloque_criterios_check=new BloqueCriteriosCheckLabelDrodown(numero_criterios_check, i_labelcheck, str_labelcheck,
				i_check, str_check, i_dropdown, str_dropdown);
	}
	
	public boolean estaChequeadoCriterioCheck(int i) {
		return bloque_criterios_check.estaChequeadoCriterioCheck(i);
	}
	
	public void clickCriterioCheck(int i) {
		bloque_criterios_check.clickCriterioCheck(i);
	}
	
	public String leerCriterioDesplegable(int i) {
		return bloque_criterios_check.leerOpcion(i);
	}
	
	public Error seleccionarCriterioDesplegable(int i, String valor) {
		return bloque_criterios_check.seleccionarOpcion(i, valor);
	}
	
	public boolean sintacticAnalysis(String encabezado, String[] labelCriteriosBusqueda, String[] labelCriteriosCheck) {
		
		boolean resultado=true;

		if (labelCriteriosBusqueda!=null) {
			resultado=super.sintacticAnalysis(encabezado, labelCriteriosBusqueda) && resultado;
		}
		return bloque_criterios_check.sintacticAnalysis(labelCriteriosCheck) && resultado;
	}
}
