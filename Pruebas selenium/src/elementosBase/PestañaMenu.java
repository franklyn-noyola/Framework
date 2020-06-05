package elementosBase;

import org.apache.log4j.Logger;

import unidadesGraficas.*;

public class PestañaMenu {

	private Link label;
	private Link[][] desplegable;
	private final static Logger logger = Logger.getLogger(PestañaMenu.class);
	
	public PestañaMenu(TipoBy i_pestaña, String str_pestaña, TipoBy[][] i_desplegable, String[][] str_desplegable) {
		label=new Link(i_pestaña, str_pestaña);
		desplegable=new Link[i_desplegable.length][i_desplegable[0].length];
		
		for (int i=0; i<desplegable.length; i++) {
			for (int j=0; (j<desplegable[i].length) && (str_desplegable[i][j]!=null);j++) {
				desplegable[i][j]=new Link(i_desplegable[i][j], str_desplegable[i][j]);
			}
		}
	}
	
	public String label() {
		return label.getText();
	}
	
	public void desplegarPestañaMenu() {
		logger.trace("Despliego pestaña "+label.getText());
		label.seleccionarPuntero();
	}
	
	public void desplegarOpcionMenu(Link link) {
		logger.trace("Despliego opcion "+link.getText());
		link.seleccionarPuntero();
	}
	
	public void clicar_opcion(String opcion) {
		boolean encontrada=false;
		for (int i=0; (i<desplegable.length) && !encontrada; i++) {
			desplegable[i][0].seleccionarPuntero();
			if (desplegable[i][0].getText().equals(opcion)) {
				encontrada=true;
				desplegarOpcionMenu(desplegable[i][0]);
				desplegable[i][0].click();
			}
		}
	}
	
	public void clicar_opcion(String opcion1, String opcion2) {
		boolean encontrada=false;
		for (int i=0; (i<desplegable.length) && !encontrada; i++) {
			if (desplegable[i][0].getText().equals(opcion1)) {
				encontrada=true;
				desplegable[i][0].seleccionarPuntero();
				boolean encontrada2=false;
				for (int j=0; (i<desplegable[i].length) && !encontrada2; j++) {
					if (desplegable[i][j].getText().equals(opcion2)) {
						encontrada2=true;
						desplegable[i][j].seleccionarPuntero();
						desplegable[i][j].click();
					}
				}
			}
		}
		
	}
	
	public boolean sintacticAnalysis(String label, String[][] desplegable) {
		boolean analisis=true;
		
		logger.debug("Análisis sintáctico Pestaña menu: "+label);
		desplegarPestañaMenu();
		for (int i=0; (i<desplegable.length); i++) {
			for (int j=0; (j<desplegable[i].length) && (desplegable[i][j]!=null); j++) {
				desplegarOpcionMenu(this.desplegable[i][j]);
				analisis=this.desplegable[i][j].sintacticAnalysis(desplegable[i][j]) && analisis;
			}
		}
		return analisis;
	}
}
