package A02_UP;

import java.util.Random;

//Classe principal que inicia a simulação
public class Main {
 public static void main(String[] args) {
     System.out.println("[SISTEMA] Iniciando simulação de produção de veículos...");

     Estoque estoque = new Estoque();

     // Cria esteiras de saída da fábrica (4 estações)
     EsteiraCircular<Carro>[] esteiras = new EsteiraCircular[4];
     for (int i = 0; i < 4; i++) {
         esteiras[i] = new EsteiraCircular<>(40);
         System.out.println("[SISTEMA] Esteira da estação " + i + " criada.");
     }

     // Inicia os funcionários (5 por estação)
     for (int i = 0; i < 4; i++) {
         for (int j = 0; j < 5; j++) {
             new Funcionario(j, i, estoque, esteiras[i]).start();
             System.out.println("[SISTEMA] Funcionario " + j + " da estação " + i + " iniciado.");
         }
     }

     // Cria 3 lojas
     Loja[] lojas = new Loja[3];
     for (int i = 0; i < 3; i++) {
         lojas[i] = new Loja("loja" + i);
         System.out.println("[SISTEMA] Loja " + lojas[i].nome + " criada.");
     }

     // Thread que envia os carros da fábrica para as lojas
     new Thread(() -> {
         while (true) {
             try {
                 for (int i = 0; i < 4; i++) {
                     Carro carro = esteiras[i].remover();
                     Loja lojaDestino = lojas[new Random().nextInt(3)];
                     lojaDestino.receberCarro(carro);
                 }
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
     }).start();

     // Inicia os 20 clientes
     for (int i = 0; i < 20; i++) {
         new Cliente("cliente" + i, lojas).start();
         System.out.println("[SISTEMA] Cliente cliente" + i + " iniciado.");
     }

     System.out.println("[SISTEMA] Simulação em execução. Acompanhe os logs no console e nos arquivos.");
 }
}
