package edx.android.galileo.gustavo.twitterclient.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.twitter.sdk.android.Twitter;

import butterknife.Bind;
import butterknife.ButterKnife;
import edx.android.galileo.gustavo.twitterclient.LoginActivity;
import edx.android.galileo.gustavo.twitterclient.R;
import edx.android.galileo.gustavo.twitterclient.hashtags.ui.HashtagsFragment;
import edx.android.galileo.gustavo.twitterclient.images.ui.ImagesFragment;
import edx.android.galileo.gustavo.twitterclient.main.ui.adapters.MainSectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.container)
    ViewPager container;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setupAdapter();
    }

    private void setupAdapter() {
        Fragment[] fragments= new Fragment[]{new ImagesFragment(),new HashtagsFragment()};
        String[] titles=new String[]{getString(R.string.main_header_images)
                ,getString(R.string.main_header_hashtags)};
        MainSectionsPagerAdapter adapter=new MainSectionsPagerAdapter(getSupportFragmentManager()
                ,fragments
                ,titles);
        container.setAdapter(adapter);
        tabs.setupWithViewPager(container);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        Twitter.logOut();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK
        );
        startActivity(intent);
    }
}
