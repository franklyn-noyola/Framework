package elementosBase;

import unidadesGraficas.*;
import procesosComunes.Error;

public class LabelCheckDropdown {

	Label label;
	Check check;
	DropDown dropdown;
	
	public LabelCheckDropdown(TipoBy i_label, String str_label, TipoBy i_check, String str_check, TipoBy i_dropdown,
			String str_dropdown) {
		label=new Label(i_label, str_label);
		check=new Check(i_check, str_check);
		dropdown=new DropDown(i_dropdown, str_dropdown);
	}
	
	boolean isChecked() {
		return check.isChecked();
	}

	public void click() {
		check.click();
	}
	
	public String leerOpcion() {
		return dropdown.getFirst();
	}
	
	public Error seleccionarOpcion(String opcion) {
		return dropdown.seleccionarOpcion(opcion);
	}
	
	public boolean sintacticAnalysis(String labelcheck) {
		return label.sintacticAnalysis(labelcheck);
	}
}
