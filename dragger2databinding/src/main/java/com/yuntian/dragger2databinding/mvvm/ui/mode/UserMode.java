package com.yuntian.dragger2databinding.mvvm.ui.mode;

import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;
import android.widget.Toast;

/**
 * description  .
 * Created by ChuYingYan on 2018/4/30.
 */
public class UserMode  implements IMode {


    public ObservableField<String> name = new ObservableField<>();
    public ObservableInt age = new ObservableInt();

    public UserMode(String pName, int pAge) {
        name.set(pName);
        age.set(pAge);
    }

    @Bindable
    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
        //notifyPropertyChanged(mvvm.wangjing.com.mvvm.BR.name);
    }

    @Bindable
    public int getAge() {
        return age.get();
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public void onItemClick(View pView) {
        Toast.makeText(pView.getContext(), name.get(), Toast.LENGTH_SHORT).show();
        setName("June");
    }


}
