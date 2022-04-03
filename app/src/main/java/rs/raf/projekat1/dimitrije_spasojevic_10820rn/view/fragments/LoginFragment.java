package rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import rs.raf.projekat1.dimitrije_spasojevic_10820rn.R;
import rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.activities.MainActivity;

public class LoginFragment extends Fragment {

    private EditText userName;
    private EditText email;
    private EditText password;
    private Button loginBtn;
    private static final String passwordAdmin = "12345";
    private static final String passwordUser = "67890";
    public static final String prefKeyName = "prefKeyName";
    public static final String prefKeyEmail = "prefKeyEmail";

    public LoginFragment(){
        super(R.layout.fragment_login);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {
        initView(view);
        initInnerFragment();
        initListeners();
    }

    private void initView(View view){
        userName = view.findViewById(R.id.text_input_user_name);
        email = view.findViewById(R.id.text_input_email);
        password = view.findViewById(R.id.text_input_password);
        loginBtn = view.findViewById(R.id.btn_login);
    }

    private void initInnerFragment() {
        // U fragment-u koristimo getChildFragmentManager() za dobijanje fragment managera
//
    }

    private void initListeners(){
        loginBtn.setOnClickListener(view -> {
            String userName = this.userName.getText().toString();
            String email = this.email.getText().toString();
            String password = this.password.getText().toString();

            if(userName.length() == 0 || email.length() == 0 || password.length() == 0)
                Toast.makeText(requireContext(), "Each field must be filled", Toast.LENGTH_SHORT).show();
            else if(password.length() < 5)
                Toast.makeText(requireContext(), "The password must be at least 5 characters", Toast.LENGTH_SHORT).show();
            else if( (userName.startsWith("admin") && password.equals(passwordAdmin)) || password.equals(passwordUser) ){
                  saveUserAndStartMainFragment(userName, email);
            }else
                Toast.makeText(requireContext(), "Incorrect credentials", Toast.LENGTH_SHORT).show();
        });
    }

    private void saveUserAndStartMainFragment(String userName, String email){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getPackageName(), Context.MODE_PRIVATE);
        sharedPreferences
                .edit()
                .putString(prefKeyName,userName)
                .putString(prefKeyEmail,email)
                .apply();
        if(userName.startsWith("admin"))
            MainActivity.isAdminLogged = true;
        else
            MainActivity.isAdminLogged = false;

        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFcv, new MainFragment());
        transaction.commit();
    }
}
