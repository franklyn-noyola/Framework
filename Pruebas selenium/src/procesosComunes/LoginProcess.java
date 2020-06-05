package procesosComunes;

import pantallasBase.LoginPage;
import unidadesGraficas.*;

import org.apache.log4j.Logger;

public class LoginProcess {

	private LoginPage loginpage;
	
	private TipoBy i_titulo=TipoBy.ID;
	private String str_titulo="backoffice";
	private TipoBy i_subtitulo=TipoBy.ID;  
	private String str_subtitulo="LblPlace";
	private TipoBy i_error=TipoBy.ID;  
	private String str_error="LblError";
	private TipoBy  i_labelUser=TipoBy.ID;
	private String str_labelUser="LblLogin";
	private TipoBy i_fieldUser=TipoBy.ID;
	private String str_fieldUser="BoxLogin";
	private TipoBy i_labelPassword=TipoBy.ID;
	private String str_labelPassword="LblPassword";
	private TipoBy i_fieldPassword=TipoBy.ID;
	private String str_fieldPassword="BoxPassword";
	private TipoBy i_buttonEntrar=TipoBy.ID;
	private String str_buttonEntrar="BtnLogin";
	private TipoBy i_buttonLimpiar=TipoBy.ID;
	private String str_buttonLimpiar="BtnCancel";
	private String[] valoresVariables;
	private final static Logger logger = Logger.getLogger(LoginProcess.class);
	
	public LoginProcess() {
		 loginpage=new LoginPage(i_titulo, str_titulo,  i_subtitulo,  str_subtitulo, i_error,  str_error,
			 i_labelUser,  str_labelUser,  i_fieldUser,  str_fieldUser,
			 i_labelPassword,  str_labelPassword,  i_fieldPassword,  str_fieldPassword,
			 i_buttonEntrar,  str_buttonEntrar,  i_buttonLimpiar,  str_buttonLimpiar);
		 valoresVariables=new String[2];
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String labelUser, String labelPassword,
			String labelButtonEntrar, String labelButtonLimpiar) {
		
	    logger.debug("Página Login: inicio análisis sintáctico");
		return loginpage.sintacticAnalysis(titulo, subtitulo, labelUser, labelPassword, labelButtonEntrar, labelButtonLimpiar);
	}
	
	public boolean hayMensajeError() {
		boolean error;
		
		try {
			error=loginpage.hayMensajeError();
		}
		catch (Exception e) {
			error=false;
		}
		return error;
	}
	
	public String verMensajeError() {
		return loginpage.verMensajeError();
	}
	
	public ErrorMessage mensajeError() {
		return loginpage.mensajeError();
	}
	
	public String[] valoresVariables() {
		return valoresVariables;
	}
	
	public void escribirUsuario(String user) {
		loginpage.writeUser(user);
		valoresVariables[0]=user;
		valoresVariables[1]=loginpage.subtitulo();
	}
	
	public void escribirPassword(String password) {
		loginpage.writePassword(password);
	}
	
	public boolean entrar() {
		return loginpage.entrar();
	}
	
}
