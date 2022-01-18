package com.wgw.photopreview;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView.ScaleType;

import com.bumptech.glide.Glide;
import com.wgw.photo.preview.IndicatorType;
import com.wgw.photo.preview.PhotoPreview;
import com.wgw.photo.preview.ShapeTransformType;

import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Main2Activity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        
        final RecyclerView recyclerView = findViewById(R.id.rv);
        final LinearLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        final PhotoAdapter adapter = new PhotoAdapter(Arrays.asList(MainActivity.picDataMore), ScaleType.CENTER_CROP, true);
        recyclerView.setAdapter(adapter);
        
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            PhotoPreview.with(Main2Activity.this)
                .indicatorType(IndicatorType.DOT)
                .selectIndicatorColor(0xffEE3E3E)
                .normalIndicatorColor(0xff3954A0)
                .delayShowProgressTime(200)
                .shapeTransformType(ShapeTransformType.CIRCLE)
                .imageLoader((position1, url, imageView1) ->
                    Glide.with(Main2Activity.this)
                        .load(((String) url))
                        // .override(Target.SIZE_ORIGINAL)
                        .into(imageView1))
                .sources(Arrays.asList(MainActivity.picDataMore))
                .defaultShowPosition(position)
                .animDuration(350L)
                .build()
                .show(position1 -> layoutManager.findViewByPosition(position1).findViewById(R.id.itemIv));
        });
        
        final RecyclerView recyclerView2 = findViewById(R.id.rv2);
        final LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        recyclerView2.setLayoutManager(layoutManager2);
        final PhotoAdapter adapter2 = new PhotoAdapter(Arrays.asList(MainActivity.picDataMore), ScaleType.CENTER_CROP, null);
        recyclerView2.setAdapter(adapter2);
        
        adapter2.setOnItemClickListener((adapter1, view, position) -> {
            PhotoPreview.with(Main2Activity.this)
                .indicatorType(IndicatorType.DOT)
                .selectIndicatorColor(0xffEE3E3E)
                .normalIndicatorColor(0xff3954A0)
                .delayShowProgressTime(200)
                // .shapeTransformType(ShapeTransformType.ROUND_RECT)
                // .shapeCornerRadius(100)
                .imageLoader((position1, url, imageView1) ->
                    Glide.with(Main2Activity.this)
                        .load(((String) url))
                        // .override(Target.SIZE_ORIGINAL)
                        .into(imageView1))
                .sources(Arrays.asList(MainActivity.picDataMore))
                .defaultShowPosition(position)
                .animDuration(350L)
                .build()
                .show(position1 -> {
                    View viewByPosition = layoutManager2.findViewByPosition(position1);
                    if (viewByPosition == null) {
                        return null;
                    }
                    return viewByPosition.findViewById(R.id.itemIv);
                });
        });
    }
}
