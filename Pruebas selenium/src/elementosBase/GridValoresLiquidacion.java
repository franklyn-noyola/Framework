package elementosBase;

import java.util.ArrayList;
import java.util.Iterator;
import org.apache.log4j.Logger;

import unidadesGraficas.Element;
import unidadesGraficas.Field;
import unidadesGraficas.Label;
import unidadesGraficas.TipoBy;

public class GridValoresLiquidacion extends GridEstatico {

	private ArrayList<Element[]> content_grid;
	private final static Logger logger = Logger.getLogger(GridValoresLiquidacion.class);
	
	
	public GridValoresLiquidacion(int num_filas, int num_columnas, TipoBy i_cabecera, String str_cabecera,  int columna_inicial) 
					throws RuntimeException {
		
		super(num_filas, num_columnas, i_cabecera, str_cabecera, columna_inicial);
	}
	
	
	protected void parseContentGrid(TipoBy i_cabecera, String str_cabecera, int columna_inicial) {
		content_grid=new ArrayList<Element[]>();
		for (int i=0; i<this.num_filas;i++) {
			Element[] fila=new Element[num_columnas];
			String str=str_cabecera+"tr["+(i+2)+"]/td[";
			logger.trace("Str fila: "+str);
			String tipo=null;
			String valor=null;
			for (int j=columna_inicial;j<num_columnas;j++) {
				logger.trace("Str columna: "+str+(j+columna_inicial+1)+"]");
				switch (j) {
					case 0: fila[j]=new Label(i_cabecera,str+(j+columna_inicial+1)+"]");
							if (((Label)fila[j]).getText().equals("Billete")) tipo="N";
							else tipo="C";
							break;
					case 1: fila[j]=new Label(i_cabecera,str+(j+columna_inicial+1)+"]");
							valor=((Label)fila[j]).getText().replace(".","");
							break;
					case 2: String id="ctl00_ContentZone_NumberCASH01"+tipo+valor+"_1";
							fila[j]=new Field(TipoBy.ID,id);
							break;
					default: fila[j]=new Label(i_cabecera,str+(j+columna_inicial+1)+"]"); break;
				}
			}
			content_grid.add(fila);
		}
	}

	
	public String obtenerValor(int num_fila,int num_columna) {
		Iterator<Element[]> it=content_grid.iterator();
		int i=0;
		Element[] label=null;
		while (it.hasNext() && i<num_fila) {
			label=it.next();
			i++;
		}
		if (num_columna==3) { // Corresponde a la columna "Número" de la tabla "Valores"
			return ((Field)label[num_columna-1]).getValue();
		}
		else {
			return ((Label)label[num_columna-1]).getText();
		}
	}
	
}
