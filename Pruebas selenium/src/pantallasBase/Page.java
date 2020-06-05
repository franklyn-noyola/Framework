package pantallasBase;

import org.apache.log4j.Logger;

import unidadesGraficas.*;

public class Page {
	
	private Title titulo;
	private Title subtitulo;
	private final static Logger logger = Logger.getLogger(Page.class);
	
	public Page(TipoBy i_titulo, String str_titulo, TipoBy i_subtitulo, String str_subtitulo) {
		titulo=new Title(i_titulo,str_titulo);
		subtitulo=new Title(i_subtitulo, str_subtitulo);
	}
	
	public String titulo() {
		return titulo.getText();
	}
	
	public String subtitulo() {
		return subtitulo.getText();
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo) {
		logger.debug("Análisis sintáctico Page");
		return (this.titulo.sintacticAnalysis(titulo) && this.subtitulo.sintacticAnalysis(subtitulo));
	}

}
