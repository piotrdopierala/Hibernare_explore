package pl.dopierala.HibernateAssociations.UnidirectionOneToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.dopierala.HibernateAssociations.UnidirectionOneToMany.Entity.Company;
import pl.dopierala.HibernateAssociations.UnidirectionOneToMany.Entity.CompanyDetail;
import pl.dopierala.HibernateAssociations.UnidirectionOneToMany.Entity.Department;
import pl.dopierala.HibernateAssociations.UnidirectionOneToMany.Entity.Property;

import java.util.Set;

public class OneToManyUniDeleteApp {
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

        //usuniecie bezposretnio przez uzycie ID
//        int id = 3;
//        Department object = currentSession.get(Department.class,id);
//        currentSession.delete(object);
        //posrednio przez pobrane Company i usuniecie powiozanego obiektu
//        int id=36;
//        Company companyPeugeot = currentSession.get(Company.class,36);
//        Set<Department> departments = companyPeugeot.getDepartments();
//        Department depToRemove = departments.stream()
//                .filter(d->d.getName().equals("HR"))
//                .findFirst().orElse(null);
//        departments.remove(depToRemove);
//        currentSession.delete(depToRemove);
        // z wykozystaniem HQL
        int id=2;
        String depDeleteQuery="DELETE FROM Department d WHERE d.idDepartment=:idToDelete";
        currentSession.createQuery(depDeleteQuery)
                .setParameter("idToDelete",id)
                .executeUpdate();

        ////////////////////
        //zakonczenie transakcji
        currentSession.getTransaction().commit();
        //zamkniecie obiektu Session Factory
        sessionFactory.close();
    }
}
