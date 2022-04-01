package rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import rs.raf.projekat1.dimitrije_spasojevic_10820rn.R;

public class LoginFragment extends Fragment {

    public LoginFragment(){
        super(R.layout.fragment_login);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        initInnerFragment();
    }

    private void initInnerFragment() {
        // U fragment-u koristimo getChildFragmentManager() za dobijanje fragment managera
//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//        transaction.add(R.id.outerFragmentFcv, new FirstFragment());
//        transaction.commit();
    }
}
