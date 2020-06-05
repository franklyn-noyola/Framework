package ventanasComunes;

import org.apache.log4j.Logger;

import unidadesGraficas.Button;
import unidadesGraficas.ErrorMessage;
import unidadesGraficas.TipoBy;
import unidadesGraficas.Title;

public class Screen {
	private Title titulo;
	private Title subtitulo;
	protected Button[] botones_negros;
	private ErrorMessage msgError;
	private final static Logger logger = Logger.getLogger(Screen.class);
	
	public Screen(TipoBy i_titulo, String str_titulo, TipoBy i_subtitulo, String str_subtitulo, TipoBy[] i_botones,
			String[] str_botones,TipoBy i_msgerror, String str_msgerror) throws RuntimeException {

		if (i_botones.length!=str_botones.length) {
			logger.fatal("Screen: i_botones.length="+i_botones.length+"<>str_botones.length="+str_botones.length);
			throw new RuntimeException("Screen: i_botones.length<>str_botones.length");
		}
		titulo=new Title(i_titulo,str_titulo);
		subtitulo=new Title(i_subtitulo, str_subtitulo);
		botones_negros=new Button[i_botones.length];
		for (int i=0; i<i_botones.length; i++) {
			botones_negros[i]=new Button(i_botones[i],str_botones[i]);
		}
		msgError=new ErrorMessage(i_msgerror,str_msgerror);
	}
	
	public String titulo() {
		return titulo.getText();
	}
	
	public boolean clickBoton(int i) {
		String tituloPagIni=titulo();
		return botones_negros[i].click(tituloPagIni);
	}
	
	public boolean hayMensajeError() {
		return msgError.hayMensajeError();
	}
	
	public String verMensajeError() {
		return msgError.getText();
	}
	
	public ErrorMessage mensajeError() {
		return msgError;
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones) {
		logger.debug("Screen: Inicio análisis sintáctico");
		boolean resultado=false;
		
		if (titulo!=null) {
			resultado=this.titulo.sintacticAnalysis(titulo);
		}
		if (subtitulo!=null) {
			resultado=resultado && this.subtitulo.sintacticAnalysis(subtitulo);
		}
		for (int i=0; (i<botones.length); i++) {
			resultado=botones_negros[i].sintacticAnalysis(botones[i]) && resultado;
		}
		return resultado;
	}
}
