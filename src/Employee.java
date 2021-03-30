public class Employee implements Comparable {
    protected int tier;
    protected double salary;
    protected String name;
    protected String department;
    protected String title;
    public Employee (int sal, String name_, String depar, String title_) {
        tier = 1;
        salary = sal;
        name = name_;
        department = depar;
        title = title_;
    }

    public String getDepartment() {
        return department;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public double getSalary() {
        return salary;
    }

    public int getTier() {
        return tier;
    }

    public void changeSalary(double change) {
        this.salary += change;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public double getCompensation() {
        return this.salary;
    }
}
