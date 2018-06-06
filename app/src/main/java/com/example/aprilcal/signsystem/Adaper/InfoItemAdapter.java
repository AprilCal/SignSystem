package com.example.aprilcal.signsystem.Adaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.aprilcal.signsystem.Activity.LinkInfo;
import com.example.aprilcal.signsystem.R;
import java.util.List;

/**
 * Created by AprilCal on 2018/4/4.
 */

public class InfoItemAdapter extends ArrayAdapter<LinkInfo>{
    private int resourceId;

    public InfoItemAdapter(Context context, int resource, List<LinkInfo> objects)
    {
        super(context, resource, objects);
        resourceId=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LinkInfo linkInfo=getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView==null)
        {
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView)view.findViewById(R.id.infoitem_content);
            view.setTag(viewHolder);
        }
        else
        {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.textView.setText(linkInfo.getWifi_name());
        return view;
    }
}
