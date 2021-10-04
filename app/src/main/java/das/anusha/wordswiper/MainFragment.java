package das.anusha.wordswiper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainFragment extends Fragment {
    ViewPager2 parentScrn;
    Chord scrnChord;
    Chord[] allChords;
    //static method can create itself
    public static Fragment newInstance(ViewPager2 parentScrn, Chord pos, Chord[] allChords){
        MainFragment fragment = new MainFragment();//uses super constructor calls overrided methods
        fragment.parentScrn = parentScrn;
        fragment.scrnChord = pos;
        fragment.allChords = allChords;
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
        super.onViewCreated(view, savedInstanceState);
        //access text, and buttons
        TextView wordTxt = view.findViewById(R.id.words);
        int randExtIndx = scrnChord.getRandomExt();
        String txtViewTxt = "For the chord "+ scrnChord.getBase() + ", is " + scrnChord.getExtString(randExtIndx) + " available?";
        wordTxt.setText(txtViewTxt);
        AppCompatButton yesBtn = view.findViewById(R.id.myBtn1);
        AppCompatButton noBtn = view.findViewById(R.id.myBtn2);

        //can access parent activity layout (no need for activity bundles)
        TabLayout fragBar = getActivity().findViewById(R.id.fragPicker);
        //ADD ALL TABS as frags being created from adapter, creates variables tab and position
        new TabLayoutMediator(fragBar, parentScrn, (tab, position) -> tab.setText(allChords[position].getBase())).attach();
    }

    //add animations to bitmap, ui movement, layout changes, between activities
        //developer.android.com/training/animation/overview
        //define  original shape: GROUP of  PATH values (vector)-drawable folder
            //search up vector attribute possibilites
        //target those paths or whole group and set their animation (animated-vector)-drawable folder
        //specify time location etc(objectAnimator or set-objectAnimator)
            //call in java using ObjectAnimator or AnimatorSet

}

