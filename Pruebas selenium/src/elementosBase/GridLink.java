package elementosBase;

import org.apache.log4j.Logger;
import unidadesGraficas.Link;
import unidadesGraficas.TipoBy;

public class GridLink extends Grid {

	private Link[] grid_links;
	private final static Logger logger = Logger.getLogger(GridLink.class);
	
	public GridLink(int num_columnas, TipoBy i_leyendamostrado, String str_leyendamostrado, /*int num_botones,*/
			TipoBy i_cabecera, String str_cabecera, TipoBy[] i_botones, String[] str_botones, int columna_inicial) {
		
		super(num_columnas, i_leyendamostrado, str_leyendamostrado, /*num_botones,*/ i_cabecera, str_cabecera, i_botones,
				str_botones, columna_inicial);
		int fila_inicial=getNumPrimeraFilaMostrada();
		parseContentGrid(i_cabecera,str_cabecera,fila_inicial);
	}

		
	public void parseContentGrid(TipoBy i_cabecera, String str_cabecera, int fila_inicial) {
		grid_links=new Link[getNumFilas()];
		for (int i=fila_inicial-1; i<this.getNumFilas();i++) {
			String link=str_cabecera+"tr["+(i+2)+"]/td[1]";
			grid_links[i]=new Link(i_cabecera,link);
		}
	}

	public void pulsarLink(int indice) { // El indice empieza por 1
		if (grid_links!=null) {
			if (indice>=1 && indice<=getNumFilas()) {
				grid_links[indice-1].click();
			}
			else {
				logger.error("Error: se quiere clicar la fila "+indice+" pero solo hay "+getNumFilas()+" filas");
			}
		}
		else {
			logger.error("Error: la Tabla no tiene radio buttons para clicar");
		}
	}
}
