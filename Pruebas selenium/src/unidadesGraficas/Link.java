package unidadesGraficas;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Link extends Element {
		
	    private final static Logger logger = Logger.getLogger(Link.class);
	
		public Link(TipoBy i, String str) {
			super(i,str);
		}
		
		public void click() {
			try {
				WebDriverWait wait = new WebDriverWait(this.driver(), 10);
				WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by()));
				webElement().click();
			}
			catch (Exception e) {
				logger.fatal("Error al pulsar un link");
				System.exit(0);
			}
		}
		
		public String getText() {
			try {
				WebDriverWait wait = new WebDriverWait(this.driver(), 10);
				WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by()));
				return webElement().getText();
			}
			catch (Exception e) {
				logger.fatal("Error al obtener texto de un link");
				return null;
			}
		}
		
		public void seleccionarPuntero() {
			Actions action = new Actions(this.driver());
			try {
				WebDriverWait wait = new WebDriverWait(this.driver(), 10);
				WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by()));
				action.moveToElement(element).build().perform();
			} catch (Exception e) {
				logger.fatal("Error al seleccionar link");
				System.exit(0);
			}
		}
		
		public boolean sintacticAnalysis(String label) {
			WebDriverWait wait = new WebDriverWait(this.driver(), 10);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by()));
			boolean resultado=this.getText().equals(label);
			if (!resultado) {
				logger.error("Error sintáctico en Link: se esperaba '"+label+"' y se ha encontrado '"+this.getText()+"'");
			}
			return resultado;
		}
}
