package bloques;

import org.apache.log4j.Logger;
import procesosComunes.Error;
import elementosBase.*;
import unidadesGraficas.*;

public class CrudBlock {

	protected Title encabezado=null;
	protected BloqueCriterios bloque_criterios=null;
	private final static Logger logger = Logger.getLogger(CrudBlock.class);
	
	public CrudBlock(TipoBy i_encabezado, String str_encabezado,int num_criterios_campo, int num_criterios_desplegable,
			int num_criterios_fecha, TipoBy[] i_label, String[] str_label, TipoBy[] i_field, String[] str_field,
			TipoBy[][] i_tooltipcampo, String[][] str_tooltipcampo,TipoBy[][][] i_tooltipfecha, String[][][] str_tooltipfecha) {
		
		if (i_label!= null && str_label!=null) {
			if (i_label.length!=str_label.length) {
				logger.fatal("CrudBlock: i_label.length="+i_label.length+"<>str_label.length="+str_label.length);
				throw new RuntimeException("CrudBlock: i_label.length="+i_label.length+"<>str_label.length="+str_label.length);
			}
		}
		if (i_field!= null && str_field!=null) {
			if (i_field.length!=str_field.length) {
				logger.fatal("CrudBlock: i_field.length="+i_field.length+"<>str_field.length="+str_field.length);
				throw new RuntimeException("CrudBlock: i_field.length="+i_field.length+"<>str_field.length="+str_field.length);
			}
		}
		if (i_tooltipcampo!=null && str_tooltipcampo!=null) {
			if (i_tooltipcampo.length!=str_tooltipcampo.length) {
				logger.fatal("CrudBlock: i_tooltipcampo.length="+i_tooltipcampo.length+"<>str_tooltipcampo.length="+str_tooltipcampo.length);
				throw new RuntimeException("CrudBlock: i_tooltipcampo.length="+i_tooltipcampo.length+"<>str_tooltip.length="+str_tooltipcampo.length);
			}
			if (i_tooltipcampo.length!=num_criterios_campo) {
				logger.fatal("CrudBlock: i_tooltipcampo.length="+i_tooltipcampo.length+"<>num_Criterios_campo("+num_criterios_campo+")");
				throw new RuntimeException("CrudBlock: i_tooltipcampo.length="+i_tooltipcampo.length+"<>num_Criterios_campo("+num_criterios_campo+")");
			}
		}

		if (i_tooltipfecha!=null && str_tooltipfecha!=null) {
			if (i_tooltipfecha.length!=str_tooltipfecha.length) {
				logger.fatal("CrudBlock: i_tooltipfecha.length="+i_tooltipfecha.length+"<>str_tooltipfecha.length="+str_tooltipfecha.length);
				throw new RuntimeException("CrudBlock: i_tooltipfecha.length="+i_tooltipfecha.length+"<>str_tooltipfecha.length="+str_tooltipfecha.length);
			}
			if (i_tooltipfecha.length!=num_criterios_fecha) {
				logger.fatal("CrudBlock: i_tooltipfecha.length="+i_tooltipfecha.length+"<>num_Criterios_fecha("+num_criterios_fecha+")");
				throw new RuntimeException("CrudBlock: i_tooltipfecha.length="+i_tooltipfecha.length+"<>num_Criterios_fecha("+num_criterios_fecha+")");
			}
		}
		
		if (i_encabezado!=null && str_encabezado!=null) {
			encabezado=new Title(i_encabezado, str_encabezado);
		}
		bloque_criterios=new BloqueCriterios(num_criterios_campo, num_criterios_desplegable, num_criterios_fecha, 
				i_label, str_label, i_field, str_field, i_tooltipcampo, str_tooltipcampo, i_tooltipfecha, str_tooltipfecha);
	}
	
	public String leerCriterioDesplegable(int i) {
		return bloque_criterios.leerCriterioDesplegable(i);
	}
	
	public Error desplegarCriterioDesplegable(int i,String opcion) {
		return bloque_criterios.desplegarCriterioDesplegable(i,opcion);
	}
	
	public String leerOpcion(int opcion) {
		return bloque_criterios.leerOpcion(opcion);
	}
	
	public void escribirOpcion(int opcion, String valor) {
		bloque_criterios.escribirOpcion(opcion, valor);
	}
	
	public void borrarOpcion(int opcion) {
		bloque_criterios.borrarOpcion(opcion);
	}
	
	public boolean hayErrorValidacionOpcion(int opcion, int tipoerror) {
		return bloque_criterios.hayErrorValidacionOpcion(opcion,tipoerror);
	}
	
	public boolean hayErrorValidacionFechaFecha(int opcion, int tipoerror) {
		return bloque_criterios.hayErrorValidacionFechaFecha(opcion,tipoerror);
	}
	
	public boolean hayErrorValidacionFechaHora(int opcion, int tipoerror) {
		return bloque_criterios.hayErrorValidacionFechaHora(opcion,tipoerror);
	}
	
	public String leerFecha(int opcion) {
		return bloque_criterios.leerFecha(opcion);
	}
	
	public String leerHora(int opcion) {
		return bloque_criterios.leerHora(opcion);
	}
	
	public void escribirFecha(int opcion, String fecha, String hora) {
		bloque_criterios.escribirFecha(opcion, fecha, hora);
	}
	
	public void borrarFecha(int opcion) {
		bloque_criterios.borrarFecha(opcion);
	}
	
	public void borrarHora(int opcion) {
		bloque_criterios.borrarHora(opcion);
	}
	
	public boolean sintacticAnalysis(String encabezado, String[] labelCriteriosBusqueda) {
		
		boolean resultado=true;
		
		if (encabezado!=null) {
			resultado=this.encabezado.sintacticAnalysis(encabezado);
		}
		if (labelCriteriosBusqueda!=null) {
			resultado=bloque_criterios.sintacticAnalysis(labelCriteriosBusqueda) && resultado;
		}
		return resultado;
	}
}
