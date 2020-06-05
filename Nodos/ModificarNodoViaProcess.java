package procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos;

import org.apache.log4j.Logger;
import unidadesGraficas.TipoBy;
import ventanasITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos.ModificarNodoViaScreen;
import procesosComunes.Process;

public class ModificarNodoViaProcess extends Process {
	
	private TipoBy[] i_botones= {TipoBy.ID,TipoBy.ID};
	private String[] str_botones= {"ctl00_ButtonsZone_BtnSubmit_IB_Label","ctl00_ButtonsZone_BtnBack_IB_Label"};
	private final static Logger logger = Logger.getLogger(ModificarNodoViaProcess.class);
	 
	public ModificarNodoViaProcess() {
		screen=new ModificarNodoViaScreen(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,
				i_msgerror,str_msgerror);
	}
	
	public String leerCampoCodigoPlaza() {
		return ((ModificarNodoViaScreen) screen).leerOpcionVariable1(OpcionesModificarNodoViaVariable1.Codigo);
	}
	
	public String leerCampoDescripcionPlaza() {
		return ((ModificarNodoViaScreen) screen).leerOpcionVariable1(OpcionesModificarNodoViaVariable1.Descripcion);
	}
	
	public String leerCampoDireccionPlaza() {
		return ((ModificarNodoViaScreen) screen).leerOpcionVariable1(OpcionesModificarNodoViaVariable1.Direccion);
	}
	
	public String leerCampoCodigoVia() {
		return ((ModificarNodoViaScreen) screen).leerOpcionVariable2(OpcionesModificarNodoViaVariable2.Codigo);
	}
	
	public String leerCampoDireccionVia() {
		return ((ModificarNodoViaScreen) screen).leerOpcion(OpcionesModificarNodoViaCampo.Direccion);
	}
	
	public void escribirCampoDireccionVia(String valor) {
		borrarCampoDireccionVia();
		((ModificarNodoViaScreen) screen).escribirOpcion(OpcionesModificarNodoViaCampo.Direccion, valor);
	}
	
	public void borrarCampoDireccionVia() {
		((ModificarNodoViaScreen) screen).borrarOpcion(OpcionesModificarNodoViaCampo.Direccion);
	}
	
	public boolean hayErrorValidacionDireccionVia() {
		return ((ModificarNodoViaScreen) screen).hayErrorValidacionOpcion(OpcionesModificarNodoViaCampo.Direccion);
	}
	
	public boolean hayErrorValidacionRequeridoDireccionVia() {
		return ((ModificarNodoViaScreen) screen).hayErrorValidacionOpcionRequerido(OpcionesModificarNodoViaCampo.Direccion);
	}
	
	public boolean hayErrorValidacionFormatoDireccionVia() {
		return ((ModificarNodoViaScreen) screen).hayErrorValidacionOpcionFormato(OpcionesModificarNodoViaCampo.Direccion);
	}
	
	public void confirmar() {
		screen.clickBoton(BotonesCrearModificarNodoVia.Confirmar.ordinal());
	}

	public void volver() {
		screen.clickBoton(BotonesCrearModificarNodoVia.Volver.ordinal());
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones,  String encabezadoVar1,
			String[] labelsVariables1, String[] valoresvariables1, String encabezadoVar2, String[] labelsVariables2,
			String[] valoresvariables2, String[] labels) {
		logger.debug("Modificar Nodo Vía: inicio análisis sintáctico");
		return ((ModificarNodoViaScreen) screen).sintacticAnalysis(titulo, subtitulo, botones, encabezadoVar1,
				labelsVariables1,valoresvariables1, encabezadoVar2, labelsVariables2, valoresvariables2, labels);
	}

}
