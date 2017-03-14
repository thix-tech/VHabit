package com.sqchen.vhabit.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnShowListener;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.sqchen.vhabit.R;


/**
 * Caiyuan Huang
 * <p>
 * 2014-12-8上午10:13:07
 * </p>
 * <p>
 * 自定义对话框<br>
 * 使用方法注意点:<br>
 * 使用{@link #setContentView}方法的view的根节点的大小设置为android:layout_width="match_parent"
 * android:layout_height="wrap_content"，对话框的高度在子布局中设置。
 * 
 * </p>
 */
public class CustomDialog {
	private Dialog dialog = null;
	private Activity mActivity;

	// private LayoutInflater inflater;

	public CustomDialog(Context context) {
		this.mActivity = (Activity) context;
		// this.inflater = (LayoutInflater) mActivity
		// .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	private CustomDialog create() {
		create(R.style.Theme_CustomDialog);
		return this;
	}

	/**
	 * 根据对话框主题创建对话框
	 * 
	 * @param theme
	 * @return
	 */
	public CustomDialog create(int theme) {
		if (dialog == null && mActivity.isFinishing() == false)
			dialog = new Dialog(mActivity, theme);
		return this;
	}

	/**
	 * 根据布局id设置对话框的布局
	 * 
	 * @param layoutResID
	 * @return
	 */
	public CustomDialog setContentView(int layoutResID) {
		if (dialog == null)
			create();
		dialog.setContentView(layoutResID);
		return this;
	}

	/**
	 * 设置对话框布局，对话框展示的位置居中、对话框大小自适应
	 * 
	 * @param view
	 * @return
	 */
	public CustomDialog setContentView(View view) {
		setContentView(view, WindowManager.LayoutParams.MATCH_PARENT,
				WindowManager.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
		return this;
	}

	/**
	 * 设置对话框布局、对话框展示的位置，对话框大小自适应
	 * 
	 * @param view
	 * @param gravity
	 * @return
	 */
	public CustomDialog setContentView(View view, int gravity) {
		setContentView(view, WindowManager.LayoutParams.MATCH_PARENT,
				WindowManager.LayoutParams.WRAP_CONTENT, gravity);
		return this;
	}

	/**
	 * 设置对话框的布局、大小、显示位置
	 * 
	 * @param view
	 * @param width
	 * @param height
	 * @param gravity
	 * @return
	 */
	public CustomDialog setContentView(View view, int width, int height,
									   int gravity) {
		if (dialog == null)
			create();
		dialog.setContentView(view);
		Window dlgWindow = dialog.getWindow();
		WindowManager.LayoutParams lp = dlgWindow.getAttributes();
		lp.width = width;
		lp.height = height;
		dlgWindow.setGravity(gravity);
		dlgWindow.setAttributes(lp);
		return this;
	}

	/**
	 * 设置对话框是否允许外部点击取消
	 * 
	 * @param cancel
	 * @return
	 */
	public CustomDialog setCanceledOnTouchOutside(boolean cancel) {
		if (dialog == null)
			create();
		dialog.setCanceledOnTouchOutside(cancel);
		return this;
	}

	/**
	 * 
	 * 设置是否允许取消
	 * 
	 * @param isCanCancel
	 * @return
	 */
	public CustomDialog setCancelable(boolean isCanCancel) {
		if (dialog == null)
			create();
		dialog.setCancelable(isCanCancel);

		return this;
	}

	/**
	 * 设置取消监听
	 * 
	 * @param l
	 * @return
	 */
	public CustomDialog setOnDismissListener(OnDismissListener l) {

		if (dialog == null)
			create();
		if (l != null)
			dialog.setOnDismissListener(l);
		return this;
	}

	/**
	 * 设置动画功能
	 * 
	 * @param resId
	 * @return
	 */
	public CustomDialog setAnimations(int resId) {
		if (dialog == null)
			create();
		dialog.getWindow().setWindowAnimations(resId);
		return this;
	}

	/**
	 * 设置对话框显示监听
	 * 
	 * @param l
	 * @return
	 */
	public CustomDialog setOnShowListener(OnShowListener l) {
		if (dialog == null)
			create();
		if (l != null)
			dialog.setOnShowListener(l);
		return this;
	}

	/**
	 * 显示对话框
	 */
	public void show() {
		// 三星手机，如果最后一个条件没有做判断，会挂掉的
		if (dialog != null && dialog.isShowing() == false
				&& !mActivity.isFinishing()) {
			dialog.show();
		}
	}

	/**
	 * 关闭对话框
	 */
	public void dismiss() {
		// 三星手机，如果最后一个条件没有做判断，会挂掉的
		if (dialog != null && dialog.isShowing() && !mActivity.isFinishing()) {
			dialog.dismiss();
		}
	}

	/**
	 * 对话框是否已经显示
	 * 
	 * @return
	 */
	public boolean isShowing() {
		if (dialog == null)
			return false;
		return dialog.isShowing();
	}
}
