package br.com.up.caronaup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.Profile;
import com.squareup.picasso.Picasso;

public class PerfilFragment extends Fragment {

    public PerfilFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        ImageView imagePerfil = (ImageView) (ImageView) view.findViewById(R.id.imageFotoPerfil);
        TextView textNome = (TextView) view.findViewById(R.id.textViewNomePerfil);

        Profile profile = Profile.getCurrentProfile();
        if (profile != null) {
            textNome.setText(profile.getName());
            String imageProfileProfileUrl = "https://graph.facebook.com/" + profile.getId() + "/picture?type=large";
            Picasso.with(getContext()).load(imageProfileProfileUrl).into(imagePerfil);
        }
        return view;
    }
}
