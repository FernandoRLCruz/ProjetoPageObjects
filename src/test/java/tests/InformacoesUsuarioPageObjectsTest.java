package tests;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.LoginPage;
import suporte.Web;

import static org.junit.Assert.assertEquals;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuariosPageObjects.csv")
public class InformacoesUsuarioPageObjectsTest {

    private WebDriver navegador;

    @Rule
    public TestName test = new TestName();

    @Before
    public void setUp(){

        navegador = Web.createBrowserStack();



    }

    @Test
    public void testAdicionarUmInformacaoAdicionalDoUsuario
            (
            @Param(name="usuario") String usuario,
            @Param(name="senha") String senha,
            @Param(name="tipo") String tipo,
            @Param(name="valor") String valor,
            @Param(name="mensagem") String mensagem
            )
    {

        String textoToast = new LoginPage(navegador)
                .clickSignIn()
                .fazerLogin(usuario, senha)
                .clicarMe()
                .clicarLinkMoreData()
                .clicarBotaoAddMoreData()
                .adicionarContato(tipo, valor)
                .capturarTextoDoToast();

        if (tipo.contains("Phone"))
            assertEquals(mensagem,textoToast);
        else
            assertEquals(mensagem,textoToast);
    }

    @After
    public void tearDown(){
        //Fechar o navegador
        navegador.quit();

    }

}
