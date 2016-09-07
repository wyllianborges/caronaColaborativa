package br.com.up.caronaup;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;

/**
 * Carpool
 * Created by Wyllian on 23/06/2016.
 */
public class CaronaAdapter extends RecyclerView.Adapter<CaronaAdapter.MyViewHolderCarona> {

    private List<ItemCarona> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    public CaronaAdapter(Context c, List<ItemCarona> l) {
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolderCarona onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.fragment_card_carona, viewGroup, false);
        return new MyViewHolderCarona(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolderCarona myViewHolder, int position) {

        myViewHolder.idCarona.setText(mList.get(position).getId());
        myViewHolder.dataCarona.setText(mList.get(position).getData());
        myViewHolder.horarioCarona.setText(mList.get(position).getHorario());
        myViewHolder.destinoCarona.setText(mList.get(position).getDestino());
        myViewHolder.saidaCarona.setText(mList.get(position).getSaida());

        try {
            YoYo.with(Techniques.FadeIn)
                    .duration(300)
                    .playOn(myViewHolder.itemView);
        } catch (Exception ignored) {
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r) {
        mRecyclerViewOnClickListenerHack = r;
    }

    public void addListItem(ItemCarona c, int position) {
        mList.add(c);
        notifyItemInserted(position);
    }

    public void removeListItem(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public class MyViewHolderCarona extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView idCarona;
        public TextView dataCarona;
        public TextView horarioCarona;
        public TextView destinoCarona;
        public TextView saidaCarona;

        public MyViewHolderCarona(View itemView) {
            super(itemView);
            idCarona = (TextView) itemView.findViewById(R.id.textViewIdCardCarona);
            dataCarona = (TextView) itemView.findViewById(R.id.textViewDataCardCarona);
            horarioCarona = (TextView) itemView.findViewById(R.id.textViewHorarioCardCarona);
            destinoCarona = (TextView) itemView.findViewById(R.id.textViewDestinoCardCarona);
            saidaCarona = (TextView) itemView.findViewById(R.id.textViewLocalSaidaCardCarona);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mRecyclerViewOnClickListenerHack != null) {
                mRecyclerViewOnClickListenerHack.onClickListener(v, getPosition());
            }
        }
    }
}
