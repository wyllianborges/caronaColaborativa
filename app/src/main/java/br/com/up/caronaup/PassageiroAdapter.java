package br.com.up.caronaup;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
 * Created by Wyllian on 23/06/2016.
 */
public class PassageiroAdapter extends RecyclerView.Adapter<PassageiroAdapter.MyViewHolderPassageiro> {
    private Context mContext;
    private List<ItemPassageiro> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;
//    private float scale;
//    private int width;
//    private int height;

    public PassageiroAdapter(Context c, List<ItemPassageiro> l) {
        mContext = c;
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

//        scale = mContext.getResources().getDisplayMetrics().density;
//        width = mContext.getResources().getDisplayMetrics().widthPixels - (int) (14 * scale + 0.5f);
//        height = (width / 16) * 9;
    }

    @Override
    public MyViewHolderPassageiro onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.fragment_card_passageiro, viewGroup, false);
        return new MyViewHolderPassageiro(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolderPassageiro myViewHolder, int position) {
        myViewHolder.idPassageiro.setText(mList.get(position).getId());
        myViewHolder.nomePassageiro.setText(mList.get(position).getNome());
        myViewHolder.descricaoPassageiro.setText(mList.get(position).getDescricao());

        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), mList.get(position).getPhoto());
//        bitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
//        bitmap = ImageHelper.getRoundedCornerBitmap(mContext, bitmap, 4, width, height, false, false, true, true);
        myViewHolder.imagePassageiro.setImageBitmap(bitmap);
        
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

    public void addListItem(ItemPassageiro c, int position) {
        mList.add(c);
        notifyItemInserted(position);
    }


    public void removeListItem(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public class MyViewHolderPassageiro extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imagePassageiro;
        public TextView idPassageiro;
        public TextView nomePassageiro;
        public TextView descricaoPassageiro;

        public MyViewHolderPassageiro(View itemView) {
            super(itemView);
            imagePassageiro = (ImageView) itemView.findViewById(R.id.imageViewPassageiroCardPassageiro);
            idPassageiro = (TextView) itemView.findViewById(R.id.idPassageiroCardPassageiro);
            nomePassageiro = (TextView) itemView.findViewById(R.id.textNomePassageiroCardPassageiro);
            descricaoPassageiro = (TextView) itemView.findViewById(R.id.textDescricaoPassageiroCardPassageiro);
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
