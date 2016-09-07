package br.com.up.caronaup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.facebook.Profile;
import com.squareup.picasso.Picasso;

public class PerfilFragment extends Fragment {

    public PerfilFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("Perfil");
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        ImageView imagePerfil = (ImageView)  view.findViewById(R.id.imageFotoPerfil);
        TextView textNome = (TextView) view.findViewById(R.id.textViewNomePerfil);

        CardView cardView1 = (CardView) view.findViewById(R.id.cardViewPerfil1);
        CardView cardView2 = (CardView) view.findViewById(R.id.cardViewPerfil2);
        CardView cardView3 = (CardView) view.findViewById(R.id.cardViewPerfil3);
        CardView cardView4 = (CardView) view.findViewById(R.id.cardViewPerfil4);

        try {
            YoYo.with(Techniques.FadeIn).duration(300).playOn(cardView1);
            YoYo.with(Techniques.FadeIn).duration(500).playOn(cardView2);
            YoYo.with(Techniques.FadeIn).duration(500).playOn(cardView3);
            YoYo.with(Techniques.FadeIn).duration(700).playOn(cardView4);
        } catch (Exception ignored) {
        }

        Profile profile = Profile.getCurrentProfile();
        if (profile != null) {
            textNome.setText(profile.getName());
            String imageProfileProfileUrl = "https://graph.facebook.com/" + profile.getId() + "/picture?type=large";
            Picasso.with(getContext()).load(imageProfileProfileUrl).into(imagePerfil);
        }
        return view;
    }

}
