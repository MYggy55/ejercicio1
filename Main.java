import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaVentas sistema = new SistemaVentas();
        Comprador activo = null;
        int opcion;
        do {
            System.out.println("\n=== THE ERAS TOUR ===");
            System.out.println("1. Nuevo comprador");
            System.out.println("2. Nueva solicitud de boletos");
            System.out.println("3. Consultar disponibilidad total");
            System.out.println("4. Consultar disponibilidad individual");
            System.out.println("5. Reporte de caja");
            System.out.println("6. Salir");
            opcion = leerEntero(scanner, "Opcion: ");
            switch (opcion) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine().trim();
                    System.out.print("Email: ");
                    String email = scanner.nextLine().trim();
                    if (nombre.isEmpty() || email.isEmpty()) {
                        System.out.println("Nombre y email son obligatorios.");
                    } else {
                        activo = new Comprador(nombre, email);
                        System.out.println("Comprador activo: " + activo.getNombre());
                    }
                    break;
                case 2:
                    if (activo == null) {
                        System.out.println("Primero debe registrar un comprador.");
                        break;
                    }
                    int cantidad = leerEntero(scanner, "Cantidad deseada: ");
                    double presupuesto = leerDouble(scanner, "Presupuesto maximo ($): ");
                    try {
                        activo.solicitarBoletos(cantidad, presupuesto);
                        System.out.println(sistema.procesarSolicitud(activo));
                    } catch (IllegalArgumentException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 3:
                    System.out.println(sistema.reporteDisponibilidad());
                    break;
                case 4:
                    int numero = leerEntero(scanner, "Localidad (1, 5 o 10): ");
                    Localidad localidad = sistema.buscarLocalidad(numero);
                    System.out.println(localidad == null ? "Localidad inexistente." :
                            "Boletos disponibles: " + localidad.getDisponibles());
                    break;
                case 5:
                    System.out.printf("Total de caja: $%.2f%n", sistema.calcularCaja());
                    break;
                case 6:
                    System.out.println("Hasta pronto.");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 6);
        scanner.close();
    }

    private static int leerEntero(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try { return Integer.parseInt(scanner.nextLine().trim()); }
            catch (NumberFormatException ex) { System.out.println("Ingrese un numero entero valido."); }
        }
    }

    private static double leerDouble(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try { return Double.parseDouble(scanner.nextLine().trim()); }
            catch (NumberFormatException ex) { System.out.println("Ingrese un numero valido (use punto decimal)."); }
        }
    }
}
