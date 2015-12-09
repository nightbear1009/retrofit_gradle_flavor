package com.mockdatawithflavor;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create an instance of our GitHub API interface.
        SimpleService.GitHub github = Injection.getInjection();

        // Create a call instance for looking up Retrofit contributors.
        Observable<List<SimpleService.Contributor>> call = github.contributors("square", "retrofit");

        // Fetch and print a list of the contributors to the library.
        Log.d("Ted","fire");
        call.observeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Action1<List<SimpleService.Contributor>>() {
                    @Override
                    public void call(List<SimpleService.Contributor> contributors) {
                        for (SimpleService.Contributor contributor : contributors) {
                            Log.d("Ted",contributor.login + " (" + contributor.contributions + ")");
                        }
                    }
                });

    }

}
