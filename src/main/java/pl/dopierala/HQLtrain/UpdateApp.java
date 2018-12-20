package pl.dopierala.HQLtrain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.dopierala.HQLtrain.entity.Employee;

import java.util.List;

public class UpdateApp {
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
        //odczytanie pracownika
        ////////////////////
        Integer idEmployeeToGiveRaise=115;
        Integer newSalary = 15000;

        String update = "UPDATE Employee SET salary=:newSalary WHERE idEmployee=:idEmpToUpdate";

        Query query = currentSession.createQuery(update)
                .setParameter("newSalary",newSalary)
                .setParameter("idEmpToUpdate",idEmployeeToGiveRaise);

        query.executeUpdate();


        /////////////////
        //zakonczenie transakcji
        currentSession.getTransaction().commit();
        //zamkniecie obiektu Session Factory
        sessionFactory.close();
    }
}
