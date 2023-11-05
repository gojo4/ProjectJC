package services.exeption;

public class CheckingAccessRights extends RuntimeException{
    public CheckingAccessRights() {
        super("У сотрудника недостаточно прав доступа");
    }
}