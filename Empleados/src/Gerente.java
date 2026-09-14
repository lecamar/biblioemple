public class Gerente extends EmpleadoFijo {

    protected double bonoAnual;

    public Gerente(String nombre, String cedula, double salarioBase,
                    int anosAntiguedad, double bonoAnual) {
        super(nombre, cedula, salarioBase, anosAntiguedad);
        this.bonoAnual = bonoAnual;
    }

    public double getBonoAnual() {
        return bonoAnual;
    }

    @Override
    public double calcularSueldo() {
        double sueldoBaseConAntiguedad = super.calcularSueldo();
        double bonoMensual = bonoAnual / 12.0;
        return sueldoBaseConAntiguedad + bonoMensual;
    }

    @Override
    public String toString() {
        return "Gerente{" +
                "nombre='" + nombre + '\'' +
                ", cedula='" + cedula + '\'' +
                ", anosAntiguedad=" + anosAntiguedad +
                ", bonoAnual=" + bonoAnual +
                ", sueldoCalculado=" + calcularSueldo() +
                '}';
    }
}