package pantallasBase;

import elementosBase.*;
import unidadesGraficas.*;

public class BOMainPage extends Page {

	private Button logout;
	private Menu menu;
	
	public BOMainPage(TipoBy i_titulo, String str_titulo, TipoBy i_subtitulo, String str_subtitulo, 
			TipoBy i_button, String str_button, TipoBy[] i_opcion, String[] str_opcion, TipoBy[][][] i_desplegable, String[][][] str_desplegable) {
	
		super(i_titulo,str_titulo,i_subtitulo,str_subtitulo);
		logout=new Button(i_button,str_button);
		menu=new Menu(i_opcion, str_opcion, i_desplegable, str_desplegable);
	}
	
	public void seleccionar_opcion(String opcion, String subopcion) {
		int index=menu.desplegarPestañaMenu(opcion);
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		menu.clicar_opcion(index, subopcion);
	}
	
	public void seleccionar_opcion(String opcion, String subopcion1, String subopcion2) {
		int index=menu.desplegarPestañaMenu(opcion);
		menu.clicar_opcion(index, subopcion1, subopcion2);
	}
	
	public void logout() {
		logout.click();
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] label, String[][][] desplegable) {
		return super.sintacticAnalysis(titulo, subtitulo) && menu.sintacticAnalysis(label, desplegable);
	}
}
