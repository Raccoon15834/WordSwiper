package das.anusha.wordswiper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

public class MainFragment extends Fragment {
    ViewPager2 parentScrn;
    int position;
    //static method can create itself
    public static Fragment newInstance(ViewPager2 parentScrn, int pos){
        MainFragment fragment = new MainFragment();//uses super constructor calls overrided methods
        fragment.parentScrn = parentScrn;
        fragment.position = pos;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState); inflate layout ourselves
        return inflater.inflate(R.layout.frag, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //TabLayout bar =
        //fragment can set values of parent activity (new activities send info back and forth through bundles)
        super.onViewCreated(view, savedInstanceState);
        //access text
        TextView wordTxt = view.findViewById(R.id.words);
        wordTxt.setText("word array index: "+ position);
    }
}

