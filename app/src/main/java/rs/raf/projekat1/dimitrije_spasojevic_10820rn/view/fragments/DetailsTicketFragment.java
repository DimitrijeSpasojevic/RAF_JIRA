package rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import rs.raf.projekat1.dimitrije_spasojevic_10820rn.R;
import rs.raf.projekat1.dimitrije_spasojevic_10820rn.model.Ticket;
import rs.raf.projekat1.dimitrije_spasojevic_10820rn.viewmodels.ViewModelSelectedItem;

public class DetailsTicketFragment extends Fragment {

    private ViewModelSelectedItem viewModelSelectedItem;

    private TextView textViewTitle;
    private TextView textViewType;
    private TextView textViewPriority;
    private TextView textViewEstimation;
    private TextView textViewDesc;
    private Button button;

    public DetailsTicketFragment() {
        super(R.layout.fragment_item_details);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModelSelectedItem = new ViewModelProvider(requireActivity()).get(ViewModelSelectedItem.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textViewDesc = view.findViewById(R.id.text_view_ticket_description);
        textViewEstimation = view.findViewById(R.id.ticket_estimation);
        textViewPriority = view.findViewById(R.id.ticket_priority);
        textViewTitle = view.findViewById(R.id.text_view_item_title);
        textViewType = view.findViewById(R.id.ticket_type);
        button = view.findViewById(R.id.btn_logged_time);
        initView();
    }

    private void initView(){
        viewModelSelectedItem.getTicket().observe(requireActivity(),ticket -> {
            textViewDesc.setText(ticket.getDescription());
            textViewEstimation.setText(ticket.getEstimation());
            textViewPriority.setText(ticket.getTicketPriority().toString());
            textViewTitle.setText(ticket.getTitle());
            textViewType.setText(ticket.getTicketType().toString());
        });
    }
}
