package rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.recycler.adapter;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.activities.MainActivity;

public class TicketAdapterToDo extends ListAdapter<Ticket,TicketAdapterToDo.ViewHolder> {

    private final Consumer<ClickConsumer> onTicketClicked;
    private ClickConsumer.Click click;


    public TicketAdapterToDo(@NonNull DiffUtil.ItemCallback<Ticket> diffCallback, Consumer<ClickConsumer> onTicketClicked) {
        super(diffCallback);
        this.onTicketClicked = onTicketClicked;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_list_item_todo,parent,false);
        if(!MainActivity.isAdminLogged){
            view.findViewById(R.id.img_remove).setVisibility(View.GONE);
        }
        return new TicketAdapterToDo.ViewHolder(view, parent.getContext(), position ->{
            Ticket ticket = getItem(position);
            onTicketClicked.accept(new ClickConsumer(ticket, this.click));
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

            ImageButton imageButton = itemView.findViewById(R.id.image_btn);
            ImageButton buttonRemove = itemView.findViewById(R.id.img_remove);
            ImageButton buttonReplaceAtInProgress = itemView.findViewById(R.id.img_next);

            imageButton.setOnClickListener(v -> {
                if ( getAdapterPosition() != RecyclerView.NO_POSITION) {
                    click= ClickConsumer.Click.DETAILS;
                    onItemClicked.accept(getAdapterPosition());
                }
            });
            buttonRemove.setOnClickListener(v -> {
                if ( getAdapterPosition() != RecyclerView.NO_POSITION) {
                    click = ClickConsumer.Click.REMOVE;
                    onItemClicked.accept(getAdapterPosition());
                }
            });
            buttonReplaceAtInProgress.setOnClickListener(v -> {
                if ( getAdapterPosition() != RecyclerView.NO_POSITION) {
                    click = ClickConsumer.Click.ADD_IN_PROGRESS;
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
