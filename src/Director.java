import java.util.ArrayList;

public class Director extends Manager {
    protected double stocks;
    public Director (int salary, int b, int stockshares, String name, String department, String title,
                     ArrayList<Employee> a, int t) throws Exception {
        super(salary, b, name, department, title, a, t);
        this.stocks = stockshares;
    }

    public double getCompensation() {
        return salary + stocks * Company.SHARE_PRICE + bonus;
    }
}
