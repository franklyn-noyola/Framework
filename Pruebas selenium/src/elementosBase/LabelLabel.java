package elementosBase;

import unidadesGraficas.*;

public class LabelLabel {

	private Label label1;
	private Label label2;
	
	public LabelLabel(TipoBy i_label1, String str_label1, TipoBy i_label2, String str_label2) {
		label1=new Label(i_label1, str_label1);
		label2=new Label(i_label2, str_label2);
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
		return label1.sintacticAnalysis(label) && label2.sintacticAnalysisValue(value);
	}
}
