package com.yuntian.dragger2databinding.mvvm.ui.mode;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;


/**
 * description  .
 * Created by ChuYingYan on 2018/4/30.
 */
public class UserMode extends BaseObservable implements IModeData {


    public ObservableField<String> name = new ObservableField<>();

    public ObservableField<String> age = new ObservableField<>();


    public UserMode(String pName, String pAge) {
        name.set(pName);
        age.set(pAge);
    }

    @Bindable
    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getAge() {
        return age.get();
    }

    public void setAge(String age) {
        this.age.set(age);
        notifyPropertyChanged(BR.age);
    }

    public void onItemClick(View pView) {
        Toast.makeText(pView.getContext(), name.get(), Toast.LENGTH_SHORT).show();
        setName("June");
    }



}
