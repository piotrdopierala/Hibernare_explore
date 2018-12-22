package pl.dopierala.HibernateAssociations.Entity;

import javax.persistence.*;

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

    @OneToOne(targetEntity = CompanyDetail.class)
    @JoinColumn(name="id_company_detail")
    private CompanyDetail details;

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

    @Override
    public String toString() {
        return "Company{" +
                "id_company=" + id_company +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", details=" + details +
                '}';
    }
}
