package A02_UP;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

class Funcionario extends Thread {
    int id;
    int estacao;
    Estoque estoque;
    EsteiraCircular<Carro> esteira;
    static String[] cores = {"R", "G", "B"}; // cores alternadas
    static String[] tipos = {"SUV", "SEDAN"}; // tipos alternados
    static Random rand = new Random();

    // Construtor com ID do funcionário, estação, estoque e esteira de saída
    public Funcionario(int id, int estacao, Estoque estoque, EsteiraCircular<Carro> esteira) {
        this.id = id;
        this.estacao = estacao;
        this.estoque = estoque;
        this.esteira = esteira;
    }

    // Loop de produção contínuo do funcionário
    public void run() {
        while (true) {
            try {
                if (estoque.pegarPeca()) { // tenta pegar peça do estoque
                    String cor = cores[(int) (Math.random() * 3)];
                    String tipo = tipos[(int) (Math.random() * 2)];
                    Carro carro = new Carro(cor, tipo, estacao, id, new Random().nextInt(40));
                    esteira.adicionar(carro); // adiciona carro na esteira circular
                    logFabrica(carro); // grava no log da fábrica
                } else {
                    Thread.sleep(100); // espera se não há peça
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Gera o log de produção da fábrica
    public void logFabrica(Carro carro) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("log_fabrica.txt", true))) {
            writer.write(carro.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
