package elementosBase;

import org.apache.log4j.Logger;
import unidadesGraficas.Button;
import unidadesGraficas.Label;
import unidadesGraficas.TipoBy;

public class Grid extends GridEstatico {

	private Label leyenda_mostrado;
	private Button[] botones_grid;
	protected TipoBy i_cabecera;
	protected String str_cabecera;
	private int columna_inicial;
	private final static Logger logger = Logger.getLogger(Grid.class);
	
	public Grid(int num_columnas, TipoBy i_leyendamostrado, String str_leyendamostrado,
			TipoBy i_cabecera, String str_cabecera, TipoBy[] i_botones, String[] str_botones, int columna_inicial) 
					throws RuntimeException {
		
		super(num_columnas, i_cabecera, str_cabecera,columna_inicial);
		int num_botones=0;
		if (i_botones!=null && str_botones!=null) {
			num_botones=i_botones.length;
			if (i_botones.length!=str_botones.length) {
				logger.fatal("Grid: i_botones.length="+i_botones.length+"<>str_botones.str_botonescrud="+str_botones.length);
				throw new RuntimeException("Grid: i_botones.length="+i_botones.length+"<>str_botones.str_botonescrud="+str_botones.length);
			}
		}
		leyenda_mostrado=new Label(i_leyendamostrado,str_leyendamostrado);
		
		// Guardamos los siguientes valores para poderlos reusar cada ver que carguemos un nuevo grid
		this.i_cabecera=i_cabecera;
		this.str_cabecera=str_cabecera;
		this.columna_inicial=columna_inicial;

		this.num_columnas=num_columnas;
		this.botones_grid=new Button[num_botones];
		for (int i=0;i<num_botones;i++) {
			this.botones_grid[i]=new Button(i_botones[i],str_botones[i]);
		}
		this.recargarGrid();
	}

		
	public void recargarGrid() {
		cabecera=new Header(this.i_cabecera,this.str_cabecera,this.num_columnas,this.columna_inicial);
		int fila_inicial=this.getNumPrimeraFilaMostrada();
		try {
			if (fila_inicial>0) {
				this.num_filas=this.getNumFilasMostradas()-fila_inicial+1;
			}
			else {
				this.num_filas=0;
			}
		}
		catch (NumberFormatException e) {
			logger.error("Error en el parseo de la leyenda del grid de la tabla");
		}
		parseContentGrid(i_cabecera,str_cabecera,columna_inicial);
	}
	
	
	public String getLeyendaMostrado() {
		return leyenda_mostrado.getText();
	}
	
	public int getNumTotalFilas() {
		// Devuelve el número de filas total de la tabla
		return this.getUltimaFilaMostrada();
	}

	
	public int getNumPrimeraFilaMostrada() throws NumberFormatException {
		String leyenda=getLeyendaMostrado();
		String str_filas=leyenda.substring("Mostrando del ".length(),leyenda.length());
		int i2=str_filas.indexOf("al ");
        return Integer.parseInt(str_filas.substring(0, i2-1));
	}
	
	protected int getNumFilasMostradas() throws NumberFormatException {
		String leyenda=getLeyendaMostrado();
		String str_filas=leyenda.substring("Mostrando del ".length(),leyenda.length());
		int i2=str_filas.indexOf("al ");
		return Integer.parseInt(str_filas.substring(i2+3, str_filas.indexOf(" ",i2+3)));
	}
	
	private int getUltimaFilaMostrada() throws NumberFormatException {
		String leyenda=getLeyendaMostrado();
		String str_filas=leyenda.substring("Mostrando del ".length(),leyenda.length());
		int i3=str_filas.indexOf("de ");
		return Integer.parseInt(str_filas.substring(i3+3,str_filas.length()));
	}
	
	public boolean ultimoGrid() {
		//Devuelve true si ya no hay más elementos por mostrar en la tabla que los mostrados en el grid
		return (this.getUltimaFilaMostrada()==this.getNumFilasMostradas());
	}

	
	public void pulsarBoton(int i) {
		if (botones_grid[i].habilitado()) {
			botones_grid[i].click();
			this.recargarGrid(); //Cargamos de nuevo el grid ya que ha cambiado
		}
	}
	
	public boolean sintacticAnalysis(String[] labelTitulosColumnas, String[] labelBotonesGrid) {
		
		// Faltaría comprobar que tiene el número de filas que indica la leyenda
		
		boolean resultado=super.sintacticAnalysis(labelTitulosColumnas);
		String leyenda=getLeyendaMostrado();
		resultado=leyenda.substring(0,"Mostrando del ".length()).equals("Mostrando del ") && resultado;
        try {
        	int primer_numero=this.getNumPrimeraFilaMostrada();
        	int segundo_numero=this.getNumFilasMostradas();
        	int tercer_numero=this.getUltimaFilaMostrada();
        }
        catch (NumberFormatException e) {
        	resultado=false;
        }
		for (int i=0; (i<labelBotonesGrid.length); i++) {
			resultado=botones_grid[i].getValue().equals(labelBotonesGrid[i]) && resultado;
		}
		return resultado;
	}
}
