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

    public Ticket(String title, String description, TicketType ticketType, TicketPriority ticketPriority) {
        this.title = title;
        this.description = description;
        this.ticketType = ticketType;
        this.ticketPriority = ticketPriority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public TicketPriority getTicketPriority() {
        return ticketPriority;
    }

    public void setTicketPriority(TicketPriority ticketPriority) {
        this.ticketPriority = ticketPriority;
    }
}
