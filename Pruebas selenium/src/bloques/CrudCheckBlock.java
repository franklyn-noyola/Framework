package bloques;

import elementosBase.*;
import unidadesGraficas.*;

public class CrudCheckBlock extends CrudBlock {

	private BloqueCriteriosLabelCheckLabels bloque_criterios_check=null;
	
	public CrudCheckBlock(TipoBy i_encabezado, String str_encabezado,int num_criterios_campo, int num_criterios_desplegable,
			int num_criterios_fecha, int numero_criterios_check, int[] num_checks, TipoBy[] i_label, String[] str_label,
			TipoBy[] i_field, String[] str_field, TipoBy[][] i_tooltipcampo, String[][] str_tooltipcampo, 
			TipoBy[][][] i_tooltipfecha, String[][][] str_tooltipfecha,
			TipoBy[][] i_labelcheck, String[][] str_labelcheck, TipoBy[][] i_checks, String[][] str_checks) {
		
		super(i_encabezado,  str_encabezado, num_criterios_campo,  num_criterios_desplegable,
			 num_criterios_fecha,i_label,str_label, i_field, str_field, i_tooltipcampo,str_tooltipcampo,i_tooltipfecha,str_tooltipfecha);
		bloque_criterios_check=new BloqueCriteriosLabelCheckLabels(numero_criterios_check, num_checks, i_labelcheck, str_labelcheck, i_checks, str_checks);
	}
	
	public String leerCriterioCheck(int opcion) {
		return bloque_criterios_check.leerCriterioCheck(opcion);
	}
	
	public void clickCriterioCheck(int i, int check) {
		bloque_criterios_check.clickCriterioCheck(i, check);
	}
	
	public boolean estaChequeadoCriterioCheck(int i, int check) {
		return bloque_criterios_check.estaChequeadoCriterioCheck(i,check);
	}
	
	
	public boolean sintacticAnalysis(String encabezado, String[] labelCriteriosBusqueda, String[][] labelCriteriosCheck) {
		
		boolean resultado=true;
		
		if (encabezado!=null) {
			resultado=this.encabezado.sintacticAnalysis(encabezado);
		}
		if (labelCriteriosBusqueda!=null) {
			resultado=bloque_criterios.sintacticAnalysis(labelCriteriosBusqueda) && resultado;
			resultado=bloque_criterios_check.sintacticAnalysis(labelCriteriosCheck) && resultado;
		}
		return resultado;
	}
}
