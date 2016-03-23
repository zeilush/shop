package zeilush.shop.exception;

/**
 * Created by AAA on 23.03.2016.
 */
public class Error {
    private String errorMessage;

    public Error(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
