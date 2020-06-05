package testCasesITATAHost.ConfiguracionSistema.Operadores.GestionOperadores;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.Operadores.GestionOperadores.GestionOperadoresProcess;
import procesosITATAHost.ConfiguracionSistema.Operadores.GestionOperadores.ModificarOperadorProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;
import testsComunes.RutinasTestComunes;


public class ModificarOperadorTest {

	final static String titulo_ver_operador="Gesti�n de operadores: operador"; 
	final static String subtitulo_ver_operador="Configuraci�n sistema   -   Operadores   -   Gesti�n de operadores   -   Ver/modificar"; 
	final static String encabezadocrud_ver_operador1="Informaci�n de Operador";
	final static String[] botones_ver_operador= {"Confirmar","Volver"};
	final static String[] labels_ver_operadorvariables= {"Concesionaria","ID de Operador"};
	final static String[] labels_ver_operador1= {"Nombre","Apellidos","Direcci�n","C.P.","Ciudad",
			"Pa�s","E-mail","Tel�fono","T�tulo","G�nero","Grupo Op.","Fecha nacimiento"};
	final static String encabezadocrud_ver_operador2="Estado";
	final static String label_ver_operador2="Suspendido";
	final static String encabezadocrud_ver_operador3="Contrase�a";
	final static String label_ver_operador3="Nueva contrase�a";
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass1() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(ModificarOperadorTest.class);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		InitBOTest.tearDownAfterClass();
	}
	
	@After
	public void afterTest() throws Exception {
		logger.info("Fin TC");
		logger.info("-----------------------------------------------------------------");
	}
	
	//@Test
	public void testModificarOperador1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Modificar Operador");
		logger.info("TC1 Modificar Operador: probar que el an�lisis sint�ctico es correcto");
		logger.info("TC1: Modificar Operador: resultado esperado=true (operaci�n correcta)");
		String[] valoresvariables=new String[2];
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Operadores","Gesti�n de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		valoresvariables[0]="01: Autopista del Itata";
		int numfila=1;
		String columna="Operador";
		String valor= gestionOperadores.obtenerValorColumna(numfila,columna);
		valoresvariables[1]=valor;		
		if (valor==null) {
			logger.error("TC1 Modificar Operador: El nombre de la columna especificada no existe: "+columna);
		}
		gestionOperadores.seleccionarFila(numfila);
		gestionOperadores.vermodificar();
		ModificarOperadorProcess verOperador=new ModificarOperadorProcess();
		boolean analisis=verOperador.sintacticAnalysis(titulo_ver_operador, subtitulo_ver_operador,
				botones_ver_operador, encabezadocrud_ver_operador1, labels_ver_operadorvariables,
				valoresvariables,labels_ver_operador1, encabezadocrud_ver_operador2, label_ver_operador2,
				encabezadocrud_ver_operador3, label_ver_operador3);
		if (analisis) {
			logger.info("TC1: Modificar Operador: resultado obtenido=true");
			logger.info("P�gina Modificar operador: an�lisis sint�ctico correcto");
		}
		else {
			logger.error("P�gina Modificar operador: an�lisis sint�ctico incorrecto");
			LoginTest.minors++;
		}
		assertTrue(analisis);
	}
		
	
	@Test
	public void testModificarOperador2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Modificar Operador");
		logger.info("TC2 Modificar Operador: probar la edici�n de un elemento existente");
		logger.info("TC2: Modificar Operador: resultado esperado=true (operaci�n correcta)");
		String[] datosEntrada={"Sr","Masculino","Pepe","P�rez","Diagonal 234, 3� 3�","08023","Barcelona","pepe@0x.com",
				"Espa�a","6457698","01: Cobradores","01/05/1960"};
		String[] datosSalida=new String[12];
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Operadores","Gesti�n de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		String operador="00190";
		gestionOperadores.escribirOpcionOperador(operador);
		gestionOperadores.busqueda();
		if (gestionOperadores.numFilasTabla()!=1) {
			logger.error("TC2 Modificar Operador: el n�mero de filas obtenidas en la tabla tras la b�squeda es incorrecto");
			LoginTest.majors++;
			fail();
		}
		else {
			gestionOperadores.seleccionarFila(1);
			gestionOperadores.vermodificar();
			ModificarOperadorProcess verOperador=new ModificarOperadorProcess();
			verOperador.seleccionarOpcionTitulo(datosEntrada[0]);
			verOperador.seleccionarOpcionGenero(datosEntrada[1]);
		    verOperador.escribirCampoNombre(datosEntrada[2]);
		    verOperador.escribirCampoApellidos(datosEntrada[3]);
		    verOperador.escribirCampoDireccion(datosEntrada[4]);
		    verOperador.escribirCampoCP(datosEntrada[5]);
		    verOperador.escribirCampoCiudad(datosEntrada[6]);
		    verOperador.escribirCampoEmail(datosEntrada[7]);
		    verOperador.escribirCampoPais(datosEntrada[8]);
		    verOperador.escribirCampoTelefono(datosEntrada[9]);
		    verOperador.seleccionarOpcionGrupoOP(datosEntrada[10]);
		    verOperador.escribirCampoFechaNacimiento(datosEntrada[11]);
		    verOperador.confirmar();
	   }
	   gestionOperadores.escribirOpcionOperador(operador);
	   gestionOperadores.busqueda();
	   int numFilasObt=gestionOperadores.numFilasTabla();
	   if (numFilasObt!=1) {
			logger.error("TC2 Modificar Operador: el n�mero de filas obtenidas en la tabla tras la b�squeda no es 1, es "+numFilasObt);
			LoginTest.majors++;
			fail();
	   }
	   else {
		   gestionOperadores.seleccionarFila(1);
		   gestionOperadores.vermodificar();
		   ModificarOperadorProcess verOperador=new ModificarOperadorProcess();
		   datosSalida[0]=verOperador.leerOpcionTitulo();
		   datosSalida[1]=verOperador.leerOpcionGenero();
		   datosSalida[2]=verOperador.leerCampoNombre();
		   datosSalida[3]=verOperador.leerCampoApellidos();
		   datosSalida[4]=verOperador.leerCampoDireccion();
		   datosSalida[5]=verOperador.leerCampoCP();
		   datosSalida[6]=verOperador.leerCampoCiudad();
		   datosSalida[7]=verOperador.leerCampoEmail();
		   datosSalida[8]=verOperador.leerCampoPais();
		   datosSalida[9]=verOperador.leerCampoTelefono();
		   datosSalida[10]=verOperador.leerOpcionGrupoOP();
		   datosSalida[11]=verOperador.leerCampoFechaNacimiento();
	       if (!RutinasTestComunes.assertArrayEquals(datosEntrada,datosSalida)) {
	    	   logger.error("TC2 Modificar Operador: El operador editado no coincide con el esperado");
	    	   LoginTest.majors++;
				fail();
	       }
	       verOperador.volver();
	       gestionOperadores.escribirOpcionOperador(operador);
		   gestionOperadores.busqueda();
		   numFilasObt=gestionOperadores.numFilasTabla();
		   if (numFilasObt!=1) {
				logger.error("TC2 Modificar Operador: El n�mero de filas obtenidas en la tabla tras la b�squeda no es 1, es "+numFilasObt);
				LoginTest.majors++;
				fail();
		   }
		   else {
			   String filatabla[]=new String[gestionOperadores.numColumnasTabla()];
			   String datosesperados[]=new String[gestionOperadores.numColumnasTabla()];
			   filatabla=gestionOperadores.obtenerFilaTabla(1);
			   datosesperados[0]=operador;
			   datosesperados[1]=datosSalida[0];
			   datosesperados[2]=datosSalida[2];
	           datosesperados[3]=datosSalida[3];
	           datosesperados[4]=filatabla[4];
	           datosesperados[5]=filatabla[5];
	           datosesperados[6]=datosSalida[10].replace(":", "-").replace("- ", "-");
		       if (!RutinasTestComunes.assertArrayEquals(datosesperados,filatabla)) {
		    	   logger.error("TC2 Modificar Operador: Los datos mostrados en la tabla para el operador editado no coinciden");
		    	   LoginTest.majors++;
				   fail();
		       }
		   }
	   }
	}
	
	@Test
	public void testModificarOperador3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Modificar Operador");
		logger.info("TC3 Modificar Operador: Probar los tooltips de validaci�n de fecha");
		logger.info("TC3 Modificar Operador: Resultado esperado=true (operaci�n correcta)");
		String[] datosEntrada={"Sr","Masculino","Pepe","P�rez","Diagonal 234, 3� 3�","08023","Barcelona","pepe@0x.com",
				"Espa�a","6457698","01: Cobradores","01/05/196"};
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Operadores","Gesti�n de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		String operador="00190";
		gestionOperadores.escribirOpcionOperador(operador);
		gestionOperadores.busqueda();
		if (gestionOperadores.numFilasTabla()!=1) {
			logger.error("TC3 Modificar Operador: el n�mero de filas obtenidas en la tabla tras la b�squeda es incorrecto");
			LoginTest.majors++;
			fail();
		}
		gestionOperadores.seleccionarFila(1);
		gestionOperadores.vermodificar();
		ModificarOperadorProcess verOperador=new ModificarOperadorProcess();
		verOperador.escribirCampoFechaNacimiento(datosEntrada[11]+"6");
		boolean saltaPantalla=verOperador.confirmar();
		boolean incorrecto=false;
		if (!saltaPantalla) {
		    incorrecto=verOperador.hayErrorValidacionFecha(); // Esto falla porque ya ha cambiado de pantalla
		    if (incorrecto) {
		    	LoginTest.minors++;
		    	logger.error("TC3 Modificar Operador: No deber�a haber aparecido ning�n tooltip de validaci�n de fecha");
		    }
		}
		else {  // Estamos en Gestion Operadores
			gestionOperadores.seleccionarFila(1);
			gestionOperadores.vermodificar();
			verOperador=new ModificarOperadorProcess();
		}
		verOperador.borrarCampoFecha();
		verOperador.confirmar();
		boolean hayMsgErrorReq=verOperador.hayErrorValidacionRequeridoFecha();
		if (!hayMsgErrorReq) {
			LoginTest.minors++;
			logger.error("TC3 Modificar Operador: Deber�a haber aparecido el tooltip de 'Campo requerido'");
		}
		verOperador.borrarCampoFecha();
		verOperador.confirmar();
		boolean hayMsgError=verOperador.hayErrorValidacionFormatoFecha();
		if (hayMsgError) {
			LoginTest.minors++;
			logger.error("TC3 Modificar Operador: Deber�a haber aparecido el tooltip de 'Campo requerido' y no el de 'Formato incorrecto'");
		}
		verOperador.escribirCampoFechaNacimiento(datosEntrada[11]);
		verOperador.confirmar();
		boolean hayMsgErrorFor=verOperador.hayErrorValidacionFormatoFecha();
		if (!hayMsgErrorFor) {
			LoginTest.minors++;
			logger.error("TC3 Modificar Operador: Deber�a haber aparecido el tooltip de 'Formato incorrecto'");
		}
		verOperador.escribirCampoFechaNacimiento(datosEntrada[11]);
		verOperador.confirmar();
		boolean hayMsgError2=verOperador.hayErrorValidacionRequeridoFecha();
		if (hayMsgError2) {
			LoginTest.minors++;
			logger.error("TC3 Modificar Operador: Deber�a haber aparecido el tooltip de 'Formato incorrecto' y no el de 'Campo requerido'");
		}
		logger.info("TC3 Modificar Operador: Resultado obtenido="+(saltaPantalla && !incorrecto && hayMsgErrorReq && hayMsgErrorFor && !hayMsgError && !hayMsgError2));
	}
}
