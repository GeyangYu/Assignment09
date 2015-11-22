package csc296.assignment09;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private static final String EXTRA_MESSAGE = "extra.Message";

    public static Intent newInstance(Context parent, CharSequence message) {
        Intent intent = new Intent(parent, DetailActivity.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        CharSequence message = intent.getCharSequenceExtra(EXTRA_MESSAGE);

        DetailFragment fragment = DetailFragment.newFragment(message);

        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .add(R.id.frame_layout_detail, fragment)
                .commit();
    }
}
