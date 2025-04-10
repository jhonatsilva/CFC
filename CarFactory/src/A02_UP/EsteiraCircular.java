package A02_UP;

class EsteiraCircular<T> {
    private final Object[] buffer;
    private int head = 0, tail = 0, count = 0;

    public EsteiraCircular(int size) {
        buffer = new Object[size];
    }

    // Adiciona um item na esteira (espera se estiver cheia)
    public synchronized void adicionar(T item) throws InterruptedException {
        while (count == buffer.length) wait();
        buffer[tail] = item;
        tail = (tail + 1) % buffer.length;
        count++;
        notifyAll();
    }

    // Remove um item da esteira (espera se estiver vazia)
    public synchronized T remover() throws InterruptedException {
        while (count == 0) wait();
        T item = (T) buffer[head];
        head = (head + 1) % buffer.length;
        count--;
        notifyAll();
        return item;
    }
}
