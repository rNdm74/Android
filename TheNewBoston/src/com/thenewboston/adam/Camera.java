package com.thenewboston.adam;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Camera extends Activity implements View.OnClickListener {
	final static int cameraData = 0;
	ImageButton takePic;
	Button setWallpaper;
	ImageView returnedPic;
	Intent intent;
	Bitmap takenPic;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo);
		initialize();
		
		InputStream is = getResources().openRawResource(R.drawable.apple_iphone);
		takenPic = BitmapFactory.decodeStream(is);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ibTakePic:
			takePic();
			break;
		case R.id.bSetWallpaper:
			setWallpaper();
			break;
		}
	}

	private void initialize() {
		returnedPic = (ImageView) findViewById(R.id.ivReturnedPic);

		takePic = (ImageButton) findViewById(R.id.ibTakePic);
		takePic.setOnClickListener(this);

		setWallpaper = (Button) findViewById(R.id.bSetWallpaper);
		setWallpaper.setOnClickListener(this);

	}

	private void takePic() {
		intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, cameraData);
	}

	private void setWallpaper() {
		try {
			// Gets the phones wallpaper manager so we can change the background
			WallpaperManager wm = WallpaperManager.getInstance(getApplicationContext());
			
			// Sets the phones wallpaper
			wm.setBitmap(takenPic);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			// get extras for the intent that was passed into method
			Bundle extras = data.getExtras();

			// key reference one is data
			takenPic = (Bitmap) extras.get("data");

			returnedPic.setImageBitmap(takenPic);
		}
	}
}
