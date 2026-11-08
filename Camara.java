public class Camara extends Dispositivo implements Alertar {
    
    public Camara(int codigo, int oficina, double potencial) {
        double consumo = potencial;
        super(4, codigo, oficina, potencial, false, consumo);
    }

  
    @Override
    public Alerta alertar(double dato){
        Alerta alerta = null;
        if(dato > 0){
            alerta = new Alerta(2, oficina, tipo);
        }
        return alerta;
    }
    @Override
    public void mostrar(double actual){
        System.out.println("Dispositivo de camaras No. " + codigo);
        System.out.println("Instalado en oficina No." + oficina);
        System.out.println("Potencia nominal: " + potencial + " Watts");
        if(encendido){
            System.out.println("Estado: encendido");
        }
        else{
            System.out.println("Estado: apagado");
        }
        System.out.println("Consumo por hora: " + consumo + " kWh");
        double dia = consumo*actual;
        System.out.println("Consumo del día en curso: " + dia + " kWh");
        System.out.println("");
    }
}
