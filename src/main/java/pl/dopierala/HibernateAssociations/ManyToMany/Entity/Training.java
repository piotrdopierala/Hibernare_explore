package pl.dopierala.HibernateAssociations.ManyToMany.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="training")
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_training")
    private Integer idTraining;
    @Column(name="name")
    private String name;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name="employee_training", joinColumns = @JoinColumn(name="id_training"), inverseJoinColumns = @JoinColumn(name="id_employee"))
    private List<Employee> employees;

    public Training() {
    }

    public Training(String name) {
        this.name = name;
    }

    public Integer getIdTraining() {
        return idTraining;
    }

    public void setIdTraining(Integer idTraining) {
        this.idTraining = idTraining;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(Employee emp){
        if(Objects.isNull(this.employees)){
            this.employees=new ArrayList<>();
        }
        this.employees.add(emp);
        //add training to employee
    }

    @Override
    public String toString() {
        return "Training{" +
                "name='" + name + '\'' +
                '}';
    }
}
