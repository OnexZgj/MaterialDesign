package com.it.onex.materialdesigndemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.it.onex.materialdesigndemo.bean.Movie;
import com.it.onex.materialdesigndemo.net.HttpMethods;

import java.util.ArrayList;

import rx.Subscriber;

/**
 * 带有ToolbarActivity的使用
 */
public class ToolbarActivity extends AppCompatActivity {

    private DrawerLayout dlAtDrawLayout;
    private ArrayList<String> mData = new ArrayList<>();
    private SwipeRefreshLayout srlRefresh;
    private RecyclerView mRvList;
    private Toolbar tbAtToolbar;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);

        //- start ---使用这段代码会出现  label的字体，否则不会出现
        tbAtToolbar = findViewById(R.id.tb_at_toolbar);
        //不设置会显示label的属性
        //也可以在清单文件中进行配置
//        tbAtToolbar.setTitle(" I am toolbar ");
        setSupportActionBar(tbAtToolbar);
        //- end -


        dlAtDrawLayout = findViewById(R.id.dl_at_draw_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer_am);
        }


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, dlAtDrawLayout, tbAtToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        dlAtDrawLayout.addDrawerListener(toggle);
        toggle.syncState();

        FloatingActionButton fabButton = (FloatingActionButton) findViewById(R.id.fab_at_action);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "snack action ", 1000)
                        .setAction("Toast", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(ToolbarActivity.this, " to do ", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });

        mRvList = (RecyclerView) findViewById(R.id.rv_at_list);
        srlRefresh = (SwipeRefreshLayout) findViewById(R.id.srl_refresh);
        srlRefresh.setRefreshing(true);
        mRvList.setLayoutManager(new LinearLayoutManager(this));


        initData();


    }

    private void initData() {

        HttpMethods.getInstance().getTopMovie(new Subscriber<Movie>() {
            @Override
            public void onCompleted() {
                srlRefresh.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                srlRefresh.setRefreshing(false);
            }

            @Override
            public void onNext(Movie movie) {

                mAdapter = new MyAdapter(movie,ToolbarActivity.this);
                mRvList.setAdapter(mAdapter);
                initListener(movie);
            }
        },0,30 );
    }

    private void initListener(final Movie movie) {
        mAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {

            private Intent intent;

            @Override
            public void onItemClick(View view, int position) {



                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        ToolbarActivity.this,
                        view.findViewById(R.id.iv_icon),
                        "basic"
                );


                intent = new Intent(ToolbarActivity.this, MovieDetailActivity.class);
                intent.putExtra("URL",movie.getSubjects().get(position).getImages().getMedium());
                intent.putExtra("NAME",movie.getSubjects().get(position).getTitle());
                startActivity(intent,optionsCompat.toBundle());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toobalr, menu);
        return true;
    }











    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                dlAtDrawLayout.openDrawer(Gravity.START);
                break;

            case R.id.add:
                Toast.makeText(this, "add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tb_setting:
                Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }


}
