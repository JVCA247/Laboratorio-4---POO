public class Luminaria extends Dispositivo implements Regular {

    public Luminaria(int codigo, int oficina, double potencial) {
        double consumo = potencial/1000;
        super(1, codigo, oficina, potencial, false, consumo);
    }

    @Override
    public void regular(int nivel) {
        setNivel(nivel);
        double nuevoConsumo = (potencial*nivel)/100000;
        setConsumo(nuevoConsumo);
    }
    @Override
    public void mostrar(double actual){
        System.out.println("Dispositivo Luminaria No. " + codigo);
        System.out.println("Instalado en oficina No." + oficina);
        System.out.println("Potencia nominal: " + potencial + " Watts");
        if(encendido){
            System.out.println("Estado: encendido");
        }
        else{
            System.out.println("Estado: apagado");
        }
        System.out.println("Nivel actual: " + nivel);
        System.out.println("Consumo por hora: " + consumo + " kWh");
        double dia = consumo*actual;
        System.out.println("Consumo del día en curso: " + dia + " kWh");
        System.out.println("");
    }
}
