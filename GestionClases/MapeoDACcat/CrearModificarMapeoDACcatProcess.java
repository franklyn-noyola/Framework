package procesosITATAHost.ConfiguracionSistema.GestionClases.MapeoDACcat;

import org.apache.log4j.Logger;
import unidadesGraficas.TipoBy;
import ventanasITATAHost.ConfiguracionSistema.GestionClases.MapeoDACcat.CrearMapeoDACcatScreen;
import procesosComunes.Process;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Motivos.BotonesCrearModificarMotivo;

public class CrearModificarMapeoDACcatProcess extends Process {
	
	private TipoBy[] i_botones= {TipoBy.ID,TipoBy.ID};
	private String[] str_botones= {"ctl00_ButtonsZone_BtnSubmit_IB_Label","ctl00_ButtonsZone_BtnBack_IB_Label"};
	private final static Logger logger = Logger.getLogger(CrearModificarMapeoDACcatProcess.class);
	 
	public CrearModificarMapeoDACcatProcess() {
		screen=new CrearMapeoDACcatScreen(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,
				i_msgerror,str_msgerror);
	}
	
	public String leerCampoIndice() {
		return ((CrearMapeoDACcatScreen) screen).leerOpcion(OpcionesCrearModificarMapeoDACcatCampo.Indice);
	}
	
	public void escribirCampoIndice(String valor) {
		borrarCampoIndice();
		((CrearMapeoDACcatScreen) screen).escribirOpcion(OpcionesCrearModificarMapeoDACcatCampo.Indice, valor);
	}
	
	public void borrarCampoIndice() {
		((CrearMapeoDACcatScreen) screen).borrarOpcion(OpcionesCrearModificarMapeoDACcatCampo.Indice);
	}
	
	public String leerCampoConcesionaria() {
		return ((CrearMapeoDACcatScreen) screen).leerOpcionDesplegable(OpcionesCrearModificarMapeoDACcatDesplegable.Concesionaria);
	}
	
	public void seleccionarCampoConcesionaria(String valor) {
		((CrearMapeoDACcatScreen) screen).seleccionarOpcion(OpcionesCrearModificarMapeoDACcatDesplegable.Concesionaria, valor);
	}
	
	public String leerCampoCategoria() {
		return ((CrearMapeoDACcatScreen) screen).leerOpcionDesplegable(OpcionesCrearModificarMapeoDACcatDesplegable.Categoria);
	}
	
	public void seleccionarCampoCategoria(String valor) {
		((CrearMapeoDACcatScreen) screen).seleccionarOpcion(OpcionesCrearModificarMapeoDACcatDesplegable.Categoria, valor);
	}
	
	public String leerCampoMinRuedaDoble() {
		return ((CrearMapeoDACcatScreen) screen).leerOpcion(OpcionesCrearModificarMapeoDACcatCampo.MinRuedaDoble);
	}
	
	public void escribirCampoMinRuedaDoble(String valor) {
		borrarCampoMinRuedaDoble();
		((CrearMapeoDACcatScreen) screen).escribirOpcion(OpcionesCrearModificarMapeoDACcatCampo.MinRuedaDoble, valor);
	}
	
	public void borrarCampoMinRuedaDoble() {
		((CrearMapeoDACcatScreen) screen).borrarOpcion(OpcionesCrearModificarMapeoDACcatCampo.MinRuedaDoble);
	}
	
	public String leerCampoMaxRuedaDoble() {
		return ((CrearMapeoDACcatScreen) screen).leerOpcion(OpcionesCrearModificarMapeoDACcatCampo.MaxRuedaDoble);
	}
	
	public void escribirCampoMaxRuedaDoble(String valor) {
		borrarCampoMaxRuedaDoble();
		((CrearMapeoDACcatScreen) screen).escribirOpcion(OpcionesCrearModificarMapeoDACcatCampo.MaxRuedaDoble, valor);
	}
	
