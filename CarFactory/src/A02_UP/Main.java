package A02_UP;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Estoque estoque = new Estoque();

        // Cria esteiras de saída da fábrica (4 estações)
        EsteiraCircular<Carro>[] esteiras = new EsteiraCircular[4];
        for (int i = 0; i < 4; i++) esteiras[i] = new EsteiraCircular<>(40);

        // Inicia os funcionários (5 por estação)
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                new Funcionario(j, i, estoque, esteiras[i]).start();
            }
        }

        // Cria 3 lojas
        Loja[] lojas = new Loja[3];
        for (int i = 0; i < 3; i++) lojas[i] = new Loja("loja" + i);

        // Thread que envia os carros da fábrica para as lojas
        new Thread(() -> {
            while (true) {
                try {
                    for (int i = 0; i < 4; i++) {
                        Carro carro = esteiras[i].remover();
                        lojas[new Random().nextInt(3)].receberCarro(carro);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // Inicia os 20 clientes
        for (int i = 0; i < 20; i++) {
            new Cliente("cliente" + i, lojas).start();
        }
    }
}
