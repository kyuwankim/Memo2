package com.kyuwankim.android.memo2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.kyuwan.android.util.FileUtil;

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
        String memo = FileUtil.read(this, "memofile.txt");
        et.setText(memo);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1. 컨텐츠 가져오기
                String content = et.getText().toString();

                // 2. 컨텐츠를 파일에 저장
                FileUtil.write(DetailActivity.this, content);

            }
        });
    }

    // 파일읽기

}
