package uz.pdp.onekhm.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String m) {
        super(m + " not found");
    }
}
