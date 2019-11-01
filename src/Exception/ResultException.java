package Exception;

public class ResultException extends RuntimeException{
    int id;
    String Error;

    public ResultException(int x, String Error) {
        this.Error = Error;
        id = x;
    }

    public String toString() {
        return "ResultException[" + id + "]"+Error;
    }
}
