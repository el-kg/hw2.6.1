package skypro.EmployeeBook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Не корректные символы в имени пользователя!")
public class EmployeeIncorrectNameException extends RuntimeException{
}
