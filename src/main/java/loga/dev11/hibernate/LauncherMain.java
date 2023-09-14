package loga.dev11.hibernate;

import loga.dev11.hibernate.entities.Client;
import loga.dev11.hibernate.entities.Planet;
import loga.dev11.hibernate.entities.Ticket;
import loga.dev11.hibernate.service.ClientCrudService;
import loga.dev11.hibernate.service.PlanetCrudService;
import loga.dev11.hibernate.service.TicketCrudService;
import loga.dev11.hibernate.utils.MigrationUtil;
import loga.dev11.hibernate.utils.HibernateUtil;

import java.util.List;

public class LauncherMain {

    public static void main(String[] args) {

        MigrationUtil migrationUtil = new MigrationUtil();
        migrationUtil.migration();

        ClientCrudService clientService = new ClientCrudService();
        PlanetCrudService planetService = new PlanetCrudService();
        TicketCrudService ticketService = new TicketCrudService();

        // Створення клієнтів
        clientService.createClient(new Client("John"));
        clientService.createClient(new Client("Emily"));
        clientService.createClient(new Client("Sophia"));
        clientService.createClient(new Client("William"));
        clientService.createClient(new Client("Emma"));

        // Отримання клієнта за ID та оновлення імені клієнта
        List<Client> clients = clientService.getAll();
        clients.forEach(System.out::println);
        Client client = clients.get(3);
        Client client1 = clientService.getClientById(client.getId());
        System.out.println(client1);
        clientService.updateClientById(client.getId(), "Viktor");
        Client client2 = clients.get(4);
        clientService.deleteClientById(client2.getId());


        // Створення планет
        planetService.deletePlanetById("MARS");
        planetService.createPlanet(new Planet("MARS", "Mars"));
        // Отримання планети за кодом та оновлення назви планети
        System.out.println(planetService.getPlanetById("JUP7"));
        planetService.updatePlanetById("MARS", "NewMars");
        planetService.getAllPlanet().forEach(System.out::println);

        // Створення та оновлення квитків
        ticketService.createTicket(client1, planetService.getPlanetById("ERTH"), planetService.getPlanetById("MERC"));
        List<Ticket> allTickets = ticketService.getAllTickets();
        allTickets.forEach(System.out::println);
        Ticket ticket = allTickets.get(0);

        ticketService.updateTicketById(ticket.getId(), client1, planetService.getPlanetById("MARS"), planetService.getPlanetById("JUP7"));
        System.out.println(ticketService.getTicketById(ticket.getId()));
        ticketService.deleteTicketById(ticket.getId());


        HibernateUtil.getInstance().close();
    }
}