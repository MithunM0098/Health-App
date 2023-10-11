package com.example.health_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MySymptomsAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mlist;
    private LayoutInflater mInflater;
    public MySymptomsAdapter(Context context,List<String> list){
        this.mContext=context;
        this.mlist=list;
        this.mInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    static class ViewHolder{
        protected TextView symtoms;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (view==null){
            view=mInflater.inflate(R.layout.list_mysymptoms,null,true);
            viewHolder = new ViewHolder();

            viewHolder.symtoms=(TextView)view.findViewById(R.id.my_symptoms);

            view.setTag(viewHolder);



        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.symtoms.setTag(position);
        viewHolder.symtoms.setText(mlist.get(position));
        // viewHolder.tvTagCount.setText(mlist.get(position));
        //viewHolder.tvTagRssi.setText(mlist.get(position));

        return view;
    }
}
