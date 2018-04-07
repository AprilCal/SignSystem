package com.example.aprilcal.signsystem.Busi;

import android.content.Context;

import com.example.aprilcal.signsystem.Dao.CourseDao;
import com.example.aprilcal.signsystem.vo.Course;

/**
 * Created by AprilCal on 2018/4/7.
 */

public class CourseBusi {
    public static boolean createCourse(Context context, Course course){
        CourseDao.insert(context,course);
        return true;
    }

    public static boolean deleteCourse(Context context, int courseID){
        CourseDao.delete(context,courseID);
        return true;
    }
}
