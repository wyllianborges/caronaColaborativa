package br.com.up.caronaup;


import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("Carpool");
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        CardView cardViewHome = (CardView) view.findViewById(R.id.cardViewHome1);
        CardView cardViewViajens = (CardView) view.findViewById(R.id.cardViewViajensHome);
        CardView cardViewCaronas = (CardView) view.findViewById(R.id.cardViewCaronasHome);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            StateListAnimator stateListAnimator = AnimatorInflater.loadStateListAnimator(getContext(), R.layout.lift_on_touch);
            cardViewViajens.setStateListAnimator(stateListAnimator);
            cardViewCaronas.setStateListAnimator(stateListAnimator);
            cardViewHome.setStateListAnimator(stateListAnimator);
        }

        cardViewViajens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), NovaViagemActivity.class);
                startActivity(i);
            }
        });

        cardViewCaronas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MapsActivity.class);
                startActivity(i);
            }
        });

        return view;
    }


}
