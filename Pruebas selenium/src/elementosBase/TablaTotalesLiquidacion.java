package elementosBase;

import org.apache.log4j.Logger;

import unidadesGraficas.*;

public class TablaTotalesLiquidacion {
	
	Label cabeceraSuperior;
	Label[] cabeceraInferior;
	Label[] lineaEfectivo;
	Label[] lineaCheques;
	Label[] lineaTotal;

	private final static Logger logger = Logger.getLogger(TablaTotalesLiquidacion.class);
	public TablaTotalesLiquidacion(TipoBy i_cabeceraSuperior, String str_cabeceraSuperior,
			TipoBy[] i_cabecera, String[] str_cabecera, TipoBy[] i_columna1,String[] str_columna1, TipoBy[] i_columna2,
			String[] str_columna2){
		
		cabeceraInferior=new Label[2];
		lineaEfectivo=new Label[2];
		lineaTotal=new Label[2];
		lineaCheques=new Label[2];
		cabeceraSuperior=new Label(i_cabeceraSuperior,str_cabeceraSuperior);
		cabeceraInferior[0]=new Label(i_cabecera[0], str_cabecera[0]);
		cabeceraInferior[1]=new Label(i_cabecera[1], str_cabecera[1]);
		if (i_columna1.length==str_columna1.length && i_columna2.length==str_columna2.length) {
			lineaEfectivo[0]=new Label(i_columna1[0], str_columna1[0]);
			lineaEfectivo[1]=new Label(i_columna2[0], str_columna2[0]);
			lineaCheques[0]=new Label(i_columna1[1], str_columna1[1]);
			lineaCheques[1]=new Label(i_columna2[1], str_columna2[1]);
			lineaTotal[0]=new Label(i_columna1[2], str_columna1[2]);
			lineaTotal[1]=new Label(i_columna2[2], str_columna2[2]);
		}
		else {
			String error="TablaTotalesLiquidacion: las longitudes de los arrays "
					+ "i_columna1,str_columna1,i_columna2,str_columna2 no coinciden";
			logger.fatal(error);
			throw new RuntimeException(error);

		}
	}

	public String obtenerValorEfectivo() {
		return lineaEfectivo[1].getText();
	}
	
	public String obtenerValorCheques() {
		return lineaCheques[1].getText();
	}
	
	public String obtenerValorTotal() {
		return lineaTotal[1].getText();
	}
	
	public boolean sintacticAnalysis(String[] cabeceraSuperiorTotales, String[] cabeceraInferiorTotales, String[] columna1) {
		boolean resultado=cabeceraSuperior.sintacticAnalysis(cabeceraSuperiorTotales[0]);
		resultado=lineaEfectivo[0].sintacticAnalysis(columna1[0]) && resultado;
		resultado=lineaCheques[0].sintacticAnalysis(columna1[1]) && resultado;
		resultado=lineaTotal[0].sintacticAnalysis(columna1[2]) && resultado;
		return cabeceraInferior[0].sintacticAnalysis(cabeceraInferiorTotales[0]) &&
				cabeceraInferior[1].sintacticAnalysis(cabeceraInferiorTotales[1]) && resultado;
	}
}
