package elementosBase;

import unidadesGraficas.*;

public class LabelRadioButtonLabels {

	Label label=null;
	RadioButtonLabel[] radiobuttonlabels;
	
	public LabelRadioButtonLabels(TipoBy[] i_label, String[] str_label, TipoBy[] i_radiobuttons, String[] str_radiobuittons) {

		if (i_label!=null) {
			label=new Label(i_label[0], str_label[0]);
			radiobuttonlabels=new RadioButtonLabel[i_label.length-1];
			for (int i=1; i<i_label.length; i++) {
				radiobuttonlabels[i-1]=new RadioButtonLabel(i_label[i], str_label[i], i_radiobuttons[i], str_radiobuittons[i]);
			}
		}
	}
	
	public String leerRadioButton() {
		boolean encontrado=false;
		int j=0;
		for (int i=0; i<radiobuttonlabels.length && !encontrado; i++) {
			if (radiobuttonlabels[i].isChecked()) {
				encontrado=true;
				j=i;
			}
		}
		return radiobuttonlabels[j].getText();
	}
	
	public void click(int indice) {
		radiobuttonlabels[indice].click();
	}
	
	public boolean sintacticAnalysis(String[] labelsradiobuttons) {
		boolean resultado=label.sintacticAnalysis(labelsradiobuttons[0]);
		for (int i=1; i<labelsradiobuttons.length; i++) {
			resultado=radiobuttonlabels[i-1].sintacticAnalysis(labelsradiobuttons[i]) && resultado;
		}
		return resultado;
	}
}
