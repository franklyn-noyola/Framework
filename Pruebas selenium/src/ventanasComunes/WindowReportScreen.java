package ventanasComunes;

import unidadesGraficas.*;

public class WindowReportScreen extends Screen {
	
	Window window;
	
	public WindowReportScreen(TipoBy i_titulo, String str_titulo, TipoBy i_subtitulo, String str_subtitulo, 
			TipoBy[] i_botones_negros, String[] str_botones_negros,  TipoBy i_msgerror,
			String str_msgerror) {
		
		super(i_titulo,str_titulo,i_subtitulo,str_subtitulo,i_botones_negros,str_botones_negros,i_msgerror,str_msgerror);
		window=new Window();
	}
	
	public void pasarANuevaVentana() {
		window.pasarANuevaVentana();
	}
	
	public void cerrar() {
		window.volverAVentanaAnterior();
	}
	
	public boolean sintacticAnalysis(String titulo, String[] labelBotonesNegros) {
		
		return super.sintacticAnalysis(titulo, null, labelBotonesNegros);
	}
}
