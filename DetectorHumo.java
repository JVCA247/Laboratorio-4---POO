public class DetectorHumo extends Dispositivo implements Alertar {

    public DetectorHumo(int codigo, int oficina, double potencial) {
        double consumo = potencial/1000;
        super(3, codigo, oficina, potencial, true, consumo);
    }

    @Override
    public Alerta alertar(double dato) {
        Alerta alerta = null;
        if((dato >= 150) & (dato < 300)){
            alerta = new Alerta(2, oficina, tipo);
        }
        if(dato > 300){
            alerta = new Alerta(3, oficina, tipo);
        }
        return alerta;
    }
    @Override
    public void mostrar(double actual){
        System.out.println("Dispositivo de detección de humo No. " + codigo);
        System.out.println("Instalado en oficina No." + oficina);
        System.out.println("Potencia nominal: " + potencial + " Watts");
        System.out.println("Consumo por hora: " + consumo + " kWh");
        double dia = consumo*actual;
        System.out.println("Consumo del día en curso: " + dia + " kWh");
        System.out.println("");
    }
}
