package pl.dopierala.HibernateAssociations.ManyToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.dopierala.HibernateAssociations.ManyToMany.Entity.Employee;
import pl.dopierala.HibernateAssociations.ManyToMany.Entity.Training;
import pl.dopierala.HibernateAssociations.UnidirectionOneToMany.Entity.Company;
import pl.dopierala.HibernateAssociations.UnidirectionOneToMany.Entity.CompanyDetail;
import pl.dopierala.HibernateAssociations.UnidirectionOneToMany.Entity.Department;
import pl.dopierala.HibernateAssociations.UnidirectionOneToMany.Entity.Property;

import java.util.List;

public class ManyToManyGetApp {
    public static void main(String[] args) {
        //tworzenie obiektu configuration
        Configuration cfg = new Configuration();
        //wczytanie pliku koknfiguracyjnego
        cfg.configure("hibernate.cfg.xml");
        //wczytanie adnotacji
        cfg.addAnnotatedClass(Company.class);
        cfg.addAnnotatedClass(CompanyDetail.class);
        cfg.addAnnotatedClass(Property.class);
        cfg.addAnnotatedClass(Department.class);
        cfg.addAnnotatedClass(Employee.class);
        cfg.addAnnotatedClass(Training.class);
        //stworzenie obiektu Session Factory
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        //pobieranie sesji
        Session currentSession = sessionFactory.getCurrentSession();
        //rozpoczecie tranzakcji
        currentSession.beginTransaction();
        //odczytanie pracownika
        ////////////////////


//        Training trn = currentSession.get(Training.class,1);
//        System.out.println(trn);
//        System.out.println(trn.getEmployees());

//        Training trn = new Training("Java training");
//        String queryString = "FROM Employee WHERE firstName=:empNameToGet";
//        Employee empMiley = currentSession
//                .createQuery(queryString, Employee.class)
//                .setParameter("empNameToGet", "Miley")
//                .getSingleResult();
//        Employee empJohny = currentSession
//                .createQuery(queryString, Employee.class)
//                .setParameter("empNameToGet", "Johny")
//                .getSingleResult();
//
//        trn.addEmployee(empMiley);
//        trn.addEmployee(empJohny);
//
//        currentSession.persist(trn);

        // pobierz wszstkie obiety klasy Training iterowac po nich i wyswietlac jacy pracownicy dane szkolenie zrobili

        String getAllTrainingsQuery="FROM Training";
        List<Training> trainingsList = currentSession.createQuery(getAllTrainingsQuery, Training.class).getResultList();

        for (Training trn : trainingsList) {
            List<Employee> employeesInTraining = trn.getEmployees();
            System.out.println("Training "+trn.getName());
            for (Employee emp : employeesInTraining) {
                System.out.println(emp);
            }
        }

        ////////////////////
        //zakonczenie transakcji
        currentSession.getTransaction().commit();
        //zamkniecie obiektu Session Factory
        sessionFactory.close();
    }
}
