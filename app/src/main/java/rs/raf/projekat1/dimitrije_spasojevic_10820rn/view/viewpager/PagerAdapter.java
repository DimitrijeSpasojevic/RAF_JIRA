package rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.viewpager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import rs.raf.projekat1.dimitrije_spasojevic_10820rn.R;
import rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.fragments.AddTicketFragment;
import rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.fragments.ProfileFragment;
import rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.fragments.StatisticsFragment;
import rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.fragments.TicketsListFragment;

public class PagerAdapter extends FragmentPagerAdapter {
    private final int ITEM_COUNT = 4;
    public static final int FRAGMENT_1 = 0;
    public static final int FRAGMENT_2 = 1;
    public static final int FRAGMENT_3 = 2;
    public static final int FRAGMENT_4 = 3;

    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case FRAGMENT_1: fragment = new StatisticsFragment(); break;
            case FRAGMENT_2: fragment = new AddTicketFragment(); break;
            case FRAGMENT_3: fragment = new TicketsListFragment(); break;
            default: fragment = new ProfileFragment(); break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return ITEM_COUNT;
    }

}
