package SeleniumTests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClassDeTest extends BaseForTests{


    //1. un utilisateur se logue avec des identifiants corrects d'enseignant2. il clique sur Teachers list3. il clique sur Add Teacher4. Une page d'erreur est obtenue (titre="Error")5. si on retourne à la liste des enseignants, la liste est inchangée.Scénario 2 : l'ajout d'un nouvel enseignant est possible pour un utilisateur TERManager1. un utilisateur se logue avec des identifiants corrects de gestinnaire de TER2. Il clique sur Teacher List3. Il clique sur Add Teacher4. Il saisit le prénom "TeacherfirstnameforTest" et le nom "TeacherLastNameForTest"5. Il clique sur le bouton create.6. La page chargée est la liste des enseignants (titre = "Teacher list"), on y trouve la chaîne"TeacherLastNameForTest"7. On réalise une capture d'écran.Deuxième étape : placer les tests dans le projet de TER.Pour la première étape, les tests ont été écrits dans un projet séparé de celui du ter, et pour les lancer, ilfaut au préalable exécuter manuellement le projet de TER.Nous allons maintenant placer les tests selenium dans le projet de TER, et paramétrer Maven pour que :Quand on exécute maven test, les tests unitaires et d'intégration s'exécutent, mais pas les testsseleniumQuand on exécute maven verify, le serveur se lance, puis les tests selenium sont exécutés, puis leserveur s'éteint.Travail à réaliser
    @Test
    void Scenario1(){
        login("pra", "pra");
        assertTrue(driver.getTitle().contains("Page des TER de M1"));

        WebElement TeachersList = driver.findElement(By.id("TeachersList"));
        click(TeachersList);

        WebElement addTeacher = driver.findElement(By.id("addTeachers"));
        click(addTeacher);

        assertTrue(driver.getTitle().contains("Error"));
        WebElement BackHome = driver.findElement(By.id("BackHome"));
        click(BackHome);

        WebElement TeachersList1 = driver.findElement(By.id("TeachersList"));
        click(TeachersList1);
    }

    @Test
    void Scenario2() throws IOException {
        login("Chef", "mdp");
        assertTrue(driver.getTitle().contains("Page des TER de M1"));

        click(driver.findElement(By.id("TeachersList")));

        click(driver.findElement(By.id("addTeachers")));
        assertFalse(driver.getTitle().contains("Error"));

        WebElement firstNameField = driver.findElement(By.id("firstName"));
        WebElement lastNameField = driver.findElement(By.id("lastName"));
        WebElement creatButton = driver.findElement(By.cssSelector("[type=submit]"));

        write(firstNameField, "TeacherfirstnameforTest");
        write(lastNameField, "TeacherlastnameforTest");
        click(creatButton);
        assertTrue(driver.getTitle().contains("Teacher List"));
        assertTrue(driver.getPageSource().contains("TeacherfirstnameforTest"));
        assertTrue(driver.getPageSource().contains("TeacherlastnameforTest"));
        screenshot("test");
    }
}
