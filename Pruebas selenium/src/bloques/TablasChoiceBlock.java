package bloques;

import elementosBase.TablasChoice;
import unidadesGraficas.*;

public class TablasChoiceBlock {

	private Title encabezado=null;
	private TablasChoice tablas;
	
	public TablasChoiceBlock(TipoBy i_encabezado, String str_encabezado, TipoBy[] i_titulostabla, String[] str_titulostabla, TipoBy[] i_botonestabla,
			String[] str_botonestabla, TipoBy i_tabla_izquierda, String str_tabla_izquierda, TipoBy i_tabla_derecha, String str_tabla_derecha) {
		
		if (i_encabezado!=null && str_encabezado!=null) {
			encabezado=new Title(i_encabezado, str_encabezado);
		}
		tablas=new TablasChoice(i_titulostabla, str_titulostabla, i_botonestabla, str_botonestabla, i_tabla_izquierda, str_tabla_izquierda, i_tabla_derecha, str_tabla_derecha);
	}
	
	public void seleccionarTablaIzquierda(int indice) {
		tablas.seleccionarTablaIzquierda(indice);
	}
	
	public void seleccionarTablaDerecha(int indice) {
		tablas.seleccionarTablaDerecha(indice);
	}
	
	public void seleccionarTablaIzquierda(String opcion) {
		tablas.seleccionarTablaIzquierda(opcion);
	}
	
	public void seleccionarTablaDerecha(String opcion) {
		tablas.seleccionarTablaDerecha(opcion);
	}
	
	public void BotonIzquierda() {
		tablas.pulsarBotonHaciaIzquierda();
	}
	
	public void BotonDerecha() {
		tablas.pulsarBotonHaciaDerecha();
	}
	
	public boolean existeOpcionEnTablaIzquierda(String opcion) {
		return tablas.existeOpcionEnTablaIzquierda(opcion);
	}
	
	public boolean existeOpcionEnTablaDerecha(String opcion) {
		return tablas.existeOpcionEnTablaDerecha(opcion);
	}
	
	public boolean sintacticAnalysis(String encabezado, String[] labels, String[] botones) {
		
		boolean resultado=true;
		
		if (encabezado!=null) {
			resultado=this.encabezado.sintacticAnalysis(encabezado);
		}
		return tablas.sintacticAnalysis(labels, botones) && resultado;
	}
}
