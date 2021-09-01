import org.hibernate.Session;

import model.*;
import java.time.LocalDate;
import persistence.HibernateUtil;

public class FastE {

	public static void main(String[] args) {
        FastE faste = new FastE();

        faste.mappingTest();
        
        HibernateUtil.getSessionFactory().getCurrentSession().close();

	}

    private void mappingTest() {

    	//Instantiate a session 
    	Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        
        //Instantiate persistent objects 
        Customer pepe = Customer.register("usuario", "contraseña", "Pepe", "pepe@mail.com", LocalDate.now());
        Delivery jose = Delivery.register("josepe", "algo", "Jose", "jose@mail.com", LocalDate.now(), 10f);
        Provider cocaCola = Provider.register(3030123, "Coca-Cola", "calle falsa 123", 0, 10);
        Product coca = Product.publishProduct("coca", cocaCola, 1.0f, 50.0f);
        Product cindor = Product.publishProduct("Cindor", cocaCola, 1.0f, 50.0f);
//        pepe.makeAnOrder(0, 1, coca);

        //Persist objects
        session.save(pepe);
        session.save(jose);
        session.save(cocaCola);
        coca.updatePrice(15.0f);
        cindor.updatePrice(25.0f);
        cindor.updatePrice(30.0f);
        session.save(cindor);
        session.save(coca);
        
        session.getTransaction().commit();
    }

}

