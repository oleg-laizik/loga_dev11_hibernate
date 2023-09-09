package loga.dev11.hibernate;

import loga.dev11.hibernate.entities.Client;
import loga.dev11.hibernate.entities.Planet;
import loga.dev11.hibernate.service.ClientCrudService;
import loga.dev11.hibernate.service.PlanetCrudService;
import loga.dev11.hibernate.service.TicketCrudService;
import loga.dev11.hibernate.utils.MigrationUtil;
import loga.dev11.hibernate.utils.HibernateUtil;

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
        System.out.println(clientService.getClientById(4L));
        clientService.updateClientById(5L, "Viktor");
        clientService.deleteClientById(2L);
        clientService.getAll().forEach(System.out::println);

        // Створення планет
        planetService.createPlanet(new Planet("MARS", "Mars"));
        planetService.createPlanet(new Planet("ERTH", "Earth"));
        planetService.createPlanet(new Planet("JUP7", "Jupiter"));

        // Отримання планети за кодом та оновлення назви планети
        System.out.println(planetService.getPlanetById("JUP7"));
        planetService.updatePlanetById("MARS", "New Mars");
        planetService.deletePlanetById("MARS");
        planetService.getAllPlanet().forEach(System.out::println);

        // Створення та оновлення квитків
        ticketService.createTicket(clientService.getClientById(3L), planetService.getPlanetById("ERTH"), planetService.getPlanetById("MERC"));
        ticketService.updateTicketById(3, clientService.getClientById(3L), planetService.getPlanetById("MARS"), planetService.getPlanetById("JUP7"));
        System.out.println(ticketService.getTicketById(1));
        ticketService.deleteTicketById(9);
        ticketService.getAllTickets().forEach(System.out::println);

        HibernateUtil.getInstance().close();
    }
}