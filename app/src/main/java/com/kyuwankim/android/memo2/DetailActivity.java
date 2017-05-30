package com.kyuwankim.android.memo2;

import android.content.Intent;
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

    String document_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        // 호출한 activity에서 intent에 값을 아무것도 넘기지 않으면 bundle이 null이 되기때문에
        // null에서는 getString  호출 시 Exception이 발생한다
        // 따라서 bundle의 null여부를 체크해준다
        if (bundle != null) {
            document_id = bundle.getString("document_id");
        }


        et = (EditText) findViewById(editText);
        btnsave = (FloatingActionButton) findViewById(R.id.fab);
        String memo = FileUtil.read(this, "memofile.txt");
        et.setText(memo);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1. 컨텐츠 가져오기
                String content = et.getText().toString();

                // 2. 파일 이름을 생성한다
                String filename = "MEMO" + System.nanoTime() + ".txt";
                // document_id 가 있으면 파일을 새로 생성하지 않고 기존 이름을 사용해서 수정처리한다
                if (!document_id.equals("")) {
                    filename = document_id;
                }

                // 3. 컨텐츠를 파일에 저장
                FileUtil.write(DetailActivity.this, filename, content);

            }
        });
    }

    // 파일읽기

}
