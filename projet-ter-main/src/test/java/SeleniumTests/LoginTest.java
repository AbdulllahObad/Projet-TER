package SeleniumTests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseForTests {

    @Test
    void successfulLogin()  {
        login("Chef", "mdp");
        assertTrue(driver.getTitle().contains("Page des TER de M1"));
    }

    @Test
    void passwordError(){
        login("Chef", "erreur");
        WebElement  error= driver.findElement(By.className("alert-danger"));
        String errorText=read(error);
        assertTrue(errorText.contains("Bad credentials"));
    }

    // On note en passant que l'authentification, en cas de login erroné (ce serait un test à inclure ici),
    // affiche une vilaine exception ce qui n'est ni très sérieux ni très raisonnable,
    // il faudrait également renvoyer un bad credentials ...
}