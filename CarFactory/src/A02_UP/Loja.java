package A02_UP;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class Loja {
    String nome;
    EsteiraCircular<Carro> esteira = new EsteiraCircular<>(40); // esteira da loja

    public Loja(String nome) {
        this.nome = nome;
    }

    // Recebe carro da fábrica e armazena na esteira
    public void receberCarro(Carro carro) {
        try {
            esteira.adicionar(carro);
            logLoja(carro); // grava log da loja
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Log de recebimento de veículo na loja
    public void logLoja(Carro carro) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("log_" + nome + ".txt", true))) {
            writer.write(carro.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Cliente compra carro da loja (remove da esteira)
    public Carro venderCarro() throws InterruptedException {
        return esteira.remover();
    }
}