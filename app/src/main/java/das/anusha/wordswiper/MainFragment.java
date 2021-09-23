package das.anusha.wordswiper;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

public class MainFragment extends Fragment {
    ViewPager2 parentScrn;
    //static method can create itself
    public static Fragment newInstance(ViewPager2 parentScrn){
        MainFragment fragment = new MainFragment();
        fragment.parentScrn = parentScrn;
    }
}
