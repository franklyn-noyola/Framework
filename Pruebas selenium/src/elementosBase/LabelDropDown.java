package elementosBase;

import unidadesGraficas.*;
import procesosComunes.Error;

public class LabelDropDown {

	private Label label;
	private DropDown drop_down;
	
	public LabelDropDown(TipoBy i_label, String str_label, TipoBy i_field, String str_field) {
		label=new Label(i_label, str_label);
		drop_down=new DropDown(i_field, str_field);
	}
	
	public Label getLabel() {
		return label;
	}
	
	public String getText() {
		return label.getText();
	}
	
	public String leerOpcion() {
		return drop_down.getFirst();
	}
	
	public Error seleccionarOpcion(String opcion) {
		return drop_down.seleccionarOpcion(opcion);
	}
	
	public boolean sintacticAnalysis(String label) {
		return this.label.sintacticAnalysis(label);
	}
	
}
