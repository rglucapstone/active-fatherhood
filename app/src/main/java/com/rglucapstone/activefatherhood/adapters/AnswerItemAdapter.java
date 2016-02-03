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

    // Status para configurar acciones en las respuestas
    private boolean suggest_answer_status = false;//answer.user_suggest_answer;
    private boolean like_answer_status = true; //answer.user_like_answer;//user.getLikeAnswerStatus() = true;
    private boolean highlight_father_status = false; //answer.user_hightlight_father;

    public AnswerItemAdapter(Context context, ArrayList<Answer> answers) {
        super(context, R.layout.fragment_list_answers, answers);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final Answer answer = getItem(position);
        final User user = this.logged;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_answer, parent, false);
        }else{ }

        final View viewAnswer = convertView;



        TextView txt_content = (TextView) convertView.findViewById(R.id.answer_content);
        txt_content.setText(answer.content);

        TextView txt_date = (TextView) convertView.findViewById(R.id.answer_date);
        txt_date.setText(answer.created);

        TextView txt_user = (TextView) convertView.findViewById(R.id.answer_user);
        txt_user.setText(answer.user.name);

        TextView txt_likes = (TextView) convertView.findViewById(R.id.txt_likes);
        txt_likes.setText(Integer.toString(answer.likes.size()));

        setActions(convertView, user, answer);

        return convertView;
    }

    public void setActions(final View convertView, User user, Answer answer){
        final String userId = user.id;
        final int userLevel = user.level;
        final User usr = user;
        final Answer ans = answer;

        //Se setea el icono de padre destacado
        setFatherHighlights( convertView,userLevel );

        //Se setea el icono de like de la respuesta
        setLikeAnswer(convertView, user, ans);

        //Se setea el icono de sugerencia de la respuesta solo si el padre es = al padre de la pregunta
        //setSuggestAnswer(convertView, user, ans);

        // Event Click on Like Answer
        final LinearLayout btn_like_answer = (LinearLayout) convertView.findViewById(R.id.btn_like_answer);
        btn_like_answer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                actionLikeAnswer(convertView, usr, ans);
            }
        });

        // Event Click on Suggest Publication
        final LinearLayout btn_suggest_publication = (LinearLayout) convertView.findViewById(R.id.btn_suggest_publication);
        btn_suggest_publication.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Desea sugerir a" + " " + usr.name + " " + "que publique esta respuesta?");
                alertDialogBuilder.setMessage(R.string.mensaje_sugerir_post)
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                setSuggestAnswer(convertView, usr, ans);
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
        });

        //Action View User Profile
        final ImageButton link_user = (ImageButton) convertView.findViewById(R.id.iconUser);
        link_user.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(context, ProfileActivity.class);
                intent.putExtra("user_id", userId);
                context.startActivity(intent);
            }
        });

    }

    public void actionLikeAnswer(final View convertView, User user, Answer answer){

        Answer ans_sync = new Answer(new userCheckLike(convertView));
        ans_sync.id = answer.id;
        boolean status = ans_sync.like(user.id);
    }

    // Action Like Answer
    public void setLikeAnswer(View convertView,User user,Answer answer){

        final User usr = user;
        final Answer ans = answer;

        final ImageView ic = (ImageView) convertView.findViewById(R.id.icon_up);//icono like

        //User user_async = new User(new userCheckLike());
        //user_async.id = user.id;
        like_answer_status = user.getLikeAnswerStatus(answer.likes);

        if (like_answer_status) {// si status era true (like) pasa a false, se resta un like y se cambia el icono a gris
            //like_answer_status = false;//usr.setCountLikeAnwer(ans.id,"dis");
            ic.setBackgroundResource(R.mipmap.ico_like_primary_24);
        } else {// si status era true (unlike) pasa a true, se suma un like y se cambia el icono a primary
            //like_answer_status = true; //usr.setCountLikeAnwer(ans.id,"add");
            ic.setBackgroundResource(R.mipmap.ico_like_grey_24);
        }
    }
    // Action Suggest Answer
    public void setSuggestAnswer(View convertView,User user,Answer answer){
        final User usr = user;
        final Answer ans = answer;
        final TextView tx = (TextView) convertView.findViewById(R.id.title_sugest_publication);

        final ImageView ic = (ImageView) convertView.findViewById(R.id.ico_suggest_publication);
        suggest_answer_status = usr.getSuggestAnswerStatus(ans.id);

        //SI STATUS ES FALSE (NO SUGERIDA AUN) SE LLAMA AL DIALOGO Y SI ACEPTA SE CAMBIA EL STATUS A TRUE, ICONO EN PRIMARY,
        // EL TEXTO CAMBIA A RESPUESTA SUGERIDA y se bloquea el boton

        if (!suggest_answer_status) {//si el padre aun no sugiere la respuesta
            suggest_answer_status = true; //usr.setSuggestAnswerStatus(true);
            Toast.makeText(context, "Tu sugerencia fue realizada con éxito", Toast.LENGTH_SHORT).show();
            //ic.setBackgroundResource(R.drawable.ico_suggest); //ya se bloquea y no puede sugerir mas
        } else { //si el padre ya sugirio la respuesta
            //usr.setSuggestAnswerStatus(false);
            Toast.makeText(context, "Ya sugeriste esta respuesta", Toast.LENGTH_SHORT).show();
            suggest_answer_status = false; //usr.setSuggestAnswerStatus(false);
            ic.setBackgroundResource(R.drawable.ico_suggest);
        }
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

    private class userCheckLike extends RestfulClient {

        private View view;

        public userCheckLike(View view){
            this.view = view;
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(JSONObject result) {

            final ImageView ic = (ImageView) this.view.findViewById(R.id.icon_up);

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
            }catch (JSONException e){ e.printStackTrace(); }

        }
    }


        /*public AnswerItemAdapter(Activity activity, String[] items){
        super(activity, R.layout.item_answer, items);
        inflater = activity.getWindow().getLayoutInflater();
    }*/
}
