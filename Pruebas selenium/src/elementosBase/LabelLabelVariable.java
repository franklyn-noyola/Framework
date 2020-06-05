package elementosBase;

import unidadesGraficas.*;

public class LabelLabelVariable {

	private Label label1;
	private LabelVariable label2;
	
	public LabelLabelVariable(TipoBy i_label1, String str_label1, TipoBy i_label2, String str_label2) {
		label1=new Label(i_label1, str_label1);
		label2=new LabelVariable(i_label2, str_label2);
	}
	
	public String getLabel1Text() {
		return label1.getText();
	}
	
	public String getLabel2Text() {
		return label2.getText();
	}
	
	public String getLabel2Value() {
		return label2.getValue();
	}
	
	public boolean sintacticAnalysis(String label, String value) {
		return label1.sintacticAnalysis(label) && label2.sintacticAnalysis(value);
	}
}
