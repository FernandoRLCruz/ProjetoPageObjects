package tests;

import static org.junit.Assert.*;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Generator;
import suporte.Screenshot;
import suporte.Web;

import java.util.concurrent.TimeUnit;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InfUsuariosTestData.csv")
public class InformacoesUsuarioTest {

    private WebDriver navegador;

    @Rule
    public TestName test = new TestName();

    @Before
    public void setUp(){

        navegador = Web.createChrome();

        //clicar no link Sign in
        navegador.findElement(By.linkText("Sign in")).click();

        //Identificando o formulario de Login
        WebElement formularioSignInBox = navegador.findElement(By.id("signinbox"));

        //Digitar no campo com name "login" que está dentro de do formulario de id=signinbox o texto "fernando_cruz"
        formularioSignInBox.findElement(By.name("login")).sendKeys("fernando_cruz");

        //Digitar no campo com name "password" que está dentro de do formulario de id=signinbox o texto "123456"
        formularioSignInBox.findElement(By.name("password")).sendKeys("123456");

        //clicar no link SIGN IN
        navegador.findElement(By.linkText("SIGN IN")).click();

        //Clicar no link que possui a class "me"
        navegador.findElement(By.className("me")).click();

        //Clicar no link que possui texto = "More data about you"
        navegador.findElement(By.linkText("More data about you".toUpperCase())).click();

    }



    @Test
    public void testAdicionarUmInformacaoAdicionalDoUsuario(@Param(name="tipo") String tipo, @Param(name="contato") String contato, @Param(name="mensagem") String msgEperada){

            //Clicar no botão atraves do seu xpath=//button[@data-target="addmoredata"]
            navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

            //Identificar a popup onde esta o formulario de id addmoredata
            WebElement popupElement = navegador.findElement(By.id("addmoredata"));

            //Na combobox de name "type" escolher a opção "Phone"
            WebElement campoType = popupElement.findElement(By.name("type"));
            new Select(campoType).selectByVisibleText(tipo);

            //Digitar no elemento Contact atraves do name="contact" digita="+558199999999"
            popupElement.findElement(By.name("contact")).sendKeys(contato);

            //Clicar no link no texto "SAVE" na popup
            popupElement.findElement(By.linkText("SAVE")).click();

            //Validar a msg no elemento de id="toast-container" a msg="Your contact has been added!"
            String valueCompare = msgEperada;
            String campoMsg = navegador.findElement(By.id("toast-container")).getText();

            assertEquals(campoMsg, valueCompare);
    }

    @Test
    public void removerUmContatoDeUmUsuario()
    {

       //Clicar no elemento de email responsavel pela exclusão do item teste@teste.com.br
        navegador.findElement(By.xpath("//span[text()=\"teste@teste.com.br\"]/following-sibling::a")).click();

        //Confimar a janela javascript
        navegador.switchTo().alert().accept();
        Screenshot.tirar(navegador, "/home/crazydogretro/IdeaProjects/evidencia"
                + "_" + Generator.dataHoraParaArquivo() + "_" + test.getMethodName() + ".png");


        //Validar a msg no elemento de id="toast-container" a msg="Rest in peace, dear phone!"
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String valueCompare = "Rest in peace, dear email!";
        String campoMsg = mensagemPop.getText();

        //Aguardar ate 10 secundos para a desaparecer
        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.stalenessOf(mensagemPop));

        //Efetuar Logout clicar no link com texto = "Logout"
        navegador.findElement(By.linkText("Logout")).click();



        assertEquals(campoMsg, valueCompare);



    }

    @After
    public void tearDown(){
        //Fechar o navegador
        navegador.quit();

    }


}
