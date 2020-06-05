package procesosComunes;

import ventanasComunes.*;
import elementosBase.BotonesTabla;

public class FormProcess extends Process {
	
	public void seleccionarFila(int indice) {
		((FormRadioScreen) screen).seleccionarFila(indice);
	}
	
	public boolean ultimaTabla() {
		return ((FormRadioScreen) screen).ultimaTabla();
	}
	
	public int numFilasTabla() {
		// Devuelve el número de filas de la tabla mostradas por pantalla
		return ((FormScreen) screen).numFilasTabla();
	}
	
	public int numFilasTotalTabla() {
		// Devuelve el número de filas total de la tabla
		return ((FormScreen) screen).numFilasTotalTabla();
	}
	
	public int numColumnasTabla() {
		return ((FormScreen) screen).numColumnasTabla();
	}
	
	public String obtenerValorColumna(int num_fila,String columna) {
		return ((FormScreen) screen).obtenerValorColumna(num_fila,columna);
	}
	
	public String obtenerValorColumna(String columnaFinal, String columnaInicial, String valorInicial) {
		// Busca valorInicial en ColumnaInicial y devuelve el valor de esa fila en ColumnaFinal
		return ((FormScreen) screen).obtenerValorColumna(columnaFinal,columnaInicial,valorInicial);
	}
	
	public String[] obtenerFilaTabla(int num_fila) {
		return ((FormScreen) screen).obtenerFilaTabla(num_fila);
	}
	
	public int obtenerNumFilaTabla(String columna, String valor) {
		String[] columnas=new String[1];
		String[] valores=new String[1];
		columnas[0]=columna;
		valores[0]=valor;
		return obtenerNumFilaTabla(columnas, valores);
	}
	
	
	public String[] obtenerFilaTabla(String columna, String valor) {
		String[] columnas=new String[1];
		String[] valores=new String[1];
		columnas[0]=columna;
		valores[0]=valor;
		return obtenerFilaTabla(columnas, valores);
	}
	
	public int obtenerNumFilaTabla(String[] columnas, String[] valores) {
		return ((FormScreen) screen).obtenerNumFilaTabla(columnas, valores);
	}
	
	public String[] obtenerFilaTabla(String[] columnas, String[] valores) {
		return ((FormScreen) screen).obtenerFilaTabla(columnas, valores);
	}
	
	public void pulsarBotonTablaPrimero() {
		((FormScreen) screen).pulsarBotonTabla(BotonesTabla.Primero.ordinal());
	}
	
	public void pulsarBotonTablaAnterior() {
		((FormScreen) screen).pulsarBotonTabla(BotonesTabla.Anterior.ordinal());
	}
	
	public void pulsarBotonTablaSiguiente() {
		((FormScreen) screen).pulsarBotonTabla(BotonesTabla.Siguiente.ordinal());
	}
	
	public void pulsarBotonTablaUltimo() {
		((FormScreen) screen).pulsarBotonTabla(BotonesTabla.Ultimo.ordinal());
	}
	
	public void seleccionarUltimaFila() {
		pulsarBotonTablaUltimo();
		seleccionarFila(numFilasTabla());
	}
	
	public void recargarTabla() {
		((FormScreen) screen).recargarTabla();
	}
}
