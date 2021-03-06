package com.rglucapstone.activefatherhood.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.curioustechizen.ago.RelativeTimeTextView;
import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.activities.ProfileActivity;
import com.rglucapstone.activefatherhood.activities.QuestionActivity;
import com.rglucapstone.activefatherhood.data.Answer;
import com.rglucapstone.activefatherhood.data.Question;
import com.rglucapstone.activefatherhood.data.User;
import com.rglucapstone.activefatherhood.sync.RestfulClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ronald on 17/12/15.
 */
public class AnswerItemAdapter extends ArrayAdapter<Answer>{
    private LayoutInflater inflater;
    private Context context;
    public User logged;
    private Question question;

    public User user_anser;

    // Status para configurar acciones en las respuestas
    private boolean suggest_answer_status = false;//answer.user_suggest_answer;
    private boolean like_answer_status = true; //answer.user_like_answer;//user.getLikeAnswerStatus() = true;
    private boolean highlight_father_status = false; //answer.user_hightlight_father;

    public AnswerItemAdapter(Context context, ArrayList<Answer> answers, Question question, User logged) {
        super(context, R.layout.fragment_list_answers, answers);
        this.context = context;
        this.logged = logged;
        this.question = question;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Answer answer = getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_answer, parent, false);

        setData(convertView, answer);
        setActions(convertView, answer);
        return convertView;
    }

    // setting data
    private void setData(View v, Answer a){

        TextView txt_content = (TextView) v.findViewById(R.id.answer_content);
        txt_content.setText(a.content);

        //TextView txt_date = (TextView) v.findViewById(R.id.answer_date);
        //txt_date.setText(a.created);

        // relative date
        RelativeTimeTextView date = (RelativeTimeTextView) v.findViewById(R.id.answer_date);
        date.setReferenceTime(a.created_ago);

        TextView txt_user = (TextView) v.findViewById(R.id.answer_user);
        txt_user.setText(a.user.login);

        TextView txt_likes = (TextView) v.findViewById(R.id.txt_likes);
        txt_likes.setText(Integer.toString(a.likes.size()));

    }

    private void setActions(final View v, final Answer a){

        // setting likes
        setLikeAnswer(v, a);
        LinearLayout btn_like_answer = (LinearLayout) v.findViewById(R.id.btn_like_answer);
        btn_like_answer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                actionLikeAnswer(v, a);
            }
        });

        // setting profile
        ImageButton link_user = (ImageButton) v.findViewById(R.id.iconUser);
        link_user.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(context, ProfileActivity.class);
                intent.putExtra("user_id", a.user.id);
                intent.putExtra("logged_id", logged.id);
                context.startActivity(intent);
            }
        });
        setImageUser(link_user, a.user.id);

        // setting suggest
        setSuggestAnswer(v, a);

        /*//Se setea el icono de padre destacado
        setFatherHighlights(convertView, userLevel);

        // Action Highlight Father
        final LinearLayout btn_highlight_father = (LinearLayout) convertView.findViewById(R.id.btn_highlight_father);
        btn_highlight_father.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                final ImageView ic = (ImageView) convertView.findViewById(R.id.ico_highlight_father);
                highlight_father_status = usr.getHighlightFatherStatus(ans.id);

                if (highlight_father_status) {
                    highlight_father_status = false;//user.setHighlightFatherStatus(false);
                    ic.setBackgroundResource(R.mipmap.ico_highlight_father_primary);
                } else {
                    highlight_father_status = true;//user.setHighlightFatherStatus(true);
                    ic.setBackgroundResource(R.mipmap.ico_highlight_father_grey);
                }
            }
        });*/

    }

    // setting like icon
    private void setLikeAnswer(View v, Answer answer){
        ImageView ic = (ImageView) v.findViewById(R.id.icon_up);
        like_answer_status = logged.getLikeAnswerStatus(answer.likes);
        if (like_answer_status) {
            ic.setBackgroundResource(R.mipmap.ico_like_primary_24);
        } else {
            ic.setBackgroundResource(R.mipmap.ico_like_grey_24);
        }
    }

    // set action like
    private void actionLikeAnswer(View v, Answer a){
        Answer taskAnswer = new Answer(new userCheckLike(v));
        taskAnswer.like(a.id, logged.id);
    }

    // task to make likes
    private class userCheckLike extends RestfulClient {
        private View view;
        public userCheckLike(View view){
            this.view = view;
        }

        @Override
        protected void onPostExecute(JSONObject result) {

            final ImageView ic = (ImageView) view.findViewById(R.id.icon_up);
            if(this.status == 200)
                ic.setBackgroundResource(R.mipmap.ico_like_grey_24);

            if(this.status == 201)
                ic.setBackgroundResource(R.mipmap.ico_like_primary_24);

            int likes = 0;
            try {
                if( result.has("data") ){
                    JSONObject u = result.getJSONObject("data");
                    if (u.has("likes")) likes = Integer.parseInt(u.getString("likes"));
                    TextView txt_likes = (TextView) view.findViewById(R.id.txt_likes);
                    txt_likes.setText(Integer.toString(likes));
                }
            }catch (JSONException e){ e.printStackTrace(); }
        }
    }


    public void setSuggestAnswer(final View v, final Answer a) {
        LinearLayout btnSugerir = (LinearLayout) v.findViewById(R.id.btn_suggest_publication);

        if( logged.id.equals(question.user.id) ){
            final TextView tx = (TextView) v.findViewById(R.id.title_sugest_publication);
            final ImageView ic = (ImageView) v.findViewById(R.id.ico_suggest_publication);

            if( a.suggestions.size() > 0 ){
                ic.setBackgroundResource(R.mipmap.ico_highlight_father);
                tx.setText("Publicación sugerida");
            }else{
                ic.setBackgroundResource(R.mipmap.ico_highlight_father_grey);

                //setting suggest action
                btnSugerir.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                        alertDialogBuilder.setTitle("Desea sugerir a" + " " + a.user.name + " " + "que publique esta respuesta?");
                        alertDialogBuilder.setMessage(R.string.mensaje_sugerir_post)
                                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {
                                        actionSuggestAnswer(v, logged, a);
                                    }
                                })
                                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //close dialog
                                    }
                                });
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    }
                });
            }

        }else{
            btnSugerir.setVisibility(View.GONE);
        }
    }

    // action to sugget answer
    public void actionSuggestAnswer(View convertView, User user, Answer answer){
        Answer ans_async = new Answer(new createSuggest(convertView));
        ans_async.id = answer.id;
        ans_async.user = answer.user;
        ans_async.suggest(user);
    }

    // Action Father HighLights
    public void setFatherHighlights(View view, int level){
        final ImageView ico_father_highlights = (ImageView) view.findViewById(R.id.ico_father_highlights);
        //Toast.makeText(context, "level" + level , Toast.LENGTH_SHORT).show();
        if ( level >= 3) {
            ico_father_highlights.setVisibility(View.VISIBLE);
        }else {
            ico_father_highlights.setVisibility(View.GONE);
        }
    }

    public void changeBtnStatus(View view,String action){
        switch (action){
            case "like":
                break;
            case "suggest":
                break;
            default:
                break;
        }
    }


    // task to suggest answer
    private class createSuggest extends RestfulClient {

        private View view;

        public createSuggest(View view){
            this.view = view;
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(JSONObject result) {

            Toast.makeText(context, "Tu sugerencia fue realizada con éxito", Toast.LENGTH_SHORT).show();

            final TextView tx = (TextView) this.view.findViewById(R.id.title_sugest_publication);
            final ImageView ic = (ImageView) this.view.findViewById(R.id.ico_suggest_publication);

            ic.setBackgroundResource(R.mipmap.ico_highlight_father);
            tx.setText("Publicación sugerida");

            final LinearLayout btn_suggest_publication = (LinearLayout) this.view.findViewById(R.id.btn_suggest_publication);
            btn_suggest_publication.setOnClickListener(null);


            /*final ImageView ic = (ImageView) this.view.findViewById(R.id.icon_up);

            if(this.status == 200)
                ic.setBackgroundResource(R.mipmap.ico_like_grey_24);

            if(this.status == 201)
                ic.setBackgroundResource(R.mipmap.ico_like_primary_24);

            int likes = 0;
            try {
                if( result.has("data") ){
                    JSONObject u = result.getJSONObject("data");
                    if (u.has("likes")) likes = Integer.parseInt(u.getString("likes"));
                    TextView txt_likes = (TextView) this.view.findViewById(R.id.txt_likes);
                    txt_likes.setText(Integer.toString(likes));
                }
            }catch (JSONException e){ e.printStackTrace(); }*/

        }
    }


    // setting image profile
    private void setImageUser(ImageButton img_view,String id){
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
