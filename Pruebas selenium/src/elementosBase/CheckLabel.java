package elementosBase;

import unidadesGraficas.*;

public class CheckLabel {

	Check check;
	Label label=null;
	
	public CheckLabel(TipoBy i_label, String str_label, TipoBy i_check, String str_check) {
		check=new Check(i_check, str_check);
		if (i_label!=null && str_label!=null) label=new Label(i_label, str_label);
	}
	
	public boolean isChecked() {
		return check.isChecked();
	}
	
	public void click() {
		check.click();
	}
	
	public String getText() {
		if (label!=null)  return label.getText();
		else return null;
	}
	
	public boolean sintacticAnalysis(String str_label) {
		if (str_label!=null) return label.sintacticAnalysis(str_label);
		else return true;
	}
}
