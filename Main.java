import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Sistema sistema = new Sistema();
        // Crear las tres oficinas con identificadores únicos.
        Oficina oficina1 = new Oficina(
            sistema.getIdentificadorM(), "Administración"
        );

        Oficina oficina2 = new Oficina(
            sistema.getIdentificadorM(), "Contabilidad"
        );

        Oficina oficina3 = new Oficina(
            sistema.getIdentificadorM(), "Recursos Humanos"
        );

        // Guardar las oficinas en el sistema.
        sistema.getvOficina().add(oficina1);
        sistema.getvOficina().add(oficina2);
        sistema.getvOficina().add(oficina3);

        /*
         * instalarDispositivo(tipo, codigo, potencia, inverter)
         *
         * Tipos:
         * 1 = Luminaria
         * 2 = Climatizador
         * 3 = Detector de humo
         * 4 = Cámara
         *
         * Potencia expresada en watts.
         */

        // Oficina 1: luminaria, climatizador y detector.
        oficina1.instalarDispositivo(
            1, sistema.getCodigoM(), 60, false
        );
        oficina1.instalarDispositivo(
            2, sistema.getCodigoM(), 1500, false
        );
        oficina1.instalarDispositivo(
            3, sistema.getCodigoM(), 5, false
        );

        // Oficina 2: luminaria y cámara.
        oficina2.instalarDispositivo(
            1, sistema.getCodigoM(), 40, false
        );
        oficina2.instalarDispositivo(
            4, sistema.getCodigoM(), 15, false
        );

        // Oficina 3: climatizador inverter, detector y cámara.
        oficina3.instalarDispositivo(
            2, sistema.getCodigoM(), 1200, true
        );
        oficina3.instalarDispositivo(
            3, sistema.getCodigoM(), 5, false
        );
        oficina3.instalarDispositivo(
            4, sistema.getCodigoM(), 20, false
        );
        sistema.funcionamiento(teclado);
    }
}
