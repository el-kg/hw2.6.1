package skypro.EmployeeBook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Такой сотрудник уже есть !")
public class EmployeeAlreadyAddedException extends RuntimeException{
}
