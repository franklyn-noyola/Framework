package elementosBase;

import org.apache.log4j.Logger;

import unidadesGraficas.TipoBy;

public class Menu {

	private PestañaMenu[] opcionesMenu;
	private final static Logger logger = Logger.getLogger(Menu.class);
	
	public Menu(TipoBy[] i_opcionesMenu, String[] str_opcionesmenu, TipoBy[][][] i_desplegable, String[][][] str_desplegable) {
		
		opcionesMenu=new PestañaMenu[i_opcionesMenu.length];
		for (int i=0; i<i_opcionesMenu.length; i++) {
			for (int j=0; j<i_desplegable[i].length; j++) {
				for (int z=0; z<i_desplegable[i][j].length; z++) {
					opcionesMenu[i]=new PestañaMenu(i_opcionesMenu[i],str_opcionesmenu[i],i_desplegable[i],str_desplegable[i]);
				}
			}
		}
	}
	
	public int desplegarPestañaMenu(String opcion) {
		int indice=0;
		
		boolean encontrada=false;
		for (int i=0; (i<opcionesMenu.length) && !encontrada; i++) {
			if (opcionesMenu[i].label().equals(opcion)) {
				encontrada=true;
				opcionesMenu[i].desplegarPestañaMenu();
				indice=i;
			}
		}
		return indice;
	}
	
	public void clicar_opcion(int i, String opcion) {
		opcionesMenu[i].clicar_opcion(opcion);
	}
	
	public void clicar_opcion(int i, String opcion1, String opcion2) {
		opcionesMenu[i].clicar_opcion(opcion1, opcion2);
	}
	
	public boolean sintacticAnalysis(String[] label, String[][][] desplegable) {
		boolean analisis=true;
		
		logger.debug("Inicio análisis sintáctico Menu");
		for (int i=0; (i<label.length); i++) {
			analisis=opcionesMenu[i].sintacticAnalysis(label[i],desplegable[i]) && analisis;
		}
		return analisis;
	}
}
