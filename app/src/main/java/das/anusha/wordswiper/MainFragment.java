package das.anusha.wordswiper;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
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
        TextView extTxt = view.findViewById(R.id.words);
        TextView baseTxt = view.findViewById(R.id.baseChordName);
        int randExtIndx = scrnChord.getRandomExt();
        String txtViewTxt = scrnChord.getBase() + "\n" + scrnChord.getNotes();
        extTxt.setText(scrnChord.getExtString(randExtIndx));
        baseTxt.setText(txtViewTxt);
        boolean correctness = scrnChord.isAvailable(randExtIndx);

//        ObjectAnimator correct = (ObjectAnimator) AnimatorInflater.loadAnimator(view.getContext(), R.animator.button_correct);
//        ObjectAnimator wrong = (ObjectAnimator) AnimatorInflater.loadAnimator(view.getContext(), R.animator.button_wrong);
        Animator correct = AnimatorInflater.loadAnimator(view.getContext(), R.animator.button_correct);
        Animator wrong =  AnimatorInflater.loadAnimator(view.getContext(), R.animator.button_wrong);
        ImageView yesCheck = view.findViewById(R.id.checkYes);
        Animator checkAnim = AnimatorInflater.loadAnimator(view.getContext(), R.animator.popupscheck);
        AppCompatButton yesBtn = view.findViewById(R.id.myBtn1);
        AppCompatButton noBtn = view.findViewById(R.id.myBtn2);
        yesBtn.setOnClickListener(new btnReaction(yesBtn, correctness, wrong, correct, yesCheck, checkAnim));
        noBtn.setOnClickListener(new btnReaction(noBtn, correctness, correct, wrong, yesCheck, checkAnim));


        //can access parent activity layout (no need for activity bundles)
        TabLayout fragBar = getActivity().findViewById(R.id.fragPicker);
        //ADD ALL TABS as frags being created from adapter, creates variables tab and position
        new TabLayoutMediator(fragBar, parentScrn, (tab, position) -> tab.setText(allChords[position].getBase())).attach();
    }
     class btnReaction implements View.OnClickListener {
        Button btn;
        ImageView check;
        boolean isCorrect;
        Animator redAnim, greenAnim, checker;
        public btnReaction(Button btn, boolean isCorrect, Animator redAnim, Animator greenAnim, ImageView check, Animator checker){
            this.btn = btn;
            this.isCorrect = isCorrect;
            this.redAnim = redAnim;
            this.greenAnim = greenAnim;
            check.setVisibility(View.VISIBLE);
            this.check = check;
            this.checker = checker;
        }
        @Override
        public void onClick(View view) {
            if (isCorrect){
                greenAnim.setTarget(btn);
                greenAnim.start();
                checker.setTarget(check);
                checker.start();
            }
            else{
                redAnim.setTarget(btn);
                redAnim.start();
            }
        }
    }

}

