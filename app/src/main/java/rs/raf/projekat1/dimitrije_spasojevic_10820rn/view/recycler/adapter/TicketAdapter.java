package rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.recycler.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.function.Consumer;

import rs.raf.projekat1.dimitrije_spasojevic_10820rn.model.Ticket;

public class TicketAdapter extends ListAdapter<Ticket,TicketAdapter.ViewHolder> {

    private final Consumer<Ticket> onTicketClicked;

    public TicketAdapter(@NonNull DiffUtil.ItemCallback<Ticket> diffCallback, Consumer<Ticket> onTicketClicked) {
        super(diffCallback);
        this.onTicketClicked = onTicketClicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
