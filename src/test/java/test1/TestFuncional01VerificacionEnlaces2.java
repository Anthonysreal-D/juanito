import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Clase que realiza pruebas de navegación en el sitio web de la UPO.
 */
public class testFuncional01VerificacionEnlaces2 {

    private WebDriver navegador;

    // Configuración inicial
    @BeforeEach
    public void configurarNavegador() {
    	System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        // Configura Chrome en modo headless
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Modo sin interfaz gráfica
        options.addArguments("--disable-gpu"); // Desactiva GPU
        options.addArguments("--no-sandbox"); // Necesario para entornos Linux
        options.addArguments("--disable-dev-shm-usage"); // Previene errores de memoria compartida
        options.addArguments("--remote-allow-origins=*");
        // Inicia el navegador Chrome
        WebDriver navegador = new ChromeDriver(options);
    }

    // Prueba inicial de funcionamiento  
    @Test
    public void cargaPaginaInicio() {
        String urlEsperada = "https://www.upo.es/portal/impe/web/portada/index.html";
        
        // Navegar a la URL
        navegador.get(urlEsperada);
        String urlActual = navegador.getCurrentUrl();
        
        // Asegurarse de que la página se ha abierto correctamente
        assertTrue(urlActual.contains("upo.es"), 
            "La página se ha abierto correctamente");
    
        // Salida
        if (urlActual.equals(urlEsperada)) {
            System.out.println("Enlace correcto: " + urlEsperada);
        } else {
            System.out.println("Error en el enlace: " + urlEsperada);
        }
    }

    // Cierra el navegador después de cada prueba
    @AfterEach
    public void cerrarNavegador() {
        if (navegador != null) {
            navegador.quit();
        }
    }
}
