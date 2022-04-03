package rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.recycler.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.function.Consumer;

import rs.raf.projekat1.dimitrije_spasojevic_10820rn.R;
import rs.raf.projekat1.dimitrije_spasojevic_10820rn.model.ClickConsumer;
import rs.raf.projekat1.dimitrije_spasojevic_10820rn.model.Ticket;

public class TicketAdapterInProgress extends ListAdapter<Ticket, TicketAdapterInProgress.ViewHolder> {

    private final Consumer<ClickConsumer> onTicketClicked;
    private ClickConsumer.Click click;
    public TicketAdapterInProgress(@NonNull DiffUtil.ItemCallback<Ticket> diffCallback, Consumer<ClickConsumer> onTicketClicked) {
        super(diffCallback);
        this.onTicketClicked = onTicketClicked;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_list_item_in_progress,parent,false);
        return new ViewHolder(view, parent.getContext(), position ->{
            Ticket ticket = getItem(position);
            onTicketClicked.accept(new ClickConsumer(ticket,click));
        });
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ticket ticket = getItem(position);
        holder.bind(ticket);
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        private Context context;

        @RequiresApi(api = Build.VERSION_CODES.N)
        public ViewHolder(@NonNull View itemView, Context context, Consumer<Integer> onItemClicked) {
            super(itemView);
            this.context = context;

            ImageButton next = itemView.findViewById(R.id.img_next);
            ImageButton prev= itemView.findViewById(R.id.img_prev);
            ImageButton imgBtn= itemView.findViewById(R.id.image_btn);

            next.setOnClickListener(v -> {
                if ( getAdapterPosition() != RecyclerView.NO_POSITION) {
                    click= ClickConsumer.Click.ADD_IN_DONE;
                    onItemClicked.accept(getAdapterPosition());
                }
            });
            prev.setOnClickListener(v -> {
                if ( getAdapterPosition() != RecyclerView.NO_POSITION) {
                    click= ClickConsumer.Click.ADD_IN_TODO;
                    onItemClicked.accept(getAdapterPosition());
                }
            });

            imgBtn.setOnClickListener(v->{
                if ( getAdapterPosition() != RecyclerView.NO_POSITION) {
                    click= ClickConsumer.Click.DETAILS;
                    onItemClicked.accept(getAdapterPosition());
                }
            });
        }

        public void bind(Ticket ticket){
            ImageButton imageButton = itemView.findViewById(R.id.image_btn);

            if(ticket.getTicketType() == Ticket.TicketType.BUG){
                Glide
                        .with(context)
                        .load(R.drawable.ic_baseline_bug_report_24)
                        .into(imageButton);
            }else {
                Glide
                        .with(context)
                        .load(R.drawable.ic_baseline_rocket_launch_24)
                        .into(imageButton);
            }

            ((TextView)itemView.findViewById(R.id.text_view_ticket_title)).setText(ticket.getTitle());
            ((TextView)itemView.findViewById(R.id.text_view_ticket_description)).setText(ticket.getDescription());
        }
    }
}
