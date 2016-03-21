package ru.egalvi.shop;

/**
 */
public class CheckoutResponse {
    private String error;
    private String errorCode;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "CheckoutResponse{" +
                "error='" + error + '\'' +
                ", errorCode='" + errorCode + '\'' +
                '}';
    }
}
