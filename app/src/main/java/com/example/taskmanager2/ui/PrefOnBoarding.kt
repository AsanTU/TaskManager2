package com.dacha.taskmanager1.data.local

import android.content.Context
import android.content.SharedPreferences

class PrefOnBoarding(private val context: Context) {

    private var pref : SharedPreferences = context.getSharedPreferences(PREF_BOARD , Context.MODE_PRIVATE)


    fun isonBoardingShow():Boolean{
        return pref.getBoolean(SHOW_BOARDING , false)
    }
    fun saveOnBoardingShow(isShow: Boolean){
        pref.edit().putBoolean(SHOW_BOARDING , isShow).apply()
    }
    fun getName():String?{
        return pref.getString(SHOW_NAME , "")
    }
    fun saveName(name: String){
        pref.edit().putString(SHOW_NAME , name).apply()
    }
    fun getAge():String?{
        return pref.getString(SHOW_AGE , "")
    }
    fun saveAge(age: String){
        pref.edit().putString(SHOW_AGE , age).apply()
    }


    companion object{
        const val PREF_BOARD = "pref.task"
        const val SHOW_BOARDING = "board"
        const val SHOW_AGE = "age"
        const val SHOW_NAME = "name"
    }
}