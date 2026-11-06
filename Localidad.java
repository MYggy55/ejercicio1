public class Localidad {
    private final int numero;
    private final double precio;
    private final int capacidad;
    private int vendidos;

    public Localidad(int numero, double precio) {
        this(numero, precio, 20);
    }

    public Localidad(int numero, double precio, int capacidad) {
        if (precio <= 0 || capacidad <= 0) {
            throw new IllegalArgumentException("Precio o capacidad invalidos.");
        }
        this.numero = numero;
        this.precio = precio;
        this.capacidad = capacidad;
        this.vendidos = 0;
    }

    public int getNumero() { return numero; }
    public double getPrecio() { return precio; }
    public int getVendidos() { return vendidos; }
    public int getDisponibles() { return capacidad - vendidos; }
    public double getIngresos() { return vendidos * precio; }

    public int vender(int cantidad) {
        if (cantidad <= 0) return 0;
        int vendidosAhora = Math.min(cantidad, getDisponibles());
        vendidos += vendidosAhora;
        return vendidosAhora;
    }
}
