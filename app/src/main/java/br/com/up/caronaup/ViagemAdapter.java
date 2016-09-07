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
 * Created by Wyllian on 23/06/2016.
 */
public class ViagemAdapter extends RecyclerView.Adapter<ViagemAdapter.MyViewHolderViagem> {
    private List<ItemViagem> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    public ViagemAdapter(Context c, List<ItemViagem> l) {
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolderViagem onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.fragment_card_viagem, viewGroup, false);
        return new MyViewHolderViagem(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolderViagem myViewHolder, int position) {

        myViewHolder.idViagem.setText(mList.get(position).getId());
        myViewHolder.dataViagem.setText(mList.get(position).getData());
        myViewHolder.horarioViagem.setText(mList.get(position).getHorario());
        myViewHolder.destinoViagem.setText(mList.get(position).getDestino());
        myViewHolder.saidaViagem.setText(mList.get(position).getSaida());

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

    public void addListItem(ItemViagem c, int position) {
        mList.add(c);
        notifyItemInserted(position);
    }

    public void removeListItem(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public class MyViewHolderViagem extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView idViagem;
        public TextView dataViagem;
        public TextView horarioViagem;
        public TextView destinoViagem;
        public TextView saidaViagem;

        public MyViewHolderViagem(View itemView) {
            super(itemView);
            idViagem = (TextView) itemView.findViewById(R.id.textViewIdCardViagem);
            dataViagem = (TextView) itemView.findViewById(R.id.textViewDataCardViagem);
            horarioViagem = (TextView) itemView.findViewById(R.id.textViewHorarioCardViagem);
            destinoViagem = (TextView) itemView.findViewById(R.id.textViewDestinoCardViagem);
            saidaViagem = (TextView) itemView.findViewById(R.id.textViewLocalSaidaCardViagem);
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
