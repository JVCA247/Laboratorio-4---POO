import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
    private ArrayList<Alerta> bajas;
    private ArrayList<Alerta> altas;
    private ArrayList<Alerta> criticas;
    private ArrayList<Oficina> vOficina;
    private double hora;
    private ArrayList<Factura> facturas;
    private int identificadorM;
    private int codigoM;
    private int secuencialM;
    private int nSATM;


    public Sistema() {
        bajas = new ArrayList<Alerta>();
        altas = new ArrayList<Alerta>();
        criticas = new ArrayList<Alerta>();
        vOficina = new ArrayList<Oficina>();
        hora = 0;
        facturas = new ArrayList<Factura>();
        identificadorM = 1000;
        codigoM = 1000;
        secuencialM = 1000;
        nSATM = 1000;
    }

    public void setAltas(ArrayList<Alerta> altas) {
        this.altas = altas;
    }
    public ArrayList<Alerta> getAltas() {
        return altas;
    }
    public void setBajas(ArrayList<Alerta> bajas) {
        this.bajas = bajas;
    }
    public ArrayList<Alerta> getBajas() {
        return bajas;
    }
    public void setCriticas(ArrayList<Alerta> criticas) {
        this.criticas = criticas;
    }
    public ArrayList<Alerta> getCriticas() {
        return criticas;
    }

    public ArrayList<Oficina> getvOficina() {
        return vOficina;
    }

    public void setvOficina(ArrayList<Oficina> vOficina) {
        this.vOficina = vOficina;
    }
    public void setHora(double hora) {
        this.hora = hora;
    }
    public double getHora() {
        return hora;
    }
    public void setCodigoM(int codigoM) {
        this.codigoM = codigoM;
    }
    public int getCodigoM() {
        codigoM++;
        return codigoM;
    }
    public void setFacturas(ArrayList<Factura> facturas) {
        this.facturas = facturas;
    }
    public ArrayList<Factura> getFacturas() {
        return facturas;
    }
    public void setIdentificadorM(int identificadorM) {
        this.identificadorM = identificadorM;
    }
    public int getIdentificadorM() {
        identificadorM++;
        return identificadorM;
    }
    public void setSecuencialM(int secuencialM) {
        this.secuencialM = secuencialM;
    }
    public int getSecuencialM() {
        secuencialM++;
        return secuencialM;
    }
    public void setnSATM(int nSATM) {
        this.nSATM = nSATM;
    }
    public int getnSATM() {
        nSATM++;
        return nSATM;
    }
    //Intervalo cerrado para max
    public int validarInt(Scanner teclado, int min, int max){
        boolean tonto = true;
        int numero = 0;

        while (tonto) {
            String entrada = teclado.nextLine();

            try {
                int pnumero = Integer.parseInt(entrada);
                if ((pnumero >= min)&& (pnumero <= max)) {
                    numero = pnumero;
                    tonto = false;
                } else {
                    System.out.println("Ingrese un número número dentro del rango aceptable");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido");
            }
        }

        return numero;
    }
    public double validarDouble(Scanner teclado,double min, double max){
        boolean tonto = true;
        double numero = 0;

        while (tonto) {
            String entrada = teclado.nextLine();

            try {
                double pnumero = Double.parseDouble(entrada);
                if ((pnumero > 0)&& (pnumero < max)) {
                    numero = pnumero;
                    tonto = false;
                } else {
                    System.out.println("Ingrese un número número dentro del rango aceptable");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido");
            }
        }

        return numero;
    }
    public void verOficinas(){
        for(int n = 0; n < vOficina.size(); n++){
            if(vOficina.get(n) != null){
                vOficina.get(n).mostrar(hora);
            }
        }
    }
    public void sumarAcumulado(double multiplicador){
        for(int n = 0; n < vOficina.size(); n++){
            if(vOficina.get(n) != null){
                vOficina.get(n).calcularAcumulado(multiplicador);
            }
        }
    }
    public void tiempoAlertas(double m){
        for(int n = 0; n < bajas.size(); n++){
            if( bajas.get(n) != null){
                bajas.get(n).simular(m);
            }
        }
        for(int n = 0; n < altas.size(); n++){
            if( altas.get(n) != null){
                altas.get(n).simular(m);
            }
        }
        for(int n = 0; n < criticas.size(); n++){
            if( criticas.get(n) != null){
                criticas.get(n).simular(m);
            }
        }
    }

    public double acreditar(int identificador){
        double credito = 0;
        for(int n = 0; n < bajas.size(); n++){
            if(bajas.get(n) != null){
                if(bajas.get(n).getEmisor() == identificador){
                    credito = credito + bajas.get(n).getCredito();
                    bajas.get(n).setCredito(0);
                }
            }
        }
        for(int n = 0; n < altas.size(); n++){
            if(altas.get(n) != null){
                if(altas.get(n).getEmisor() == identificador){
                    credito = credito + altas.get(n).getCredito();
                    altas.get(n).setCredito(0);
                }
            }
        }
        for(int n = 0; n < criticas.size(); n++){
            if(criticas.get(n) != null){
                if(criticas.get(n).getEmisor() == identificador){
                    credito = credito + criticas.get(n).getCredito();
                    criticas.get(n).setCredito(0);
                }
            }
        }
        return credito;
    }
    public boolean newDay(){
        boolean sigue = true;
        if(hora >= 24){
            hora = 0;
            sigue = false;
            for(int n = 0; n < vOficina.size(); n++){
                if(vOficina.get(n) != null){
                    if(vOficina.get(n).getAcumulado() > 0){
                        Factura factura = new Factura(getnSATM(), vOficina.get(n).getIdentificador(), vOficina.get(n).getAcumulado(), vOficina.get(n).getSaldo());
                        facturas.add(factura);
                        vOficina.get(n).resetSaldo();
                        vOficina.get(n).setAcumulado(0);    
                    }
                }
            }
        }
        return sigue;
    }
    public void reloj(double paso){
        boolean condicion1 = true;
        while(condicion1){
            boolean condicion2 = false;
            if(hora < 24){
                condicion2 = true;
            }
            while(condicion2) {
                if(paso <= 0){
                    condicion1 = false;
                }
                double m = 0;
                double restante = 24 - hora;
                double v = 0;
                double n = 0;
                double p = 0;
                if((paso >= 1) & (restante >= 1)){
                    m = 1;
                }
                else{
                    if(restante <= paso){
                        m = restante;
                    }
                    else{
                        m = paso;
                    }
                }
                double siguiente = hora + m;
                //valle (0:00-5:59)
                if((hora >= 0) & (hora < 6)){
                    if(siguiente >= 6){
                        v = v + (6 - hora);
                    }
                    else{
                        v = v + m;
                    }
                }
                //normal(6:00-17:59)
                //Inferior
                if((hora >= 6) & (hora < 18)){
                    if(siguiente >= 18){
                        n = n + (18 - hora);
                    }
                    else{
                        n = n + m;
                    }
                }
                //Superior
                if((siguiente >= 6) & (siguiente < 18)){
                    if(hora < 6){
                        n = n + (siguiente - 6);
                    }
                }
                //punta(18:00-21:59)
                //Inferior
                if((hora >= 18) & (hora < 22)){
                    if(siguiente >= 22){
                        p = p + (22 - hora);
                    }
                    else{
                        p = p + m;
                    }
                }
                //Superior
                if((siguiente >= 18) & (siguiente < 22)){
                    if(hora < 18){
                        n = n + (siguiente - 18);
                    }
                }
                //valle (22:00-23:59)
                //Inferior
                if((hora >= 22) & (hora < 24)){
                    if(siguiente == 24){
                        v = v + (24 - hora);
                    }
                    else{
                        n = n + m;
                    }
                }
                //Superior
                if((siguiente >= 22) & (siguiente < 24)){
                    if(hora < 6){
                        n = n + (siguiente - 22);
                    }
                }
                double multiplicador = 0.85*v + 1.40*n + 2.10*p;
                sumarAcumulado(multiplicador);
                tiempoAlertas(m);
                hora = siguiente;
                paso = paso - m;
                for(int a = 0; a < vOficina.size(); a++){
                    if(vOficina.get(a) != null){
                        vOficina.get(a).setSaldo(acreditar(vOficina.get(a).getIdentificador()));
                    }
                }
                condicion2 = newDay();
            }
            
        }
            
    }
    public Oficina buscarOficina(int identificador){
        Oficina buscado = null;
        for(int n = 0; n < vOficina.size(); n++){
            if (vOficina.get(n) != null) {
                if(vOficina.get(n).getIdentificador() == identificador){
                    buscado = vOficina.get(n);
                }
            }
        }
        return buscado;
    }
    public void masOficina(Scanner teclado){
        System.out.println("Ingrese el nombre del inquilino");
        String inquilino = teclado.nextLine();
        Oficina nuevaOficina = new Oficina(getIdentificadorM(), inquilino);
        System.out.println("Oficina agregada con éxito");
        nuevaOficina.mostrar(0);
    }
    public void menosOficina(Scanner teclado){
        System.out.println("Ingrese el identificador de la oficina que desea eliminar");
        int identificador = validarInt(teclado, 0, 32000);
        Oficina juzgado = buscarOficina(identificador);
        if(juzgado != null){
            if(juzgado.getSaldo() == 0){
                Factura factura = new Factura(getnSATM(), identificador, juzgado.getAcumulado(), 0);
                facturas.add(factura);
                System.out.println("Se ha emitido una factura previo a su eliminación");
                vOficina.remove(juzgado);
                System.out.println("Se ha eliminado la oficina con éxito");
            }
            else{
                System.out.println("No se puede eliminar una oficina con saldo a favor");
            }
        }
        else{
            System.out.println("No se pudo encontrar la oficina No. " + identificador);
        }
        
    }
    public void ver1Oficina(Scanner teclado){
        System.out.println("Ingrese el identificador de la oficina que busca");
        int identificador = validarInt(teclado, 0, 32000);
        Oficina deseado = buscarOficina(identificador);
        if(deseado != null){
            deseado.mostrar(hora);
        }
        else{
            System.out.println("No se pudo encontrar la oficina No. " + identificador);
        }

    }
    public void masDispositivo(Scanner teclado){
        System.out.println("Ingrese el identificador de la oficina donde se desea instalar el dispositivo");
        int identificador = validarInt(teclado, 0, 32000);
        Oficina interesado = buscarOficina(identificador);
        if(interesado != null){
            System.out.println("¿Que dispositivo le interesa?");
            System.out.println("1. Luminaria");
            System.out.println("2. Sistema climatizador");
            System.out.println("3. Detector de humo");
            System.out.println("4. Sistema de camara");
            System.out.println("Ingrese el número de la opción que desea");
            int eleccion = validarInt(teclado, 1, 4);
            boolean inverter = false;
            if(eleccion == 2){
                System.out.println("¿Desea un climatizador inverter? (si/no)");
                String respuesta = teclado.nextLine();
                if(respuesta.equalsIgnoreCase("si")){
                    inverter = true;
                }
            }
            System.out.println("Ingrese la potencia nominal del dispositivo");
            double potencial = validarDouble(teclado, 1, 100000);
            interesado.instalarDispositivo(eleccion, getCodigoM(), potencial, inverter);
        }
        else{
            System.out.println("No se pudo encontrar la oficina No. " + identificador);
        }
    }
    public void menosDispositivo(Scanner teclado){
        System.out.println("Ingrese el identificador de la oficina donde se encuentra instalado este dispositivo");
        int identificador = validarInt(teclado, 0, 32000);
        Oficina oficina = buscarOficina(identificador);
        if(oficina != null){
            System.out.println("Ingrese el código del dispositivo a remover");
            int codigo = validarInt(teclado, 0, 32000);
            oficina.quitarDispositivo(codigo);
        }
        else{
            System.out.println("No se pudo encontrar la oficina No. " + identificador);
        }
    }
    public void bandeja(){
        for(int n = 0; n < criticas.size(); n++){
            if(criticas.get(n) != null){
                criticas.get(n).mostrar();
            }
        }
        for(int n = 0; n < altas.size(); n++){
            if(altas.get(n) != null){
                altas.get(n).mostrar();
            }
        }
        for(int n = 0; n < bajas.size(); n++){
            if(bajas.get(n) != null){
                bajas.get(n).mostrar();
            }
        }
    }
    public void verFacturas(){
        for(int n = 0 ; n < facturas.size(); n++){
            if(facturas.get(n) != null){
                facturas.get(n).mostrar();
            }
        }
    }
    public void reportarLectura(Scanner teclado){
        System.out.println("Ingrese el identificador de la oficina en cuestión");
        int identificador = validarInt(teclado, 0, 32000);
        Oficina oficina = buscarOficina(identificador);
        if(oficina != null){
            System.out.println("¿Qué tipo de lectura desea reportar?");
            System.out.println("1. Temperatura");
            System.out.println("2. Concentración de humo");
            System.out.println("3. Movimiento");
            System.out.println("Ingrese el número e la opción deseada");
            int eleccion = validarInt(teclado, 1, 3);
            double dato = 0;
            switch (eleccion) {
                case 1:
                    System.out.println("Ingrese el valor de la temperatura registrado por el dispositivo");
                    dato = validarDouble(teclado, -10, 60);
                    break;
                case 2:
                    System.out.println("Ingrese el valor de la concentración de humo registrada por el dispositivo");
                    dato = validarDouble(teclado, 0, 1000);
                    break;
                case 3:
                    System.out.println("¿Hubo movimiento detectado por las camaras? (si/no)");
                    String respuesta = teclado.nextLine();
                    if(respuesta.equalsIgnoreCase("si")){
                        if((hora >= 7) & (hora <= 19)){
                            //No pasa nada
                        }
                        else{
                            dato = 1;
                        }
                    }
                    break;
            }
            Alerta alerta = oficina.alertador(eleccion, dato);
            if(alerta != null){
                switch (alerta.getGravedad()) {
                    case 1:
                        bajas.add(alerta);
                        break;
                    case 2:
                        altas.add(alerta);
                        break;
                    case 3:
                        criticas.add(alerta);
                        break;
                }
                System.out.println("Se ha emitido una alerta");
                alerta.mostrar();
            }
        }
        else{
            System.out.println("No se pudo encontrar la oficina No. " + identificador);
        }
    }
    public void ajustar(Scanner teclado){
        System.out.println("Ingrese el identificador de la oficina en cuestión");
        int identificador = validarInt(teclado, 0, 32000);
        Oficina oficina = buscarOficina(identificador);
        if(oficina != null){
            System.out.println("¿Que tipo de dispositivo desea regular?");
            System.out.println("1. Luminaria");
            System.out.println("2. Climatizador regular");
            System.out.println("3. Climatizador inverter");
            System.out.println("Ingrese el número e la opción deseada");
            int eleccion = validarInt(teclado, 1, 3);
            int donde = -1;
            int nivel = 0;
            System.out.println("Ingrese el nivel al que desea el dispositivo");
            switch (eleccion) {
                case 1:
                    nivel = validarInt(teclado, 10, 100);
                    donde = 0;
                    break;
                case 2:
                    nivel = validarInt(teclado, 20, 100);
                    donde = 1;
                    break;
                case 3:
                    nivel = validarInt(teclado, 10, 100);
                    donde = 1;
                    break;
            }
            oficina.regulacion(donde, nivel);
        }
        else{
            System.out.println("No se pudo encontrar la oficina No. " + identificador);
        }
    }
    public void atender(Scanner teclado){
        System.out.println("¿Desea atender la alerta más urgente? (si/no)");
        String respuesta = teclado.nextLine();
        if(respuesta.equalsIgnoreCase("si")){
            boolean noAtendida = true;
            Alerta removido = null;
            while (noAtendida) {
                for(int n = 0; n < criticas.size(); n++){
                    if(criticas.get(n) != null){
                        removido = criticas.get(n);
                        criticas.remove(n);
                        noAtendida = false;
                    }
                }
                for(int n = 0; n < altas.size(); n++){
                    if(altas.get(n) != null){
                        removido = altas.get(n);
                        altas.remove(n);
                        noAtendida = false;
                    }   
                }
                for(int n = 0; n < bajas.size(); n++){
                    if(bajas.get(n) != null){
                        removido = bajas.get(n);
                        bajas.remove(n);
                        noAtendida = false;
                    }
                }
                noAtendida = false;
            }
            if(removido != null){
                System.out.println("La siguiente alerta ha sido removida con éxito: ");
                removido.mostrar();
            }
            else{
                System.out.println("No hay alertas que remover");
            }
        }
    }
    public void simular(Scanner teclado){
        System.out.println("Son las " + hora + "del día en curso");
        System.out.println("Ingrese cuantas horas han pasado");
        double paso = validarDouble(teclado, 0, 100000);
        if(paso > 0){
            reloj(paso);
        }
        System.out.println("Simulación completada");
        System.out.println("Son las " + hora + "del día en curso");
    }
    public void funcionamiento(Scanner teclado) {
        boolean activo = true;
        while (activo) {
            try {
                System.out.println("BIENVENIDO");
                System.out.println("¿Que desea hacer el día de hoy?");
                System.out.println("1. Registrar una nueva oficina");
                System.out.println("2. Eliminar una oficina");
                System.out.println("3. Buscar una oficina");
                System.out.println("4. Ver todas las oficinas");
                System.out.println("5. Instalar un dispositivo");
                System.out.println("6. Retirar un dispositivo");
                System.out.println("7. Ver bandeja de alertas");
                System.out.println("8. Ver facturas emitidas");
                System.out.println("9. Reportar lectura de algún dispositivo");
                System.out.println("10. Ajustar el nivel de algún dispositivo");
                System.out.println("11. Atender alerta más urgente");
                System.out.println("12. Simular paso de tiempo");
                System.out.println("13. Salir");
                System.out.println("Ingrese el número de la opción que desea");
                int eleccion = validarInt(teclado, 1, 13);
                switch (eleccion) {
                    case 1:
                        masOficina(teclado);
                        break;
                     case 2:
                        menosOficina(teclado);
                        break;
                     case 3:
                        ver1Oficina(teclado);
                        break;
                     case 4:
                        verOficinas();
                        break;
                     case 5:
                        masDispositivo(teclado);
                        break;
                     case 6:
                        menosDispositivo(teclado);
                        break;
                     case 7:
                        bandeja();
                        break;
                     case 8:
                        verFacturas();
                        break;
                     case 9:
                        reportarLectura(teclado);
                        break;
                     case 10:
                        ajustar(teclado);
                        break;
                     case 11:
                        atender(teclado);
                        break;
                     case 12:
                        simular(teclado);
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                System.out.println("ERROR :(");
            }
            finally{
                System.out.println("¿Desea continuar operaciones? (si/no)");
                String salir = teclado.nextLine();
                if(salir.equalsIgnoreCase("no")){
                    activo = false;
                }
            }
        }
    }
}
