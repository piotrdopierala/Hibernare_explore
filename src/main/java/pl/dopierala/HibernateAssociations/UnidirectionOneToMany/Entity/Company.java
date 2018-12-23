package pl.dopierala.HibernateAssociations.UnidirectionOneToMany.Entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="company")
public class Company {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_company")
    private Integer id_company;
    @Column(name="name")
    private String name;
    @Column(name="value")
    private Integer value;

    @OneToOne(targetEntity = CompanyDetail.class, cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
    @JoinColumn(name="id_company_detail")
    private CompanyDetail details;
    @OneToMany(fetch=FetchType.LAZY, mappedBy = "company", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Property> properties;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="id_company")
    private Set<Department> departments;

    public Company() {
    }

    public Company(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public Integer getId_company() {
        return id_company;
    }

    public void setId_company(Integer id_company) {
        this.id_company = id_company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public CompanyDetail getDetails() {
        return details;
    }

    public void setDetails(CompanyDetail details) {
        this.details = details;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public void addProperty(Property property){
        if (Objects.isNull(this.properties)) {
            this.properties = new ArrayList<>();
        }

        this.properties.add(property);
        property.setCompany(this);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id_company=" + id_company +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    public void addDepartment(Department departmentToAdd){
        if(Objects.isNull(this.departments)){
            this.departments=new HashSet<>();
        }
        this.departments.add(departmentToAdd);
        //departmentToAdd.setCompany(this);
    }

}
