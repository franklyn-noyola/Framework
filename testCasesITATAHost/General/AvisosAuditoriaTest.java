package testCasesITATAHost.General;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import procesosRDLPlaza.AvisosAuditoriaProcessRDLPlaza;
import testCasesITATAHost.InitBOTest;

public class AvisosAuditoriaTest {

	final static String titulo_avisosdeauditoria="Avisos activos"; 
	final static String subtitulo_avisosdeauditoria="General   -   Avisos"; 
	final static String[] botones_avisosdeauditoria={"Actualizar","Volver"}; 
	final static String encabezado_avisosdeauditoria=null;
	final static String[] labels_avisosdeauditoria= {"Operador","Estado","Nodo Plaza","Nodo Vía","Fecha"};
	final static String[] columnasgrid_avisosdeauditoria={"Número","ID","Módulo","Plaza",
			"Vía","Fecha Generación","Gravedad","Cerrar","Texto"};
	final static String[] botonesgrid_avisosdeauditoria={"Primera","<",">","Última"};
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(AvisosAuditoriaTest.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		InitBOTest.tearDownAfterClass();
	}
	
	@After
	public void AfterTest() throws Exception {
		logger.info("Fin TC");
		logger.info("-----------------------------------------------------------------");
	}
	
	@Test
	public void testAvisosAuditoria() {
		
		botest.bo.seleccionarOpcionMenu("General","Avisos");
		AvisosAuditoriaProcessRDLPlaza avisosAuditoria=new AvisosAuditoriaProcessRDLPlaza();
		if (avisosAuditoria.sintacticAnalysis(titulo_avisosdeauditoria, subtitulo_avisosdeauditoria,
			botones_avisosdeauditoria, encabezado_avisosdeauditoria, labels_avisosdeauditoria,columnasgrid_avisosdeauditoria,
			botonesgrid_avisosdeauditoria)) {
			System.out.println("Página Avisos auditoría: análisis sintáctico correcto");
		}
		else {
			System.out.println("Página Avisos auditoría: análisis sintáctico incorrecto");
		}
	
	}

}
