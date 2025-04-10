package A02_UP;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

class Estoque {
    Semaphore sem = new Semaphore(5); // limita a esteira a 5 acessos simultâneos
    AtomicInteger pecas = new AtomicInteger(500); // quantidade inicial de peças no estoque

    // Método para tentar pegar uma peça do estoque
    public boolean pegarPeca() {
        try {
            sem.acquire(); // tenta acessar a esteira
            if (pecas.get() > 0) {
                pecas.decrementAndGet(); // diminui o número de peças disponíveis
                return true;
            }
        } catch (InterruptedException ignored) {
        } finally {
            sem.release(); // libera o acesso à esteira
        }
        return false; // não conseguiu pegar peça
    }
}

