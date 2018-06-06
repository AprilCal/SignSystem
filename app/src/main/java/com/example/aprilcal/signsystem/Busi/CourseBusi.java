package com.example.aprilcal.signsystem.Busi;

import android.content.Context;
import com.example.aprilcal.signsystem.Dao.CourseDao;
import com.example.aprilcal.signsystem.vo.Course;
import java.util.List;

/**
 * Created by AprilCal on 2018/4/7.
 */

public class CourseBusi {

    /**
     *
     * @param context
     * @param course
     * @return value of last insert row id;
     */
    public static int createCourse(Context context, Course course){
        return CourseDao.insert(context,course);
    }

    public static boolean deleteCourseByCourseID(Context context, int courseID){
        return CourseDao.delete(context,courseID);
    }

    public static List<Course> getAllCourse(Context context){
        return CourseDao.selectAllCourse(context);
    }
}
