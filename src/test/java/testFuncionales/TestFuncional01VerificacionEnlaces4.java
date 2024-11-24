package testFuncionales;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.List;

/**
 * Clase que realiza pruebas de navegación en el sitio web de la UPO.
 */
public class TestFuncional01VerificacionEnlaces4 extends PageTest {

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
        navegador = new ChromeDriver(options);
    }

    // Prueba para verificar que los enlaces funcionan correctamente
    @Test
    public void verificarEnlacesMenuPrincipal() {
        String urlBase = "https://www.upo.es"; // URL base de la UPO

        // Navegar a la página principal
        navegador.get(urlBase);

        // Obtener la lista desde PageTest
        List<String> enlacesEsperados = obtenerMenuPrincipal();

        // Verificar que cada nombre de enlace lleva a la URL esperada
        for (String nombreEnlace : enlacesEsperados) {
            // Buscar el enlace por su texto
            WebElement enlace = navegador.findElement(By.linkText(nombreEnlace));
            
            // Obtener el href del enlace (la URL de destino)
            String urlEsperada = enlace.getAttribute("href");

            // Hacer clic en el enlace para verificar la redirección
            enlace.click();

            // Esperar un momento para que la página cargue completamente
            try {
                Thread.sleep(2000); // Esperar 2 segundos (puedes mejorar esto usando WebDriverWait)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Obtener la URL actual después de hacer clic
            String urlActual = navegador.getCurrentUrl();

            // Comprobar si la URL actual contiene la URL esperada
            assertTrue(urlActual.contains(urlEsperada),
                    "El enlace '" + nombreEnlace + "' no redirige correctamente a: " + urlEsperada);
            
            // Salida
            if (urlActual.equals(urlEsperada)) {
                System.out.println("Enlace correcto: " + urlEsperada);
            } else {
                System.out.println("Error en el enlace: " + urlEsperada);
            
            }
            // Volver a la página principal
            navegador.navigate().back();
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
