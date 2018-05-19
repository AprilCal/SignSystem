package com.example.aprilcal.signsystem.Adaper;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.aprilcal.signsystem.R;
import com.example.aprilcal.signsystem.vo.Sign;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by AprilCal on 2018/4/9.
 */

public class SignItemAdaper extends ArrayAdapter<Sign> {
    private int resourceId;

    public SignItemAdaper(Context context, int resource, List<Sign> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int positon, View convertView, ViewGroup parent){
        Sign sign = getItem(positon);
        View view;
        ViewHolder viewHolder;


        long date = sign.getSignDate();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if(convertView==null)
        {
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView)view.findViewById(R.id.sign_item_text_view);
            view.setTag(viewHolder);
        }
        else
        {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.textView.setText(df.format(new java.util.Date(date))+" "+sign.getTotalNumber()+"/"+sign.getActualNumber());
        return view;
    }
}
