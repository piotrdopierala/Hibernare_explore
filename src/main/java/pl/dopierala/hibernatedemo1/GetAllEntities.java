package pl.dopierala.hibernatedemo1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.dopierala.hibernatedemo1.entity.Employee;

import java.util.List;

public class GetAllEntities {
    public static void main(String[] args) {
        //tworzenie obiektu configuration
        Configuration cfg = new Configuration();
        //wczytanie pliku koknfiguracyjnego
        cfg.configure("hibernate.cfg.xml");
        //wczytanie adnotacji
        cfg.addAnnotatedClass(Employee .class);
        //stworzenie obiektu Session Factory
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        //pobieranie sesji
        Session currentSession = sessionFactory.getCurrentSession();
        //rozpoczecie tranzakcji
        currentSession.beginTransaction();
        //odczytanie pracownika
        Query query = currentSession.createQuery("FROM Employee ORDER BY idEmployee DESC");
        List employees = query.getResultList();
        employees.stream().forEach(System.out::println);
        //currentSession.save(emp);
        //zakonczenie transakcji
        currentSession.getTransaction().

                commit();
        //zamkniecie obiektu Session Factory
        sessionFactory.close();
    }
}
