import model.*;
import repository.EmployeeRepository;
import services.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Department departmentEngineer = new Department(Skill.ENGINEER);
        Department departmentHR = new Department(Skill.HR);
        Employee employeeHR = new Employee("Севостьянова"
                , "Евгения"
                , LocalDate.of(1990, 5, 16)
                , 200
                , Skill.MANAGER);
        EmployeeRepository.addEmployee(employeeHR);
        DepartmentHRService departmentHRService = new DepartmentHRService();
        departmentHRService.appointManager(departmentHR, employeeHR);
        departmentHRService.addDepartment(departmentHR);
        Employee employee1 = new Employee("Евтехов"
                , "Иван"
                , LocalDate.of(1985, 4, 4)
                , 200
                , Skill.ENGINEER);
        EmployeeRepository.addEmployee(employee1);
        departmentHRService.addEmployeeDepartment(departmentEngineer, employee1);
        Employee employee2 = new Employee("Антонов"
                , "Денис"
                , LocalDate.of(1989, 8, 14)
                , 200
                , Skill.ENGINEER);
        EmployeeRepository.addEmployee(employee2);
        departmentHRService.addEmployeeDepartment(departmentEngineer, employee2);
        Employee employee3 = new Employee("Сергеев"
                , "Петр"
                , LocalDate.of(1976, 5, 10)
                , 300
                , Skill.MANAGER);
        EmployeeRepository.addEmployee(employee3);
        departmentHRService.appointManager(departmentEngineer, employee3);
        Employee employee4 = new Employee("Просников"
                , "Владислав"
                , LocalDate.of(1995, 12, 10)
                , 100
                , Skill.HR);
        EmployeeRepository.addEmployee(employee4);
        departmentHRService.addEmployeeDepartment(departmentHR, employee4);
        Director director = new Director("Павлов"
                , "Александр"
                , "Алексеевич"
                , LocalDate.of(1984, 8, 17)
                , 1000
                , Skill.DIRECTOR);
        EmployeeRepository.addEmployee(director);
        departmentHRService.addDepartment(departmentEngineer);

        Task task1 = new Task("Задача1"
                , 1,
                LocalDate.now()
                , Skill.ENGINEER
                , 10);

        Task task2 = new Task("Задача2"
                , 2,
                LocalDate.now()
                , Skill.ENGINEER
                , 120);
        Task task3 = new Task("Задача3"
                , 2,
                LocalDate.now()
                , Skill.ENGINEER
                , 20);
        task3.setPriority(Priority.P1);
        Task task4 = new Task("Задача4"
                , 2,
                LocalDate.now()
                , Skill.TECHNOLOGIST
                , 20);
        task3.setPriority(Priority.P1);
        Task task5 = new Task("Задача5"
                , 2,
                LocalDate.now()
                , Skill.ENGINEER
                , 120);
        Task task6 = new Task("Задача6"
                , 2,
                LocalDate.now()
                , Skill.ENGINEER
                , 120);

        ManagerService managerService = ManagerService.factoryManagerService(employee3, departmentEngineer);
        EmployeeService employeeService = new EmployeeService();
        SelectionEmployee selectionEmployee = new SelectionEmployee(managerService, departmentHRService, employeeService);
        TaskPlanner taskPlanner = new TaskPlanner(selectionEmployee);

        taskPlanner.planTask(task1);
        employeeService.startTaskByEmployee(employee1);
        taskPlanner.planTask(task2);
        employeeService.startTaskByEmployee(employee2);
        taskPlanner.planTask(task3);
        taskPlanner.planTask(task3);
        taskPlanner.planTask(task3);
        taskPlanner.planTask(task4);
        taskPlanner.planTask(task5);
        taskPlanner.planTask(task6);

        employeeService.startTaskByEmployee(employee3);
        employeeService.startTaskByEmployee(employee4);

        FileService fileService = new FileService();
        fileService.fileWriterEmployee(EmployeeRepository.getEmployees());
        System.out.println(fileService.fileReaderEmployee());
    }
}