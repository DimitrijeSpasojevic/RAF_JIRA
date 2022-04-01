package rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.recycler.differ;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import rs.raf.projekat1.dimitrije_spasojevic_10820rn.model.Ticket;

public class TicketDiffItemCallback extends DiffUtil.ItemCallback<Ticket> {
    @Override
    public boolean areItemsTheSame(@NonNull Ticket oldItem, @NonNull Ticket newItem) {
        return oldItem.getDescription() == newItem.getDescription() &&
                oldItem.getTicketType() == newItem.getTicketType() &&
                oldItem.getTitle() == newItem.getTitle();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Ticket oldItem, @NonNull Ticket newItem) {
        return oldItem.getDescription().equals(newItem.getDescription()) &&
                oldItem.getTicketType().equals(newItem.getTicketType()) &&
                oldItem.getTitle().equals(newItem.getTitle());
    }
}
