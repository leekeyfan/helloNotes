package cn.com.test.hellonotes;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.VideoView;

import com.example.hellonotes.R;

public class AddContent extends Activity implements OnClickListener{

	private String valString;
	private Button saveButton,deleteButton;
	private EditText etText;
	private ImageView c_Img;
	private VideoView c_Video;
	private NotesDB notesDB;
	private SQLiteDatabase dbWriter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addcontent);
		valString = getIntent().getStringExtra("flag");
		saveButton = (Button) findViewById(R.id.save);
		deleteButton = (Button) findViewById(R.id.delete);
		etText = (EditText) findViewById(R.id.ettext);
		c_Img = (ImageView) findViewById(R.id.c_img);
		c_Video = (VideoView) findViewById(R.id.c_video);
		saveButton.setOnClickListener(this);
		deleteButton.setOnClickListener(this);
		notesDB = new NotesDB(this);
		dbWriter = notesDB.getWritableDatabase();
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.save:
			addDB();
			finish();
			break;
		case R.id.delete:
			finish();
			break;
		default:
			break;
		}
	}
	public void addDB() {
		ContentValues cv = new ContentValues();
		cv.put(NotesDB.CONTENT, etText.getText().toString());
		cv.put(NotesDB.TIME, getTime());
//		dbWriter.insert(NotesDB.TABLE_NAME, null, cv);
		dbWriter.insert(NotesDB.TABLE_NAME, null, cv);
		notesDB.close();
		
	}

	private String getTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
		Date curDate = new Date();
		String str = dateFormat.format(curDate);
		return str;
	}
}
