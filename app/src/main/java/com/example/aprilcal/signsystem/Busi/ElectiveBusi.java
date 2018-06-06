package com.example.aprilcal.signsystem.Busi;

import android.content.Context;
import com.example.aprilcal.signsystem.Dao.ElectiveDao;
import com.example.aprilcal.signsystem.vo.Elective;
import java.util.List;

/**
 * Created by AprilCal on 2018/4/21.
 */

public class ElectiveBusi {

    public static Elective getElectiveBySchoolID(Context context, String schoolID){
        return ElectiveDao.selectElectiveBySchoolID(context, schoolID);
    }

    /**
     * insert an item of elective into db;
     * @param context
     * @param elective
     */
    //TODO return value;
    public static void elect(Context context, Elective elective){
        ElectiveDao.insert(context,elective);
    }

    /**
     * Insert a list of elective into db;
     * @param context
     * @param electiveList
     */
    public static void elect(Context context, List<Elective> electiveList){
        ElectiveDao.insert(context,electiveList);
    }

    public static List<Elective> getAllElectiveByCourseID(Context context, int courseID){
        return ElectiveDao.selectAllElectiveByCourseID(context,courseID);
    }

    public static void removeElectiveByCourseID(Context context, int courseID){
        ElectiveDao.deleteAllByCourseID(context,courseID);
    }

    public static void retreat(Context context, String schoolID, int courseID){
        ElectiveDao.deleteByCourseIDAndSchoolID(context,schoolID,courseID);
    }

    public static void batchRetreat(Context context, List<Elective> retreatList){
        for(Elective elective : retreatList){
            ElectiveDao.deleteByCourseIDAndSchoolID(context,elective.getSchoolID(),elective.getCourseID());
        }
    }
}
