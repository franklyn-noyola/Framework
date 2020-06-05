package elementosBase;

import org.apache.log4j.Logger;
import procesosComunes.Error;
import unidadesGraficas.*;

public class BloqueCriterios {

	private LabelField[] criterios_campo;
	private LabelDropDown[] criterios_desplegable;
	private FechaHora[] criterios_fecha;
	private static int num_criterios_campo;
	private static int num_criterios_desplegable;
	private static int num_criterios_fechahora;
	private final static Logger logger = Logger.getLogger(BloqueCriterios.class);
	
	public BloqueCriterios(int numero_criterios_campo, int numero_criterios_desplegable,int numero_criterios_fecha,
			TipoBy[] i_label, String[] str_label, TipoBy[] i_field, String[] str_field,
			TipoBy[][] i_tooltipcampo, String[][] str_tooltipcampo,TipoBy[][][] i_tooltipfecha, String[][][] str_tooltipfecha) {
		
		num_criterios_campo=0;
		num_criterios_desplegable=0;
		num_criterios_fechahora=0;
		criterios_campo=new LabelField[numero_criterios_campo];
		criterios_desplegable=new LabelDropDown[numero_criterios_desplegable];
		criterios_fecha=new FechaHora[numero_criterios_fecha];
		int j=0;
		if (i_tooltipcampo!=null && str_tooltipcampo!=null) {
			for (int i=0; i<numero_criterios_campo; i++) {
				addCriteriosCampo(i_label[i], str_label[i], i_field[i], str_field[i], i_tooltipcampo[i],
						str_tooltipcampo[i]);
			}
		}
		else { 
			for (int i=0; i<numero_criterios_campo; i++) {
				addCriteriosCampo(i_label[i], str_label[i], i_field[i], str_field[i], null, null);
			}
		}
		
		j+=numero_criterios_campo;
		logger.debug("Criterios desplegable");
		for (int i=0; i<numero_criterios_desplegable; i++) {
			String s ="i_label["+(j+i)+"]="+i_label[j+i]+" str_label["+(j+i)+"]="+str_label[j+i]+" i_field["+(j+i)+"]="+i_field[j+i]+" str_field["+(i+j)+"]="+str_field[j+i];
			logger.trace(s);
			addCriteriosDesplegable(i_label[j+i], str_label[j+i], i_field[j+i], str_field[j+i]);
		}
		j+=numero_criterios_desplegable;
		logger.debug("Criterios fecha");
		for (int i=0; i<numero_criterios_fecha; i++) {
			String s="i_label["+(j+i)+"]="+i_label[j+i]+" str_label["+(j+i)+"]="+str_label[j+i]+" i_field["+(j+(2*i))+"]="+i_field[j+(2*i)]+" str_field["+(j+(2*i))+"]="+str_field[j+(2*i)]+" i_field["+(j+(2*i)+1)+"]="+i_field[j+(2*i)+1]+" str_field["+(j+(2*i)+1)+"]="+str_field[(j+(2*i))+1];
			logger.trace(s);
			if (i_tooltipfecha!=null && str_tooltipfecha!=null) {
				addCriteriosFecha(i_label[j+i], str_label[j+i], i_field[j+(2*i)], str_field[j+2*i],i_field[j+(2*i)+1],
						str_field[j+(2*i)+1],i_tooltipfecha[i],str_tooltipfecha[i]);
			}
			else {
				addCriteriosFecha(i_label[j+i], str_label[j+i], i_field[j+(2*i)], str_field[j+2*i],i_field[j+(2*i)+1],
						str_field[j+(2*i)+1],null,null);
			}
		}
	}
	
	private void addCriteriosCampo(TipoBy i_label, String str_label, TipoBy i_field, String str_field,
			TipoBy[] i_tooltip, String[] str_tooltip) {
		if (i_tooltip==null || str_tooltip==null) {
			criterios_campo[num_criterios_campo++]=new LabelField(i_label, str_label, i_field, str_field);
		}
		else {
			criterios_campo[num_criterios_campo++]=new LabelField(i_label, str_label, i_field, str_field,
					 i_tooltip, str_tooltip);
		}
	}
	
