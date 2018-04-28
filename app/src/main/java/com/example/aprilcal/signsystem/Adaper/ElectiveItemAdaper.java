package com.example.aprilcal.signsystem.Adaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.aprilcal.signsystem.R;
import com.example.aprilcal.signsystem.vo.Elective;
import java.util.List;

/**
 * Created by AprilCal on 2018/4/24.
 */

public class ElectiveItemAdaper extends ArrayAdapter<Elective> {
    private int resourceId;

    public ElectiveItemAdaper(Context context, int resource, List<Elective> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int positon, View convertView, ViewGroup parent){
        Elective elective = getItem(positon);
        View view;
        ViewHolder viewHolder;
        if(convertView==null)
        {
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView)view.findViewById(R.id.elective_list_text_view);
            view.setTag(viewHolder);
        }
        else
        {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }

        if(elective.getStudentName()!=null){
            viewHolder.textView.setText(elective.getSchoolID()+"  "+elective.getStudentName());
        }
        return view;
    }
}
