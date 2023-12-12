public class Monitor {
    protected int vez = 0;

    public synchronized void acordaTodas() {
        this.notifyAll();
    }

    public synchronized void acorda() {
        this.notify();
    }

    public synchronized void espera() {
        try {
            this.wait();
        } catch (InterruptedException ie) {
        }
    }

    public synchronized void defineVez(int vez) {
        this.vez = vez;
    }

    public synchronized int lerVez() {
        return this.vez;
    }
}
