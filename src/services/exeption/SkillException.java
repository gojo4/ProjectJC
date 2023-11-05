package services.exeption;

public class SkillException extends RuntimeException{

    public SkillException (){
        super("Неверная квалификация сотрудника");
    }
}
