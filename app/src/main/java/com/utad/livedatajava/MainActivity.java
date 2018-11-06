package com.utad.livedatajava;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private MutableLiveData<Boolean> mWorkHouse = new MutableLiveData<>();
    private MutableLiveData<Boolean> mStudyPass = new MutableLiveData<>();
    private MutableLiveData<Integer> mHasYears = new MutableLiveData<>();
    @BindView(R.id.switchWork) Switch swW;
    @BindView(R.id.textW) TextView txtW;
    @BindView(R.id.switchStudy) Switch swS;
    @BindView(R.id.textS) TextView txtS;
    @BindView(R.id.textEdit) EditText edthas;
    @BindView(R.id.textA) TextView txtA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        configureObserversHouse();
        configureObserversPass();
        configureObserversHas();
        onClickHouse();
        onClickStudy();
        yearsWatch();
    }

    private void configureObserversHouse() {

        mWorkHouse.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean bool) {
                if (bool == true) {
                    txtW.setText("Si trabajo en casa");
                } else if (bool == false) {
                    txtW.setText("No trabajo en casa");
                }
            }
        });
    }

    private void configureObserversPass() {
        mStudyPass.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean bool) {
                if (bool == true) {
                    txtS.setText("Si voy aprobar");
                } else if (bool == false) {
                    txtS.setText("No voy aprobar");
                }
            }
        });
    }

    private void configureObserversHas() {
        mHasYears.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer e) {
                if (!e.equals(null)) {
                    txtA.setText("Tengo " + e + " años");
                }
            }
        });
    }

    public void onClickHouse() {
        swW.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    mWorkHouse.postValue(true);
                } else {
                    mWorkHouse.postValue(false);
                }
            }
        });
    }

    public void onClickStudy() {
        swS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    mStudyPass.postValue(true);
                } else {
                    mStudyPass.postValue(false);
                }
            }
        });
    }

    public void yearsWatch() {
        edthas.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    if(s.equals(null)) {
                        mHasYears.postValue(Integer.valueOf(s.toString()));
                    }else{
                        mHasYears.postValue(0);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
