package elementosBase;

import unidadesGraficas.*;

public class LabelCheckLabels {

	Label label=null;
	CheckLabel[] checklabels;
	
	public LabelCheckLabels(TipoBy[] i_label, String[] str_label, TipoBy[] i_check, String[] str_check) {

		if (i_label!=null) {
			label=new Label(i_label[0], str_label[0]);
			checklabels=new CheckLabel[i_label.length-1];
			for (int i=1; i<i_label.length; i++) {
				checklabels[i-1]=new CheckLabel(i_label[i], str_label[i], i_check[i], str_check[i]);
			}
		}
	}
	
	public String leerCheck() {
		boolean encontrado=false;
		int j=0;
		for (int i=0; i<checklabels.length && !encontrado; i++) {
			if (checklabels[i].isChecked()) {
				encontrado=true;
				j=i;
			}
		}
		return checklabels[j].getText();
	}
	
	public void click(int indice) {
		checklabels[indice].click();
	}
	
	public boolean isChecked(int indice) {
		return checklabels[indice].isChecked();
	}
	
	public boolean sintacticAnalysis(String[] labelscheck) {
		boolean resultado=label.sintacticAnalysis(labelscheck[0]);
		for (int i=1; i<labelscheck.length; i++) {
			resultado=checklabels[i-1].sintacticAnalysis(labelscheck[i]) && resultado;
		}
		return resultado;
	}
}
