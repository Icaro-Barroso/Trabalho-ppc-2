import java.util.concurrent.Semaphore;

public class main {
    static Semaphore caldeirao = new Semaphore(5);
    public static void main(String[] args) {

        System.out.println("MAIN");
        Object travaCanibal = new Object();
        Object travaCozinheiro = new Object();

        cozinheiro cozinheiro = new cozinheiro(travaCanibal,travaCozinheiro);

        Thread tc = new Thread(cozinheiro);

        tc.start();

        canibal can1 = new canibal("1", travaCozinheiro, travaCanibal);
        canibal can2 = new canibal("2", travaCozinheiro, travaCanibal);
        canibal can3 = new canibal("3", travaCozinheiro, travaCanibal);

        Thread t1 = new Thread(can1);
        Thread t2 = new Thread(can2);
        Thread t3 = new Thread(can3);

        t1.start();
        t2.start();
        t3.start();
        try {
            Thread.sleep(2*60*1000);
            t1.interrupt();
            t2.interrupt();
            t3.interrupt();

            System.out.println(can1.qtd_comidos+"\t"+can2.qtd_comidos+"\t"+can3.qtd_comidos+"\t"+cozinheiro.refei_preparadas);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