	private void addCriteriosDesplegable(TipoBy i_label, String str_label, TipoBy i_field, String str_field) {
		criterios_desplegable[num_criterios_desplegable++]=new LabelDropDown(i_label, str_label, i_field, str_field);
	}
	
	private void addCriteriosFecha(TipoBy i_label, String str_label,TipoBy i_fecha, String str_fecha, TipoBy i_hora,
			String str_hora, TipoBy[][] i_tooltipfecha, String[][] str_tooltipfecha) {
		if (i_tooltipfecha==null || str_tooltipfecha==null) {
			criterios_fecha[num_criterios_fechahora++]=new FechaHora(i_label, str_label, i_fecha, str_fecha,i_hora,str_hora,
					null,null,null,null);
		}
		else {
			criterios_fecha[num_criterios_fechahora++]=new FechaHora(i_label, str_label, i_fecha, str_fecha,i_hora,str_hora,
				i_tooltipfecha[0],str_tooltipfecha[0],i_tooltipfecha[1],str_tooltipfecha[1]);
		}
	}
	
	public String leerCriterioDesplegable(int i) {
		return criterios_desplegable[i].leerOpcion();
	}
	
	public Error desplegarCriterioDesplegable(int i, String opcion) {
		return criterios_desplegable[i].seleccionarOpcion(opcion);
	}
	
	public String leerOpcion(int opcion) {
		return criterios_campo[opcion].getField().getValue();
	}
	
	public void escribirOpcion(int opcion, String valor) {
		criterios_campo[opcion].writeText(valor);
	}
	
	public boolean hayErrorValidacionFechaFecha(int opcion, int tipoerror) {
		return criterios_fecha[opcion].hayErrorValidacionFecha(tipoerror);
	}
	
	public boolean hayErrorValidacionFechaHora(int opcion, int tipoerror) {
		return criterios_fecha[opcion].hayErrorValidacionHora(tipoerror);
	}
	
	public boolean hayErrorValidacionOpcion(int opcion, int tipoerror) {
		return criterios_campo[opcion].hayErrorValidacion(tipoerror);
	}
	
	public void borrarOpcion(int opcion) {
		criterios_campo[opcion].clear();
	}
	
	public String leerFecha(int opcion) {
		return criterios_fecha[opcion].getFecha();
	}
	
	public String leerHora(int opcion) {
		return criterios_fecha[opcion].getHora();
	}
	
	public void escribirFecha(int opcion, String fecha, String hora) {
		if (fecha!=null) {
			criterios_fecha[opcion].writeFecha(fecha);
		}
		if (hora!=null) {
			criterios_fecha[opcion].writeHora(hora);
		}
	}
	
	public void borrarFecha(int opcion) {
		criterios_fecha[opcion].clearFecha();
	}
	
	public void borrarHora(int opcion) {
		criterios_fecha[opcion].clearHora();
	}
	
	public boolean sintacticAnalysis(String[] labelCriteriosBusqueda) {
		boolean analisisCriteriosBuqueda=true;
		int j=0;
		
		for (int i=0; (i<criterios_campo.length); i++) {
			analisisCriteriosBuqueda=criterios_campo[i].sintacticAnalysis(labelCriteriosBusqueda[i]) && analisisCriteriosBuqueda;
		}
		j+=criterios_campo.length;
		for (int i=0; (i<criterios_desplegable.length); i++) {
			analisisCriteriosBuqueda=criterios_desplegable[i].sintacticAnalysis(labelCriteriosBusqueda[j+i]) && analisisCriteriosBuqueda;
		}
		j+=criterios_desplegable.length;
		for (int i=0; (i<criterios_fecha.length); i++) {
			analisisCriteriosBuqueda=criterios_fecha[i].sintacticAnalysis(labelCriteriosBusqueda[j+i]) && analisisCriteriosBuqueda;
		}
		return analisisCriteriosBuqueda;
	}

}
