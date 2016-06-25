package br.com.up.caronaup;

import android.view.View;

/**
 * Created by Wyllian on 23/06/2016.
 */
public interface RecyclerViewOnClickListenerHack {
    void onClickListener(View view, int position);
    void onLongPressClickListener(View view, int position);
}