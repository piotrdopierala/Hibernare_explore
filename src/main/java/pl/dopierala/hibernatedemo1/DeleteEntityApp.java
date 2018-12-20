package pl.dopierala.hibernatedemo1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.dopierala.hibernatedemo1.entity.Employee;

public class DeleteEntityApp {
    public static void main(String[] args) {
        //tworzenie obiektu configuration
        Configuration cfg = new Configuration();
        //wczytanie pliku koknfiguracyjnego
        cfg.configure("hibernate.cfg.xml");
        //wczytanie adnotacji
        cfg.addAnnotatedClass(Employee.class);
        //stworzenie obiektu Session Factory
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        //pobieranie sesji
        Session currentSession = sessionFactory.getCurrentSession();
        //rozpoczecie tranzakcji
        currentSession.beginTransaction();
        //usuniecie pracownika
        Employee empToDelete = currentSession.get(Employee.class,9);
        currentSession.delete(empToDelete);
        //zakonczenie transakcji
        currentSession.getTransaction().commit();


        //zamkniecie obiektu Session Factory
        sessionFactory.close();
    }
}
