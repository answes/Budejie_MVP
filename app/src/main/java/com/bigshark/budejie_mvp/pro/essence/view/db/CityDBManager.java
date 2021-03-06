package com.bigshark.budejie_mvp.pro.essence.view.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;


import com.bigshark.budejie_mvp.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 城市列表的数据库的管理类
 */
public class CityDBManager
{
	private final int BUFFER_SIZE = 400000;
	private static final String PACKAGE_NAME = "com.bigshark.budejie_mvp";
	public static final String DB_NAME = "china_city_name.db";
	public static final String DB_PATH = "/data"
			+ Environment.getDataDirectory().getAbsolutePath() + "/"
			+ PACKAGE_NAME; // 存放路径
	private Context mContext;
	private SQLiteDatabase database;

	public CityDBManager(Context context)
	{
		this.mContext = context;
	}

	/**
	 * 被调用方法
	 */
	public void openDateBase()
	{
		this.database = this.openDateBase(DB_PATH + "/" + DB_NAME);

	}

	/**
	 * 打开数据库
	 * 
	 * @param dbFile
	 * @return SQLiteDatabase
	 * @author sy
	 */
	private SQLiteDatabase openDateBase(String dbFile)
	{
		File file = new File(dbFile);
		if (!file.exists())
		{
			// // 打开raw中得数据库文件，获得stream流
			InputStream stream = this.mContext.getResources().openRawResource(
					R.raw.china_city_name);
			try
			{

				// 将获取到的stream 流写入道data中
				FileOutputStream outputStream = new FileOutputStream(dbFile);
				byte[] buffer = new byte[BUFFER_SIZE];
				int count = 0;
				while ((count = stream.read(buffer)) > 0)
				{
					outputStream.write(buffer, 0, count);
				}
				outputStream.close();
				stream.close();
				SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbFile,
						null);
				return db;
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return database;
	}

	/**
	 * 关闭数据库
	 */
	public void closeDatabase()
	{
		if (database != null && database.isOpen())
		{
			this.database.close();
		}
	}
}
