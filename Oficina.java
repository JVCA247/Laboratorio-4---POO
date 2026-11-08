public class Oficina {
    private int identificador;
    private String inquilino;
    public Dispositivo[] eDispositivo;
    private double promedio;
    //Dinero
    private double acumulado;
    private double saldo;

    public Oficina(int identificador, String inquilino) {
        this.identificador = identificador;
        this.inquilino = inquilino;
        eDispositivo = new Dispositivo[4];
        promedio = 0;
        acumulado = 0;
        saldo = 0;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getInquilino() {
        return inquilino;
    }

    public void setInquilino(String inquilino) {
        this.inquilino = inquilino;
    }

    public Dispositivo[] getEdispositivo() {
        return eDispositivo;
    }

    public void setEdispositivo() {
        eDispositivo = new Dispositivo[4];
    }
    public void setPromedio() {
        promedio = 0;
        for(int n = 0; n < 4; n++){
            if(eDispositivo[n] != null){
                if(eDispositivo[n].getEncendido()){
                    promedio = promedio + eDispositivo[n].getConsumo();                    
                }
            }
        }
    }
    public double getPromedio() {
        return promedio;
    }
    public void setAcumulado(double acumulado) {
        this.acumulado = acumulado;
    }
    public double getAcumulado() {
        return acumulado;
    }
    public void setSaldo(double masSaldo) {
        saldo = saldo + masSaldo;
    }
    public double getSaldo() {
        return saldo;
    }
    public void resetSaldo(){
        if(saldo > acumulado){
            saldo = saldo - acumulado;
        }
        else{
            saldo = 0;
        }
    }
    public void calcularAcumulado(double multiplicador){
        acumulado = acumulado + multiplicador*promedio;
    }
    public Dispositivo instalarDispositivo(int tipo, int codigo, double potencial, boolean inverter) {
        Dispositivo dispositivo = null;
        if(inverter){
            dispositivo = new Climatizador(codigo, identificador, potencial, true);
        }
        else{
            switch (tipo) {
                case 1:
                    dispositivo = new Luminaria(codigo, identificador, potencial);
                    break;
                case 2:
                    dispositivo = new Climatizador(codigo, identificador, potencial, false);
                    break;
                case 3:
                    dispositivo = new DetectorHumo(codigo, identificador, potencial);
                    break;
                case 4:
                    dispositivo = new Camara(codigo, identificador, potencial);
                    break;
            }
        }
        int posicion = tipo - 1;
        if((dispositivo != null) & (eDispositivo[posicion] == null)){
            eDispositivo[posicion] = dispositivo;
            setPromedio();
            System.out.println("Dispositivo agregado con éxito");
            eDispositivo[posicion].mostrar(0);
        }
        else{
            if(eDispositivo[posicion] != null){
                System.out.println("Ya hay un dispositivo de ese tipo instalado en la oficina");
            }
            else{
                System.out.println("No se ha podido instalar el dispositivo");
            }
        }
        return dispositivo;
    }
    public int buscarDispositivo(int codigo){
        int buscado = 4;
        for(int n = 0; n < 4; n++){
            if(eDispositivo[n] != null){
                if(eDispositivo[n].getCodigo() == codigo){
                    buscado = n;
                }
            }
        }
        if(buscado == 4){
            System.out.println("No hay ningún dispositivo con ese código registrado en esta oficina");
        }
        return buscado;
    }

    public boolean quitarDispositivo(int codigo) {
        boolean seQuito = false;
        int posicion = buscarDispositivo(codigo);
        if(posicion < 4){
            eDispositivo[posicion] = null;
            System.out.println("Dispositivo removido con éxito");
            seQuito = true;
            setPromedio();
        }
        else{
            System.out.println("El dispositivo no fue encontrado");
        }
        return seQuito;
    }

    public void verDispositivos(double actual) {
        for(int n = 0; n < 4; n++){
            if(eDispositivo[n] != null){
                eDispositivo[n].mostrar(actual);
            }
        }
    }
    public void onOff(boolean luz, boolean clima, boolean camara){
        int posicion = 4;
        if(luz){
            posicion = 0;
        }
        if(clima){
            posicion = 1;
        }
        if(camara){
            posicion = 3;
        }
        if(posicion < 4){
            if(eDispositivo[posicion] != null){
                if(eDispositivo[posicion].getEncendido()){
                    eDispositivo[posicion].setEncendido(false);
                }
                else{
                    eDispositivo[posicion].setEncendido(true);
                }
            }
        }
        setPromedio();
    }
    public void regulacion(int cual, int nivel){
        if(eDispositivo[cual] != null){
            Regular regulable = (Regular) eDispositivo[cual];
            regulable.regular(nivel);
            setPromedio();
            System.out.println("Dispositivo regulado");
        }
        else{
            System.out.println("No hay ningún dispositivo de ese tipo registrado");
        }
    }
    public Alerta alertador(int donde, double dato){
        Alerta alerta = null;
        if(eDispositivo[donde] != null){
            Alertar alertante = (Alertar) eDispositivo[donde];
            alerta = alertante.alertar(dato);
        }
        else{
            System.out.println("No hay ningún dispositivo de ese tipo registrado");
        }
        return alerta;
    }
    public void mostrar(double actual){
        System.out.println(inquilino);
        System.out.println("Oficina No. " + identificador);
        System.out.println("Por hora consume: " + promedio + "kWh");
        System.out.println("El día de hoy tienen un monto por consumo acumulado de: Q" + acumulado);
        System.out.println("Saldo a favor: Q" + saldo);
        System.out.println("Tiene los siguientes dispositivos asociados:");
        verDispositivos(actual);
    }
}
