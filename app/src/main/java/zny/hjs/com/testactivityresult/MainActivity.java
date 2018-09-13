package zny.hjs.com.testactivityresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import zny.hjs.com.testactivityresult.manager.ActivityResultInfo;
import zny.hjs.com.testactivityresult.manager.AvoidOnResult;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.callback)
    Button callback;
    @BindView(R.id.rxjava)
    Button rxjava;
    @BindView(R.id.normal)
    Button normal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    AvoidOnResult avoidOnResult = new AvoidOnResult(this);

    @OnClick({R.id.callback, R.id.rxjava, R.id.normal})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.callback:

                avoidOnResult.startForResult(FetchDataActivity.class, new AvoidOnResult.Callback() {
                    @Override
                    public void onActivityResult(int resultCode, Intent data) {
                        if (resultCode == Activity.RESULT_OK) {
                            String text = data.getStringExtra("text");
                            Toast.makeText(MainActivity.this,
                                    "callback -> " + text, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this,
                                    "callback canceled", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case R.id.rxjava:
                break;
            case R.id.normal:
                Intent intent = new Intent(MainActivity.this, FetchDataActivity.class);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 1) {
            String text = data.getStringExtra("text");
            Toast.makeText(this, "normal -> " + text, Toast.LENGTH_SHORT).show();
            Log.e("TAG", "normal -> " + text);
        }
    }
}
