package rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import rs.raf.projekat1.dimitrije_spasojevic_10820rn.R;
import rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.fragments.LoginFragment;
import rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {


    private String name;
    public static final String prefKeyName = "prefKeyName";
    public static final String prefKeyEmail = "prefKeyEmail";
    public static boolean isAdminLogged;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setContentView(R.layout.activity_main);
    }

    private void initView() {
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);

        splashScreen.setKeepOnScreenCondition(() -> {
            checkIfUserLog();
            return false;
        });
    }

    private void checkIfUserLog(){
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        name = sharedPreferences.getString(prefKeyName,null);

        if (name == null) startFragment(new LoginFragment());
        else {
            if(name.startsWith("admin"))
                isAdminLogged = true;
            else
                isAdminLogged = false;
            startFragment(new MainFragment());
        }
    }

    private void startFragment(Fragment fragment){

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.mainFcv, fragment);
        transaction.commit();
    }
}