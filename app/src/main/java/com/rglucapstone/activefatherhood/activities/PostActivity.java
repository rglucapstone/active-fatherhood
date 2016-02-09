package com.rglucapstone.activefatherhood.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.curioustechizen.ago.RelativeTimeTextView;
import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.adapters.AnswerItemAdapter;
import com.rglucapstone.activefatherhood.adapters.CommentItemAdapter;
import com.rglucapstone.activefatherhood.data.Answer;
import com.rglucapstone.activefatherhood.data.Comment;
import com.rglucapstone.activefatherhood.data.Post;
import com.rglucapstone.activefatherhood.data.Question;
import com.rglucapstone.activefatherhood.data.User;
import com.rglucapstone.activefatherhood.sync.RestfulClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Luisa Castro on 27/01/2016.
 */
public class PostActivity extends AppCompatActivity {

    private Post post;
    public User logged;
    public Context context;

    private TextView container;
    private LinearLayout container_report;
    private ImageButton btn_like;
    private int btn_like_status = 0;//se obtiene de base si esta pregunta me gusta, devuelve 1

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        setToolbar();
        this.context = this;
        this.logged = new User(getIntent().getStringExtra("logged_id"));
        Post post = new Post(this, new load());
        post.load(getIntent().getStringExtra("post_id"));
    }

    private void setToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_action);
        toolbar.setTitle("Publicación");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setTags(Context context){

        int total = 3;
        for (int i = 1; i <= total; i++) {
            int imgtag = context.getResources().getIdentifier("ic_post_tag" + i, "id", context.getPackageName());
            ImageView img_tag = (ImageView) findViewById(imgtag);
            img_tag.setVisibility(View.GONE);

            int txttag = context.getResources().getIdentifier("txt_post_tag" + i, "id", context.getPackageName());
            TextView txt_tag = (TextView) findViewById(txttag);
            txt_tag.setVisibility(View.GONE);

        }

    }

    private void setData(Post p){

        LinearLayout layout = (LinearLayout) findViewById(R.id.layout_item_post);

        TextView txt_post_user = (TextView) layout.findViewById(R.id.txt_post_user);
        txt_post_user.setText(p.user.login);

        RelativeTimeTextView v = (RelativeTimeTextView) layout.findViewById(R.id.txt_post_date);
        v.setReferenceTime(p.created_ago);

        TextView txt_post_title = (TextView) layout.findViewById(R.id.txt_post_title);
        txt_post_title.setText(p.title);

        TextView txt_post_content = (TextView) layout.findViewById(R.id.txt_post_content);
        txt_post_content.setText(p.content);

        TextView txt_post_likes = (TextView) layout.findViewById(R.id.txt_post_likes);
        txt_post_likes.setText(Integer.toString(p.likes.size()) + " me gusta");

        setTags(this);
        for (int i = 1; i <= p.themes.length; i++) {
            int index = i - 1;

            int imgtag = this.getResources().getIdentifier("ic_post_tag" + i, "id", this.getPackageName());
            ImageView img_tag = (ImageView) layout.findViewById(imgtag);
            img_tag.setVisibility(View.VISIBLE);

            int txttag = this.getResources().getIdentifier("txt_post_tag" + i, "id", this.getPackageName());
            TextView txt_tag = (TextView) layout.findViewById(txttag);
            txt_tag.setText(p.themes[index]);
            txt_tag.setVisibility(View.VISIBLE);
        }

        //set image user
        final ImageView img_user = (ImageView) findViewById(R.id.ic_post_user);
        setImageUser(img_user, p.user.id);

        TextView txt_comments = (TextView) findViewById(R.id.total_comments);
        txt_comments.setText(p.listComments.size() + " comentarios");

        setComments(p.listComments);

    }


    private void setComments(ArrayList<Comment> comments){
        CommentItemAdapter adapter = new CommentItemAdapter(this, comments, logged);
        LinearLayout layout_comments = (LinearLayout) findViewById(R.id.layout_list_comments);
        ListView list = (ListView) layout_comments.findViewById(R.id.listComments);
        list.setAdapter(adapter);
    }


    // settings actions
    public void setActions(final Post p){

        // settin profile link
        final ImageView link_user = (ImageView) findViewById(R.id.ic_post_user);
        link_user.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(context, ProfileActivity.class); //create an Intent object
                intent.putExtra("user_id", post.user.id); //add data to the Intent object
                intent.putExtra("logged_id", logged.id); //add data to the Intent object
                context.startActivity(intent); //start the second activity
            }
        });

        // setting like action
        setLikePost(p);
        ImageButton btn_like_post = (ImageButton) findViewById(R.id.btn_like_post);
        btn_like_post.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                actionLikePost(p);
            }
        });

        //Se oculta la fecha y el reporte de la publicacion
        //container = (TextView) findViewById(R.id.txt_date_post);
        //container.setVisibility(View.GONE);

        //container_report = (LinearLayout) findViewById(R.id.container_report_post);
        //container_report.setVisibility(View.GONE);
    }

    // make like action
    private void actionLikePost(Post p){
        Post taskPost = new Post(new like());
        taskPost.like(p.id, logged.id);
    }

    // setting like icon
    private void setLikePost(Post post){
        ImageButton ic = (ImageButton) findViewById(R.id.btn_like_post);
        boolean like_post_status = logged.getLikeStatus(post.likes);
        if (like_post_status) {
            ic.setBackgroundResource(R.mipmap.ico_like_primary_24); // ico_like_blue_24
        } else {
            ic.setBackgroundResource(R.mipmap.ico_like_grey_24);
        }
    }



    /* Action Back*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    // action to comment to a post
    public void commenting(View view) {
        Intent intent = new Intent(this, CommentActivity.class);
        intent.putExtra("post_id", post.id);
        intent.putExtra("logged_id", logged.id);
        startActivity(intent);
    }


    private class load extends RestfulClient {

        @Override
        protected void onPostExecute(JSONObject result) {
            User logged = new User(getIntent().getStringExtra("logged_id"));
            try {
                ArrayList<Post> list = Post.fromJson(result.getJSONArray("data"));
                if( !list.isEmpty() ){
                    post = list.get(0);
                    setData(post);
                    setActions(post);
                }
            }catch (JSONException e){
                e.printStackTrace();
            }

            RelativeLayout loadingLayout = (RelativeLayout) findViewById(R.id.loading_post);
            loadingLayout.setVisibility(View.GONE);
        }

    }


    private class like extends RestfulClient {

        @Override
        protected void onPostExecute(JSONObject result) {

            final ImageButton ic = (ImageButton) findViewById(R.id.btn_like_post);
            if(this.status == 200)
                ic.setBackgroundResource(R.mipmap.ico_like_grey_24);

            if(this.status == 201)
                ic.setBackgroundResource(R.mipmap.ico_like_primary_24);

            int likes = 0;
            try {
                if( result.has("data") ){
                    JSONObject u = result.getJSONObject("data");
                    if (u.has("likes")) likes = Integer.parseInt(u.getString("likes"));
                    TextView txt_likes = (TextView) findViewById(R.id.txt_post_likes);
                    txt_likes.setText(Integer.toString(likes) + " me gusta");
                }
            }catch (JSONException e){ e.printStackTrace(); }
        }
    }


    public void setImageUser(ImageView img_view,String id){
        switch (id){
            case "1":
                img_view.setBackgroundResource(R.drawable.padre2);
                break;
            case "2":
                img_view.setBackgroundResource(R.drawable.padre4);
                break;
            case "3":
                img_view.setBackgroundResource(R.drawable.padre2);
                break;
            case "4":
                img_view.setBackgroundResource(R.drawable.padre4);
                break;
            case "5":
                img_view.setBackgroundResource(R.drawable.padre5);
                break;
            case "6":
                img_view.setBackgroundResource(R.drawable.padre8);
                break;
            case "8":
                img_view.setBackgroundResource(R.drawable.padre10);
                break;
            case "9":
                img_view.setBackgroundResource(R.drawable.padre5);
                break;
            case "10":
                img_view.setBackgroundResource(R.drawable.padre5);
                break;
            case "11":
                img_view.setBackgroundResource(R.drawable.padre10);
                break;
            case "13":
                img_view.setBackgroundResource(R.drawable.padre8);
                break;
            case "15":
                img_view.setBackgroundResource(R.drawable.padre8);
                break;
            case "14":
                img_view.setBackgroundResource(R.drawable.padre10);
                break;
            case "16":
                img_view.setBackgroundResource(R.drawable.padre2);
                break;
            default:
                img_view.setBackgroundResource(R.drawable.ico_profile_grey);
                break;
        }
    }

}