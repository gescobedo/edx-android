package edx.android.galileo.gustavo.androidchat.contactlist.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edx.android.galileo.gustavo.androidchat.R;
import edx.android.galileo.gustavo.androidchat.addcontact.ui.AddContactFragment;
import edx.android.galileo.gustavo.androidchat.chat.ui.ChatActivity;
import edx.android.galileo.gustavo.androidchat.contactlist.ContactListPresenter;
import edx.android.galileo.gustavo.androidchat.contactlist.ContactListPresenterImpl;
import edx.android.galileo.gustavo.androidchat.contactlist.ui.adapters.ContactListAdapter;
import edx.android.galileo.gustavo.androidchat.contactlist.ui.adapters.OnItemClickListener;
import edx.android.galileo.gustavo.androidchat.entities.User;
import edx.android.galileo.gustavo.androidchat.lib.GlideImageLoader;
import edx.android.galileo.gustavo.androidchat.lib.ImageLoader;
import edx.android.galileo.gustavo.androidchat.login.ui.LoginActivity;


public class ContactListActivity extends AppCompatActivity implements ContactListView,OnItemClickListener {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @Bind(R.id.recyclerViewContacts)
    RecyclerView recyclerViewContacts;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    private ContactListPresenter presenter;
    private ContactListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        ButterKnife.bind(this);



        presenter=new ContactListPresenterImpl(this);
        presenter.onCreate();

        setupToolbar();
        setupAdapter();
        setupRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contactlist,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_logout){
            presenter.signOff();
            Intent intent=new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    |Intent.FLAG_ACTIVITY_NEW_TASK
                    |Intent.FLAG_ACTIVITY_CLEAR_TASK
            );
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView() {
        recyclerViewContacts.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewContacts.setAdapter(adapter);
    }

    private void setupAdapter() {
        ImageLoader loader = new GlideImageLoader(this.getApplicationContext());
        adapter = new ContactListAdapter(new ArrayList<User>(), loader, this );
    }

    private void setupToolbar() {
        toolbar.setTitle(presenter.getCurrentUserEmail());
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @OnClick(R.id.fab)
    public void addContact() {
        new AddContactFragment().show(getSupportFragmentManager(),getString(R.string.addcontact_message_title));
    }

    @Override
    public void onContactAdded(User user) {
    adapter.add(user);
    }

    @Override
    public void onContactChanged(User user) {
    adapter.update(user);
    }

    @Override
    public void onContactRemoved(User user) {
    adapter.remove(user);
    }

    @Override
    public void onItemClick(User user) {
       Intent intent= new Intent(this, ChatActivity.class);
        intent.putExtra(ChatActivity.EMAIL_KEY,user.getEmail());
        intent.putExtra(ChatActivity.ONLINE_KEY,user.isOnline());
        startActivity(intent);
        //Toast.makeText(this,user.getEmail(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemLongClick(User user) {
        presenter.removeContact(user.getEmail());
    }
}
