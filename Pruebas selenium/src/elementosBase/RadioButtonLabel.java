package elementosBase;

import unidadesGraficas.*;

public class RadioButtonLabel {

	RadioButton radiobutton;
	Label label;
	
	public RadioButtonLabel(TipoBy i_label, String str_label, TipoBy i_radiobutton, String str_radiobutton) {
		radiobutton=new RadioButton(i_radiobutton, str_radiobutton);
		label=new Label(i_label, str_label);
	}
	
	public String getText() {
		return label.getText();
	}
	
	public boolean isChecked() {
		return radiobutton.isChecked();
	}
	
	public void click() {
		radiobutton.click();
	}
	
	public String getTextLabel() {
		return label.getText();
	}
	
	public boolean sintacticAnalysis(String str_label) {
		return label.sintacticAnalysis(str_label);
	}
}
