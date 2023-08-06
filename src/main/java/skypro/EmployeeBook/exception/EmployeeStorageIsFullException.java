package skypro.EmployeeBook.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST,reason= "Нет места в Хранилище!")
public class EmployeeStorageIsFullException extends RuntimeException{
}
