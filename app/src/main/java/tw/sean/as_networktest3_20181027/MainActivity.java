package tw.sean.as_networktest3_20181027;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.BreakIterator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private File sdroot, uploadFile;
    private RequestQueue queue;
    private TextView mesg;
    private ListView list;
    private MyAdapter myAdapter;
    //private LinkedList<>;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //google: android developer request
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            init();
        }
        //google:
    }

    public void init() {
        sdroot = Environment.getExternalStorageDirectory();
        mesg = findViewById(R.id.mesg);
        list = findViewById(R.id.list);
        data = new LinkedList<>();
        myAdapter = new MyAdapter(this);
        list.setAdapter(myAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gotoDetail(position);
            }
        });
        queue = volley.newRequestQueue(this);
    }


    public void test1(View view){
        new Thread(){
            @Override
            public void run() {
                postTest();
            }
        }.start();
    }

    private void postTest(){
        try {
            URL url = new URL("http://javaee的Brad02");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setReadTimeout(3000);
            conn.setConnectTimeout(3000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");

            ContentValues values = new ContentValues();
            values.put("account", "brad");
            values.put("passwd", "1234");
            String query = queryString(values);

            OutputStream out = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(query);
            writer.flush();
            writer.close();

            conn.connect();
            conn.getInputStream();

            Log.v("brad", "Response code : ");


        }catch (Exception e) {
            Log.v("brad", e.toString());
        }
    }

    private String queryString(ContentValues data){
        //通用性的
        Set<String> keys = data.keySet();
        StringBuffer sb = new StringBuffer();

        try {
            for (String key : keys) {
                sb.append(URLEncoder.encode(key,"UTF-8"));
                sb.append("=");
                sb.append(URLEncoder.encode(data.getAsString(key),"UTF-8"));
                sb.append("&");
            }

            sb.deleteCharAt(sb.length()-1);
            return  sb.toString();
        }catch (Exception e) {
            return null;
        }
    }

    //上傳功能
    public void test2(View view) {
        new Thread(){
            @Override
            public void run() {
                uploadFile();
            }
        }.start();
    }

    private void uploadFile(){
        try {
            uploadFile = new File(sdroot, "XXX.pdf");
            MultipartUtility mu =
                    new MultipartUtility("","","UTF-8");
            mu.addFilePart("upload",uploadFile);
            List<String> result = mu.finish();
            for (String line : result) {
                Log.v("brad", line);
            }
        }catch (Exception e) {
            Log.v("brad", e.toString());
        }
    }

    //官方好用的api: volley
    //天生內建執行緒
    public void test3(View view) {
        StringRequest request = new StringRequest(
                Request.Method.GET,
                "http://data.coa.gov.tw/Service/OpenData/ODwsv/ODwsvTravelFood.aspx",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //mesg.setText(response);
                        parseJ
                    }
                },
                null
        );
        queue.add(request);
    }

    private void parseJSON(String json) {
        try {

        }catch (Exception e) {

        }
    }

    public void test4 (View view) {
        String send = input.getText().toString();
        StringRequest request = new StringRequest(
                Request.Method.POST,
                "url",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },null){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                HashMap<String, String> params = new HashMap<>();
                params.put("account", send);
                params.put("passwd", "123456");

                return params;
            }

        };
    }

    public void test5 (View view) {
        File upload = new File(sdroot, "brad.pdf");
        try {
            FileInputStream fin = new FileInputStream(upload);
            byte[] buf = new byte[(int)upload.length()];
            fin.read(buf);
            fin.close();
            final byte[] sendData = buf;

            VolleyMultipartRequest request =
                    new VolleyMultipartRequest(Request.Method.POST, "", new Response.Listener<NetworkResponse>() {
                        @Override
                        public void onResponse(NetworkResponse response) {

                        }
                    },null){
                        protected Map<String, DataPart> getByteData() throws AuthFailureError {

                        return params;
                        }
                    };

        }catch (Exception e){

        }


    }

    private class MyAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        MyAdapter(Context context){
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater.from(MainActivity.this);//浮現右鍵視窗
            View view = inflater.inflate(R.layout.item,null);

            TextView name = view.findViewById(R.id.name);
            TextView tel = view.findViewById(R.id.tel);
            name.setText(data.get(position).getName());
            tel.setText(data.get(position).getTel());

            if (position % 2 == 0) {

            }else {

            }

            return null;//回傳的畫面，需新增layout:item.xml
        }
    }


}
