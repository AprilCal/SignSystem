package com.example.aprilcal.signsystem.Adaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.aprilcal.signsystem.R;
import com.example.aprilcal.signsystem.vo.Course;
import java.util.List;

/**
 * Created by AprilCal on 2018/4/8.
 */

public class CourseItemAdaper extends ArrayAdapter<Course> {
    private int resourceId;

    public CourseItemAdaper(Context context, int resource, List<Course> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int positon, View convertView, ViewGroup parent){
        Course course = getItem(positon);
        View view;
        ViewHolder viewHolder;
        if(convertView==null)
        {
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView)view.findViewById(R.id.course_list_content_text_view);
            view.setTag(viewHolder);
        }
        else
        {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }

        if(course.getCourseName()!=null){
            viewHolder.textView.setText(course.getCourseName().toString());
        }
        return view;
    }
}
