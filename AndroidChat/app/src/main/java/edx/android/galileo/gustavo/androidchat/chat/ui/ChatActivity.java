package edx.android.galileo.gustavo.androidchat.chat.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import edx.android.galileo.gustavo.androidchat.R;
import edx.android.galileo.gustavo.androidchat.chat.ui.adapters.ChatAdapter;
import edx.android.galileo.gustavo.androidchat.chat.ChatPresenter;
import edx.android.galileo.gustavo.androidchat.chat.ChatPresenterImpl;
import edx.android.galileo.gustavo.androidchat.entities.ChatMessage;
import edx.android.galileo.gustavo.androidchat.domain.AvatarHelper;
import edx.android.galileo.gustavo.androidchat.lib.GlideImageLoader;
import edx.android.galileo.gustavo.androidchat.lib.ImageLoader;

public class ChatActivity extends AppCompatActivity implements ChatView{

    @Bind(R.id.imageAvatar)
    CircleImageView imageAvatar;
    @Bind(R.id.txtUser)
    TextView txtUser;
    @Bind(R.id.txtStatus)
    TextView txtStatus;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.messageRecyclerView)
    RecyclerView messageRecyclerView;
    @Bind(R.id.editTxtMessage)
    EditText editTxtMessage;
    @Bind(R.id.btnSendMessage)
    ImageButton btnSendMessage;

    public final static String EMAIL_KEY="email";
    public final static String ONLINE_KEY="online";

    private ChatPresenter presenter;
    private ChatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);



        setupAdapter();
        setupRecyclerView();


        presenter=new ChatPresenterImpl(this);
        presenter.onCreate();
        setupToolbar(getIntent());
        setSupportActionBar(toolbar);
    }

    private void setupToolbar(Intent intent) {
        String  recipient= intent.getStringExtra(EMAIL_KEY);
        presenter.setChatRecipient(recipient);
        boolean  online= intent.getBooleanExtra(ONLINE_KEY,false);

        String status = online ? "online" : "offline";
        int color = online ? Color.GREEN : Color.RED;
        txtUser.setText(recipient);
        txtStatus.setText(status);
        txtStatus.setTextColor(color);
        ImageLoader imageLoader= new GlideImageLoader(getApplicationContext());
        imageLoader.load(imageAvatar, AvatarHelper.getAvatar(recipient));
    }

    private void setupRecyclerView() {
        messageRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        messageRecyclerView.setAdapter(adapter);
    }

    private void setupAdapter() {
        adapter = new ChatAdapter(this, new ArrayList<ChatMessage>());

    }

    @Override
    protected void onResume() {
presenter.onResume();
        super.onResume();
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

    @Override
    public void onMessageReceived(ChatMessage msg) {
        adapter.add(msg);
        messageRecyclerView.scrollToPosition(adapter.getItemCount()-1);
    }
    @OnClick(R.id.btnSendMessage)
    public void sendMessage(){
        presenter.sendMessage(editTxtMessage.getText().toString());
        editTxtMessage.setText("");
    }
}
