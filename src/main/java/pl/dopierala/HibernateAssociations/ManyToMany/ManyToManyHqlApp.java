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

public class ManyToManyHqlApp {
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

//zwrocic kazde szkolenie w ktorym bralo udzial wiecej niz 5 pracownikow

//        int minNoOfEmpInOneTraining = 6;
//        String trsWithMinNoEmps = "FROM Training t WHERE size(t.employees)>=:minNoOfEmps";
//        Query<Training> minNoOfEmps = currentSession.createQuery(trsWithMinNoEmps, Training.class)
//                .setParameter("minNoOfEmps", minNoOfEmpInOneTraining);
//        List<Training> trainings = minNoOfEmps.getResultList();
//        trainings.forEach(System.out::println);

//wszystkich pracownikow ktorzy brali udzial w okreslonym kursie
//
//        String course = "javascript";
//        String empsWhoTookCourse = "SELECT e FROM Employee e JOIN e.trainings t WHERE t.name=:courseName ";
//        Query<Employee> courseAttendantsHQL = currentSession.createQuery(empsWhoTookCourse,Employee.class).setParameter("courseName", course);
//        List<Employee> attendants = courseAttendantsHQL.getResultList();
//        attendants.forEach(System.out::println);

//wszystkich pracownikow ktorzy brali udzial w x szkoleniach i zarabiaja mniej niz y

        int coursesTookByEmp = 1;
        int maxSalary = 12000;

        String empTookXcoursesAndEarnLessY = "SELECT e FROM Employee e WHERE size(e.trainings)=:X AND e.salary<:Y";
        Query<Employee> employeeQuery = currentSession.createQuery(empTookXcoursesAndEarnLessY, Employee.class)
                .setParameter("X", coursesTookByEmp)
                .setParameter("Y", maxSalary);

        List<Employee> emps = employeeQuery.getResultList();

        emps.forEach(System.out::println);


        ////////////////////
        //zakonczenie transakcji
        currentSession.getTransaction().commit();
        //zamkniecie obiektu Session Factory
        sessionFactory.close();
    }
}
