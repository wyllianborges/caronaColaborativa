package br.com.up.caronaup;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class MinhasViagensFragment extends Fragment {


    public MinhasViagensFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_minhas_viagens, container, false);

        Button btn = (Button) view.findViewById(R.id.buttonNewViagem);
        btn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MinhasViagensCadastro.class);
                startActivity(intent);
            }
        });


        return view;
    }

}
