package com.example.mobilehanzjoki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Switch;

import com.example.mobilehanzjoki.databinding.ActivityMainBinding;
import com.example.mobilehanzjoki.databinding.FragmentHomeBinding;
import com.example.mobilehanzjoki.fragment.HomeFragment;
import com.example.mobilehanzjoki.fragment.OrderFragment;
import com.example.mobilehanzjoki.fragment.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigationBar.setOnItemSelectedListener(item -> {


            switch(item.getItemId()) {
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;

                case R.id.list_order:
                    replaceFragment(new OrderFragment());
                    break;

                case R.id.account:
                    replaceFragment(new ProfileFragment());
                    break;
            }

            return true;
        });
    }
    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
}