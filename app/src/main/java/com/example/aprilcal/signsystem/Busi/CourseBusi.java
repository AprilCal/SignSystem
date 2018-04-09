package com.example.aprilcal.signsystem.Busi;

import android.content.Context;
import android.widget.Toast;

import com.example.aprilcal.signsystem.Dao.CourseDao;
import com.example.aprilcal.signsystem.vo.Course;

import java.util.List;

/**
 * Created by AprilCal on 2018/4/7.
 */

public class CourseBusi {
    public static boolean createCourse(Context context, Course course){
        return CourseDao.insert(context,course);
    }

    public static boolean deleteCourse(Context context, int courseID){
        return CourseDao.delete(context,courseID);
    }

    public static List<Course> getAllCourse(Context context){
        return CourseDao.selectAllCourse(context);
    }
}
