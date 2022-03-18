package com.example.map_fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AutoPermissionsListener {
    Context context;
    private Button tab1, tab2, tab3;
    private Fragment fragment = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this.getBaseContext();
        initView();
        AutoPermissions.Companion.loadAllPermissions(this, 101);
    }

    private void initView() {
        tab1 = findViewById(R.id.btn_tab1);
        tab2 = findViewById(R.id.btn_tab2);
        tab3 = findViewById(R.id.btn_tab3);

        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
        tab3.setOnClickListener(this);

        if(findViewById(R.id.fragment_container) != null){
            Fragment02 fragment02 = new Fragment02();
            fragment02.setArguments(getIntent().getExtras());

            getFragmentManager().beginTransaction().add(R.id.fragment_container, fragment02).commitAllowingStateLoss();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_tab1:
//                fragment = new Fragment01();
//                selectFragment(fragment);
                break;
            case R.id.btn_tab2:
                fragment = new Fragment02();
                selectFragment(fragment);
                break;
            case R.id.btn_tab3:
//                fragment = new Fragment03();
//                selectFragment(fragment);
                break;
        }
    }

    private void selectFragment(Fragment fragment) {
        // 액티비티 내의 프래그먼트를 관리하려면 FragmentManager를 사용해야 함.
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        // FragmentTransaction을 변경하고 나면, 반드시 commit()을 호출해야 변경 내용이 적용됨
        fragmentTransaction.commit();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
    }

    @Override
    public void onDenied(int i, String[] strings) {

    }

    @Override
    public void onGranted(int i, String[] strings) {

    }
}


