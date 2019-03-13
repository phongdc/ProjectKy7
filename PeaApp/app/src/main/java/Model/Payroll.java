package Model;

public class Payroll {
    private int id;
    private int payroll_detail_category_id;
    private int salary_rule_group_id;
    private String name;
    private int value;
    private String description;
    private int default_formula_id;
    private String frequency;
    private String variability;
    private String destination;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPayroll_detail_category_id() {
        return payroll_detail_category_id;
    }

    public void setPayroll_detail_category_id(int payroll_detail_category_id) {
        this.payroll_detail_category_id = payroll_detail_category_id;
    }

    public int getSalary_rule_group_id() {
        return salary_rule_group_id;
    }

    public void setSalary_rule_group_id(int salary_rule_group_id) {
        this.salary_rule_group_id = salary_rule_group_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDefault_formula_id() {
        return default_formula_id;
    }

    public void setDefault_formula_id(int default_formula_id) {
        this.default_formula_id = default_formula_id;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getVariability() {
        return variability;
    }

    public void setVariability(String variability) {
        this.variability = variability;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
