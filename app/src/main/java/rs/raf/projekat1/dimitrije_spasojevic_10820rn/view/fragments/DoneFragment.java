package rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import rs.raf.projekat1.dimitrije_spasojevic_10820rn.R;
import rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.recycler.adapter.TicketAdapterDone;
import rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.recycler.differ.TicketDiffItemCallback;
import rs.raf.projekat1.dimitrije_spasojevic_10820rn.viewmodels.RecyclerViewModel;
import rs.raf.projekat1.dimitrije_spasojevic_10820rn.viewmodels.ViewModelSelectedItem;

public class DoneFragment extends Fragment {

    private RecyclerView recyclerView;
    private EditText editText;

    private TicketAdapterDone adapter;
    private RecyclerViewModel recyclerViewModel;
    private ViewModelSelectedItem viewModelSelectedItem;

    public DoneFragment() {
        super(R.layout.fragment_recycler);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerViewModel = new ViewModelProvider(requireActivity()).get(RecyclerViewModel.class);
        viewModelSelectedItem = new ViewModelProvider(requireActivity()).get(ViewModelSelectedItem.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        recyclerViewModel.filterTicket(recyclerViewModel.getFilterDone(),"done",Boolean.FALSE);
    }

    private void init(View view){
        adapter = new TicketAdapterDone(new TicketDiffItemCallback(), ticket -> {
            viewModelSelectedItem.setTicket(ticket);
            startFragment();
        });
        editText = view.findViewById(R.id.search);
        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);
        recyclerViewModel.getDoneTickets().observe(requireActivity(),tickets -> {
            adapter.submitList(tickets);
        });
        initListeners();
    }
    private void initListeners(){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                recyclerViewModel.filterTicket(editable.toString(),"done",Boolean.FALSE);
            }
        });
    }

    private void startFragment(){
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFcv, new DetailsTicketFragment());
        transaction.commit();
    }
}
