import java.lang.reflect.Array;
import java.util.ArrayList;

public class Manager extends Employee {
    protected double bonus;
    protected ArrayList<Employee> reports;
    public Manager(int salary, int b, String name, String department, String title, ArrayList<Employee> a, int t) throws Exception {
        super(salary, name, department, title);
        this.tier = t;
        bonus = b;
        reports = a;
        for(Employee e: reports) {
            if(e.getTier() >= 3){
                throw new Exception("ERROR: cannot supervise an Employee of an equal or greater tier.");
            }
        }
    }

    public void hire(Employee e) throws Exception{
        if (this.getTier() <= e.getTier()){
            throw new Exception("ERROR: cannot hire an Employee of an equal or greater tier.");
        } else {
            reports.add(e);
            System.out.println("LOG: new Employee hired (" + e.getName() + ", " + e.getDepartment() + ", " + e.getTitle() + ")");
            Company.addEmployee(e);
        }
    }

    public void fire(Manager manager) throws Exception {
        ArrayList<Employee> abandoned = manager.reports;
        if (this.getDepartment().equals(manager.getDepartment())) {
            this.reports.remove(manager);
            System.out.println("LOG: existing Employee fired (" + manager.getName() + ", " + manager.getDepartment() + ", " + manager.getTitle() + ")");

            boolean reassigned = false;

            String log = "LOG: reports re-assigned [";
            for (int x = 0; x < this.reports.size(); x++) {
                if (this.reports.get(x).getDepartment().equals(manager.getDepartment())) {
                    for (Employee employee : abandoned) {
                        reassigned = true;
                        this.reports.add(employee);
                        log += employee.getName() + ", " + employee.getDepartment() + ", " + employee.getTitle() + ", ";
                    }
                }
            }

            if (reassigned) {
                System.out.println(log.substring(0, log.length() - 2) + "]");
            }

        }
        else if (this.getTier() <= manager.getTier()){
            throw new Exception("ERROR: cannot fire an Employee of an equal or greater tier.");
        }
        else {
            throw new Exception("ERROR: cannot fire an Employee who is not a direct or indirect report.");
        }
        Company.removeEmployee(manager);
    }

    public void fire(Employee employee) throws Exception {
        if (this.getDepartment().equals(employee.getDepartment())) {
            reports.remove(employee);
            System.out.println("LOG: existing Employee fired (" + employee.getName() + ", " + employee.getDepartment() + ", " + employee.getTitle() + ")");
        }
        else if (this.getTier() <= employee.getTier()) {
            throw new Exception("ERROR: cannot fire an Employee who is not a direct or indirect report.");
        }
        else {
            throw new Exception("ERROR: cannot fire an Employee of an equal or greater tier.");
        }
    }

    public ArrayList<Employee> getReports() {
        return reports;
    }

    public void adjustSalary(double adj, Employee e) throws Exception {
        boolean direct = false;
        for (Employee manager: this.reports) {
            if (manager.getName().equals(e.getName())) {
                direct = true;
                break;
            }

            if (manager.getTier() >= 2) {
                for (Employee employee1 : ((Manager) manager).getReports()) {
                    if (employee1.getName().equals(e.getName())) {
                        direct = true;
                        break;
                    }
                }
            }
        }
        if (direct) {
            if (e.getTier() < this.getTier()){
                Company.adjustSalaryHelper(adj, e);
            }
            else {
                throw new Exception("ERROR: cannot alter salary of an Employee of an equal or greater tier.");
            }
        }
        else {
            throw new Exception("ERROR: cannot alter salary of an Employee who is not a report.");
        }
    }

    public double getCompensation() {
        return this.bonus + this.salary;
    }
}