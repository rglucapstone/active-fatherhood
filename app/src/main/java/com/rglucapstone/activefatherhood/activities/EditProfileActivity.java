package com.rglucapstone.activefatherhood.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.data.Question;
import com.rglucapstone.activefatherhood.data.User;
import com.rglucapstone.activefatherhood.sync.RestfulClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Luisa Castro on 17/01/2016.
 */
public class EditProfileActivity extends AppCompatActivity {

    private User user;
    private Context context;


    /** Upload Image Resource **/
    private Button buttonChoose;
    private Button buttonUpload;
    private ImageView imageView;
    private EditText editTextName;
    private Bitmap bitmap;
    private int PICK_IMAGE_REQUEST = 1;

    private String UPLOAD_URL ="http://simplifiedcoding.16mb.com/VolleyUpload/upload.php"; /**** Cambiar ****/

    private String KEY_IMAGE = "image";
    private String KEY_NAME = "name";
    /** **/

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        setToolbar();
        context = this;

        this.user = new User(new load());
        this.user.id = getIntent().getStringExtra("user_id");
        this.user.load(this.user.id);

        buttonChoose = (Button) findViewById(R.id.uploadPhoto);
        imageView  = (ImageView) findViewById(R.id.photoUser);

        buttonChoose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(v == buttonChoose){
                    showFileChooser();
                }
            }
        });

    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    public Bitmap getCroppedBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        //Bitmap _bmp = Bitmap.createScaledBitmap(output, 60, 60, false);
        //return _bmp;
        return output;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //String image = getStringImage(bitmap);
                Bitmap img_oval = getCroppedBitmap(bitmap);

                /********************** Imagen Final ********************/
                //Setting the Bitmap to ImageView
                imageView.setImageBitmap(img_oval);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(this, "Tamaño maximo superado", Toast.LENGTH_LONG).show();
        }
    }

      /* private void uploadImage(){
        //Showing the progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Uploading...", "Please wait...", false, false);
        StringRequest stringRequest = new StringRequest(DownloadManager.Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(MainActivity.this, s , Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(MainActivity.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String
                String image = getStringImage(bitmap);

                //Getting Image Name
                String name = editTextName.getText().toString().trim();

                //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put(KEY_IMAGE, image);
                params.put(KEY_NAME, name);

                //returning parameters
                return params;
            }
        };*/

    /* toolbar */
    public void setToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_action);
        toolbar.setTitle("Editar datos del Perfil");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void updateProfile(View view){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
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

    private class load extends RestfulClient {

        @Override
        protected void onPostExecute(JSONObject result) {
            ArrayList<User> list = new ArrayList<>();
            try {
                list = User.fromJson(result.getJSONArray("data"));
                if( !list.isEmpty() ){
                    user = list.get(0);
                    setData(user);
                    setActions(user);
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        }

    }

    private class send extends RestfulClient {

        private Toast toast;

        @Override
        protected void onPreExecute() {
            toast = Toast.makeText(context, "Guardando perfil de usuario", Toast.LENGTH_SHORT);
            toast.show();
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            toast.cancel();
            if( this.status == 200 ){
                Intent intent = new Intent(context, ProfileActivity.class);
                intent.putExtra("user_id", user.id);
                intent.putExtra("logged_id", user.id);
                context.startActivity(intent);
            }else{
                toast = Toast.makeText(context, "Error al guardar perfil de usuario", Toast.LENGTH_SHORT);
                toast.show();
            }
        }

    }

    private void setActions(final User u){

        Button btn_acept = (Button) findViewById(R.id.btn_acept);
        btn_acept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                User user = u;
                user.AsyncTask = new send();
                EditText name = (EditText) findViewById(R.id.input_user_name);
                user.name = name.getText().toString();
                EditText login = (EditText) findViewById(R.id.input_user_login);
                user.login = login.getText().toString();
                EditText email = (EditText) findViewById(R.id.input_email);
                user.email = email.getText().toString();
                EditText age = (EditText) findViewById(R.id.input_age);
                user.edad = age.getText().toString();
                EditText buen_padre = (EditText) findViewById(R.id.input_mantra);
                user.buen_padre = buen_padre.getText().toString();
                user.send();
            }
        });

    }

    private void setData(User u){

        TextView login = (TextView) findViewById(R.id.input_user_login);
        login.setText(u.login);

        TextView name = (TextView) findViewById(R.id.input_user_name);
        name.setText(u.name);

        TextView email = (TextView) findViewById(R.id.input_email);
        email.setText(u.email);

        TextView age = (TextView) findViewById(R.id.input_age);
        age.setText(u.edad);

        TextView buen_padre = (TextView) findViewById(R.id.input_mantra);
        buen_padre.setText(u.buen_padre);


    }

}
