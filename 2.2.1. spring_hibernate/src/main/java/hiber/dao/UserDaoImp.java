package hiber.dao;

import hiber.model.User;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().persist(user);
    }

    //!Спросить почему в createQuery не работает From User?????
    @Override
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("SELECT u from User u", User.class);
        return query.getResultList();
    }

    @Override
    public User takeUserByCar(String model, int series) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("From User u where u.car.model = :model and u.car.series = :series", User.class)
                .setParameter("model", model)
                .setParameter("series", series);
        return (User) query.getSingleResult(); //Писать тут
    }
}
