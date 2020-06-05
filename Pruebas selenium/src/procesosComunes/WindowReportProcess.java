package procesosComunes;

import unidadesGraficas.*;
import ventanasComunes.WindowReportScreen;

public class WindowReportProcess extends Process {

	protected TipoBy i_titulo=TipoBy.ID;
	protected String str_titulo="LblTitle";
	protected TipoBy[] i_botones= {TipoBy.ID};
	protected String[] str_botones= {"BtnAsExcel_IB_Label"};

	
	public void Excel() {
		screen.clickBoton(BotonesWindowInforme.Excel.ordinal());
	}
	
	public void pasarANuevaVentana() {
		((WindowReportScreen) screen).pasarANuevaVentana();
	}
	
	public void cerrar() {
		((WindowReportScreen) screen).cerrar();
	}
	
}
