package ventanasITATAPlaza.GestionCobrador.HistoricoExpediciones;


import org.apache.log4j.Logger;

import bloques.CrudBlockVariable;
import elementosBase.TablaTotalesLiquidacion;
import elementosBase.TablaValoresLiquidacion;
import procesosITATAHost.GestionCobrador.HistoricoExpediciones.OpcionesVerLiquidacionCampo;
import procesosITATAPlaza.GestionCobrador.HistoricoExpediciones.OpcionesVerLiquidacionDesplegable;
import procesosITATAHost.GestionCobrador.HistoricoExpediciones.OpcionesVerLiquidacionFecha;
import procesosITATAHost.GestionCobrador.HistoricoExpediciones.OpcionesVerLiquidacionVariable;
import testCasesITATAPlaza.GestionCobrador.HistoricoExpediciones.VerLiquidacionTest;
import unidadesGraficas.TipoBy;
import unidadesGraficas.Title;
import ventanasComunes.Screen;

public class VerLiquidacionScreen extends Screen {

	private CrudBlockVariable crudVariable;
	private Title encabezado2;
	private TablaValoresLiquidacion tablaValores;
	private TablaTotalesLiquidacion tablaTotales;
	
	private final TipoBy i_encabezadovar=TipoBy.ID;
	private final String str_encabezadovar="ctl00_ContentZone_LblShiftDetails_Lbl_SubtitleInfo";
	private final int num_criterios_var=2;
	private final TipoBy[] i_labelvar={TipoBy.ID,TipoBy.ID};
	private final String[] str_labelvar={"ctl00_ContentZone_txt_Shift_lbl_Description","ctl00_ContentZone_txt_Cashout_lbl_Description"};
	private final TipoBy[] i_fieldvar={TipoBy.ID,TipoBy.ID};
	private final String[] str_fieldvar={"ctl00_ContentZone_txt_Shift_box_data","ctl00_ContentZone_txt_Cashout_box_data"};
	
