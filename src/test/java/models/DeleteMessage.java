package models;

import java.util.Objects;

public class DeleteMessage {
    String code;

    public String getCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeleteMessage)) return false;
        DeleteMessage that = (DeleteMessage) o;
        return Objects.equals(getCode(), that.getCode()) && Objects.equals(getType(), that.getType()) && Objects.equals(getMessage(), that.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getType(), getMessage());
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    String type;
    String message;
}
