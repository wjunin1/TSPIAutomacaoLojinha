package pages;

import utils.DSL;

import static utils.Variaveis.senhaLojinha;
import static utils.Variaveis.usuarioLojinha;

public class LojinhaLogin {
    DSL dsl = new DSL();

    public void logar() {
        dsl.url("http://165.227.93.41/lojinha-web/");
        dsl.escreveId("usuario", usuarioLojinha);
        dsl.escreveId("senha", senhaLojinha);
        dsl.clickName("action");
    }
}
