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
public class CarroAdapter extends RecyclerView.Adapter<CarroAdapter.MyViewHolderCarro> {
    private Context mContext;
    private List<ItemCarro> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;
    private int width;
    private int height;


    public CarroAdapter(Context c, List<ItemCarro> l) {
        mContext = c;
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        float scale = mContext.getResources().getDisplayMetrics().density;
        width = mContext.getResources().getDisplayMetrics().widthPixels - (int) (14 * scale + 0.5f);
        height = (width / 16) * 9;
    }


    @Override
    public MyViewHolderCarro onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.fragment_card_veiculo, viewGroup, false);
        return new MyViewHolderCarro(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolderCarro myViewHolder, int position) {

        myViewHolder.modeloCarro.setText(mList.get(position).getModelo());
        myViewHolder.descricaoCarro.setText(mList.get(position).getMarca());

        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), mList.get(position).getPhoto());
        bitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);

        bitmap = ImageHelper.getRoundedCornerBitmap(mContext, bitmap, 4, width, height, false, false, true, true);
        myViewHolder.imageCarro.setImageBitmap(bitmap);

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

    public void addListItem(ItemCarro c, int position) {
        mList.add(c);
        notifyItemInserted(position);
    }


    public void removeListItem(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public class MyViewHolderCarro extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageCarro;
        public TextView modeloCarro;
        public TextView descricaoCarro;

        public MyViewHolderCarro(View itemView) {
            super(itemView);
            imageCarro = (ImageView) itemView.findViewById(R.id.imageViewCarro);
            modeloCarro = (TextView) itemView.findViewById(R.id.textModeloCarro);
            descricaoCarro = (TextView) itemView.findViewById(R.id.textDescricaoCarro);
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
