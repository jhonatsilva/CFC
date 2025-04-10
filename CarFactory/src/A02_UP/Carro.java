package A02_UP;

//Importa as bibliotecas necessárias
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

//Classe que representa um carro produzido
class Carro {
 static int count = 0; // contador global de veículos produzidos
 int id;
 String cor;
 String tipo;
 int idEstacao;
 int idFuncionario;
 int posicaoEsteira;

 // Construtor do carro com todos os atributos da cadeia produtiva
 public Carro(String cor, String tipo, int idEstacao, int idFuncionario, int posicaoEsteira) {
     this.id = ++count;
     this.cor = cor;
     this.tipo = tipo;
     this.idEstacao = idEstacao;
     this.idFuncionario = idFuncionario;
     this.posicaoEsteira = posicaoEsteira;
 }

 // Retorna os dados do carro em formato de texto para logging
 public String toString() {
     return "ID:" + id + ", Cor:" + cor + ", Tipo:" + tipo + ", Estacao:" + idEstacao + ", Funcionario:" + idFuncionario + ", Posicao:" + posicaoEsteira;
 }
}
