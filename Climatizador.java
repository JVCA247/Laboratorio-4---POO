public class Climatizador extends Dispositivo implements Regular, Alertar {
    private boolean inverter;

    public Climatizador(int codigo, int oficina, double potencial, boolean inverter) {
        double consumo = (potencial*(0.3+((0.7*50)/100)))/1000;
        if(inverter){
            consumo = consumo*0.8;
        }
        super(2, codigo, oficina, potencial, false, consumo);
        this.inverter = inverter;
    }

    public void setInverter(boolean inverter) {
        this.inverter = inverter;
    }

    public boolean getInverter() {
        return inverter;
    }

    @Override
    public void regular(int nivel) {
        setNivel(nivel);
        double nuevoConsumo = (potencial*(0.3+((0.7*nivel)/100)))/1000;
        if(inverter){
            nuevoConsumo = nuevoConsumo*0.8;
        }
        setConsumo(nuevoConsumo);
    }

    @Override
    public Alerta alertar(double dato) {
        Alerta alerta = null;
        if((dato > 28) & (dato < 32)){
            alerta = new Alerta(1, oficina, tipo);
        }
        if((dato >= 32) & (dato < 40)){
            alerta = new Alerta(2, oficina, tipo);
        }
        if(dato >= 40){
            alerta = new Alerta(3, oficina, tipo);
        }
        return alerta;
    }
    @Override
    public void mostrar(double actual){
        if(inverter){
            System.out.println("Dispositivo climatizador Inverter No. " + codigo);
        }
        else{
            System.out.println("Dispositivo climatizador regular No. " + codigo);
        }
        System.out.println("Dispositivo Luminaria No. " + codigo);
        System.out.println("Instalado en oficina No." + oficina);
        System.out.println("Potencia nominal: " + potencial + " kWtts");
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
