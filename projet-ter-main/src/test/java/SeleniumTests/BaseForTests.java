package SeleniumTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;


public class BaseForTests {
    static WebDriver driver; //Interface, à l'exécution on utilise une instance de Firefox driver (qui est une implémentation de webDriver)
    // le webDriver communique directement avec Firefox
    String serverAdress="http://localhost:8080";   //l'adresse de serveur

    protected WebDriverWait wait;  //permet d'attendre que certaines conditions deviennent vraies, par exemple attendre la fin de chargement d'une page


    @BeforeAll //se déclanche une fois avant tout les tests
    static void setup() {
        WebDriverManager.firefoxdriver().setup();  //mettre en place le driver Firefox
    }

    @BeforeEach //avant chaque test
    void init() {
        driver = WebDriverManager.firefoxdriver().create();  //créé un driver firefox qu'elle range dans driver
        Duration d = Duration.ofSeconds(1);
        wait = new WebDriverWait(driver, d);//configure l'attente lors l'un wait 1second

    }

    public void waitElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element)); //attende qu'un élément de page soit chargé
    }

    public void click(WebElement element) {
        waitElement(element); //cliquer un element
        element.click();
    }

    public void write(WebElement element, String text) {
        waitElement(element);
        element.sendKeys(text); //écrire un text dans un élément web

    }

    public String read(WebElement element) {
        waitElement(element);
        return element.getText(); //lire un text
    }

    public  void screenshot(String fileName)
            throws IOException  //faire une capture d'écran
    {
        File File = ((TakesScreenshot)driver)
                .getScreenshotAs(OutputType.FILE);
        String home = System.getenv("HOME");
        FileUtils.copyFile(File,
                new File(home+"/tmp/selenium/"+ this.getClass().getName()+"-"+fileName + ".jpeg"));
    }

    void login(String userName, String password)  { //méthode spécifique à notre application
        driver.get(serverAdress+"/login");
        WebElement userNameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("[type=submit]")); //le submit n'a pas d'id donc on utilise le cssSelector

        write(userNameField, userName);
        write(passwordField, password);
        click(loginButton);
    }
}
