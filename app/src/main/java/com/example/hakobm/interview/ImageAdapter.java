package com.example.hakobm.interview;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by hakobm on 8/31/2017.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    String json_string;
    int imageTotal = 11;
    public static String[] mThumbIds = {
           "http://fototips.ru/wp-content/uploads/2011/12/landscape_03.jpg",
    "http://fototips.ru/wp-content/uploads/2011/12/landscape_02.jpg",
    "http://www.art-portrets.ru/pict6/peyzaj-s-tserkovyu.jpg",
    "http://www.art-vernissage.ru/media/picture/28/osenvstaromparke.jpg",
    "https://img-fotki.yandex.ru/get/47606/67000176.32f/0_bc7c5_1a726f4c_XL.jpg",
    "https://wpapers.ru/wallpapers/nature/Autumn/9991/PREV_%D0%9A%D1%80%D0%B0%D1%81%D0%B8%D0%B2%D1%8B%D0%B9-%D0%BE%D1%81%D0%B5%D0%BD%D0%BD%D0%B8%D0%B9-%D0%BF%D0%B5%D0%B9%D0%B7%D0%B0%D0%B6.jpg",
    "http://cdn-tn.fishki.net/26/upload/post/201506/08/1560136/gora-shasta-v-kalifornii-ssha.jpg",
    "http://www.fotolandscape.com/wp-content/uploads/2014/10/013.jpg",
   "https://s-media-cache-ak0.pinimg.com/736x/ea/ef/72/eaef72c16646993346a43892b54bedee.jpg",
   "http://fotoprirodi.narod.ru/images/gory/gory24.jpg","http://img0.liveinternet.ru/images/attach//2//71/413/71413156_WindowsLiveWriter_a070158a2e19_E7F6_RRSRRRRyoR_RRRRRRRyo_3_d6da9e5c44da4bb59e4927dc951c43df.jpg",
    "https://s-media-cache-ak0.pinimg.com/736x/1a/7c/b3/1a7cb3c34b2f7210846f7ddc98f9f99f.jpg"



    };


    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return imageTotal;
    }

    @Override
    public String getItem(int position) {
        return mThumbIds[position];
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(480, 480));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        String url = getItem(position);
        Picasso.with(mContext)
                .load(url)
                .placeholder(R.drawable.loader)
                .fit()
                .centerCrop().into(imageView);
        return imageView;
    }


    class BackgroundTask extends AsyncTask {
        String JSON_STRING;
        String json_url;

        @Override
        protected void onPreExecute() {
            json_url = "http://devcandidates.alef.im/list.php";
        }

        @Override
        protected String doInBackground(Object[] params) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = bufferedReader.readLine()) != null) {
                    stringBuilder.append(JSON_STRING + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Object o) {
         //   TextView textView = (TextView) findViewById(R.id.textview);
           // textView.setText((CharSequence) o);
            json_string = (String) o;
        }


    }
}
