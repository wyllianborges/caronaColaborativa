package br.com.up.caronaup;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Eduardo on 26/06/2016.
 */
public class listviewAdapter extends BaseAdapter {
    public ArrayList<Model> productList;
    Activity activity;

    public listviewAdapter(Activity activity, ArrayList<Model> productList) {
        super();
        this.activity = activity;
        this.productList = productList;
    }


    @Override
    public int getCount() { return productList.size(); }

    @Override
    public Object getItem(int position) { return productList.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    public class ViewHolder {
        TextView mSNo;
        TextView mProduct;
        // TextView mCategory;
        TextView mPrice;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null){
            convertView = inflater.inflate(R.layout.listview_row, null);
            holder = new ViewHolder();
            holder.mSNo = (TextView) convertView.findViewById(R.id.sNo);
            holder.mProduct = (TextView) convertView.findViewById(R.id.product);
            //holder.mCategory = (TextView) convertView.findViewById(R.id.category);
            holder.mPrice = (TextView) convertView.findViewById(R.id.price);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Model item = productList.get(position);
        holder.mSNo.setText(item.getsNo().toString());
        holder.mProduct.setText(item.getProduct().toString());
        //holder.mCategory.setText(item.getCategory().toString());
        holder.mPrice.setText(item.getPrice().toString());

        return convertView;
    }
}
