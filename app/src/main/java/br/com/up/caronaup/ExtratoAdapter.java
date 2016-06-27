package br.com.up.caronaup;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;

/**
 * Created by Wyllian on 26/06/2016.
 */
public class ExtratoAdapter extends RecyclerView.Adapter<ExtratoAdapter.MyViewHolderExtrato> {
    private Context mContext;
    private List<ItemExtrato> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;
//    private float scale;
//    private int width;
//    private int height;

    public ExtratoAdapter(Context c, List<ItemExtrato> l) {
        mContext = c;
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

//        scale = mContext.getResources().getDisplayMetrics().density;
//        width = mContext.getResources().getDisplayMetrics().widthPixels - (int) (14 * scale + 0.5f);
//        height = width;
    }


    @Override
    public MyViewHolderExtrato onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.fragment_card_item_extrato, viewGroup, false);
        MyViewHolderExtrato mvh = new MyViewHolderExtrato(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolderExtrato myViewHolder, int position) {

        myViewHolder.tituloCard.setText(mList.get(position).getTitulo());
        myViewHolder.descricaoCard.setText(mList.get(position).getDescricao());
        myViewHolder.valorCard.setText(mList.get(position).getValor());

        if (mList.get(position).getTipo().toString().equals("+")) {
            myViewHolder.imageCard.setImageResource(R.drawable.ic_add_circle_outline);
        } else if (mList.get(position).getTipo().toString().equals("-")) {
            myViewHolder.imageCard.setImageResource(R.drawable.ic_remove_circle_outline);
        } else if (mList.get(position).getTipo().toString().equals("saldo")) {
            myViewHolder.imageCard.setImageResource(R.drawable.ic_credit_card);
        }

        try {
            YoYo.with(Techniques.Tada)
                    .duration(700)
                    .playOn(myViewHolder.itemView);
        } catch (Exception e) {
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r) {
        mRecyclerViewOnClickListenerHack = r;
    }

    public void addListItem(ItemExtrato itemExtrato, int position) {
        mList.add(itemExtrato);
        notifyItemInserted(position);
    }


    public void removeListItem(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public class MyViewHolderExtrato extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageCard;
        public TextView tituloCard;
        public TextView descricaoCard;
        public TextView valorCard;

        public MyViewHolderExtrato(View itemView) {
            super(itemView);
            imageCard = (ImageView) itemView.findViewById(R.id.imageViewCardItemExtrato);
            tituloCard = (TextView) itemView.findViewById(R.id.textViewTituloCardItemExtrato);
            descricaoCard = (TextView) itemView.findViewById(R.id.textViewDescCardItemExtrato);
            valorCard = (TextView) itemView.findViewById(R.id.textViewValorCardItemExtrato);
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
