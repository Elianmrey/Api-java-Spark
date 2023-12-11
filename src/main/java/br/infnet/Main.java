package br.infnet;

import br.infnet.controller.UsuarioController;
import spark.Spark;


public class Main {

    public static void main(String[] args) {

       UsuarioController usuarioController = new UsuarioController();

       usuarioController.respostaRequisicoes();

       System.out.println("Rodando em porta " + Spark.port());

 }

}