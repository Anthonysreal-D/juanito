package testFuncionales;

import java.util.List;
import java.util.Arrays;

public abstract class PageTest {

    // Método que devuelve los enlaces del menú principal de la web
    public List<String> obtenerMenuPrincipal() {
        return Arrays.asList(
            "La UPO", 
            "Estudiar", 
            "Investigar", 
            "Internacionalización", 
            "UPO Virtual"
        );
    }
}
