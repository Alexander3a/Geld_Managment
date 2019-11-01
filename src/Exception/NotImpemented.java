package Exception;

public class NotImpemented extends Exception{
    int id;
    String Error;

    public NotImpemented(int x,String Error) {
        this.Error = Error;
        id = x;
    }

    public String toString() {
        return "NotImpemented[" + id + "]"+Error;
    }
}
