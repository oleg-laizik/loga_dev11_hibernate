package loga.dev11.hibernate.service;

import loga.dev11.hibernate.entities.Client;
import loga.dev11.hibernate.entities.Planet;
import loga.dev11.hibernate.entities.Ticket;
import loga.dev11.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class TicketCrudService {

    public void createTicket(Client client, Planet fromPlanet, Planet toPlanet) {
        Ticket ticket = new Ticket();
        ticket.setClient(client);
        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setFromPlanet(fromPlanet);
        ticket.setToPlanet(toPlanet);
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(ticket);
            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException("Ticket creation failed", e);
        }
    }

    public Ticket getTicketById(long id) {
        try (Session session = getSession()) {
            Ticket ticket = session.get(Ticket.class, id);
            if (ticket == null) {
                throw new NoSuchElementException("Ticket with ID " + id + " doesn't exist");
            }
            return ticket;
        }
    }

    public void updateTicketById(long id, Client client, Planet fromPlanet, Planet toPlanet) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, id);
            if (ticket != null) {
                ticket.setClient(client);
                ticket.setFromPlanet(fromPlanet);
                ticket.setToPlanet(toPlanet);
                session.update(ticket);
                transaction.commit();
            } else {
                throw new NoSuchElementException("Ticket with ID " + id + " doesn't exist");
            }
        }
    }

    public void deleteTicketById(long id) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, id);
            if (ticket != null) {
                session.delete(ticket);
                transaction.commit();
            } else {
                throw new NoSuchElementException("Ticket with ID " + id + " doesn't exist");
            }
        }
    }

    public List<Ticket> getAllTickets() {
        try (Session session = getSession()) {
            return session.createQuery("from Ticket", Ticket.class).list();
        }
    }

    private static Session getSession() {
        return HibernateUtil.getInstance()
                .getSessionFactory()
                .openSession();
    }
}
