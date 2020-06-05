package elementosBase;

import org.apache.log4j.Logger;

import unidadesGraficas.*;

public class TablaEstatica {

	protected GridEstatico grid;
	private final static Logger logger = Logger.getLogger(TablaEstatica.class);
	
	public TablaEstatica(int num_filas, int num_columnas, TipoBy i_cabecera, String str_cabecera,int columna_inicial) throws RuntimeException {
		
		try {
			grid=new GridEstatico(num_filas, num_columnas,i_cabecera,str_cabecera, columna_inicial);
		}
		catch (RuntimeException e) {
			System.exit(0);
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
		// Devuelve la fila donde los valores de todas las columnas coinciden con las que se pasan en valores
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
	
	public void recargarGrid() {
		grid.recargarGrid();
	}
	
	public int numFilas() {
		// Devuelve el número de filas mostradas por pantalla
		return grid.getNumFilas();
	}
	
	public int numFilasTotal() {
		// Devuelve el número de filas total de la tabla
		return numFilas();
	}
	
	public int numColumnas() {
		return grid.getNumColumnas();
	}
	
	public boolean sintacticAnalysis(String[] labelTitulosColumnas) {
		
		return grid.sintacticAnalysis(labelTitulosColumnas);
	}
}
