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

    public Post post;
    public Context context;

    private TextView container;
    private LinearLayout container_report;
    private ImageButton btn_like;
    private int btn_like_status = 0;//se obtiene de base si esta pregunta me gusta, devuelve 1

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        this.context = this;
        Intent intent = getIntent();
        this.post = new Post(this, new loadPost());
        this.post.load(intent.getStringExtra("post_id"));
        setToolbar();
        setActions();
    }

    private void setData(){

        LinearLayout layout = (LinearLayout)findViewById(R.id.layout_item_post);

        TextView txt_post_user = (TextView) layout.findViewById(R.id.txt_post_user);
        txt_post_user.setText(this.post.user.login);

        //TextView txt_post_date = (TextView) layout.findViewById(R.id.txt_post_date);
        //txt_post_date.setText(this.post.created);
        RelativeTimeTextView v = (RelativeTimeTextView) layout.findViewById(R.id.txt_post_date);
        v.setReferenceTime(this.post.created_ago);

        TextView txt_post_title = (TextView) layout.findViewById(R.id.txt_post_title);
        txt_post_title.setText(this.post.title);

        TextView txt_post_content = (TextView) layout.findViewById(R.id.txt_post_content);
        txt_post_content.setText(this.post.content);

        TextView txt_post_likes = (TextView) layout.findViewById(R.id.txt_post_likes);
        txt_post_likes.setText(this.post.likes);

        setTags();
        for (int i = 1; i <= this.post.themes.length; i++) {
            int index = i - 1;

            int imgtag = context.getResources().getIdentifier("ic_post_tag" + i, "id", context.getPackageName());
            ImageView img_tag = (ImageView) layout.findViewById(imgtag);
            img_tag.setVisibility(View.VISIBLE);

            int txttag = context.getResources().getIdentifier("txt_post_tag" + i, "id", context.getPackageName());
            TextView txt_tag = (TextView) layout.findViewById(txttag);
            txt_tag.setText(post.themes[index]);
            txt_tag.setVisibility(View.VISIBLE);
        }
        //set image user
        final ImageView img_user = (ImageView) findViewById(R.id.ic_post_user);
        User user_post = post.user;
        setImageUser(img_user, user_post.id);

        TextView txt_comments = (TextView) findViewById(R.id.total_comments);
        txt_comments.setText(this.post.listComments.size() + " comentarios");

        CommentItemAdapter adapter = new CommentItemAdapter(this, this.post.listComments);
        LinearLayout layout_comments = (LinearLayout) findViewById(R.id.layout_list_comments);
        ListView list = (ListView) layout_comments.findViewById(R.id.listComments);
        list.setAdapter(adapter);
    }

    // TOOLBAR
    public void setToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_action);
        toolbar.setTitle("Publicación");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // ACTIONS
    public void setActions(){

        final ImageView link_user = (ImageView) findViewById(R.id.ic_post_user);
        link_user.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(context, ProfileActivity.class); //create an Intent object
                intent.putExtra("user_id", post.user.id); //add data to the Intent object
                context.startActivity(intent); //start the second activity
            }
        });

        //Se oculta la fecha y el reporte de la publicacion
        //container = (TextView) findViewById(R.id.txt_date_post);
        //container.setVisibility(View.GONE);

        //container_report = (LinearLayout) findViewById(R.id.container_report_post);
        //container_report.setVisibility(View.GONE);

        // Se cambia el background del icono Me gusta si este es presionado
        btn_like = (ImageButton) findViewById(R.id.btn_like_post);
        btn_like.setVisibility(View.VISIBLE);
        btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (btn_like_status == 0) {
                    btn_like_status = 1;
                    btn_like.setBackgroundResource(R.mipmap.ico_like_blue_24);
                } else {
                    btn_like_status = 0;
                    btn_like.setBackgroundResource(R.mipmap.ico_like_grey_24);
                }
            }
        });
    }

    public void setTags(){

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

    /* Agregar Comentario */
    public void commenting(View view) {
        Intent intent = new Intent(this, CommentActivity.class);
        startActivity(intent);
    }


    private class loadPost extends RestfulClient {

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            try {
                // Populating Data into ListView
                ArrayList<Post> list = Post.fromJson(result.getJSONArray("data"));
                if( !list.isEmpty() ){
                    post = list.get(0);
                    setData();
                }
            }catch (JSONException e){
                e.printStackTrace();
            }

            RelativeLayout loadingLayout = (RelativeLayout) findViewById(R.id.loading_post);
            loadingLayout.setVisibility(View.GONE);
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