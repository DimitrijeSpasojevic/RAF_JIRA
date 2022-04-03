package rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.fragments;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;
import java.util.stream.Collectors;

import rs.raf.projekat1.dimitrije_spasojevic_10820rn.R;
import rs.raf.projekat1.dimitrije_spasojevic_10820rn.model.Ticket;
import rs.raf.projekat1.dimitrije_spasojevic_10820rn.viewmodels.RecyclerViewModel;

public class StatisticsFragment extends Fragment {

    private TextView textViewToDoSum;
    private TextView textViewDoneSum;
    private TextView textViewInProgressSum;
    private TextView textViewBugsToDo;
    private TextView textViewEnhancementsToDo;
    private TextView textViewBugsInProg;
    private TextView textViewEnhancementsInProg;
    private TextView textViewBugsDone;
    private TextView textViewEnhancementsDone;

    private RecyclerViewModel viewModel;
    public StatisticsFragment() {
        super(R.layout.fragment_statistics);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(RecyclerViewModel.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textViewToDoSum = view.findViewById(R.id.sum_todo);
        textViewDoneSum = view.findViewById(R.id.sum_done);
        textViewInProgressSum = view.findViewById(R.id.sum_in_progress);
        textViewBugsToDo= view.findViewById(R.id.b_todo);
        textViewEnhancementsToDo = view.findViewById(R.id.e_todo);
        textViewBugsInProg= view.findViewById(R.id.b_in_progress);
        textViewEnhancementsInProg= view.findViewById(R.id.e_in_progress);
        textViewBugsDone= view.findViewById(R.id.b_done);
        textViewEnhancementsDone= view.findViewById(R.id.e_done);
        viewModel.filterTicket("","todo",Boolean.TRUE);
        viewModel.filterTicket("","inProgress",Boolean.TRUE);
        viewModel.filterTicket("","done",Boolean.TRUE);

        initObservers();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initObservers(){
        viewModel.getToDoTickets().observe(requireActivity(),tickets -> {
            textViewToDoSum.setText(String.valueOf(tickets.size()));
            List<Ticket> toDoEnhList = tickets
                    .stream()
                    .filter(t -> t.getTicketType() == Ticket.TicketType.ENHANCEMENT)
                    .collect(Collectors.toList());
            List<Ticket> toDoBugList = tickets
                    .stream()
                    .filter(t -> t.getTicketType() == Ticket.TicketType.BUG)
                    .collect(Collectors.toList());

            textViewBugsToDo.setText(String.valueOf(toDoBugList.size()));
            textViewEnhancementsToDo.setText(String.valueOf(toDoEnhList.size()));
        });

        viewModel.getInProgressTickets().observe(requireActivity(),tickets -> {
            textViewInProgressSum.setText(String.valueOf(tickets.size()));
            List<Ticket> toDoEnhList = tickets
                    .stream()
                    .filter(t -> t.getTicketType() == Ticket.TicketType.ENHANCEMENT)
                    .collect(Collectors.toList());
            List<Ticket> toDoBugList = tickets
                    .stream()
                    .filter(t -> t.getTicketType() == Ticket.TicketType.BUG)
                    .collect(Collectors.toList());

            textViewBugsInProg.setText(String.valueOf(toDoBugList.size()));
            textViewEnhancementsInProg.setText(String.valueOf(toDoEnhList.size()));
        });

        viewModel.getDoneTickets().observe(requireActivity(),tickets -> {
            textViewDoneSum.setText(String.valueOf(tickets.size()));
            List<Ticket> toDoEnhList = tickets
                    .stream()
                    .filter(t -> t.getTicketType() == Ticket.TicketType.ENHANCEMENT)
                    .collect(Collectors.toList());
            List<Ticket> toDoBugList = tickets
                    .stream()
                    .filter(t -> t.getTicketType() == Ticket.TicketType.BUG)
                    .collect(Collectors.toList());

            textViewBugsDone.setText(String.valueOf(toDoBugList.size()));
            textViewEnhancementsDone.setText(String.valueOf(toDoEnhList.size()));
        });
    }
}
