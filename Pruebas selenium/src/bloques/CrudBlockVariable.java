package bloques;

import elementosBase.*;
import unidadesGraficas.*;

public class CrudBlockVariable extends CrudBlock {

	private LabelLabelVariable[] bloque_criterios_variables;
	
	public CrudBlockVariable(TipoBy i_encabezado, String str_encabezado, int num_criterios_var, TipoBy[] i_labelvar, String[] str_labelvar,
			TipoBy[] i_fieldvar, String[] str_fieldvar, int num_criterios_campo, int num_criterios_desplegable,
			int num_criterios_fecha, TipoBy[] i_label, String[] str_label, TipoBy[] i_field, String[] str_field,
			TipoBy[][] i_tooltipcampo, String[][] str_tooltipcampo, TipoBy[][][] i_tooltipfecha, String[][][] str_tooltipfecha) {

		super(i_encabezado, str_encabezado, num_criterios_campo,  num_criterios_desplegable,
			 num_criterios_fecha, i_label, str_label,i_field, str_field,i_tooltipcampo,str_tooltipcampo,i_tooltipfecha,str_tooltipfecha);
		bloque_criterios_variables=new LabelLabelVariable[num_criterios_var];
		for (int i=0; i<num_criterios_var; i++) {
			bloque_criterios_variables[i]=new LabelLabelVariable(i_labelvar[i],str_labelvar[i],i_fieldvar[i],str_fieldvar[i]);
		}
	}
	
	public String leerOpcionVariable (int opcion) {
		String valor=bloque_criterios_variables[opcion].getLabel2Value();
		if (valor==null) {
			return bloque_criterios_variables[opcion].getLabel2Text();
		}
		else {
			return valor;
		}
	}
	
	public boolean sintacticAnalysis(String encabezado, String[] labelCriteriosVariables, String[] valoresVariables, String[] labelCriteriosBusqueda) {
		
		boolean resultado=true;

		for (int i=0; i<bloque_criterios_variables.length; i++) {
			resultado=bloque_criterios_variables[i].sintacticAnalysis(labelCriteriosVariables[i],valoresVariables[i]) && resultado;
		}
		return super.sintacticAnalysis(encabezado,labelCriteriosBusqueda) && resultado;
	}
}
