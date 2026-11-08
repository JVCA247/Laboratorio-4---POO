public class Alerta {
    private int secuencial;
    private int gravedad;
    private boolean pendiente;
    private int emisor;
    private int tipo;
    private double credito;
    private double sumado;
    private double tiempo;

    public Alerta(int gravedad, int emisor, int tipo) {
        secuencial = 0;
        this.gravedad = gravedad;
        pendiente = true;
        this.emisor = emisor;
        this.tipo = tipo;
        credito = 0;
        sumado = 0;
        tiempo = 0;
    }

    public void setSecuencial(int secuencial) {
        this.secuencial = secuencial;
    }
    public int getSecuencial() {
        return secuencial;
    }
    public void setGravedad(int gravedad) {
        this.gravedad = gravedad;
    }
    public int getGravedad() {
        return gravedad;
    }
    public void setPendiente(boolean pendiente) {
        this.pendiente = pendiente;
    }
    public boolean isPendiente() {
        return pendiente;
    }
    public void setEmisor(int emisor) {
        this.emisor = emisor;
    }
    public int getEmisor() {
        return emisor;
    }
    public double getCredito() {
        return credito;
    }
    public void setCredito(double credito) {
        this.credito = credito;
    }
    public void setSumado(double sumado) {
        this.sumado = sumado;
    }
    public double getSumado() {
        return sumado;
    }
    public double getTiempo() {
        return tiempo;
    }
    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }
    public void simular(double horas){
        tiempo = tiempo + horas;
        switch (gravedad) {
            case 1:
                if(tiempo < 24){
                    credito = credito + 0.5*(horas - 24);
                }
                break;
            case 2:
                if(tiempo < 2){
                    credito = credito + 2*(horas - 2);
                }
                break;
            case 3:
                if(tiempo < 1){
                    credito = credito + 4*(horas - 1);
                }
                break;
        }
        sumado = sumado + credito;
    }
    public void mostrar(){
        switch (gravedad) {
            case 1:
                System.out.println("Alerta baja No. " + secuencial);
                break;
            case 2:
                System.out.println("Alerta alta No. " + secuencial);
                break;
            case 3:
                System.out.println("Alerta crítica No. " + secuencial);
                break;
        }
        System.out.println("Emitida por la oficina No. " + emisor);
        switch (tipo) {
            case 2:
                System.out.println("Fue emitida por temperatura");
                break;
            case 3:
                System.out.println("fue emitida por concentración de humo");
                break;
            case 4:
                System.out.println("Fue emitida por movimiento");
                break;
        }
        System.out.println("Lleva " + tiempo + "horas sin ser atendida");
        System.out.println("Saldo consedido: Q" + sumado);
    }
}
