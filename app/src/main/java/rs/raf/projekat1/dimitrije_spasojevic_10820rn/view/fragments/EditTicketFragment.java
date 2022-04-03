package rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import rs.raf.projekat1.dimitrije_spasojevic_10820rn.R;
import rs.raf.projekat1.dimitrije_spasojevic_10820rn.model.Ticket;

public class EditTicketFragment extends Fragment {


    private EditText ticketTitle;
    private EditText ticketDescription;
    private EditText ticketEst;
    private Spinner spinnerType;
    private Spinner spinnerPriority;
    private Button btnSave;
    private Ticket ticket;

    public EditTicketFragment(Ticket ticket) {
        super(R.layout.fragment_edit_ticket);
        this.ticket = ticket;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnSave = view.findViewById(R.id.btn_save);
        ticketTitle = view.findViewById(R.id.text_input_ticket_title);
        ticketDescription = view.findViewById(R.id.text_input_ticket_description);
        ticketEst = view.findViewById(R.id.est);
        spinnerPriority = view.findViewById(R.id.spinner_ticket_priority);
        spinnerType = view.findViewById(R.id.spinner_ticket_type);
        ticketEst.setText(ticket.getEstimation());
        ticketTitle.setText(ticket.getTitle());
        ticketDescription.setText(ticket.getDescription());
        spinnerType.setSelection(Ticket.TicketType.valueOf(ticket.getTicketType().toString()).ordinal());
        spinnerPriority.setSelection(Ticket.TicketType.valueOf(ticket.getTicketPriority().toString()).ordinal());

        btnSave.setOnClickListener(v -> {
            ticket.setTitle(ticketTitle.getText().toString());
            ticket.setDescription(ticketDescription.getText().toString());
            ticket.setEstimation(ticketEst.getText().toString());
            ticket.setTicketPriority(Ticket.TicketPriority.values()[(int)spinnerPriority.getSelectedItemId()]);
            ticket.setTicketType(Ticket.TicketType.values()[(int)spinnerType.getSelectedItemId()]);
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
            transaction.replace(R.id.mainFcv, new DetailsTicketFragment(ticket));
            transaction.commit();
        });
    }
}
