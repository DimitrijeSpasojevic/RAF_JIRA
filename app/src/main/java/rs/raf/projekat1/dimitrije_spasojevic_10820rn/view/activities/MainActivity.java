package rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import rs.raf.projekat1.dimitrije_spasojevic_10820rn.R;
import rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.fragments.LoginFragment;
import rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.fragments.MainFragment;
import rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.viewpager.PagerAdapter;

public class MainActivity extends AppCompatActivity {


    private String name;
    public static final String prefKey = "prefKey";
    private FragmentContainerView mainFcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setContentView(R.layout.activity_main);
    }

    private void initView() {
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);

        splashScreen.setKeepOnScreenCondition(() -> {
            checkIfuserLog();
            return false;
        });
    }

    private void checkIfuserLog(){
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        name = sharedPreferences.getString(prefKey,null);

        if (name == null) startFragment(new LoginFragment());
        else startFragment(new MainFragment());
    }

    private void startFragment(Fragment fragment){

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.mainFcv, fragment);
        transaction.commit();
    }
}