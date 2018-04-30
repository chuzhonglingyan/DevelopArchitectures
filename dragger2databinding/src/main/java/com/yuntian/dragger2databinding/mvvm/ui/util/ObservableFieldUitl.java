package com.yuntian.dragger2databinding.mvvm.ui.util;

import android.databinding.ObservableDouble;
import android.databinding.ObservableField;
import android.databinding.ObservableFloat;
import android.databinding.ObservableInt;
import android.databinding.ObservableLong;

/**
 * description  .
 * Created by ChuYingYan on 2018/4/30.
 */
public class ObservableFieldUitl {


    public static <T> ObservableField<T> toObservableField(T object){
         ObservableField<T> observableField = new ObservableField(object);
         return  observableField;
    }

    public static ObservableInt toObservableInt(int object){
        ObservableInt observableField = new ObservableInt(object);
        return  observableField;
    }

    public static ObservableLong toObservableLong(long object){
        ObservableLong observableField = new ObservableLong(object);
        return  observableField;
    }

    public static ObservableDouble toObservableDouble(double object){
        ObservableDouble observableField = new ObservableDouble(object);
        return  observableField;
    }

    public static ObservableFloat toObservableFloat(float object){
        ObservableFloat observableField = new ObservableFloat(object);
        return  observableField;
    }

}
