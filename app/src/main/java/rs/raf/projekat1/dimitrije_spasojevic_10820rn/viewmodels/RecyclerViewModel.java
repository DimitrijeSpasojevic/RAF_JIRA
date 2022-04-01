package rs.raf.projekat1.dimitrije_spasojevic_10820rn.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import rs.raf.projekat1.dimitrije_spasojevic_10820rn.model.Ticket;

public class RecyclerViewModel extends ViewModel {

    private MutableLiveData<List<Ticket>> tickets = new MutableLiveData<>();
    private List<Ticket> ticketList = new ArrayList<>();

    public RecyclerViewModel() {
        for (int i = 0; i < 30; i++) {
            Ticket ticket = new Ticket("Bug" + i,"opis bla bla", Ticket.TicketType.BUG, Ticket.TicketPriority.High);
            ticketList.add(ticket);
        }

        List<Ticket> listToSub = new ArrayList<>(ticketList);
        tickets.setValue(listToSub);
    }

    public LiveData<List<Ticket>> getTickets() {
        return tickets;
    }
}
