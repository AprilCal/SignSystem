package com.example.aprilcal.signsystem.Busi;

import android.content.Context;

import com.example.aprilcal.signsystem.Dao.SignInDao;
import com.example.aprilcal.signsystem.vo.SignIn;

import java.util.List;

/**
 * Created by AprilCal on 2018/4/25.
 */

public class SignInBusi {
    public static List<SignIn> getAllAbsentStudentBySignID(Context context, int signID){
        return SignInDao.selectAllAbsentBySignID(context,signID);
    }

    public static List<SignIn> getAllPresentStudentBySignID(Context context, int signID){
        return SignInDao.selectAllPresentBySignID(context,signID);
    }

    public static void signIn(Context context, List<SignIn> signInList){
        SignInDao.insert(context, signInList);
    }

    public static void signIn(Context context, SignIn signIn){
        SignInDao.insert(context, signIn);
    }
}
