import java.util.Random;

public class SistemaVentas {
    private final Localidad[] localidades;
    private final Random random;

    public SistemaVentas() {
        localidades = new Localidad[] {
            new Localidad(1, 100),
            new Localidad(5, 500),
            new Localidad(10, 1000)
        };
        random = new Random();
    }

    public String procesarSolicitud(Comprador comprador) {
        int ticket = random.nextInt(15000) + 1;
        int a = random.nextInt(15000) + 1;
        int b = random.nextInt(15000) + 1;
        int minimo = Math.min(a, b);
        int maximo = Math.max(a, b);
        String sorteo = "Ticket: " + ticket + " | Rango: [" + minimo + ", " + maximo + "]\n";

        if (ticket < minimo || ticket > maximo) {
            return sorteo + "Solicitud rechazada: el ticket no esta dentro del rango.";
        }

        Localidad localidad = localidades[random.nextInt(localidades.length)];
        String detalle = sorteo + "Localidad asignada: " + localidad.getNumero() +
                " | Precio por boleto: $" + localidad.getPrecio() + "\n";
        if (localidad.getDisponibles() == 0) {
            return detalle + "No hay espacio en esta localidad.";
        }

        int cantidad = Math.min(comprador.getCantidadDeseada(), localidad.getDisponibles());
        double total = cantidad * localidad.getPrecio();
        if (total > comprador.getPresupuestoMaximo()) {
            return detalle + "Compra rechazada: presupuesto insuficiente para " +
                    cantidad + " boleto(s). Total: $" + total;
        }

        int vendidos = localidad.vender(cantidad);
        return detalle + "Compra exitosa para " + comprador.getNombre() +
                ": " + vendidos + " boleto(s). Total: $" + (vendidos * localidad.getPrecio());
    }

    public Localidad buscarLocalidad(int numero) {
        for (Localidad localidad : localidades) {
            if (localidad.getNumero() == numero) return localidad;
        }
        return null;
    }

    public String reporteDisponibilidad() {
        StringBuilder reporte = new StringBuilder();
        int disponibles = 0;
        for (Localidad localidad : localidades) {
            reporte.append("Localidad ").append(localidad.getNumero())
                    .append(": vendidos ").append(localidad.getVendidos())
                    .append(", disponibles ").append(localidad.getDisponibles()).append("\n");
            disponibles += localidad.getDisponibles();
        }
        return reporte.append("Disponibles totales: ").append(disponibles).toString();
    }

    public double calcularCaja() {
        double total = 0;
        for (Localidad localidad : localidades) total += localidad.getIngresos();
        return total;
    }
}
