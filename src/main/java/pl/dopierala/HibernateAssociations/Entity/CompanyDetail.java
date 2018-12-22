package pl.dopierala.HibernateAssociations.Entity;

import javax.persistence.*;

@Entity
@Table(name="company_detail")
public class CompanyDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_company_detail")
    private Integer idCompanyDetail;
    @Column(name="residence")
    private String residence;
    @Column(name="employee_number")
    private int employeeNumber;

    public CompanyDetail() {
    }

    public CompanyDetail(String residence, int employeeNumber) {
        this.residence = residence;
        this.employeeNumber = employeeNumber;
    }

    public Integer getIdCompanyDetail() {
        return idCompanyDetail;
    }

    public void setIdCompanyDetail(Integer idCompanyDetail) {
        this.idCompanyDetail = idCompanyDetail;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    @Override
    public String toString() {
        return "CompanyDetail{" +
                "idCompanyDetail=" + idCompanyDetail +
                ", residence='" + residence + '\'' +
                ", employeeNumber=" + employeeNumber +
                '}';
    }
}