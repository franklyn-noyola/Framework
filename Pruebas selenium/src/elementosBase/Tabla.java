package elementosBase;

import org.apache.log4j.Logger;

import unidadesGraficas.*;

public class Tabla {

	private Button[] botones_crud=null;
	protected Grid grid;
	private final static Logger logger = Logger.getLogger(Tabla.class);
	
	public Tabla(int num_columnas, TipoBy i_cabecera, String str_cabecera, TipoBy i_leyendamostrado,
			String str_leyendamostrado, TipoBy[] i_botonescrud, String[] str_botonescrud, 
			TipoBy[] i_botonesgrid, String[] str_botonesgrid,int columna_inicial) throws RuntimeException {
		
		try {
			grid=new Grid(num_columnas,i_leyendamostrado,str_leyendamostrado,i_cabecera,str_cabecera,
				i_botonesgrid, str_botonesgrid, columna_inicial);
		}
		catch (RuntimeException e) {
			System.exit(0);
		}
		
		if (i_botonescrud!=null && str_botonescrud!=null) {
			if (i_botonescrud.length!=str_botonescrud.length) {
				logger.fatal("Tabla: i_botonescrud.length="+i_botonescrud.length+"<>str_botones.str_botonescrud="+str_botonescrud.length);
				throw new RuntimeException("Tabla: i_botonescrud.length="+i_botonescrud.length+"<>str_botones.str_botonescrud="+str_botonescrud.length);
			}
			int num_botonescrud=i_botonescrud.length;
			botones_crud=new Button[num_botonescrud];
			for (int i=0; i<num_botonescrud;i++) {
				botones_crud[i]=new Button(i_botonescrud[i],str_botonescrud[i]);
			}
		}
	}
	
	public int obtenerNumFilaTabla(String[] columnas, String[] valores) {
		// Devuelve el número de fila donde los valores de todas las columnas coinciden con las que se pasan en valores
		// Devuelve -1 si la búsqueda es fallida
		
		if (columnas.length==valores.length) {
			int filaEncontrada=0;
			boolean encontrado=false;
			for (int fila=1; fila<=numFilas() && !encontrado; fila++) {
				boolean igual=true;
				for (int j=0; j<columnas.length && igual; j++) {
					igual=igual && valores[j].trim().equals(obtenerValorColumna(fila,columnas[j].trim()));
				}
				if (igual) {
					filaEncontrada=fila;
					encontrado=true;
				}
			}
			if (encontrado) {
				return filaEncontrada;
			}
			else {
				return -1;
			}
		}
		else {
			return -1;
		}
	}
	
	public String[] obtenerDatosFilaTabla(String[] columnas, String[] valores) {
		// Devuelve la fila donde los valores de todas las columnas coinciden con las que se pasan en valores"
		if (columnas.length==valores.length) {
			int filaEncontrada=0;
			boolean encontrado=false;
			for (int fila=1; fila<=numFilas() && !encontrado; fila++) {
				boolean igual=true;
				for (int j=0; j<columnas.length && igual; j++) {
					igual=igual && valores[j].trim().equals(obtenerValorColumna(fila,columnas[j].trim()));
				}
				if (igual) {
					filaEncontrada=fila;
					encontrado=true;
				}
			}
			if (encontrado) {
				return obtenerDatosFilaTabla(filaEncontrada);
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}
	
	public boolean ultimaTabla() {
	// Retorna true si ya no hay más elementos en la tabla que los que aparecen en pantalla
		return grid.ultimoGrid();
	}
	
	public String[] obtenerDatosFilaTabla(int fila) {
		// Devuelve la fila de la tabla cuyo indice se pasa por parámetro"
		String[] resultado=new String[numColumnas()];
		for (int i=0; i<numColumnas(); i++) {
			resultado[i]=obtenerValorColumna(fila,i+1);
		}
		return resultado;
	}
	
	public String obtenerValorColumna(int num_fila,String columna) {
		return grid.obtenerValorColumna(num_fila,columna);
	}
	
	public String obtenerValorColumna(int num_fila,int num_columna) {
		if (num_fila>0 && num_columna>0) {
			return grid.obtenerValor(num_fila,num_columna);
		}
		else {
			return null;
		}
	}
	
	public String obtenerValorColumna(String columnaFinal, String columnaInicial, String valorInicial) {
		// Busca valorInicial en ColumnaInicial y devuelve el valor de esa fila en ColumnaFinal
		String[] columnas=new String[1];
		columnas[0]=columnaInicial;
		String[] valores=new String[1];
		valores[0]=valorInicial;
		String[] fila=obtenerDatosFilaTabla(columnas,valores);
		int indice=grid.getNumColumn(columnaFinal);
		if (indice<1 || indice>fila.length) {
			return null;
		}
		else {
			return fila[indice-1];
		}
	}
	
	public void pulsarBotonCrud(int indice) {
		botones_crud[indice].click();
	}
	
	public void pulsarBotonTabla(int indice) {
		grid.pulsarBoton(indice);
	}
	
	public void pulsarBotonPrimero() { 
		grid.pulsarBoton(BotonesTabla.Primero.ordinal());
	}
	
	public void pulsarBotonAnterior() {
		grid.pulsarBoton(BotonesTabla.Anterior.ordinal());
	}
	
	public void pulsarBotonSiguiente() {
		grid.pulsarBoton(BotonesTabla.Siguiente.ordinal());
	}
	
	public void pulsarBotonUltimo() {
		grid.pulsarBoton(BotonesTabla.Ultimo.ordinal());
	}
	
	public void recargarGrid() {
		grid.recargarGrid();
	}
	
	public int numFilas() {
		// Devuelve el número de filas mostradas por pantalla
		return grid.getNumFilas();
	}
	
	public int numFilasTotal() {
		// Devuelve el número de filas total de la tabla
		return grid.getNumTotalFilas();
	}
	
	public int numColumnas() {
		return grid.getNumColumnas();
	}
	
	public boolean sintacticAnalysis(String[] labelBotonesCrud, String[] labelTitulosColumnas, String[] labelBotonesGrid) {

		boolean resultado=true;

		if (labelBotonesCrud!=null) {
			for (int i=0; i<labelBotonesCrud.length; i++) {
				resultado=botones_crud[i].sintacticAnalysis(labelBotonesCrud[i]) && resultado;
			}
		}
		return grid.sintacticAnalysis(labelTitulosColumnas,labelBotonesGrid) && resultado;
	}
}
