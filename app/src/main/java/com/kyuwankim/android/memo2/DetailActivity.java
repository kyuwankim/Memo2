package com.kyuwankim.android.memo2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import static com.kyuwankim.android.memo2.R.id.editText;

public class DetailActivity extends AppCompatActivity {
    FloatingActionButton btnsave;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        et = (EditText) findViewById(editText);
        btnsave = (FloatingActionButton) findViewById(R.id.fab);
        String memo = read("memofile.txt");
        et.setText(memo);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1. 컨텐츠 가져오기
                String content = et.getText().toString();

                // 2. 컨텐츠를 파일에 저장
                write(content);
                Toast.makeText(DetailActivity.this, content, Toast.LENGTH_SHORT).show();

            }
        });
    }

    // 파일읽기
    private String read(String s) {
        String result = "";

        try {
            // 1. 스트림을 열고
            FileInputStream fis = openFileInput(s);

            // 2. 래퍼가 필요할 경우는 사용 - 문자열 캐릭터셋을 변환해주는 역할(생략가능)


            // 3. 버퍼를 씌워서 속도를 향상시킨후 한줄씩 읽어서 result 결과값에 계속 더해준다
            BufferedInputStream bis = new BufferedInputStream(fis);

            // 4. 내가 한번에 읽어올 단위를 설정
            byte buffer[] = new byte[1024];
            int count = 0;
                // 버퍼로 떴는데 몇글자가 들어있는지를 count에 담아준다
            while ((count = bis.read(buffer)) != -1) {

                String data = new String(buffer,0,count);

                result = result + data;
            }
            // 4. 스트림을 닫는다

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 파일에쓰기
    private void write(String content) {

        // 1. 스트림을 열거나 파일을 생성하거나
        try {
            FileOutputStream fos = openFileOutput("memofile.txt", MODE_PRIVATE);
            fos.write(content.getBytes());
            fos.close();
        } catch (Exception e) {
            Log.e("DetailActivity", e.toString());
        }

        // 2. 스트림을 통해서 데이터를 쓰고


        // 3. 스트림을 닫아준다


    }
}
