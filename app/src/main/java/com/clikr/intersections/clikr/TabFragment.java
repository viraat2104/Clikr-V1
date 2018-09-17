package com.clikr.intersections.clikr;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.clikr.intersections.clikr.ClikrHomeActivity;

import org.w3c.dom.Text;

public class TabFragment extends Fragment {

    int position;
    private TextView title_text, desc_text;
    private FloatingActionButton fab, fab_add, fab_1, fab_2;
    private Boolean isFabOpen = false;
    LinearLayout home_card;
    public Animation fab_open,fab_close,rotate_forward,rotate_backward;
    //CardView home_card;

    ClikrHomeActivity c = new ClikrHomeActivity();



    public static Fragment getInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        TabFragment tabFragment = new TabFragment();
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt("pos");


    }

    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //activity = getActivity();



        //View view = ... ;
        switch (position)
        {
            case 0: {
                rootView = inflater.inflate(R.layout.home_tab, container, false);
                //setHomeCardViewVisible(rootView);
                initializeAnimations(rootView);
                initializeViews(rootView);



                break;
            }
            case 1: {
                rootView = inflater.inflate(R.layout.clickier_view_tab, container, false);
                break;
            }

            case 2: {
                rootView = inflater.inflate(R.layout.clicker_groups_tab, container, false);
                break;
            }

        }
        return rootView;



    }







    private void initializeViews(View view) {
        fab = (FloatingActionButton) view.findViewById ( R.id.fab );
        fab_add = (FloatingActionButton) view.findViewById ( R.id.fab_add );
        fab_1 = (FloatingActionButton) view.findViewById ( R.id.fab_1 );
        fab_2 = (FloatingActionButton) view.findViewById ( R.id.fab_2 );
        title_text = (TextView) view.findViewById ( (R.id.titleText) );
        desc_text = view.findViewById(R.id.descriptionText);
        home_card = view.findViewById(R.id.home_card_view);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("main fab","works");
                home_card.setVisibility(View.VISIBLE);

                fab.setVisibility(View.INVISIBLE);

                title_text.requestFocus();


            }
        });

        ((ClikrHomeActivity)getActivity ()).setOnBackPressedListener(new OnBackPressedListener(){
            public void doBack() {
                home_card.setVisibility(View.INVISIBLE);
                fab.setVisibility(View.VISIBLE);

            }
        });

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                animateFAB();
            }
        });

        fab_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            desc_text.setVisibility(View.VISIBLE);
            desc_text.requestFocus();
            }
        });

        fab_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Toast.makeText(ClikrHomeActivity.this,"Clicked2", Toast.LENGTH_SHORT).show();*/

                //animateFAB();
            }
        });

    }

    private void initializeAnimations(View view){

        fab_open = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.rotate_backward);

    }

    private void setHomeCardViewVisible(View view) {
        home_card.setVisibility(View.VISIBLE);
    }

    public void animateFAB(){

        if(isFabOpen){

            fab_add.startAnimation(rotate_backward);
            fab_1.startAnimation(fab_close);
            fab_2.startAnimation(fab_close);
            fab_1.setClickable(false);
            fab_2.setClickable(false);
            isFabOpen = false;
            Log.d("Raj", "close");

        } else {

            fab_add.startAnimation(rotate_forward);
            fab_1.startAnimation(fab_open);
            fab_2.startAnimation(fab_open);
            fab_1.setClickable(true);
            fab_2.setClickable(true);
            isFabOpen = true;
            Log.d("Raj","open");

        }
    }




    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);






    }
}