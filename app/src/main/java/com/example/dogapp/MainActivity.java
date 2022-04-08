package com.example.dogapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.dogapp.databinding.ActivityMainBinding;
import com.example.dogapp.model.DogBreed;
import com.example.dogapp.viewmodel.DogsApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private ArrayList<DogBreed> dogList;
    private DogAdapter dogAdapter;

    private DogsApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        dogList = new ArrayList<DogBreed>();

        apiService = new DogsApiService();
        apiService.getDogs().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<DogBreed>>() {
                    @Override
                    public void onSuccess(@NonNull List<DogBreed> dogBreeds) {
                        for(DogBreed dog: dogBreeds){
                            Log.d("DEBUG1", dog.getName());
                            Log.d("DEBUG1", dogList.toString());
                            dogList.add(dog);
                        }
                        dogAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("DEBUG1", e.getMessage());
                    }
                });
        dogAdapter = new DogAdapter(dogList);
        binding.rvDog.setAdapter(dogAdapter);
        binding.rvDog.setLayoutManager(new GridLayoutManager(this,2));
    }

}