package elementosBase;

import procesosComunes.BotonesTablasChoice;
import unidadesGraficas.*;

public class TablasChoice {

	private final int num_tablas=2;
	private Label[] titulos_tabla;
	private final int num_botones=2;
	private Button[] botones_tabla;
	private ListaScroll tabla_derecha;
	private ListaScroll tabla_izquierda;
	
	public TablasChoice(TipoBy[] i_titulostabla, String[] str_titulostabla, TipoBy[] i_botonestabla, String[] str_botonestabla,
			TipoBy i_tabla_izquierda, String str_tabla_izquierda, TipoBy i_tabla_derecha, String str_tabla_derecha) {
		
		botones_tabla=new Button[num_botones];
		titulos_tabla=new Label[num_tablas];
		for (int i=0; i<num_botones;i++) {
			botones_tabla[i]=new Button(i_botonestabla[i],str_botonestabla[i]);
		}
		for (int i=0; i<num_tablas;i++) {
			titulos_tabla[i]=new Label(i_titulostabla[i],str_titulostabla[i]);
		}
		tabla_izquierda=new ListaScroll(i_tabla_izquierda,str_tabla_izquierda);
		tabla_derecha=new ListaScroll(i_tabla_derecha,str_tabla_derecha);
	}

	public void pulsarBotonTabla(int indice) {
		botones_tabla[indice].click();
	}
	
	public void pulsarBotonHaciaDerecha() {
		botones_tabla[BotonesTablasChoice.Derecha.ordinal()].click();
	}
	
	public void pulsarBotonHaciaIzquierda() {
		botones_tabla[BotonesTablasChoice.Izquierda.ordinal()].click();
	}
	
	public void seleccionarTablaIzquierda(int indice) {
		tabla_izquierda.seleccionarOpcion(indice);
	}
	
	public void seleccionarTablaDerecha(int indice) {
		tabla_derecha.seleccionarOpcion(indice);
	}
	
	public void seleccionarTablaIzquierda(String opcion) {
		tabla_izquierda.seleccionarOpcion(opcion);
	}
	
	public void seleccionarTablaDerecha(String opcion) {
		tabla_derecha.seleccionarOpcion(opcion);
	}
	
	public boolean existeOpcionEnTablaIzquierda(String opcion) {
		return tabla_izquierda.existeOpcionEnTabla(opcion);
	}
	
	public boolean existeOpcionEnTablaDerecha(String opcion) {
		return tabla_derecha.existeOpcionEnTabla(opcion);
	}
	
	public boolean sintacticAnalysis(String[] labels, String[] labelBotonesTabla) {

		boolean resultado=true;

		for (int i=0; i<num_botones; i++) {
			resultado=botones_tabla[i].sintacticAnalysis(labelBotonesTabla[i]) && resultado;
		}
		return resultado;
	}
}
