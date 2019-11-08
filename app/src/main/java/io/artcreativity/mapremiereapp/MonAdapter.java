package io.artcreativity.mapremiereapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MonAdapter extends BaseAdapter {
    private final List<ItemDeMaListe> items;
    private final Context context;
    private int selection = -1;

    public MonAdapter(Context context, List<ItemDeMaListe> items) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.item, null);
        }
        TextView titleView = view.findViewById(R.id.mon_text);
        TextView subTitleView = view.findViewById(R.id.mon_text2);
        ItemDeMaListe item = items.get(i);
        titleView.setText(item.title);
        subTitleView.setText(item.subTitle);
        if(selection == i){
            view.setBackgroundResource(R.color.colorPrimary);
            titleView.setTextColor(context.getResources().getColor(android.R.color.white));
            subTitleView.setTextColor(context.getResources().getColor(android.R.color.white));
        }else {
            view.setBackgroundResource(android.R.color.transparent);
            titleView.setTextColor(context.getResources().getColor(R.color.textColor));
            subTitleView.setTextColor(context.getResources().getColor(R.color.textColor));
        }
        return view;
    }

    public boolean selectItem(int position){
        selection = position;
        notifyDataSetChanged();
        return true;
    }

    public void addItem(ItemDeMaListe item){
        items.add(item);
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        items.remove(position);
        notifyDataSetChanged();
    }
}
