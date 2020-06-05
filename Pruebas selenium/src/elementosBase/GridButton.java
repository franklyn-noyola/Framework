package elementosBase;

import org.apache.log4j.Logger;

import unidadesGraficas.Button;
import unidadesGraficas.TipoBy;

public class GridButton extends Grid {

	private Button[] grid_buttons;
	private final static Logger logger = Logger.getLogger(GridButton.class);
	
	public GridButton(int num_columnas, TipoBy i_leyendamostrado, String str_leyendamostrado, 
			TipoBy i_cabecera, String str_cabecera, TipoBy[] i_botones, String[] str_botones) {
		
		super(num_columnas, i_leyendamostrado, str_leyendamostrado,  i_cabecera, str_cabecera, i_botones,
				str_botones, 1);
		recargarGridButton(i_cabecera,str_cabecera);
	}

		
	public void recargarGridButton(TipoBy i_cabecera, String str_cabecera) {
		grid_buttons=new Button[getNumFilas()];
		for (int i=0; i<this.getNumFilas();i++) {
			String radioButton=str_cabecera+"tr["+(i+2)+"]/td[1]";
			grid_buttons[i]=new Button(i_cabecera,radioButton);
		}
	}

	public void pulsarBoton(int i) {
		super.pulsarBoton(i);
		recargarGridButton(i_cabecera, str_cabecera);
	}
	
	public void pulsarFila(int indice) { // El indice empieza por 1
		if (grid_buttons!=null) {
			if (indice>=1 && indice<=getNumFilasMostradas()) {
				grid_buttons[indice-1].click();
			}
			else {
				logger.error("Error: se quiere clicar la fila "+indice+" pero solo hay "+getNumFilasMostradas()+" filas");
			}
		}
		else {
			logger.error("Error: la Tabla no tiene buttons para clicar");
		}
	}
}
