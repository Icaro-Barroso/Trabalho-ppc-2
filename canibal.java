public class canibal implements Runnable {

    String nome;
    Object travaCozinheiro;
    Object travaCanibal;
    static int ativos = 3;

    int qtd_comidos = 0;

    public canibal(String nome, Object travaCozinheiro, Object travaCanibal){
        this.nome = nome;
        this.travaCozinheiro = travaCozinheiro;
        this.travaCanibal = travaCanibal;
    }

    @Override
    public void run() {
        while(true){


            try {
                if(servir()) { // Verifica servir
                    comer(); // Come
                }else{
                    try{
                        synchronized (travaCanibal) {
                            canibal.ativos--;
                            if(canibal.ativos == 0) {
                                acordarCozinheiro();
                            }
                            System.out.println("Canibal " + nome + " dormindo"); // Aviso de dormir caso não consiga servir
                            travaCanibal.wait();
                            canibal.ativos++;
                            System.out.println("Canibal " + nome + " acordando"); // Acorda depois de preparar jantar
                        }
                    }catch (Exception e){

                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // restore interrupted status
                break;
            }


        }
    }

    private synchronized boolean servir() throws InterruptedException {
            if(main.caldeirao.tryAcquire()){
                System.out.println("Canibal "+nome+ " servindo");
                Thread.sleep(1000);
                return true;
            }
            return false;
    }

    private void comer() throws InterruptedException {
        synchronized (this) {
            System.out.println("Canibal " + nome + " comendo");
        }
        Thread.sleep(3000);
        this.qtd_comidos++;
    }

    private void acordarCozinheiro(){
        synchronized (travaCozinheiro) {
            travaCozinheiro.notify();
        }
    }
}
