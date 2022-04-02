package rs.raf.projekat1.dimitrije_spasojevic_10820rn.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import rs.raf.projekat1.dimitrije_spasojevic_10820rn.model.Ticket;

public class ViewModelSelectedItem extends ViewModel {

    MutableLiveData<Ticket> ticket = new MutableLiveData<>();

    public LiveData<Ticket> getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket.setValue(ticket);
    }
}
