package materialdesign.sriyank.com.appbarlayout;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements NestedScrollView.OnScrollChangeListener{

	private NestedScrollView mNestedScrollView;
	private boolean isTop=true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle("App Bar Layout");
		setSupportActionBar(toolbar);
//		toolbar.inflateMenu(R.menu.menu_main);

		mNestedScrollView= (NestedScrollView) findViewById(R.id.nestedScrollView);
		mNestedScrollView.setOnScrollChangeListener(this);
		Log.d("MainActivity","Scroll Y initial: "+mNestedScrollView.getScrollY());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main,menu);

		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.d("MainActivity","Scroll Y before: "+mNestedScrollView.getScrollY());
		switch(item.getItemId()){
			case R.id.toTop:
				// not animated
//				mNestedScrollView.scrollTo(0,0);
				// animated
				// FIXME ne skrollit prjamo do konca
				// FIXME ne rabotaet, esli nazhali rjadom s toolbar'om (pri melkom scrolle)
				mNestedScrollView.fullScroll(View.FOCUS_UP);
				Log.d("MainActivity","Scroll Y after: "+mNestedScrollView.getScrollY());
//				isTop=true;
				invalidateOptionsMenu();
				break;
		}
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		Log.d("MainActivity","onPrepareOptionsMenu()");
		if(isTop){
			menu.removeItem(R.id.toTop);
		}else{
		}
		return true;
	}

	@Override
	public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
		if(scrollY<2){
//			Log.d("MainActivity","scrollY=0");
			isTop=true;
			invalidateOptionsMenu();
		}else{
			if(isTop==true){
				invalidateOptionsMenu();
			}
//			Log.d("MainActivity","scrollY>0");
			isTop=false;
		}
	}
}
