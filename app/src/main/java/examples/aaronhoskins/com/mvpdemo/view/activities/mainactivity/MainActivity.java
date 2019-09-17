package examples.aaronhoskins.com.mvpdemo.view.activities.mainactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import examples.aaronhoskins.com.mvpdemo.R;
import examples.aaronhoskins.com.mvpdemo.view.adapters.RandomUserRvAdapter;

public class MainActivity extends AppCompatActivity implements MainActivityContract {
    @BindView(R.id.rvRandomUserList)
    RecyclerView rvRandomUserList;
    @BindView(R.id.etGenderInput)
    EditText etGender;
    @BindView(R.id.etNumOfRequestInput)
    EditText etResultCount;
    MainActivityPresenter mainActivityPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainActivityPresenter = new MainActivityPresenter(this);
    }

    @Override
    public void onAdapterReady(RandomUserRvAdapter adapter) {
        rvRandomUserList.setLayoutManager(new LinearLayoutManager(this));
        rvRandomUserList.setAdapter(adapter);
    }

    public void onClick(View view) {
        final String count = etResultCount.getText().toString();
        final String gender = etGender.getText().toString().toLowerCase();
        mainActivityPresenter.getUserInfo(count, gender);
    }
}
