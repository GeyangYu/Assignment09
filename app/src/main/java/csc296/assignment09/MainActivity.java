package csc296.assignment09;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SoundFragment.DetailItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();

        SoundFragment fragment = (SoundFragment)manager.findFragmentById(R.id.frame_layout_main);
        if(fragment == null) {
            fragment = new SoundFragment();
            manager.beginTransaction()
                    .add(R.id.frame_layout_main, fragment)
                    .commit();
        }
    }

    @Override
    public void OnDetailItemClick(CharSequence text) {
        if(findViewById(R.id.frame_layout_detail) != null) {
            DetailFragment fragment = DetailFragment.newFragment(text);
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .replace(R.id.frame_layout_detail, fragment)
                    .commit();
        }
        else {
            Toast.makeText(getApplication(), "Playing " + text, Toast.LENGTH_LONG).show();
        }
    }
}
