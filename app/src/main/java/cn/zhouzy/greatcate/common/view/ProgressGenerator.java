package cn.zhouzy.greatcate.common.view;

import java.util.Random;

import android.os.Handler;

public class ProgressGenerator
{

	private int mProgress;
	private boolean isRunning = false;
	private ProcessButton button;

	public ProgressGenerator(ProcessButton button)
	{
		super();
		this.button = button;
	}

	public boolean isRunning()
	{
		return isRunning;
	}

	public void setRunning(boolean isRunning)
	{
		this.isRunning = isRunning;
	}

	public void start()
	{
		final Handler handler = new Handler();
		isRunning = true;

		handler.postDelayed(new Runnable()
		{

			@Override
			public void run()
			{
				if (isRunning)
				{
					mProgress += 1;
					button.setProgress(mProgress);
					if (mProgress < 99)
					{
						handler.postDelayed(this, generateDelay());
					} else
					{
						mProgress = 1;
						handler.postDelayed(this, generateDelay());
					}
				}
			}
		}, generateDelay());
	}

	private Random random = new Random();

	private int generateDelay()
	{
		return random.nextInt(100);
	}

	public void onSuccessed()
	{
		isRunning = false;
		button.setProgress(100);
	}

	public void onError()
	{
		isRunning = false;
		button.setProgress(-1);
	}

}