	public void borrarCampoMaxRuedaDoble() {
		((CrearMapeoDACcatScreen) screen).borrarOpcion(OpcionesCrearModificarMapeoDACcatCampo.MaxRuedaDoble);
	}
	
	public String leerCampoNumMinEjes() {
		return ((CrearMapeoDACcatScreen) screen).leerOpcion(OpcionesCrearModificarMapeoDACcatCampo.NumMinEjes);
	}
	
	public void escribirCampoNumMinEjes(String valor) {
		borrarCampoNumMinEjes();
		((CrearMapeoDACcatScreen) screen).escribirOpcion(OpcionesCrearModificarMapeoDACcatCampo.NumMinEjes, valor);
	}
	
	public void borrarCampoNumMinEjes() {
		((CrearMapeoDACcatScreen) screen).borrarOpcion(OpcionesCrearModificarMapeoDACcatCampo.NumMinEjes);
	}
	
	public String leerCampoNumMaxEjes() {
		return ((CrearMapeoDACcatScreen) screen).leerOpcion(OpcionesCrearModificarMapeoDACcatCampo.NumMaxEjes);
	}
	
	public void escribirCampoNumMaxEjes(String valor) {
		borrarCampoNumMaxEjes();
		((CrearMapeoDACcatScreen) screen).escribirOpcion(OpcionesCrearModificarMapeoDACcatCampo.NumMaxEjes, valor);
	}
	
	public void borrarCampoNumMaxEjes() {
		((CrearMapeoDACcatScreen) screen).borrarOpcion(OpcionesCrearModificarMapeoDACcatCampo.NumMaxEjes);
	}
	
	public String leerCampoDistanciaMinEjes() {
		return ((CrearMapeoDACcatScreen) screen).leerOpcion(OpcionesCrearModificarMapeoDACcatCampo.DistanciaMinEjes);
	}
	
	public void escribirCampoDistanciaMinEjes(String valor) {
		borrarCampoDistanciaMinEjes();
		((CrearMapeoDACcatScreen) screen).escribirOpcion(OpcionesCrearModificarMapeoDACcatCampo.DistanciaMinEjes, valor);
	}
	
	public void borrarCampoDistanciaMinEjes() {
		((CrearMapeoDACcatScreen) screen).borrarOpcion(OpcionesCrearModificarMapeoDACcatCampo.DistanciaMinEjes);
	}
	
	public String leerCampoDistanciaMaxEjes() {
		return ((CrearMapeoDACcatScreen) screen).leerOpcion(OpcionesCrearModificarMapeoDACcatCampo.DistanciaMaxEjes);
	}
	
	public void escribirCampoDistanciaMaxEjes(String valor) {
		borrarCampoDistanciaMaxEjes();
		((CrearMapeoDACcatScreen) screen).escribirOpcion(OpcionesCrearModificarMapeoDACcatCampo.DistanciaMaxEjes, valor);
	}
	
	public void borrarCampoDistanciaMaxEjes() {
		((CrearMapeoDACcatScreen) screen).borrarOpcion(OpcionesCrearModificarMapeoDACcatCampo.DistanciaMaxEjes);
	}
	
	public String leerCampoAlturaMin() {
		return ((CrearMapeoDACcatScreen) screen).leerOpcion(OpcionesCrearModificarMapeoDACcatCampo.AlturaMinima);
	}
	
	public void escribirCampoAlturaMin(String valor) {
		borrarCampoAlturaMin();
		((CrearMapeoDACcatScreen) screen).escribirOpcion(OpcionesCrearModificarMapeoDACcatCampo.AlturaMinima, valor);
	}
	
	public void borrarCampoAlturaMin() {
		((CrearMapeoDACcatScreen) screen).borrarOpcion(OpcionesCrearModificarMapeoDACcatCampo.AlturaMinima);
	}
	
	public String leerCampoAlturaMax() {
		return ((CrearMapeoDACcatScreen) screen).leerOpcion(OpcionesCrearModificarMapeoDACcatCampo.AlturaMaxima);
	}
	
