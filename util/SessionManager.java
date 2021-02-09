package com.rizieq.barangku.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.rizieq.barangku.login.LoginActivity;

import java.util.HashMap;

public class SessionManager {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    public static final String KEY_NAMA = "nama";
    public static final String KEY_JK = "jk";
    public static final String KEY_ID = "id";
    public static final String KEY_ALAMAT = "alamat";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_FOTO = "foto";
    private static final String is_login = "logginstatus";
    private final String SHARE_NAME = "loginsession";
    private final int MODE_PRIVATE = 0;
    private Context _context;

    public SessionManager (Context context)
    {
        this._context = context;
        sp = _context.getSharedPreferences(SHARE_NAME,MODE_PRIVATE);
        editor = sp.edit();
    }

    public void storeLogin(String nama, String jk, String alamat, String email, String foto, String id)
    {
        editor.putBoolean(is_login,true);
        editor.putString(KEY_NAMA,nama);
        editor.putString(KEY_JK,jk);
        editor.putString(KEY_ALAMAT,alamat);
        editor.putString(KEY_EMAIL,email);
        editor.putString(KEY_FOTO,foto);
        editor.putString(KEY_ID,id);


        editor.commit();


    }

    public HashMap getDetailLogin()
    {
        HashMap<String,String> map = new HashMap<>();
        map.put(KEY_NAMA, sp.getString(KEY_NAMA,null));
        map.put(KEY_JK, sp.getString(KEY_JK,null));
        map.put(KEY_ALAMAT, sp.getString(KEY_ALAMAT,null));
        map.put(KEY_EMAIL, sp.getString(KEY_EMAIL,null));
        map.put(KEY_FOTO, sp.getString(KEY_FOTO,null));
        map.put(KEY_ID, sp.getString(KEY_ID,null));

        return map;
    }

    public void checkLogin()
    {
        if (!this.Loggin())
        {
            Intent login = new Intent(_context, LoginActivity.class);
            login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(login);
        }
    }

    public void logout()
    {
        editor.clear();
        editor.commit();
    }

    public Boolean Loggin()
    {
        return sp.getBoolean(is_login,false);
    }


}
