package rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.fragments;

import static rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.fragments.LoginFragment.prefKeyEmail;
import static rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.fragments.LoginFragment.prefKeyName;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import rs.raf.projekat1.dimitrije_spasojevic_10820rn.R;

public class ProfileFragment extends Fragment {

    private Button logoutBtn;
    private TextView textViewName;
    private TextView textViewEmail;


    public ProfileFragment() {
        super(R.layout.fragment_profile);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(requireActivity().getPackageName(), Context.MODE_PRIVATE);
        String name = sharedPreferences.getString(prefKeyName,null);
        String email = sharedPreferences.getString(prefKeyEmail,null);
        textViewName = view.findViewById(R.id.usernameTitle);
        textViewEmail = view.findViewById(R.id.username);
        textViewName.setText(name);
        textViewEmail.setText(email);
        logoutBtn = view.findViewById(R.id.button);
        logoutBtn.setOnClickListener(v ->{
            sharedPreferences
                    .edit()
                    .clear()
                    .apply();

            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.mainFcv, new LoginFragment());
            transaction.commit();
        });
    }
}
