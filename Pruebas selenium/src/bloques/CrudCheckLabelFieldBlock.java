package bloques;

import elementosBase.*;
import unidadesGraficas.*;

public class CrudCheckLabelFieldBlock extends CrudCheckLabelDropdownBlock {

	private BloqueCriteriosCheckLabelField bloque_criterios_checklabelfield=null;
	
	public CrudCheckLabelFieldBlock(TipoBy i_encabezado, String str_encabezado,int num_criterios_campo, int num_criterios_desplegable,
			int num_criterios_fecha, int num_criterios_check,  int numero_criterios_field, TipoBy[] i_label, String[] str_label,
			TipoBy[] i_field, String[] str_field, TipoBy[][] i_tooltipcampo, String[][] str_tooltipcampo, 
			TipoBy[][][] i_tooltipfecha, String[][][] str_tooltipfecha, 
			TipoBy[] i_labelcheck, String[] str_labelcheck, TipoBy[] i_check, String[] str_check,
			TipoBy[] i_dropdown, String[] str_dropdown, 
			TipoBy[] i_labelfield, String[] str_labelfield, TipoBy[] i_checkfield, String[] str_checkfield,
			TipoBy[] i_field2, String[] str_field2, TipoBy[][] i_tooltip2, String[][] str_tooltip2) {
		
		super(i_encabezado,  str_encabezado, num_criterios_campo,  num_criterios_desplegable,
			 num_criterios_fecha, num_criterios_check,i_label,str_label, i_field, str_field,
			 i_tooltipcampo, str_tooltipcampo,i_tooltipfecha,str_tooltipfecha,
			 i_labelcheck, str_labelcheck, i_check, str_check, i_dropdown, str_dropdown);
		bloque_criterios_checklabelfield=new BloqueCriteriosCheckLabelField(numero_criterios_field, i_labelfield, str_labelfield,
				i_checkfield, str_checkfield, i_field2, str_field2,i_tooltip2,str_tooltip2);
	}
	
	public boolean estaChequeadoCriterioCheck(int i) {
		return bloque_criterios_checklabelfield.estaChequeadoCriterioCheck(i);
	}
	
	public void clickCriterioCheckField(int i) {
		bloque_criterios_checklabelfield.clickCriterioCheck(i);
	}
	
	public String leerCriterioCheckField(int i) {
		return bloque_criterios_checklabelfield.leerOpcion(i);
	}
	
	public void escribirCriterioCheckField(int i, String valor) {
		 bloque_criterios_checklabelfield.escribirOpcion(i, valor);
	}
	
	public void borrarCriterioCheckField(int i) {
		 bloque_criterios_checklabelfield.borrarOpcion(i);
	}
	
	public boolean hayErrorValidacionCheckField(int opcion, int tipoerror) {
		return bloque_criterios_checklabelfield.hayErrorValidacionOpcion(opcion, tipoerror);
	}
	
	public boolean sintacticAnalysis(String encabezado, String[] labelCriteriosBusqueda, String[] labelCriteriosCheckDropdown, String[] labelCriteriosCheckField) {
		
		boolean resultado=true;
		
		if (labelCriteriosBusqueda!=null) {
			resultado=super.sintacticAnalysis(encabezado, labelCriteriosBusqueda, labelCriteriosCheckDropdown) && resultado;
		}
		return bloque_criterios_checklabelfield.sintacticAnalysis(labelCriteriosCheckField) && resultado;
	}
}
