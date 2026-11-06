public class Comprador {
    private String nombre;
    private String email;
    private int cantidadDeseada;
    private double presupuestoMaximo;

    public Comprador(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
        this.cantidadDeseada = 0;
        this.presupuestoMaximo = 0;
    }

    public void solicitarBoletos(int cantidad, double presupuesto) {
        if (cantidad <= 0 || presupuesto < 0) {
            throw new IllegalArgumentException("Cantidad o presupuesto invalidos.");
        }
        this.cantidadDeseada = cantidad;
        this.presupuestoMaximo = presupuesto;
    }

    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public int getCantidadDeseada() { return cantidadDeseada; }
    public double getPresupuestoMaximo() { return presupuestoMaximo; }
}
