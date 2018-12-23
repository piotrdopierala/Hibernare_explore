package pl.dopierala.HibernateAssociations.UnidirectionOneToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.dopierala.HibernateAssociations.UnidirectionOneToMany.Entity.Company;
import pl.dopierala.HibernateAssociations.UnidirectionOneToMany.Entity.CompanyDetail;
import pl.dopierala.HibernateAssociations.UnidirectionOneToMany.Entity.Department;
import pl.dopierala.HibernateAssociations.UnidirectionOneToMany.Entity.Property;

public class OneToManyUniGetApp {
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
        //stworzenie obiektu Session Factory
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        //pobieranie sesji
        Session currentSession = sessionFactory.getCurrentSession();
        //rozpoczecie tranzakcji
        currentSession.beginTransaction();
        //odczytanie pracownika
        ////////////////////
        int id=36;
//        Company companyPeugeot = currentSession.get(Company.class,id);
//        System.out.println(companyPeugeot);
//
//        Set<Department> departments = companyPeugeot.getDepartments();
//        departments.forEach(System.out::println);

        Department department = currentSession.get(Department.class, 1);
        System.out.println(department);


        ////////////////////
        //zakonczenie transakcji
        currentSession.getTransaction().commit();
        //zamkniecie obiektu Session Factory
        sessionFactory.close();
    }
}
