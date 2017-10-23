package com.hyyt.universaldialog;

import android.support.v7.app.AppCompatActivity;

import com.hyyt.universaldialoglibrary.UniversalAlertDialog;

/**
 * Created by w on 2017/10/23.
 */

public class BaseActivity extends AppCompatActivity {
    private UniversalAlertDialog universalAlertDialog;

    final protected UniversalAlertDialog showAlertDialog(boolean isLoading, String title, String message, String[] buttonNames,
                                                         final UniversalAlertDialog.DialogButtonClickListener listener) {
        if (universalAlertDialog == null || !universalAlertDialog.isShowing()) {//对话框没有创建，或者没有显示
            universalAlertDialog = createAlertDialog(isLoading, false, title, message, buttonNames, listener).create();
            if (!this.isFinishing()) {
                universalAlertDialog.show();
            }
        }
        return universalAlertDialog;
    }

    private UniversalAlertDialog.Builder createAlertDialog(boolean isLoading, boolean isCancle, String title, String message, String[] buttonNames, final UniversalAlertDialog.DialogButtonClickListener listener) {

        UniversalAlertDialog.Builder builder = new UniversalAlertDialog.Builder(BaseActivity.this);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setIsLoading(isLoading);
        builder.setCancelable(isCancle);
        builder.addButton(buttonNames, listener);

        return builder;
    }
}
