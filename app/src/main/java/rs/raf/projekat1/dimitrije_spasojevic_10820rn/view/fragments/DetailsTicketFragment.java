package rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;

import java.util.List;

import rs.raf.projekat1.dimitrije_spasojevic_10820rn.R;
import rs.raf.projekat1.dimitrije_spasojevic_10820rn.model.Ticket;
import rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.activities.MainActivity;
import rs.raf.projekat1.dimitrije_spasojevic_10820rn.viewmodels.RecyclerViewModel;

public class DetailsTicketFragment extends Fragment {


    private TextView textViewTitle;
    private TextView textViewType;
    private TextView textViewPriority;
    private TextView textViewEstimation;
    private TextView textViewDesc;
    private ImageView imageView;
    private Button button;
    private Button buttonEditTicket;
    private Ticket ticket;
    private Context context;
    private RecyclerViewModel recyclerViewModel;
    public DetailsTicketFragment(Ticket ticket) {
        super(R.layout.fragment_item_details);
        this.ticket = ticket;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerViewModel = new ViewModelProvider(requireActivity()).get(RecyclerViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonEditTicket = view.findViewById(R.id.btn_edit_ticket);
        textViewDesc = view.findViewById(R.id.item_description);
        textViewEstimation = view.findViewById(R.id.ticket_estimation);
        textViewPriority = view.findViewById(R.id.ticket_priority);
        textViewTitle = view.findViewById(R.id.text_view_item_title);
        textViewType = view.findViewById(R.id.ticket_type);
        imageView = view.findViewById(R.id.imageView);
        button = view.findViewById(R.id.btn_logged_time);
        context = view.getContext();
        initView();
        button.setOnClickListener(v -> {
            int loggedTime = Integer.valueOf(button.getText().toString());
            loggedTime++;
            button.setText(String.valueOf(loggedTime));
        });
        button.setOnLongClickListener(v ->{
            int loggedTime = Integer.valueOf(button.getText().toString());
            loggedTime--;
            button.setText(String.valueOf(loggedTime));
            return true;
        });
        List<Ticket> ticketsTodo = recyclerViewModel.getToDoTickets().getValue();
        List<Ticket> ticketsInProg = recyclerViewModel.getInProgressTickets().getValue();

        if(MainActivity.isAdminLogged && ( ticketsTodo.contains(ticket) || ticketsInProg.contains(ticket) )){
            buttonEditTicket.setVisibility(View.VISIBLE);
            buttonEditTicket.setOnClickListener(view1 -> {
               startFragment();
            });
        }
    }

    private void initView(){
            textViewDesc.setText(ticket.getDescription());
            textViewEstimation.setText(ticket.getEstimation());
            textViewPriority.setText(ticket.getTicketPriority().toString());
            textViewTitle.setText(ticket.getTitle());
            textViewType.setText(ticket.getTicketType().toString());
        if(ticket.getTicketType() == Ticket.TicketType.BUG){
            Glide
                    .with(this)
                    .load(R.drawable.ic_baseline_bug_report_24)
                    .into(imageView);
        }else {
            Glide
                    .with(this)
                    .load(R.drawable.ic_baseline_rocket_launch_24)
                    .into(imageView);
        }
    }

    private void startFragment(){
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
        transaction.replace(R.id.mainFcv, new EditTicketFragment(ticket));
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
