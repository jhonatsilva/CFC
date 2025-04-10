package A02_UP;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

class Cliente extends Thread {
    Loja[] lojas;
    String nome;
    static Random rand = new Random();

    public Cliente(String nome, Loja[] lojas) {
        this.nome = nome;
        this.lojas = lojas;
    }

    // Cliente tenta comprar carros aleatoriamente das lojas
    public void run() {
        while (true) {
            Loja loja = lojas[rand.nextInt(lojas.length)];
            try {
                Carro carro = loja.venderCarro(); // compra carro da loja
                logCompra(carro); // grava na garagem
            } catch (InterruptedException e) {
                try {
                    Thread.sleep(500); // espera se loja está vazia
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    // Gera log de compra do cliente (garagem)
    public void logCompra(Carro carro) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("garagem_" + nome + ".txt", true))) {
            writer.write("Comprou: " + carro);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}