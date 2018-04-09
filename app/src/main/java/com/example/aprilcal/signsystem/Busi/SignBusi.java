package com.example.aprilcal.signsystem.Busi;

import android.content.Context;

import com.example.aprilcal.signsystem.Dao.SignDao;
import com.example.aprilcal.signsystem.vo.Sign;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AprilCal on 2018/4/9.
 */

public class SignBusi {
    public static boolean sign(Context context, Sign sign){
        return SignDao.insert(context,sign);
    }

    public static List<Sign> getAllSignByCourseID(Context context, int courseID){
        return SignDao.selectAllSignByCourseID(context,courseID);
    }
}
