package rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import rs.raf.projekat1.dimitrije_spasojevic_10820rn.R;
import rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.recycler.adapter.TicketAdapterInProgress;
import rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.recycler.differ.TicketDiffItemCallback;
import rs.raf.projekat1.dimitrije_spasojevic_10820rn.viewmodels.RecyclerViewModel;

public class InProgressFragment extends Fragment {

    private RecyclerView recyclerView;
    private EditText editText;

    private TicketAdapterInProgress adapter;
    private RecyclerViewModel recyclerViewModel;

    public InProgressFragment() {
        super(R.layout.fragment_recycler);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerViewModel = new ViewModelProvider(requireActivity()).get(RecyclerViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view){
        adapter = new TicketAdapterInProgress(new TicketDiffItemCallback(), ticket -> {

        });
        editText = view.findViewById(R.id.search);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);
        recyclerViewModel.getInProgressTickets().observe(requireActivity(),tickets -> {
            adapter.submitList(tickets);
        });

    }
}
