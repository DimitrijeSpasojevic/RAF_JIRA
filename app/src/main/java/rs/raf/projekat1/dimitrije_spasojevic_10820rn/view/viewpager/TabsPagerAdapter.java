package rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.viewpager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.fragments.AddTicketFragment;
import rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.fragments.DoneFragment;
import rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.fragments.InProgressFragment;
import rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.fragments.ProfileFragment;
import rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.fragments.StatisticsFragment;
import rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.fragments.TicketsListFragment;
import rs.raf.projekat1.dimitrije_spasojevic_10820rn.view.fragments.ToDoFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {
    private final int ITEM_COUNT = 3;
    public static final int FRAGMENT_1 = 0;
    public static final int FRAGMENT_2 = 1;
    public static final int FRAGMENT_3 = 2;

    public TabsPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case FRAGMENT_1: fragment = new ToDoFragment(); break;
            case FRAGMENT_2: fragment = new InProgressFragment(); break;
            default: fragment = new DoneFragment(); break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return ITEM_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case FRAGMENT_1: return "ToDo";
            case FRAGMENT_2: return "In progress";
            case FRAGMENT_3: return "Done";
            default: return "None";
        }
    }
}
