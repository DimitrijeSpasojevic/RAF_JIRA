package rs.raf.projekat1.dimitrije_spasojevic_10820rn.model;

public class ClickConsumer {

    private Ticket ticket;
    private Click click;


    public enum Click{
        ADD_IN_TODO,ADD_IN_PROGRESS,ADD_IN_DONE,REMOVE,DETAILS
    }

    public ClickConsumer(Ticket ticket, Click click) {
        this.ticket = ticket;
        this.click = click;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Click getClick() {
        return click;
    }

    public void setClick(Click click) {
        this.click = click;
    }
}
