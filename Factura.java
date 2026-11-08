public class Factura {
    private int nSAT;
    private int oficina;
    private double monto;
    private double credito;
    private double total;

    public Factura(int nSAT, int oficina, double monto, double credito){
        this.nSAT = nSAT;
        this.oficina = oficina;
        this.monto = monto;
        this.credito = credito;
        double resta = monto - credito;
        if(resta > 0){
            total = resta;
        }
        else total = 0;
    }
    public void setMonto(double monto) {
        this.monto = monto;
    }
    public double getMonto() {
        return monto;
    }
    public void setOficina(int oficina) {
        this.oficina = oficina;
    }
    public int getOficina() {
        return oficina;
    }
    public void setnSAT(int nSAT) {
        this.nSAT = nSAT;
    }
    public int getnSAT() {
        return nSAT;
    }
    public void mostrar(){
        System.out.println("Factura No." + nSAT);
        System.out.println("Emitida por oficina No. " + oficina);
        System.out.println("Monto del recargo: Q" + monto);
        System.out.println("Credito por atención de alerta: Q" + credito);
        System.out.println("Total pagado: " + total);
        System.out.println("");
    }
}
