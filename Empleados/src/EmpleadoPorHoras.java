public class EmpleadoPorHoras extends Empleado {

    private static final double LIMITE_HORAS_NORMALES = 160.0;

    protected double horasTrabajadas;
    protected double valorHora;

    public EmpleadoPorHoras(String nombre, String cedula, double salarioBase,
                             double horasTrabajadas, double valorHora) {
        super(nombre, cedula, salarioBase);
        this.horasTrabajadas = horasTrabajadas;
        this.valorHora = valorHora;
    }

    public double getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public double getValorHora() {
        return valorHora;
    }

    @Override
    public double calcularSueldo() {
        if (horasTrabajadas <= LIMITE_HORAS_NORMALES) {
            return horasTrabajadas * valorHora;
        } else {
            double horasNormales = LIMITE_HORAS_NORMALES;
            double horasExtra = horasTrabajadas - LIMITE_HORAS_NORMALES;
            return (horasNormales * valorHora) + (horasExtra * valorHora * 2);
        }
    }

    @Override
    public String toString() {
        return "EmpleadoPorHoras{" +
                "nombre='" + nombre + '\'' +
                ", cedula='" + cedula + '\'' +
                ", horasTrabajadas=" + horasTrabajadas +
                ", valorHora=" + valorHora +
                ", sueldoCalculado=" + calcularSueldo() +
                '}';
    }
}