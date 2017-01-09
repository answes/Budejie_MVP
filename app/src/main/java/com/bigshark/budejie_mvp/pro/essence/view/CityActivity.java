package com.bigshark.budejie_mvp.pro.essence.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.bean.CityModel;
import com.bigshark.budejie_mvp.mvp.presenter.impl.MvpBasePresenter;
import com.bigshark.budejie_mvp.pro.base.view.BaseActivity;
import com.bigshark.budejie_mvp.pro.custom_view.MyLetterListView;
import com.bigshark.budejie_mvp.pro.essence.view.db.CityDBManager;
import com.bigshark.budejie_mvp.pro.essence.view.navigation.EssenceActivityNavigationBuilder;
import com.bigshark.budejie_mvp.pro.essence.view.navigation.EssenceNavigationBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 城市选择
 */
public class CityActivity extends BaseActivity
{

	private TextView city, defaultCity;
	private BaseAdapter adapter;
	private ListView mCityLit;
	private TextView overlay;
	private MyLetterListView letterListView;
	private HashMap<String, Integer> alphaIndexer;
	private String[] sections;
	private Handler handler;
	private OverlayThread overlayThread;
	private SQLiteDatabase database;
	private ArrayList<CityModel> mCityNames;

	private String cityName;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_city);
		initToolbar();
		findView();
		Event();
		initData();
	}

	@Override
	public MvpBasePresenter bindPresenter() {
		return null;
	}

	private void initToolbar() {
		RelativeLayout contentLayout = (RelativeLayout) findViewById(R.id.root_layout);
		EssenceActivityNavigationBuilder bar = new EssenceActivityNavigationBuilder(this);
		bar.setTitle("城市").setLeftIcon(R.drawable.left_back)
				.setLeftIconOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						finish();
					}
				});
		bar.createAndBind(contentLayout);
	}

	public static void openCityActivityForReslut(Activity activity,String name){
		Intent intent = new Intent(activity,CityActivity.class);
		intent.putExtra("name",name);
		activity.startActivityForResult(intent,0x1000);
	}

	/**
	 * 初始化数据
	 */
	private void initData()
	{
		city.setText("南宁市");
		CityDBManager dbManager = new CityDBManager(this);
		dbManager.openDateBase();
		dbManager.closeDatabase();
		database = SQLiteDatabase.openOrCreateDatabase(CityDBManager.DB_PATH
				+ "/" + CityDBManager.DB_NAME, null);

		mCityNames = getCityNames();
		database.close();

		alphaIndexer = new HashMap<String, Integer>();
		handler = new Handler();
		overlayThread = new OverlayThread();
		initOverlay();
		setAdapter(mCityNames);

	}

	/**
	 * 设置城市列表到listview
	 * 
	 * @param
	 */
	private void setAdapter(ArrayList<CityModel> list)
	{
		if (list != null)
		{
			adapter = new ListAdapter(this, list);
			mCityLit.setAdapter(adapter);
		}
	}

	/**
	 * ListViewAdapter
	 * 
	 */
	private class ListAdapter extends BaseAdapter
	{
		private LayoutInflater inflater;
		private List<CityModel> list;

		public ListAdapter(Context context, List<CityModel> list)
		{

			this.inflater = LayoutInflater.from(context);
			this.list = list;
			alphaIndexer = new HashMap<String, Integer>();
			sections = new String[list.size()];

			for (int i = 0; i < list.size(); i++)
			{
				// getAlpha(list.get(i));
				String currentStr = list.get(i).getNameSort();
				String previewStr = (i - 1) >= 0 ? list.get(i - 1)
						.getNameSort() : " ";
				if (!previewStr.equals(currentStr))
				{
					String name = list.get(i).getNameSort();
					alphaIndexer.put(name, i);
					sections[i] = name;
				}
			}

		}

		@Override
		public int getCount()
		{
			return list.size();
		}

		@Override
		public Object getItem(int position)
		{
			return list.get(position);
		}

		@Override
		public long getItemId(int position)
		{
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			ViewHolder holder;
			if (convertView == null)
			{
				convertView = inflater.inflate(R.layout.item_essence_collection_city, null);
				holder = new ViewHolder();
				holder.alpha = (TextView) convertView.findViewById(R.id.alpha);
				holder.name = (TextView) convertView.findViewById(R.id.name);
				convertView.setTag(holder);
			}
			else
			{
				holder = (ViewHolder) convertView.getTag();
			}

			holder.name.setText(list.get(position).getCityName());
			String currentStr = list.get(position).getNameSort();
			String previewStr = (position - 1) >= 0 ? list.get(position - 1)
					.getNameSort() : " ";
			if (!previewStr.equals(currentStr))
			{
				holder.alpha.setVisibility(View.VISIBLE);
				holder.alpha.setText(currentStr);
			}
			else
			{
				holder.alpha.setVisibility(View.GONE);
			}
			return convertView;
		}

		private class ViewHolder
		{
			TextView alpha;
			TextView name;
		}

	}

	/**
	 * 初始化导航字母里的数据
	 */
	private void initOverlay()
	{
		LayoutInflater inflater = LayoutInflater.from(this);
		overlay = (TextView) inflater.inflate(R.layout.overlay, null);
		overlay.setVisibility(View.INVISIBLE);
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.TYPE_APPLICATION,
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
						| WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
				PixelFormat.TRANSLUCENT);
		WindowManager windowManager = (WindowManager) this
				.getSystemService(Context.WINDOW_SERVICE);
		windowManager.addView(overlay, lp);
	}

	/**
	 * 获取全部城市
	 * 
	 * @return
	 */
	private ArrayList<CityModel> getCityNames()
	{
		ArrayList<CityModel> names = new ArrayList<CityModel>();
		Cursor cursor = database.rawQuery(
				"SELECT * FROM T_City ORDER BY NameSort", null);
		for (int i = 0; i < cursor.getCount(); i++)
		{
			cursor.moveToPosition(i);
			CityModel cityModel = new CityModel();
			cityModel.setCityName(cursor.getString(cursor
					.getColumnIndex("CityName")));
			cityModel.setNameSort(cursor.getString(cursor
					.getColumnIndex("NameSort")));
			names.add(cityModel);
		}
		return names;
	}

	private void Event()
	{
		letterListView
				.setOnTouchingLetterChangedListener(new LetterListViewListener());
		mCityLit.setOnItemClickListener(new CityListOnItemClick());

		city.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent();
				intent.putExtra("city", city.getText().toString());
				CityActivity.this.setResult(RESULT_OK, intent);
				finish();
			}
		});
		defaultCity.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent();
				intent.putExtra("city", defaultCity.getText().toString());
				CityActivity.this.setResult(RESULT_OK, intent);
				finish();
			}
		});
	}

	private void findView()
	{

		city = (TextView) findViewById(R.id.tv_city);
		mCityLit = (ListView) findViewById(R.id.city_list);
		letterListView = (MyLetterListView) findViewById(R.id.cityLetterListView);
		defaultCity = (TextView) findViewById(R.id.tv_defaultCity);
	}

	class CityListOnItemClick implements OnItemClickListener
	{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
								long arg3)
		{
			CityModel cityModel = (CityModel) mCityLit.getAdapter()
					.getItem(pos);
			Intent intent = new Intent();
			intent.putExtra("city", cityModel.getCityName());
			Log.w("TAG", "city" + cityModel.getCityName());
			CityActivity.this.setResult(RESULT_OK, intent);
			finish();
		}
	}

	private class LetterListViewListener implements
			MyLetterListView.OnTouchingLetterChangedListener
	{

		@Override
		public void onTouchingLetterChanged(final String s)
		{
			if (alphaIndexer.get(s) != null)
			{
				int position = alphaIndexer.get(s);
				mCityLit.setSelection(position);
				overlay.setText(sections[position]);
				overlay.setVisibility(View.VISIBLE);
				handler.removeCallbacks(overlayThread);
				handler.postDelayed(overlayThread, 1500);
			}
		}

	}

	private class OverlayThread implements Runnable
	{
		@Override
		public void run()
		{
			overlay.setVisibility(View.GONE);
		}

	}
}
