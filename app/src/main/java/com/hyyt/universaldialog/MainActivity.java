package com.hyyt.universaldialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hyyt.universaldialoglibrary.UniversalAlertDialog;

public class MainActivity extends BaseActivity {


    private Button button1;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog(false, "提示", "弹出对话框", new String[]{"确定"}, new UniversalAlertDialog.DialogButtonClickListener() {
                    @Override
                    public void positiveButtonClick() {
                        Toast.makeText(MainActivity.this, "第一个按钮", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void neutralButtonClick() {
                    }

                    @Override
                    public void negativeButtonClick() {
                    }
                });
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog(false, "提示", "弹出对话框", new String[]{"确定", "取消1"}, new UniversalAlertDialog.DialogButtonClickListener() {
                    @Override
                    public void positiveButtonClick() {
                        Toast.makeText(MainActivity.this, "第一个按钮", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void neutralButtonClick() {
                        Toast.makeText(MainActivity.this, "第2个按钮", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void negativeButtonClick() {
                    }
                });
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog(false, "提示", "弹出对话框", new String[]{"确定", "取消1", "取消2"}, new UniversalAlertDialog.DialogButtonClickListener() {
                    @Override
                    public void positiveButtonClick() {
                        Toast.makeText(MainActivity.this, "第一个按钮", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void neutralButtonClick() {
                        Toast.makeText(MainActivity.this, "第2个按钮", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void negativeButtonClick() {
                        Toast.makeText(MainActivity.this, "第3个按钮", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
