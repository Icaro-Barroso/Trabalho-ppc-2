public class cozinheiro implements Runnable {
    Object travaCozinheiro;
    Object travaCanibal;

    int refei_preparadas = 1;

    public cozinheiro(Object travaCanibal, Object travaCozinheiro){
        this.travaCozinheiro = travaCozinheiro;
        this.travaCanibal = travaCanibal;
    }

    @Override
    public void run() {
        while (true){
            try {
                synchronized (travaCozinheiro) {

                    System.out.println("\tCozinheiro dormiu");
                    travaCozinheiro.wait();
                    System.out.println("\tCozinheiro acordou");
                }
                prepararJanta();
                synchronized (travaCanibal){
                    travaCanibal.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void prepararJanta() throws InterruptedException {
        Thread.sleep(5000);
        this.refei_preparadas++;
        System.out.println("Cozinheiro preparou o jantar");
        main.caldeirao.release(5);
    }



}
