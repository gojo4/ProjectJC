package view;

import model.Employee;
import model.Priority;

public class View {

    public static void printConsole(String message) {
        System.out.println(message);
    }

    /**
     * Информирование работника о поступлении нового задания.
     */
    public static void informingEmployee(Employee employee, Priority priority) {
        View.printConsole("Для " + employee + " назначена новая задача с приоритетом " + priority);
    }
}