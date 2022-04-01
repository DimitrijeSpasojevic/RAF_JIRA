package rs.raf.projekat1.dimitrije_spasojevic_10820rn.model;

public class Ticket {

    private String title;
    private String description;
    private TicketType ticketType;
    private TicketPriority ticketPriority;

    public enum TicketType {
        ENHANCEMENT,
        BUG
    }

    public enum TicketPriority {
        Highest, High, Medium, Low, Lowest
    }

}
