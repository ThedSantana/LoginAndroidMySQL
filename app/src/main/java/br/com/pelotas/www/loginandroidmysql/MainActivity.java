package br.com.pelotas.www.loginandroidmysql;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Hashtable;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;




public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private String UPLOAD_URL ="http://diariodebordo.pe.hu/app_vig_sanitaria/upload.php";
    private Button btnEnviar;
    private String nome = "nome";
    private String senha = "senha";
    private EditText edtSenha;
    private EditText edtNome;


    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnEnviar= (Button) findViewById(R.id.btnEnviar);
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtSenha = (EditText) findViewById(R.id.edtSenha);

    }
    public void onClick(View v){
        if (v == btnEnviar){
            uploadImage();
        }
    }
    private void uploadImage(){
        //Showing the progress dialog
        final ProgressDialog loading = ProgressDialog.show(this,"Uploading...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                        loading.dismiss();
                        //Showing toast message of the response
                        //Toast.makeText(ActMain.this, s , Toast.LENGTH_LONG).show();
                        int retorno = Integer.parseInt(s);
                        if (retorno == 1){
                            Toast.makeText(MainActivity.this, "Logado!" , Toast.LENGTH_LONG).show();
                            //chamaActProxima();
                            //finish();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Erro ao enviar!" , Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        loading.dismiss();
                        Toast.makeText(MainActivity.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            protected Map<String, String> getParams() throws AuthFailureError {

                String nomev= edtNome.getText().toString();
                String senhav= edtSenha.getText().toString();
                Map<String,String> params = new Hashtable<String, String>();
                params.put(nome ,nomev);
                params.put(senha, senhav);

                Log.d("ActMain", "retorno " + params);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
