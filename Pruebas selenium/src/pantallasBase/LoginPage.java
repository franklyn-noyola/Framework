package pantallasBase;

import org.apache.log4j.Logger;
import elementosBase.*;
import unidadesGraficas.*;

public class LoginPage extends Page {

	private LabelField[] credenciales;
	private Button buttonEntrar;
	private Button buttonLimpiar;
	private ErrorMessage msgError;
	private final static Logger logger = Logger.getLogger(LoginPage.class);
	
	public LoginPage(TipoBy i_titulo, String str_titulo, TipoBy i_subtitulo, String str_subtitulo, 
			TipoBy i_msgerror, String str_msgerror,
			TipoBy i_labelUser, String str_labelUser, TipoBy i_fieldUser, String str_fieldUser,
			TipoBy i_labelPassword, String str_labelPassword, TipoBy i_fieldPassword, String str_fieldPassword,
			TipoBy i_buttonEntrar, String str_buttonEntrar, TipoBy i_buttonLimpiar, String str_buttonLimpiar) {
		
		super(i_titulo,str_titulo,i_subtitulo,str_subtitulo);
		credenciales=new LabelField[2];
		credenciales[0]=new LabelField(i_labelUser, str_labelUser, i_fieldUser, str_fieldUser);
		credenciales[1]=new LabelField(i_labelPassword, str_labelPassword, i_fieldPassword, str_fieldPassword);
		buttonEntrar=new Button(i_buttonEntrar, str_buttonEntrar);
		buttonLimpiar=new Button(i_buttonLimpiar, str_buttonLimpiar);
		msgError=new ErrorMessage(i_msgerror,str_msgerror);
		logger.debug("ConstructorLoginPage");
	}
	
	public void writeUser(String user) {
		credenciales[0].getField().writeText(user);
	}
	
	public void writePassword(String password) {
		credenciales[1].getField().writeText(password);
	}
	
	public void clickEntrar() {
		buttonEntrar.click();
	}
	
	public boolean entrar() {
		String tituloPagIni=titulo();
		return buttonEntrar.click(tituloPagIni);
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
	
	public void clickLimpiar() {
		buttonLimpiar.click();
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String labelUser, String labelPassword,
			String labelButtonEntrar, String labelButtonLimpiar) {
		
		return super.sintacticAnalysis(titulo, subtitulo) && credenciales[0].sintacticAnalysis(labelUser)
				&& credenciales[1].sintacticAnalysis(labelPassword) && buttonEntrar.sintacticAnalysis(labelButtonEntrar) &&
						buttonLimpiar.sintacticAnalysis(labelButtonLimpiar);
	}
}
