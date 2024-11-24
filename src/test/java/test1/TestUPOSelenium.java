package test1;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestUPOSelenium {

    @Test
    public void testTituloPagina() {
        // Configura la ubicación de ChromeDriver
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        // Configura Chrome en modo headless
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Modo sin interfaz gráfica
        options.addArguments("--disable-gpu"); // Desactiva GPU
        options.addArguments("--no-sandbox"); // Necesario para entornos Linux
        options.addArguments("--disable-dev-shm-usage"); // Previene errores de memoria compartida
        options.addArguments("--remote-allow-origins=*");
        // Inicia el navegador Chrome
        WebDriver driver = new ChromeDriver(options);

        try {
            // Navega a la página de la UPO
            driver.get("https://www.upo.es");

            // Obtén el título de la página
            String tituloPagina = driver.getTitle();
            System.out.println("El título de la página es: " + tituloPagina);

            // Verifica que el título contenga "UPO"
            assertTrue(tituloPagina.contains("Universidad"), "El título debería contener 'UPO'");
        } finally {
            driver.quit();
        }
    }
}
