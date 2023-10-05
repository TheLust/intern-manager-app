package md.ceiti.internmanagerapp.back;

public class BadRequest extends RuntimeException{

    public BadRequest() {
    }

    public BadRequest(String message) {
        super(message);
    }
}
