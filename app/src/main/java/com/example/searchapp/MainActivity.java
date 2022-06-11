package com.example.searchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import com.example.searchapp.API.ISearchAPI;
import com.example.searchapp.API.RetrofitClient;
import com.example.searchapp.Adapter.PersonAdapter;
import com.example.searchapp.Model.Person;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    ISearchAPI myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();


    RecyclerView recycler_Search;
    LinearLayoutManager layoutManager;
    PersonAdapter adapter;

    MaterialSearchBar materialSearchBar;
    List<String> suggestList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Init API
        myAPI = getAPI();

        recycler_Search = (RecyclerView) findViewById(R.id.recycler_search);
        recycler_Search.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_Search.setLayoutManager(layoutManager);
        recycler_Search.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));

        materialSearchBar = (MaterialSearchBar) findViewById(R.id.search_bar);
        materialSearchBar.setCardViewElevation(10);

        //Add suggest list
        addSuggestList();

        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                List<String> suggest = new ArrayList<>();
                for(String search_term:suggestList)
                    if(search_term.toLowerCase().contains(materialSearchBar.getText().toLowerCase()))
                        suggest.add(search_term);

                    materialSearchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(!enabled)
                    getAllPerson();
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text.toString());
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });

        getAllPerson();
    }

    private ISearchAPI getAPI() {
        return RetrofitClient.getInstance().create(ISearchAPI.class);
    }

    private void startSearch(String query) {
        compositeDisposable.add(myAPI.searchPerson(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Person>>() {
                    @Override
                    public void accept(List<Person> people) throws Throwable {
                        adapter = new PersonAdapter(people);
                        recycler_Search.setAdapter(adapter);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Toast.makeText(MainActivity.this, "Not Found !!!", Toast.LENGTH_SHORT).show();
                    }
                }));
    }

    private void getAllPerson() {
        compositeDisposable.add(myAPI.getPersonList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Person>>() {
                    @Override
                    public void accept(List<Person> people) throws Throwable {
                        adapter = new PersonAdapter(people);
                        recycler_Search.setAdapter(adapter);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Toast.makeText(MainActivity.this, "Not Found !!!", Toast.LENGTH_SHORT).show();
                    }
                }));
    }

    private void addSuggestList() {
        suggestList.add("Arsen");
        suggestList.add("Tom");
        suggestList.add("Bruce");

        materialSearchBar.setLastSuggestions(suggestList);
    }
}