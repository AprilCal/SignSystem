package com.example.aprilcal.signsystem.Adaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.aprilcal.signsystem.R;
import com.example.aprilcal.signsystem.vo.Sign;

import java.sql.Date;
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

        viewHolder.textView.setText(String.valueOf(new Date(sign.getSignDate()))+" "+sign.getTotalNumber()+"/"+sign.getActualNumber());
        return view;
    }
}
