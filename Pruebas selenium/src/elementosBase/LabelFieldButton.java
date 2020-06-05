package elementosBase;

import unidadesGraficas.*;

public class LabelFieldButton {

	LabelField labelfield;
	private Button button;
	
	public LabelFieldButton(TipoBy i_label, String str_label, TipoBy i_field, String str_field, TipoBy i_button, String str_button) {
		labelfield=new LabelField(i_label, str_label, i_field, str_field);
		button=new Button(i_button, str_button);
	}
	
	public void click() {
		button.click();
	}
	
	public boolean sintacticAnalysis(String label, String button) {
		return labelfield.getLabel().sintacticAnalysis(label) && this.button.sintacticAnalysis(button);
	}
}
