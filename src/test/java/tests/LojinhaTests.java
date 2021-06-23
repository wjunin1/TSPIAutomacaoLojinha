package tests;

import com.relevantcodes.extentreports.LogStatus;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LojinhaLogin;
import utils.DSL;
import utils.Screenshot;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static utils.DriverFactory.getDriver;
import static utils.DriverFactory.killDriver;
import static utils.Variaveis.*;

public class LojinhaTests extends Screenshot {

    DSL dsl;
    LojinhaLogin logar = new LojinhaLogin();

    @BeforeClass
    public void setUp() {
        getDriver();
        dsl = new DSL();
        logar.logar();
    }

    @BeforeMethod
    public void aftMet(){
        getDriver().get("http://165.227.93.41/lojinha-web/produto");
    }

    @AfterClass
    public void Close() {
        killDriver();
    }

    @Test
    public void cenario01_logarLojinha() {
        test = extentReports.startTest("cenario01_logarLojinha");
        String usuarioAtivo = getDriver().findElement(By.xpath("//*[@id=\"nav-mobile\"]/li[1]/a")).getText();
        assertEquals(msgBoasVindasLojinha, usuarioAtivo);
    }

    @Test
    public void cenario02_cadastrarProduto() throws IOException {
        test = extentReports.startTest("cenario02_cadastrarProduto");
        getDriver().findElement(By.xpath("/html/body/div[2]/div/div/a")).click(); //cadastrar produto
        test.log(LogStatus.PASS, "Tela de Produtos");
        getDriver().findElement(By.id("produtonome")).sendKeys(produtoLojinha);
        test.log(LogStatus.PASS, "Nome do Produto");
        getDriver().findElement(By.id("produtovalor")).sendKeys(precoProdutoLojinha);
        test.log(LogStatus.PASS, "Preço do Produto");
        getDriver().findElement(By.id("produtocores")).sendKeys(corProdutoLojinha);
        test.log(LogStatus.PASS, "Cor do Produto");
        getDriver().findElement(By.name("action")).click();
        test.log(LogStatus.PASS, "Salvar Produto");
        test.log(
                LogStatus.INFO,
                "Produto Adicionado com sucesso: "
                        + test.addScreenCapture(Screenshot.screen(getDriver(), "teste")));
        String validacao = getDriver().findElement(By.cssSelector(".toast")).getText();
        assertEquals(msgProdutoAdicionadoComSucesso, validacao);
        test.log(LogStatus.PASS, "Mensagem Validada");
    }

    @Test
    public void cenario03_alterarProduto() {
        test = extentReports.startTest("cenario03_alterarProduto");
        getDriver().findElements(By.linkText("PS4")).get(0).click();
        getDriver().findElement(By.id("produtovalor")).clear();
        getDriver().findElement(By.id("produtovalor")).sendKeys("550000");
        getDriver().findElements(By.className("btn")).get(0).click();

        String validacao = getDriver().findElement(By.cssSelector(".toast")).getText();
        assertEquals(msgProdutoAlteradoComSucesso, validacao);
    }

    @Test
    public void cenario04_adicionarComponentes() throws InterruptedException {
        test = extentReports.startTest("cenario04_adicionarComponentes");
        getDriver().findElement(By.linkText("PS4")).click();
        getDriver().findElement(By.xpath("/html/body/div[2]/div/div/div[3]/div[1]/a")).click();
        Thread.sleep(1000);
        getDriver().findElement(By.id("componentenomeadicionar")).sendKeys("Fone");
        getDriver().findElement(By.id("componentequantidadeadicionar")).sendKeys("1");
        getDriver().findElement(By.xpath("//*[@id=\"novocomponente\"]/div/div[4]/a[1]")).click(); // salva componente
    }
}