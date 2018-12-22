package pl.dopierala.HibernateAssociations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pl.dopierala.HibernateAssociations.Entity.Company;
import pl.dopierala.HibernateAssociations.Entity.CompanyDetail;

public class CascadeRemoveApp {
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
        Company companyToDelete = currentSession.get(Company.class, 12);
        currentSession.remove(companyToDelete);

        ////////////////////
        //zakonczenie transakcji
        currentSession.getTransaction().commit();
        //zamkniecie obiektu Session Factory
        sessionFactory.close();
    }
}
