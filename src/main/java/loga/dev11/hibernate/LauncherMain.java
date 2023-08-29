package loga.dev11.hibernate;

import loga.dev11.hibernate.entities.Client;
import loga.dev11.hibernate.entities.Planet;
import loga.dev11.hibernate.entities.Ticket;
import loga.dev11.hibernate.service.ClientCrudService;
import loga.dev11.hibernate.service.PlanetCrudService;
import loga.dev11.hibernate.service.TicketCrudService;
import loga.dev11.hibernate.utils.MigrationUtil;
import loga.dev11.hibernate.utils.HibernateUtil;

public class LauncherMain {

    public static void main(String[] args) {

        MigrationUtil migrationUtil= new MigrationUtil();
        migrationUtil.migration();

        ClientCrudService clientService = new ClientCrudService();

        clientService.createClient(new Client("John"));
        clientService.createClient(new Client("Emily"));
        clientService.createClient(new Client("Sophia"));
        clientService.createClient(new Client("William"));
        clientService.createClient(new Client("Emma"));

        System.out.println(clientService.getClientById(4L));
        clientService.updateClientById(5L, "Viktor");
        clientService.deleteClientById(2L);
        clientService.getAll().forEach(System.out::println);

        PlanetCrudService planetService = new PlanetCrudService();

        planetService.createPlanet(new Planet("MARS", "Mars"));
        planetService.createPlanet(new Planet("ERTH", "Earth"));
        planetService.createPlanet(new Planet("JUP7", "Jupiter"));

        System.out.println(planetService.getPlanetById("JUP7"));
        planetService.updatePlanetById("MARS", "New Mars");
        planetService.deletePlanetById("MARS");
        planetService.getAllPlanet().forEach(System.out::println);

        TicketCrudService ticketService = new TicketCrudService();

        ticketService.createTicket(clientService.getClientById(3L),
                planetService.getPlanetById("MARS"), planetService.getPlanetById("JUP7"));
        ticketService.updateTicketById(8, clientService.getClientById(8L),
                planetService.getPlanetById("SAT2"), planetService.getPlanetById("MERS"));
        System.out.println(ticketService.getTicketById(1));
        ticketService.deleteTicketById(9);
        System.out.println(ticketService.getAllTickets());
        HibernateUtil.getInstance().close();
    }
}
