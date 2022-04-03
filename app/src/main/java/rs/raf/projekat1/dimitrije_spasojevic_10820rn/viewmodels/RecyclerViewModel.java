package rs.raf.projekat1.dimitrije_spasojevic_10820rn.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import rs.raf.projekat1.dimitrije_spasojevic_10820rn.model.Ticket;

public class RecyclerViewModel extends ViewModel {


    private MutableLiveData<List<Ticket>> toDoTickets = new MutableLiveData<>();
    private MutableLiveData<List<Ticket>> inProgressTickets = new MutableLiveData<>();
    private MutableLiveData<List<Ticket>> doneTickets = new MutableLiveData<>();
    private List<Ticket> ticketListToDo;
    private List<Ticket> ticketListInProgress = new ArrayList<>();
    private List<Ticket> ticketListDone = new ArrayList<>();
    ArrayList<Ticket> list = new ArrayList<>();
    private String filterToDo = "";
    private String filterInProgress = "";
    private String filterDone = "";
    public RecyclerViewModel() {
        for (int i = 0; i < 5; i++) {
            Ticket ticket = new Ticket("Bug" + i,"opis bla bla", Ticket.TicketType.BUG, Ticket.TicketPriority.High,"4");
            list.add(ticket);
        }
        ticketListToDo = new ArrayList<>(list);
        toDoTickets.setValue(ticketListToDo);
    }


    public void addTicket(Ticket ticket){
        ticketListToDo.add(ticket);
        ArrayList<Ticket> listToSubmit = new ArrayList<>(ticketListToDo);
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

    public void filterTicket(String filter,String nameOfList,boolean isItFromStatistics) {

        switch (nameOfList){
            case "todo":{
                List<Ticket> filteredList = null;
                if(!isItFromStatistics)
                    filterToDo = filter;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    filteredList = ticketListToDo.stream().filter(t -> t.getTitle().toLowerCase().startsWith(filter.toLowerCase())).collect(Collectors.toList());
                }
                toDoTickets.setValue(filteredList);
                break;
            } case "inProgress":{
                List<Ticket> filteredList = null;
                if(!isItFromStatistics)
                    filterInProgress = filter;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    filteredList = ticketListInProgress.stream().filter(t -> t.getTitle().toLowerCase().startsWith(filter.toLowerCase())).collect(Collectors.toList());
                }
                inProgressTickets.setValue(filteredList);
                break;
            }
            case  "done":{
                List<Ticket> filteredList = null;
                if(!isItFromStatistics)
                    filterDone = filter;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    filteredList = ticketListDone.stream().filter(t -> t.getTitle().toLowerCase().startsWith(filter.toLowerCase())).collect(Collectors.toList());
                }
                doneTickets.setValue(filteredList);
                break;
            }
        }

    }

    public void deleteFromToDo (Ticket ticket){
        ticketListToDo.remove(ticket);
        ArrayList<Ticket> listMy = new ArrayList<>(ticketListToDo);
        toDoTickets.setValue(listMy);
    }

    public void deleteFromInProgress(Ticket ticket) {
        ticketListInProgress.remove(ticket);
        ArrayList<Ticket> listMy = new ArrayList<>(ticketListInProgress);
        inProgressTickets.setValue(listMy);
    }

    public void addInToDo(Ticket ticket){
        addTicket(ticket);
        deleteFromInProgress(ticket);
    }

    public void addInProgress(Ticket ticket){
        deleteFromToDo(ticket);
        ticketListInProgress.add(ticket);
        ArrayList<Ticket> listMy = new ArrayList<>(ticketListInProgress);
        inProgressTickets.setValue(listMy);
    }
    public void addInDone(Ticket ticket){
        deleteFromInProgress(ticket);
        ticketListDone.add(ticket);
        ArrayList<Ticket> listMy = new ArrayList<>(ticketListDone);
        doneTickets.setValue(listMy);
    }


    public String getFilterDone() {
        return filterDone;
    }

    public String getFilterInProgress() {
        return filterInProgress;
    }

    public String getFilterToDo() {
        return filterToDo;
    }
}
