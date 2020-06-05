package bloques;

import elementosBase.CheckLabel;
import unidadesGraficas.*;

public class CheckBlock {

	private Title encabezado=null;
	private CheckLabel check;
	
	public CheckBlock(TipoBy i_encabezado, String str_encabezado, TipoBy i_check, String str_check, TipoBy i_label, String str_label) {
		
		if (i_encabezado!=null && str_encabezado!=null) {
			encabezado=new Title(i_encabezado, str_encabezado);
		}
		check=new CheckLabel(i_check, str_check, i_label, str_label);
	}
	
	public void click() {
		check.click();
	}
	
	public boolean estaChequeadoCriterioCheck() {
		return check.isChecked();
	}
	
	public boolean sintacticAnalysis(String encabezado, String label) {
		
		boolean resultado=true;
		
		if (encabezado!=null) {
			resultado=this.encabezado.sintacticAnalysis(encabezado);
		}
		return check.sintacticAnalysis(label) && resultado;
	}
}
