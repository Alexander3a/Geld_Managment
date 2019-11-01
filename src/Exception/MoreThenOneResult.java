package Exception;

public class MoreThenOneResult extends Exception{
    int id;
    String Error;

    public MoreThenOneResult(int x,String Error) {
        this.Error = Error;
        id = x;
    }

    public String toString() {
        return "MoreThenOneResult[" + id + "]"+Error;
    }
}