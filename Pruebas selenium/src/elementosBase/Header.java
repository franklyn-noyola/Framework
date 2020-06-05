package elementosBase;

import unidadesGraficas.*;

public class Header {

	private Label[] columnas;

	public Header(TipoBy i, String str, int num_columnas, int columna_inicial) {
		columnas=new Label[num_columnas];
		str=str+"tr[1]/td[";
		for (int j=0;j<num_columnas;j++) {
			columnas[j]=new Label(i,str+(j+1+columna_inicial)+"]");
		}
	}
	
	
	public int getNumColumn(String columnname) {
		boolean found=false;
		int j=0;
		for (int i=0; i<columnas.length && !found; i++) {
			found=columnas[i].getText().equals(columnname);
			j=i+1;  //El número de columna empieza siempre por 1
		}
		if (found) {
			return j;
		}
		else {
			return -1;
		}
	}
	
	public boolean sintacticAnalysis(String[] labelCabecera) {

		boolean analisis=true;

		for (int i=0; i<labelCabecera.length; i++) {
			analisis=columnas[i].sintacticAnalysis(labelCabecera[i]) && analisis;
		}
		
		return analisis;
	}
}
