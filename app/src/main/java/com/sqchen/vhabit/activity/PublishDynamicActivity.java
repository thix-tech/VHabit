package com.sqchen.vhabit.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.widgets.CustomTitleView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PublishDynamicActivity extends Activity {

    private CustomTitleView mTitleView;

    private LinearLayout linAddPic;

    private ImageView mImageView;

    private static final int CROP_PHOTO = 1;

    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_dynamic);
        initView();
    }

    private void initView() {
        mTitleView = (CustomTitleView) findViewById(R.id.title_view);
        mTitleView.setImgLeft(R.drawable.ic_banner_back);
        mTitleView.setTxtCenter("记录一下");
        mTitleView.setTxtRight("发布");
        mTitleView.setImgLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mImageView = (ImageView) findViewById(R.id.add_dynamic_img);
        linAddPic = (LinearLayout) findViewById(R.id.lin_add_pic);
        linAddPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,CROP_PHOTO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        switch (requestCode) {
            case CROP_PHOTO:
                if(resultCode == RESULT_OK) {

                    //参考链接：https://www.oschina.net/question/157182_53236
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = { MediaStore.Images.Media.DATA };

                    Cursor cursor = getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();
                    mImageView.setVisibility(View.VISIBLE);
                    linAddPic.setVisibility(View.GONE);
                    mImageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                }
                break;
            default:
                break;
        }
    }
}
