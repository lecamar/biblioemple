import java.util.ArrayList;
import java.util.List;

public class Empresa {

    private String nombre;
    private List<Empleado> empleados;

    public Empresa(String nombre) {
        this.nombre = nombre;
        this.empleados = new ArrayList<>();
    }

    public void contratar(Empleado empleado) {
        empleados.add(empleado);
        System.out.println(">> Contratado: " + empleado.getNombre());
    }

    public double pagarNomina() {
        System.out.println("\n===== NOMINA DE " + nombre.toUpperCase() + " =====");
        double total = 0.0;
        for (Empleado e : empleados) {
            e.mostrarInfo();
            total += e.calcularSueldo();
        }
        System.out.println("=========================================");
        return total;
    }

    public Empleado empleadoMejorPagado() {
        if (empleados.isEmpty()) {
            return null;
        }
        Empleado mejorPagado = empleados.get(0);
        for (Empleado e : empleados) {
            if (e.calcularSueldo() > mejorPagado.calcularSueldo()) {
                mejorPagado = e;
            }
        }
        return mejorPagado;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }
}