package elementosBase;

import org.apache.log4j.Logger;

import unidadesGraficas.RadioButton;
import unidadesGraficas.TipoBy;

public class GridRadioButton extends Grid {

	private RadioButton[] grid_radiobuttons;
	private final static Logger logger = Logger.getLogger(GridRadioButton.class);
	
	public GridRadioButton(int num_columnas, TipoBy i_leyendamostrado, String str_leyendamostrado,
			TipoBy i_cabecera, String str_cabecera, TipoBy[] i_botones, String[] str_botones) {
		
		super(num_columnas, i_leyendamostrado, str_leyendamostrado, i_cabecera, str_cabecera, i_botones,
				str_botones, 1);
		recargarGridRadioButton(i_cabecera,str_cabecera);
	}

		
	public void recargarGridRadioButton(TipoBy i_cabecera, String str_cabecera) {
		grid_radiobuttons=new RadioButton[getNumFilas()];
		for (int i=0; i<this.getNumFilas();i++) {
			String radioButton=str_cabecera+"tr["+(i+2)+"]/td[1]/input";
			grid_radiobuttons[i]=new RadioButton(i_cabecera,radioButton);
		}
	}

	public void pulsarBoton(int i) {
		super.pulsarBoton(i);
		recargarGridRadioButton(i_cabecera, str_cabecera);
	}
	
	public void seleccionarFila(int numfila) { // El número de fila empieza por 1
		if (grid_radiobuttons!=null) {
			if (numfila>=1 && numfila<=getNumFilasMostradas()) {
				grid_radiobuttons[numfila-1].click();
			}
			else {
				logger.error("Error: se quiere clicar la fila "+numfila+" pero solo hay "+getNumFilasMostradas()+" filas");
			}
		}
		else {
			logger.error("Error: la Tabla no tiene radio buttons para clicar");
		}
	}
}
