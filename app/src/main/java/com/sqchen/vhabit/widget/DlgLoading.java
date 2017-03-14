package com.sqchen.vhabit.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.sqchen.vhabit.R;


/**
 * Caiyuan Huang
 * <p>
 * 2015-6-9
 * </p>
 * <p>
 * 加载对话框
 * </p>
 */
public class DlgLoading {
	private CustomDialog dialog = null;
	private LayoutInflater mInflater;
	private TextView txtContent;

	public DlgLoading(Context context) {
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		init(context);
	}

	private void init(Context context) {
		View dlgView = mInflater.inflate(R.layout.dlg_loading, null);
		txtContent = (TextView) dlgView
				.findViewById(R.id.dlg_loading_txtContent);
		dialog = new CustomDialog(context)
				.setContentView(dlgView, Gravity.CENTER)
				.setCanceledOnTouchOutside(false).setCancelable(true);
	}

	public void show(String content) {
		txtContent.setText(content);
		show();
	}

	public void show() {
		dialog.show();
	}

	public void dismiss() {
		dialog.dismiss();
	}

	public CustomDialog getDialog() {
		return dialog;
	}

	public TextView getTxtContent() {
		return txtContent;
	}

}
