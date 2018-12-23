package pl.dopierala.HibernateAssociations.UnidirectionOneToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.dopierala.HibernateAssociations.UnidirectionOneToMany.Entity.Company;
import pl.dopierala.HibernateAssociations.UnidirectionOneToMany.Entity.CompanyDetail;
import pl.dopierala.HibernateAssociations.UnidirectionOneToMany.Entity.Department;
import pl.dopierala.HibernateAssociations.UnidirectionOneToMany.Entity.Property;

public class OneToManyUniSaveApp {
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
        Company companyPeugeot = currentSession.get(Company.class,id);
        System.out.println(companyPeugeot);
        Department dep1 = new Department("sales");
        Department dep2 = new Department("production");
        Department dep3 = new Department("HR");

        companyPeugeot.addDepartment(dep1);
        companyPeugeot.addDepartment(dep2);
        companyPeugeot.addDepartment(dep3);

        currentSession.persist(companyPeugeot);
        ////////////////////
        //zakonczenie transakcji
        currentSession.getTransaction().commit();
        //zamkniecie obiektu Session Factory
        sessionFactory.close();
    }
}