	public void escribirCampoAlturaMax(String valor) {
		borrarCampoAlturaMax();
		((CrearMapeoDACcatScreen) screen).escribirOpcion(OpcionesCrearModificarMapeoDACcatCampo.AlturaMaxima, valor);
	}
	
	public void borrarCampoAlturaMax() {
		((CrearMapeoDACcatScreen) screen).borrarOpcion(OpcionesCrearModificarMapeoDACcatCampo.AlturaMaxima);
	}
	
	public String leerCampAnchuraMin() {
		return ((CrearMapeoDACcatScreen) screen).leerOpcion(OpcionesCrearModificarMapeoDACcatCampo.AnchuraMinima);
	}
	
	public void escribirCampoAnchuraMin(String valor) {
		borrarCampoAnchuraMin();
		((CrearMapeoDACcatScreen) screen).escribirOpcion(OpcionesCrearModificarMapeoDACcatCampo.AnchuraMinima, valor);
	}
	
	public void borrarCampoAnchuraMin() {
		((CrearMapeoDACcatScreen) screen).borrarOpcion(OpcionesCrearModificarMapeoDACcatCampo.AnchuraMinima);
	}
	
	public String leerCampAnchuraMax() {
		return ((CrearMapeoDACcatScreen) screen).leerOpcion(OpcionesCrearModificarMapeoDACcatCampo.AnchuraMaxima);
	}
	
	public void escribirCampoAnchuraMax(String valor) {
		borrarCampoAnchuraMax();
		((CrearMapeoDACcatScreen) screen).escribirOpcion(OpcionesCrearModificarMapeoDACcatCampo.AnchuraMaxima, valor);
	}
	
	public void borrarCampoAnchuraMax() {
		((CrearMapeoDACcatScreen) screen).borrarOpcion(OpcionesCrearModificarMapeoDACcatCampo.AnchuraMaxima);
	}
	
	public String leerRadioButtonBus() {
		return ((CrearMapeoDACcatScreen) screen).leerRadioButton(OpcionesCrearModificarMapeoDACcatRadioButton.Bus);
	}
	
	public void clicarRadioButtonBusSi() {
		((CrearMapeoDACcatScreen) screen).clickRadioButton(OpcionesCrearModificarMapeoDACcatRadioButton.Bus.ordinal(), OpcionesCrearModificarMapeoDACcatRadioButtonBus.Si.ordinal());
	}
	
	public void clicarRadioButtonBusNo() {
		((CrearMapeoDACcatScreen) screen).clickRadioButton(OpcionesCrearModificarMapeoDACcatRadioButton.Bus.ordinal(), OpcionesCrearModificarMapeoDACcatRadioButtonBus.No.ordinal());
	}
	
	public void clicarRadioButtonBusIndiferente() {
		((CrearMapeoDACcatScreen) screen).clickRadioButton(OpcionesCrearModificarMapeoDACcatRadioButton.Bus.ordinal(), OpcionesCrearModificarMapeoDACcatRadioButtonBus.Indiferente.ordinal());
	}
	
	public String leerRadioButtonRemolques() {
		return ((CrearMapeoDACcatScreen) screen).leerRadioButton(OpcionesCrearModificarMapeoDACcatRadioButton.Remolques);
	}
	
	public void clicarRadioButtonRemolquesSi() {
		((CrearMapeoDACcatScreen) screen).clickRadioButton(OpcionesCrearModificarMapeoDACcatRadioButton.Remolques.ordinal(), OpcionesCrearModificarMapeoDACcatRadioButtonBus.Si.ordinal());
	}
	
	public void clicarRadioButtonRemolquesNo() {
		((CrearMapeoDACcatScreen) screen).clickRadioButton(OpcionesCrearModificarMapeoDACcatRadioButton.Remolques.ordinal(), OpcionesCrearModificarMapeoDACcatRadioButtonBus.No.ordinal());
	}
	
