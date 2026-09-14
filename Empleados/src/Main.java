public class Main {
    public static void main(String[] args) {

        Empresa empresa = new Empresa("Monsters Inc.");

        empresa.contratar(new Gerente("James P. Sullivan", "10234567", 4000000, 12, 9600000));
        empresa.contratar(new Gerente("Henry J. Waternoose", "10234568", 5000000, 30, 12000000));

        empresa.contratar(new EmpleadoFijo("Mike Wazowski", "10234569", 2200000, 10));
        empresa.contratar(new EmpleadoFijo("Randall Boggs", "10234570", 2000000, 8));
        empresa.contratar(new EmpleadoFijo("Celia Mae", "10234571", 1800000, 5));

        empresa.contratar(new EmpleadoPorHoras("Roz", "10234572", 0, 170, 8500));
        empresa.contratar(new EmpleadoPorHoras("Boo Mary", "10234573", 0, 60, 7000));

        double totalNomina = empresa.pagarNomina();

        System.out.printf("%nTOTAL DESEMBOLSADO POR LA EMPRESA: $%,.2f%n", totalNomina);

        Empleado mejorPagado = empresa.empleadoMejorPagado();
        System.out.println("\nEl empleado con el mayor sueldo es:");
        mejorPagado.mostrarInfo();
    }
}