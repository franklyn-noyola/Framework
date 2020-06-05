package elementosBase;

import java.util.ArrayList;
import java.util.Iterator;
import org.apache.log4j.Logger;
import unidadesGraficas.Label;
import unidadesGraficas.TipoBy;

public class GridEstatico {

	protected int num_filas;
	protected int num_columnas;  //Número de columnas con información
	protected Header cabecera;
	protected ArrayList<Label[]> content_grid;
	private int columna_inicial;
	private TipoBy i_cabecera;
	private String str_cabecera;
	private final static Logger logger = Logger.getLogger(GridEstatico.class);
	
	public GridEstatico(int num_filas, int num_columnas, TipoBy i_cabecera, String str_cabecera,  int columna_inicial) 
					throws RuntimeException {
		
		this.num_filas=num_filas;
		this.num_columnas=num_columnas;
		this.columna_inicial=columna_inicial;
		this.i_cabecera=i_cabecera;
		this.str_cabecera=str_cabecera;
		this.recargarGrid();
	}

	public GridEstatico(int num_columnas, TipoBy i_cabecera, String str_cabecera,  int columna_inicial) 
			throws RuntimeException {

		// Constructor para ser llamado desde el contructor de Grid
		this.num_filas=0;
		this.num_columnas=num_columnas;
		this.columna_inicial=columna_inicial;
		this.i_cabecera=i_cabecera;
		this.str_cabecera=str_cabecera;
	}
	
	public void recargarGrid() {
		cabecera=new Header(i_cabecera,str_cabecera,num_columnas,columna_inicial);
		parseContentGrid(i_cabecera,str_cabecera,columna_inicial);
	}
	
	protected void parseContentGrid(TipoBy i_cabecera, String str_cabecera, int columna_inicial) {
		content_grid=new ArrayList<Label[]>();
		for (int i=0; i<this.num_filas;i++) {
			Label[] fila=new Label[num_columnas];
			String str=str_cabecera+"tr["+(i+2)+"]/td[";
			logger.trace("Str fila: "+str);

			if (columna_inicial==0) { // Corresponde al caso de una tabla simple sin método de selección
				for (int j=columna_inicial;j<num_columnas;j++) {
					logger.trace("Str columna: "+str+(j+columna_inicial+1)+"]");
					fila[j]=new Label(i_cabecera,str+(j+columna_inicial+1)+"]");
				}
			}
			else {
				for (int j=columna_inicial-1;j<num_columnas;j++) {
					logger.trace("Str columna: "+str+(j+columna_inicial+1)+"]");
					fila[j]=new Label(i_cabecera,str+(j+columna_inicial+1)+"]");
				}
			}
			content_grid.add(fila);
		}
	}
	
	
	public int getNumColumnas() {
		return num_columnas;
	}
	
	public int getNumFilas() {
		// Devuelve el número de filas mostradas por pantalla
		return num_filas;
	}
	
	public int getNumColumn(String column) {
		return cabecera.getNumColumn(column);
	}
	
	public String obtenerValor(int num_fila,int num_columna) {
		Iterator<Label[]> it=content_grid.iterator();
		int i=0;
		Label[] label=null;
		while (it.hasNext() && i<num_fila) {
			label=it.next();
			i++;
		}
		return label[num_columna-1].getText();
	}
	
	public String obtenerValorColumna(int num_fila,String columna) {
		int num_columna=cabecera.getNumColumn(columna);
		if (num_columna==-1) {
			return null;
		}
		else {
			return obtenerValor(num_fila,num_columna);
		}
	}
	
	public int getNumPrimeraFilaMostrada() throws NumberFormatException {

        return 1;
	}
	
	public boolean sintacticAnalysis(String[] labelTitulosColumnas) {

		return cabecera.sintacticAnalysis(labelTitulosColumnas);
	}
}