	private final TipoBy i_encabezado=TipoBy.ID;
	private final String str_encabezado="ctl00_ContentZone_LblAmount";
	private final int num_criterios_campo=1;
	private final int num_criterios_desplegable=1;
	private final int num_criterios_fecha=1;
	private final TipoBy[] i_label= {TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private final String[] str_label={"ctl00_ContentZone_txt_Seal_lbl_Description",
							"ctl00_ContentZone_companyPlazaLane_cmb_plaza_lbl_Description","ctl00_ContentZone_lbl_CashOutDate"};
	private final TipoBy[] i_field={TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private final String[] str_field={"ctl00_ContentZone_txt_Seal_box_data",null,"ctl00_ContentZone_dt_CashOutTime_box_date","ctl00_ContentZone_dt_CashOutTime_box_hour"};
	private final TipoBy[][] i_tooltipcampo=null; 
	private final String[][] str_tooltipcampo=null; 
	private final TipoBy[][][] i_tooltipfecha=null; 
	private final String[][][] str_tooltipfecha=null; 
	
	//Tabla Valores
	private final int num_filasValores=VerLiquidacionTest.numFilas;
	private final int num_columnasValores=4;
	private final TipoBy i_cabeceraValores=TipoBy.XPATH;
	private final String str_cabeceraValores="//*[@id='ctl00_ContentZone_TblResultsCash']/tbody/";
	
	//Tabla Totales
	private final TipoBy i_cabeceraSuperior=TipoBy.ID;
	private final String str_cabeceraSuperior="ctl00_ContentZone_LblResumen1";
	private final TipoBy[] i_cabeceraTotales={TipoBy.ID,TipoBy.ID};
	private final String[] str_cabeceraTotales={"ctl00_ContentZone_LblResumen2","ctl00_ContentZone_LblResumen3"};
	private final TipoBy[] i_columna1= {TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private final String[] str_columna1={"ctl00_ContentZone_LblResumen4","ctl00_ContentZone_LblResumen5","ctl00_ContentZone_LblResumen10"};
	private final TipoBy[] i_columna2= {TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private final String[] str_columna2={"ctl00_ContentZone_LblPIBnorm","ctl00_ContentZone_LblPIBcheques","ctl00_ContentZone_LblPIBtotal"};
	private final static Logger logger = Logger.getLogger(VerLiquidacionScreen.class);
	
	public VerLiquidacionScreen(TipoBy i_titulo, String str_titulo, TipoBy i_subtitulo, String str_subtitulo, TipoBy[] i_botones, String[] str_botones,
			TipoBy i_msgerror, String str_msgerror) {
		
		super(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,i_msgerror,str_msgerror);
		crudVariable=new CrudBlockVariable(i_encabezadovar,str_encabezadovar, num_criterios_var,i_labelvar,str_labelvar,
				i_fieldvar, str_fieldvar,num_criterios_campo,num_criterios_desplegable,num_criterios_fecha,i_label,
				str_label,i_field, str_field,i_tooltipcampo,str_tooltipcampo,i_tooltipfecha,str_tooltipfecha);
		encabezado2=new Title(i_encabezado,str_encabezado);
		tablaValores=new TablaValoresLiquidacion(num_filasValores, num_columnasValores, i_cabeceraValores, str_cabeceraValores, 0);
		tablaTotales=new TablaTotalesLiquidacion(i_cabeceraSuperior, str_cabeceraSuperior,
				i_cabeceraTotales, str_cabeceraTotales,i_columna1,str_columna1,i_columna2,str_columna2);
	}

	
	public String leerOpcionVariable(OpcionesVerLiquidacionVariable opcion) {
		return crudVariable.leerOpcionVariable(opcion.ordinal());
	}

	public String leerOpcion(OpcionesVerLiquidacionCampo opcion) {
		return crudVariable.leerOpcion(opcion.ordinal());
	}

	public String leerOpcionDesplegable(OpcionesVerLiquidacionDesplegable opcion) {
		return crudVariable.leerCriterioDesplegable(opcion.ordinal());
	}

	public String leerFecha(OpcionesVerLiquidacionFecha opcion) {
		return crudVariable.leerFecha(opcion.ordinal());
	}
	
	public String leerHora(OpcionesVerLiquidacionFecha opcion) {
		return crudVariable.leerHora(opcion.ordinal());
	}
	
	public String leerValorEfectivoTablaTotales() {
		return tablaTotales.obtenerValorEfectivo();
	}
	
	public String leerValorChequesTablaTotales() {
		return tablaTotales.obtenerValorCheques();
	}
	
	public String leerValorTotalTablaTotales() {
		return tablaTotales.obtenerValorTotal();
	}
	
	// 
	public boolean validarTablaValores() {
		try {
			for (int i=1; i<=tablaValores.numFilasTotal(); i++) {
				int denominacion=Integer.parseInt(tablaValores.obtenerValorColumna(i, "Denominación").replace(".",""));
				String s_numero=tablaValores.obtenerValorColumna(i, "Número");
				int numero=0;
				int valor=0;
				if (s_numero.length()>0)  {
					s_numero=s_numero.replace(".","");
					numero=Integer.parseInt(s_numero);
				}
				s_numero=tablaValores.obtenerValorColumna(i, "Valor ($)");
				if (s_numero.length()>0)  {
					s_numero=s_numero.replace(".","");
					valor=Integer.parseInt(s_numero);
				}
				if ((denominacion*numero)!=valor) {
					logger.error("Error en fila "+i+": "+denominacion+"*"+numero+"<>"+valor);
					return false;
				}
			}
			return true;
		}
		catch (Exception e) {
			logger.error("Los valores de la tabla Valores no contienen enteros");
			return false;
		}
	}
	
	public boolean validarTablaTotales(String total) {
		int efectivo=0;
		int cheques=0;
		int valortotal=0;
		total=total.substring(2, total.length()); //Eliminamos el $
		String s_numero=leerValorEfectivoTablaTotales();
		if (s_numero.length()>0)  {
			s_numero=s_numero.replace(".","");
			efectivo=Integer.parseInt(s_numero);
		}
		
		s_numero=leerValorChequesTablaTotales();
		if (s_numero.length()>0)  {
			s_numero=s_numero.replace(".","");
			cheques=Integer.parseInt(s_numero);
		}
		
		s_numero=leerValorTotalTablaTotales();
		if (s_numero.equals(total)) {
			if (s_numero.length()>0)  {
				s_numero=s_numero.replace(".","");
				valortotal=Integer.parseInt(s_numero);
			}
			if (efectivo+cheques!=valortotal) {
				logger.error("Los valores de la tabla Totales no suman el total mostrado: ("+efectivo+","+cheques+","+valortotal+")");
				return false;
			}
		}
		else {
			logger.error("El valor total mostrado en la tabla no coincide con el que apareceía en la tabla de Historico Liquidaciones: ("+s_numero+","+total+")");
			return false;
		}
		return true;
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones,  String encabezadoVar,
			String[] labelsVariable, String[] valoresvariables, String[] labels, String encabezado2,
			String[] labels_columnas1,  String idConcesionaria, String[][] monedas, String[] cabeceraSuperiorTotales,
			String[] cabeceraInferiorTotales, String[] columna1) {
		
		boolean resultado=super.sintacticAnalysis(titulo, subtitulo, botones);
		resultado=crudVariable.sintacticAnalysis(encabezadoVar, labelsVariable, valoresvariables, labels) && resultado;
		resultado=this.encabezado2.sintacticAnalysis(encabezado2) && resultado;
		resultado=tablaValores.sintacticAnalysis(labels_columnas1) && resultado;
		int j=0;
		while (!monedas[j][0].equals(idConcesionaria)) {
			j++;
		}
		int nummonedas=0;
		boolean fin=false;
		for (int z=j;z<tablaValores.numFilas() && !fin;z++) {
			if (monedas[z][0].equals(idConcesionaria)) {
				nummonedas++;
			}
			else {
				fin=true;
			}
		}
		if (nummonedas!=tablaValores.numFilas()) {
			logger.error("Analisis sintactico Ver Liquidación:  el número de filas de la tabla ("+tablaValores.numFilas()+
					") no coincide con el de las monedas para esa concesionaria ("+nummonedas+")");
			resultado=false;
		}
		for (int i=0; i<tablaValores.numFilas() && resultado; i++) {
			if (!tablaValores.obtenerValorColumna(i+1, "Tipo").equals(monedas[j+i][1])) {
				resultado=false;
				logger.error("Analisis sintactico Ver Liquidación: se esperaba "+monedas[j+i][1]+"y se ha encontrado "+tablaValores.obtenerValorColumna(i+1, "Tipo"));
			}
			if (!tablaValores.obtenerValorColumna(i+1, "Denominación").equals(monedas[j+i][2].substring(2,monedas[j+i][2].length()))) {
				resultado=false;
				logger.error("Analisis sintactico Ver Liquidación: se esperaba "+monedas[j+i][2]+"y se ha encontrado "+tablaValores.obtenerValorColumna(i+1, "Denominación"));

			}
		}
		resultado=tablaTotales.sintacticAnalysis(cabeceraSuperiorTotales,cabeceraInferiorTotales, columna1) && resultado;
		return resultado;
	}
}
