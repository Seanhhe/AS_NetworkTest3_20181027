package tw.sean.as_networktest3_20181027;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

public class DetailActivity extends AppCompatActivity {
    private TextView words;
    private ImageView img;
    private Food food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        food = (Food)intent.getSerializableExtra("food");

        words = findViewById(R.id.words);

        queue = Volley.newRequestQueue(this);
        fetchImage();

    }

    private void fetchImage() {
        ImageRequest request = new ImageRequest("", new Response.Listener<Bitmap>());
        @Override
        public void onResponse(Bitmap response){
            img.setImageBitmap(response);
        }
    }, 0, 0,


}
