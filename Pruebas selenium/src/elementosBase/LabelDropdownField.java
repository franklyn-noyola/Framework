package elementosBase;

import unidadesGraficas.*;

public class LabelDropdownField {

	private LabelDropDown labeldropdown;
	private Field field;
	
	public LabelDropdownField(TipoBy i_label, String str_label, TipoBy i_dropdown, String str_dropdown, TipoBy i_field, String str_field) {
		labeldropdown=new LabelDropDown(i_label, str_label, i_dropdown, str_dropdown);
		field=new Field(i_field, str_field);
	}
	
	public boolean sintacticAnalysis(String label) {
		return labeldropdown.getLabel().getText().equals(label);
	}
}
