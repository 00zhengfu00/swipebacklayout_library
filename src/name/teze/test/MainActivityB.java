package name.teze.test;

import name.teze.layout.lib.SlidingPaneLayout;
import name.teze.layout.lib.ViewHelper;
import name.teze.layout.lib.SlidingPaneLayout.PanelSlideListener;
import name.teze.util.ListViewAdapter;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import name.teze.swipebacklayout.R;

/**
 * 功能： MainActivityB
 * 
 * @author by Fooyou 2014年5月7日 上午11:20:34
 */
public class MainActivityB extends Activity implements PanelSlideListener {

	private TextView localViewTemp;
	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_b);

		listView = (ListView) findViewById(R.id.listView);

		ListViewAdapter mAdapter = new ListViewAdapter(this);
		listView.setAdapter(mAdapter);
		for (int i = 0; i < 200; i++) {
			TestInfo item = new TestInfo();
			item.setName("测试" + i);
			mAdapter.addItem(item);
		}
		mAdapter.notifyDataSetChanged();

	}

	public void buttononClick(View v) {
		Intent intent = new Intent(this, MainActivityC.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onPanelClosed(View arg0) {

	}

	@Override
	public void onPanelOpened(View arg0) {
		super.finish();
	}

	@Override
	public void onPanelSlide(View arg0, float slideOffset) {
		System.out.println("slideOffset == " + slideOffset);
		// int fadeColor = parserColor("#000000");
		// int baseAlpha = (fadeColor & 0xff000000) >>> 24;
		// int imag = (int) (baseAlpha * (1 - slideOffset));
		// int color = imag << 24 | (fadeColor & 0xffffff);
		// getWindow().setBackgroundDrawable(new ColorDrawable(color));
		
		ViewHelper.setAlpha(localViewTemp, (1 - slideOffset));
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		ViewGroup localViewGroup = (ViewGroup) getWindow().getDecorView();
		View localView = localViewGroup.getChildAt(0);
		localViewGroup.removeView(localView);
		SlidingPaneLayout slidingPaneLayout = new SlidingPaneLayout(this);
		slidingPaneLayout.setPanelSlideListener(this);
		// slidingPaneLayout.setSliderFadeColor(Color.RED);
		// slidingPaneLayout.setShadowDrawable(getResources().getDrawable(R.color.beta));
		// slidingPaneLayout.setCoveredFadeColor(Color.GREEN);
		/* slidingPaneLayout.setBackgroundColor(Color.alpha(0)); */

		localViewTemp = new TextView(this);
		/*
		 * localViewTemp.setTextColor(getResources().getColor(R.color.beta));
		 * localViewTemp.setBackgroundResource(android.R.color.black);
		 */
		localViewTemp.setBackgroundResource(android.R.color.black);
		
		ViewGroup.LayoutParams localLayoutParams = new ViewGroup.LayoutParams(
				-1, -1);
		slidingPaneLayout.addView(localViewTemp, localLayoutParams);
		slidingPaneLayout.addView(localView);
		localViewGroup.addView(slidingPaneLayout);
		getWindow().setBackgroundDrawableResource(R.color.alpa);
	}

	/**
	 * 解析颜色
	 * 
	 * @param value
	 * @return
	 */
	private static int parserColor(String value) {
		String regularExpression = ",";
		if (value.contains(regularExpression)) {
			String[] temp = value.split(regularExpression);

			int color = Color.parseColor(temp[0]);
			int alpha = Integer.valueOf(temp[1]);
			int red = (color & 0xff0000) >> 16;
			int green = (color & 0x00ff00) >> 8;
			int blue = (color & 0x0000ff);

			return Color.argb(alpha, red, green, blue);
		}
		return Color.parseColor(value);
	}

}
