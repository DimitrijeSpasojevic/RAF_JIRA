package rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import rs.raf.projekat1.dimitrije_spasojevic_10820rn.R;
import rs.raf.projekat1.dimitrije_spasojevic_10820rn.model.Ticket;
import rs.raf.projekat1.dimitrije_spasojevic_10820rn.viewmodels.RecyclerViewModel;

public class AddTicketFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private EditText ticketTitle;
    private EditText ticketDescription;
    private EditText ticketEst;
    private Button btnAddNew;
    private Spinner spinnerType;
    private Spinner spinnerPriority;

    private Ticket.TicketType ticketType;
    private Ticket.TicketPriority ticketPriority;

    private RecyclerViewModel recyclerViewModel;

    public AddTicketFragment() {
        super(R.layout.fragment_new_ticket);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerViewModel = new ViewModelProvider(requireActivity()).get(RecyclerViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initSpinner(view);
        ticketTitle = view.findViewById(R.id.text_input_ticket_title);
        ticketDescription = view.findViewById(R.id.text_input_ticket_description);
        ticketEst = view.findViewById(R.id.est);
        btnAddNew = view.findViewById(R.id.btn_add_new_ticket);
        btnAddNew.setOnClickListener(v -> {
          createNewTicket();
        });
    }

    private void createNewTicket(){
        checkSpinners();
        Ticket ticket = new Ticket(ticketTitle.getText().toString(),
                ticketDescription.getText().toString(),ticketType,ticketPriority);
        recyclerViewModel.addTicket(ticket);
        ticketTitle.setText("");
        ticketDescription.setText("");
        ticketEst.setText("");
        Toast.makeText(requireContext(), "Ticket created", Toast.LENGTH_SHORT).show();
    }

    private void checkSpinners(){
        long ticketPriorityId = spinnerPriority.getSelectedItemId();
        long ticketTypeId = spinnerType.getSelectedItemId();
        switch ((int) ticketPriorityId){
            case 0: ticketPriority = Ticket.TicketPriority.Highest;break;
            case 1: ticketPriority = Ticket.TicketPriority.High;break;
            case 2: ticketPriority = Ticket.TicketPriority.Medium;break;
            case 3: ticketPriority = Ticket.TicketPriority.Low;break;
            default: ticketPriority = Ticket.TicketPriority.Lowest;break;
        }
        switch ((int) ticketTypeId){
            case 0: ticketType = Ticket.TicketType.ENHANCEMENT;break;
            default:ticketType = Ticket.TicketType.BUG;
        }
    }

    private void initSpinner(View view){
        spinnerPriority = (Spinner) view.findViewById(R.id.spinner_ticket_priority);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.priority_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerPriority.setAdapter(adapter);
        spinnerType = (Spinner) view.findViewById(R.id.spinner_ticket_type);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterT = ArrayAdapter.createFromResource(requireContext(),
                R.array.type_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterT.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerType.setAdapter(adapterT);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