	public void clicarRadioButtonRemolquesIndiferente() {
		((CrearMapeoDACcatScreen) screen).clickRadioButton(OpcionesCrearModificarMapeoDACcatRadioButton.Remolques.ordinal(), OpcionesCrearModificarMapeoDACcatRadioButtonBus.Indiferente.ordinal());
	}
	
	public boolean hayErrorValidacionRequeridoIndice() {
		return ((CrearMapeoDACcatScreen) screen).hayErrorValidacionOpcionRequerido(OpcionesCrearModificarMapeoDACcatCampo.Indice);
	}

	public boolean hayErrorValidacionFormatoIndice() {
		return ((CrearMapeoDACcatScreen) screen).hayErrorValidacionOpcionFormato(OpcionesCrearModificarMapeoDACcatCampo.Indice);
	}
	
	public boolean hayErrorValidacionFormatoMinRuedaDoble() {
		return ((CrearMapeoDACcatScreen) screen).hayErrorValidacionOpcionFormato(OpcionesCrearModificarMapeoDACcatCampo.MinRuedaDoble);
	}
	
	public boolean hayErrorValidacionFormatoMaxRuedaDoble() {
		return ((CrearMapeoDACcatScreen) screen).hayErrorValidacionOpcionFormato(OpcionesCrearModificarMapeoDACcatCampo.MaxRuedaDoble);
	}
	
	public boolean hayErrorValidacionFormatoNumMinEjes() {
		return ((CrearMapeoDACcatScreen) screen).hayErrorValidacionOpcionFormato(OpcionesCrearModificarMapeoDACcatCampo.NumMinEjes);
	}
	
	public boolean hayErrorValidacionFormatoNumMaxEjes() {
		return ((CrearMapeoDACcatScreen) screen).hayErrorValidacionOpcionFormato(OpcionesCrearModificarMapeoDACcatCampo.NumMaxEjes);
	}
	
	public boolean hayErrorValidacionFormatoDistanciaMinEjes() {
		return ((CrearMapeoDACcatScreen) screen).hayErrorValidacionOpcionFormato(OpcionesCrearModificarMapeoDACcatCampo.DistanciaMinEjes);
	}
	
	public boolean hayErrorValidacionFormatoDistanciaMaxEjes() {
		return ((CrearMapeoDACcatScreen) screen).hayErrorValidacionOpcionFormato(OpcionesCrearModificarMapeoDACcatCampo.DistanciaMaxEjes);
	}
	
	public boolean hayErrorValidacionFormatoAlturaMinima() {
		return ((CrearMapeoDACcatScreen) screen).hayErrorValidacionOpcionFormato(OpcionesCrearModificarMapeoDACcatCampo.AlturaMinima);
	}
	
	public boolean hayErrorValidacionFormatoAlturaMaxima() {
		return ((CrearMapeoDACcatScreen) screen).hayErrorValidacionOpcionFormato(OpcionesCrearModificarMapeoDACcatCampo.AlturaMaxima);
	}
	
	public boolean hayErrorValidacionFormatoAnchuraMinima() {
		return ((CrearMapeoDACcatScreen) screen).hayErrorValidacionOpcionFormato(OpcionesCrearModificarMapeoDACcatCampo.AnchuraMinima);
	}
	
	public boolean hayErrorValidacionFormatoAnchuraMaxima() {
		return ((CrearMapeoDACcatScreen) screen).hayErrorValidacionOpcionFormato(OpcionesCrearModificarMapeoDACcatCampo.AnchuraMaxima);
	}
	
	public void confirmar() {
		screen.clickBoton(BotonesCrearModificarMotivo.Confirmar.ordinal());
	}

	public void volver() {
		screen.clickBoton(BotonesCrearModificarMotivo.Volver.ordinal());
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones, String encabezado_crud,
			String[] labels_crud, String[][] labels_check) {
		logger.debug("Crear mapeo DACcat: inicio análisis sintáctico");
		return ((CrearMapeoDACcatScreen) screen).sintacticAnalysis(titulo, subtitulo, botones, encabezado_crud, labels_crud, labels_check);
	}

}
