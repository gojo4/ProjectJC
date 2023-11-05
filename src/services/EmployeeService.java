package services;

import model.Assigment;
import model.Employee;
import model.Status;
import repository.AssigmentRepository;
import view.View;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class EmployeeService {

    public List<Assigment> getAssigmentsByEmployee(Employee employee) {
        return AssigmentRepository.getAssigmentList().stream()
                .filter(x -> x.getEmployee() == employee)
                .toList();
    }

    /**
     * Полностью поменял логику старта задач. При нажатии сотрудником кнопки "Взять задачу"
     * сотруднику автоматически в работу выдается задача с наивысшим приоритетом из спмска назначенных
     * ему planTask - ом
     */

    public void startTaskByEmployee(Employee employee) {
        if (checkingEmployeeHasCompletedTasks(employee).isEmpty() && !getAssigmentsByEmployee(employee).isEmpty()) {
            View.printConsole("Необходимо завершить/отложить текущее задание");
            return;
        }
        List<Assigment> assigmentsSort = getAssigmentsByEmployee(employee).stream()
                .filter(x -> x.getStatus() != Status.COMPLETE)
                .sorted(Comparator.comparingInt(x -> x.getTask().getPriority().getPriority()))
                .toList();
        if (assigmentsSort.isEmpty()) {
            View.printConsole("Спмсок назначенных задач пуст");
            return;
        }
        employee.setWorking(true);
        Assigment assigment = assigmentsSort.get(0);
        assigment.setFactStartDate(LocalDate.now());
        assigment.setStatus(Status.IN_PROGRESS);

    }

    public List<Assigment> checkingEmployeeHasCompletedTasks(Employee employee) {
        return getAssigmentsByEmployee(employee).stream()
                .filter(x -> x.getStatus() != Status.IN_PROGRESS)
                .toList();
    }

    /**
     * Автоматически завершается задача находящаяся в работе
     */
    public void finishTaskByEmployee(Employee employee) {
        List<Assigment> assigmentsSort = checkingEmployeeHasCompletedTasks(employee);
        if (assigmentsSort.isEmpty()) {
            View.printConsole("Нет выполняемых задач");
            return;
        }
        Assigment assigment = assigmentsSort.get(0);
        employee.setWorking(false);
        assigment.setFactEndDate(LocalDate.now());
        assigment.setStatus(Status.COMPLETE);
    }


    public void onHoldCurrentTask(Employee employee) {
        for (Assigment progressTask : checkingEmployeeHasCompletedTasks(employee))
            progressTask.setStatus(Status.OnHOLD);
    }
}