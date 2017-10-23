package com.hyyt.universaldialoglibrary;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by w on 2017/10/23.
 */

public class UniversalAlertDialog extends Dialog {
    private final static String TAG = "UniversalAlertDialog";

    public UniversalAlertDialog(@NonNull Context context) {
        super(context);
    }

    public UniversalAlertDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected UniversalAlertDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {
        private Context context;
        private String title,//标题
                message;//内容
        private String[] buttonNames;//按钮名字
        private View contentView = null;
        private DialogButtonClickListener listener;//监听
        private boolean cancelable = true;//是否允许取消
        private int titleTextSize = 16;//标题字体大小，单位sp
        private int messageTextSize = 14;//内容字体大小，单位sp
        private int pxOf10pid;
        private int pxOf15pid;
        protected static boolean isLoading = false;
        protected TextView tv_message;
        protected static AnimationDrawable rocketAnimation;

        public Builder(Context context) {
            this.context = context;
            pxOf10pid = (int) (context.getResources().getDisplayMetrics().density * 10 + 0.5f);
            pxOf15pid = pxOf10pid / 2 * 3;
        }

        /**
         * 是否是加载对话框,默认false
         *
         * @param isLoadingDailog
         * @return
         */
        public Builder setIsLoading(boolean isLoadingDailog) {
            isLoading = isLoadingDailog;
            return this;
        }

        /**
         * 设置标题字体大小
         *
         * @param size
         * @return
         */
        public Builder setTitleTextSize(int size) {
            this.titleTextSize = size;
            return this;
        }

        /**
         * 设置标题
         *
         * @param title
         * @return
         */
        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        /**
         * 设置内容字体大小
         *
         * @param size
         * @return
         */
        public Builder setMessageTextSize(int size) {
            this.messageTextSize = size;
            return this;
        }

        /**
         * 设置内容
         *
         * @param message
         * @return
         */
        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * 设置是否可以取消，默认可以取消
         *
         * @param cancelable
         * @return
         */
        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        /**
         * 设置view,如果View不为空，message将不显示
         *
         * @return
         */
        public Builder setView(View v) {
            this.contentView = v;
            return this;
        }

        /**
         * 添加按钮，最多3个，按顺序排列，如不添加则没有按钮
         *
         * @param buttonNames
         * @param listener
         * @return
         */
        public Builder addButton(String[] buttonNames, DialogButtonClickListener listener) {
            this.buttonNames = buttonNames;
            this.listener = listener;
            return this;
        }

        /**
         * 创建对话框
         *
         * @return
         */
        public UniversalAlertDialog create() {
            final UniversalAlertDialog dialog = new UniversalAlertDialog(context, R.style.dialog);
            View view = View.inflate(context, R.layout.dialog_custom_alerat, null);
            dialog.addContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            Button bt_left = (Button) view.findViewById(R.id.bt_left);
            ImageView iv_loading = (ImageView) view.findViewById(R.id.iv_loading);
            if (isLoading) {
                iv_loading.setVisibility(View.VISIBLE);
                rocketAnimation = (AnimationDrawable) iv_loading.getBackground();
            } else {
                iv_loading.setVisibility(View.GONE);
            }

            //标题
            TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
            if (TextUtils.isEmpty(title)) {
                tv_title.setVisibility(View.GONE);
            } else {
                tv_title.setTextSize(titleTextSize);
                tv_title.setText(title);
            }

            tv_message = (TextView) view.findViewById(R.id.tv_message);
            if (contentView == null) {
                if (TextUtils.isEmpty(message)) {
                    tv_message.setVisibility(View.GONE);
                } else {
                    tv_message.setTextSize(messageTextSize);
                    tv_message.setText(message);
                }
            } else {
                ScrollView sv = ((ScrollView) tv_message.getParent());
                sv.removeAllViews();
                sv.addView(contentView);
            }

            if (buttonNames != null && buttonNames.length > 0) {

                //添加按钮
                switch (buttonNames.length > 3 ? 3 : buttonNames.length) {
                    case 3://中间的按钮
                        Button bt_mid = (Button) view.findViewById(R.id.bt_mid);
                        bt_mid.setVisibility(View.VISIBLE);
                        bt_mid.setText(buttonNames[1]);
                        bt_mid.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                try {
                                    listener.neutralButtonClick();
                                } catch (Exception e) {
                                    Log.d(TAG, e.getMessage());
                                }
                                try {
                                    dialog.dismiss();
                                } catch (Exception e) {
                                    Log.d(TAG, e.getMessage());
                                }
                            }
                        });
                    case 2://右边的按钮
                        Button bt_right = (Button) view.findViewById(R.id.bt_right);
                        bt_right.setVisibility(View.VISIBLE);
                        bt_right.setText(buttonNames.length == 2 ? buttonNames[1] : buttonNames[2]);
                        bt_right.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                try {
                                    if (buttonNames.length == 2) {
                                        listener.neutralButtonClick();
                                    } else {
                                        listener.negativeButtonClick();
                                    }
                                } catch (Exception e) {
                                    Log.d(TAG, e.getMessage());
                                }
                                try {
                                    dialog.dismiss();
                                } catch (Exception e) {
                                    Log.d(TAG, e.getMessage());
                                }
                            }
                        });

                        bt_left.setPadding(0, pxOf10pid, 0, pxOf10pid);
                    case 1://左边的按钮
                        bt_left.setText(buttonNames[0]);
                        bt_left.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                try {
                                    listener.positiveButtonClick();
                                } catch (Exception e) {
                                    Log.d(TAG, e.getMessage());
                                }
                                try {
                                    dialog.dismiss();
                                } catch (Exception e) {
                                    Log.d(TAG, e.getMessage());
                                }
                            }
                        });
                        break;
                }
            } else {
                bt_left.setVisibility(View.GONE);
                LinearLayout ll_content = (LinearLayout) view.findViewById(R.id.ll_content);
                ll_content.setPadding(pxOf10pid, pxOf15pid, pxOf10pid, 0);
            }
            //是否允许取消
            dialog.setCancelable(cancelable);
            return dialog;
        }
    }

    /**
     * 对话框按钮点击监听
     * positiveButtonClick 积极
     * neutralButtonClick 中立
     * negativeButtonClick 消极
     */
    public interface DialogButtonClickListener {
        void positiveButtonClick();

        void neutralButtonClick();

        void negativeButtonClick();
    }


}
