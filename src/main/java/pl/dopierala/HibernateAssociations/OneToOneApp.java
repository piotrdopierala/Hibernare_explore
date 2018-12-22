package pl.dopierala.HibernateAssociations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.dopierala.HibernateAssociations.Entity.Company;
import pl.dopierala.HibernateAssociations.Entity.CompanyDetail;

import java.util.List;

public class OneToOneApp {
    public static void main(String[] args) {
        //tworzenie obiektu configuration
        Configuration cfg = new Configuration();
        //wczytanie pliku koknfiguracyjnego
        cfg.configure("hibernate.cfg.xml");
        //wczytanie adnotacji
        cfg.addAnnotatedClass(Company.class);
        cfg.addAnnotatedClass(CompanyDetail.class);
        //stworzenie obiektu Session Factory
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        //pobieranie sesji
        Session currentSession = sessionFactory.getCurrentSession();
        //rozpoczecie tranzakcji
        currentSession.beginTransaction();
        //odczytanie pracownika
        ////////////////////
            Company company = new Company("Oracle",90000000);
            CompanyDetail companyDetail = new CompanyDetail("USA",550);
            company.setDetails(companyDetail);

            currentSession.persist(companyDetail);
            currentSession.persist(company);

        ////////////////////
        //zakonczenie transakcji
        currentSession.getTransaction().commit();
        //zamkniecie obiektu Session Factory
        sessionFactory.close();
    }
}
