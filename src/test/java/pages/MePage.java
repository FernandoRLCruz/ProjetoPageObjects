package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MePage extends BasePage {


    public MePage(WebDriver navegador) {
        super(navegador);
    }

    public MePage clicarLinkMoreData(){

        navegador.findElement(By.linkText("More data about you".toUpperCase())).click();

        return this;

    }

    public AddContactPage clicarBotaoAddMoreData(){

        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        return new AddContactPage(navegador);

    }

}
