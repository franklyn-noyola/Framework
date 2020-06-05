package procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias;

import org.apache.log4j.Logger;
import unidadesGraficas.TipoBy;
import ventanasITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.CrearModificarConcesionariaScreen;
import procesosComunes.Process;

public class CrearModificarConcesionariaProcess extends Process {
	
	private TipoBy[] i_botones= {TipoBy.ID,TipoBy.ID};
	private String[] str_botones= {"ctl00_ButtonsZone_BtnSubmit_IB_Label","ctl00_ButtonsZone_BtnBack_IB_Label"};
	private final static Logger logger = Logger.getLogger(CrearModificarConcesionariaProcess.class);
	 
	public CrearModificarConcesionariaProcess() {
		screen=new CrearModificarConcesionariaScreen(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,
				i_msgerror,str_msgerror);
	}
	
	public String leerCampoID() {
		return ((CrearModificarConcesionariaScreen) screen).leerOpcion(OpcionesCrearModificarConcesionariaCampo.ID);
	}
	
	public void escribirCampoID(String valor) {
		borrarCampoID();
		((CrearModificarConcesionariaScreen) screen).escribirOpcion(OpcionesCrearModificarConcesionariaCampo.ID, valor);
	}
	
	public void borrarCampoID() {
		((CrearModificarConcesionariaScreen) screen).borrarOpcion(OpcionesCrearModificarConcesionariaCampo.ID);
	}
	
	public String leerCampoNombre() {
		return ((CrearModificarConcesionariaScreen) screen).leerOpcion(OpcionesCrearModificarConcesionariaCampo.Nombre);
	}
	
	public void escribirCampoNombre(String valor) {
		borrarCampoNombre();
		((CrearModificarConcesionariaScreen) screen).escribirOpcion(OpcionesCrearModificarConcesionariaCampo.Nombre, valor);
	}
	
	public void borrarCampoNombre() {
		((CrearModificarConcesionariaScreen) screen).borrarOpcion(OpcionesCrearModificarConcesionariaCampo.Nombre);
	}
	
	public String leerCampoNumeroFiscal() {
		return ((CrearModificarConcesionariaScreen) screen).leerOpcion(OpcionesCrearModificarConcesionariaCampo.NumeroFiscal);
	}
	
	public void escribirCampoNumeroFiscal(String valor) {
		borrarCampoNumeroFiscal();
		((CrearModificarConcesionariaScreen) screen).escribirOpcion(OpcionesCrearModificarConcesionariaCampo.NumeroFiscal, valor);
	}
	
	public void borrarCampoNumeroFiscal() {
		((CrearModificarConcesionariaScreen) screen).borrarOpcion(OpcionesCrearModificarConcesionariaCampo.NumeroFiscal);
	}
	
	public boolean hayErrorValidacionRequeridoID() {
		return ((CrearModificarConcesionariaScreen) screen).hayErrorValidacionOpcion(OpcionesCrearModificarConcesionariaCampo.ID);
	}
	
	public boolean hayErrorValidacionFormatoID() {
		return ((CrearModificarConcesionariaScreen) screen).hayErrorValidacionOpcion(OpcionesCrearModificarConcesionariaCampo.ID);
	}
	
	public boolean hayErrorValidacionNombre() {
		return ((CrearModificarConcesionariaScreen) screen).hayErrorValidacionOpcion(OpcionesCrearModificarConcesionariaCampo.Nombre);
	}
	
	public boolean hayErrorValidacionNumeroFiscal() {
		return ((CrearModificarConcesionariaScreen) screen).hayErrorValidacionOpcion(OpcionesCrearModificarConcesionariaCampo.NumeroFiscal);
	}
	
	public void confirmar() {
		screen.clickBoton(BotonesCrearModificarConcesionaria.Confirmar.ordinal());
	}

	public void volver() {
		screen.clickBoton(BotonesCrearModificarConcesionaria.Volver.ordinal());
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones, String encabezado_crud, String[] labels_crud) {
		logger.debug("CrearModificar Concesionaria: inicio análisis sintáctico");
		return ((CrearModificarConcesionariaScreen) screen).sintacticAnalysis(titulo, subtitulo, botones, encabezado_crud, labels_crud);
	}

}
