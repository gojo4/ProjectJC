package services;

import model.Director;
import model.Skill;
import repository.EmployeeRepository;
import services.exeption.CheckingAccessRights;
import view.View;

public class DirectorService {


    private DirectorService(Director director) {
    }

    public static DirectorService factoryDirectorService(Director director) {
        try {
            if (Skill.DIRECTOR == director.getSkill()) {
                return new DirectorService(director);
            } else throw new CheckingAccessRights();
        } catch (CheckingAccessRights e) {
            View.printConsole(e.getMessage());
        }
        return null;
    }

    public void raisingSalaries(double percentageIncrease) {
        EmployeeRepository.getEmployees().stream()
                .filter(x -> x.getSkill() != Skill.DIRECTOR)
                .forEach(x -> x.setSalary(x.getSalary() * (1 + percentageIncrease)));
    }

}