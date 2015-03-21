package cn.com.test.hellonotes;

import com.example.hellonotes.R;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity implements
		android.view.View.OnClickListener {

	private Button textbtn, imgbtn, videobtn;
	private ListView lv;
	private Intent intent;
	private MyAdapter myAdapter;
	private NotesDB notesDB;
	private SQLiteDatabase dbReader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	public void initView() {
		lv = (ListView) findViewById(R.id.list);
		textbtn = (Button) findViewById(R.id.text);
		imgbtn = (Button) findViewById(R.id.img);
		videobtn = (Button) findViewById(R.id.vedio);
		textbtn.setOnClickListener(this);
		imgbtn.setOnClickListener(this);
		videobtn.setOnClickListener(this);
		notesDB = new NotesDB(this);
		dbReader = notesDB.getReadableDatabase();
	}

	@Override
	public void onClick(View v) {
		intent = new Intent(this, AddContent.class);
		switch (v.getId()) {
		case R.id.text:
			intent.putExtra("flag", "1");
			startActivity(intent);
			break;

		case R.id.img:
			intent.putExtra("flag", "2");
			startActivity(intent);
			break;
		case R.id.vedio:
			intent.putExtra("flag", "3");
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	public void SelectDB() {

		Cursor cursor = dbReader.query(NotesDB.TABLE_NAME, null, null, null,
				null, null, null);
		myAdapter = new MyAdapter(this, cursor);
		lv.setAdapter(myAdapter);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		SelectDB();
	}
	
}
