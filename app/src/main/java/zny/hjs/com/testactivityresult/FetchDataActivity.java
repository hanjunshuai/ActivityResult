package zny.hjs.com.testactivityresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FetchDataActivity extends AppCompatActivity {

    @BindView(R.id.text)
    EditText text;
    @BindView(R.id.back)
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_data);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        Intent intent = new Intent();
        intent.putExtra("text", text.getText().toString());
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

}
