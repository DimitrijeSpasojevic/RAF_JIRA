package rs.raf.projekat1.dimitrije_spasojevic_10820rn.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import rs.raf.projekat1.dimitrije_spasojevic_10820rn.model.Ticket;

public class RecyclerViewModel extends ViewModel {

    private MutableLiveData<List<Ticket>> tickets = new MutableLiveData<>();

    private MutableLiveData<List<Ticket>> toDoTickets = new MutableLiveData<>();
    private MutableLiveData<List<Ticket>> inProgressTickets = new MutableLiveData<>();
    private MutableLiveData<List<Ticket>> doneTickets = new MutableLiveData<>();
    private List<Ticket> ticketList = new ArrayList<>();

    public RecyclerViewModel() {
        for (int i = 0; i < 5; i++) {
            Ticket ticket = new Ticket("Bug" + i,"opis bla bla", Ticket.TicketType.BUG, Ticket.TicketPriority.High,"4");
            ticketList.add(ticket);
        }

        List<Ticket> listToSub = new ArrayList<>(ticketList);
        toDoTickets.setValue(listToSub);
        inProgressTickets.setValue(listToSub);
        doneTickets.setValue(listToSub);
        tickets.setValue(listToSub);
    }


    public void addTicket(Ticket ticket){
        ticketList.add(ticket);
        ArrayList<Ticket> listToSubmit = new ArrayList<>(ticketList);
        tickets.setValue(listToSubmit);
        toDoTickets.setValue(listToSubmit);
    }

    public LiveData<List<Ticket>> getToDoTickets() {
        return toDoTickets;
    }

    public LiveData<List<Ticket>> getInProgressTickets() {
        return inProgressTickets;
    }

    public LiveData<List<Ticket>> getDoneTickets() {
        return doneTickets;
    }
}
