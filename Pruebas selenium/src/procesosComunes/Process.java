package procesosComunes;

import unidadesGraficas.*;
import ventanasComunes.Screen;

public class Process {

	protected Screen screen;
	
	protected TipoBy i_titulo=TipoBy.ID;
	protected String str_titulo="ctl00_SectionZone_LblTitle";
	protected TipoBy i_subtitulo=TipoBy.ID;
	protected String str_subtitulo="ctl00_LblRoute";
	protected TipoBy i_msgerror=TipoBy.ID;
	protected String str_msgerror="ctl00_LblError";
	
	public boolean hayMensajeError() {
		return screen.hayMensajeError();
	}

	public String verMensajeError() {
		return screen.verMensajeError();
	}

	public ErrorMessage mensajeError() {
		return screen.mensajeError();
	}
	
}
