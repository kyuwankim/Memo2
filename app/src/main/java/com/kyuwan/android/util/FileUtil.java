package com.kyuwan.android.util;

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by kimkyuwan on 2017. 5. 30..
 */

public class FileUtil {


    public static String read(Context context, String s) {
        String result = "";

        try {
            // 1. 스트림을 열고
            FileInputStream fis = context.openFileInput(s);

            // 2. 래퍼가 필요할 경우는 사용 - 문자열 캐릭터셋을 변환해주는 역할(생략가능)


            // 3. 버퍼를 씌워서 속도를 향상시킨후 한줄씩 읽어서 result 결과값에 계속 더해준다
            BufferedInputStream bis = new BufferedInputStream(fis);

            // 4. 내가 한번에 읽어올 단위를 설정
            byte buffer[] = new byte[1024];
            int count = 0;
            // 버퍼로 떴는데 몇글자가 들어있는지를 count에 담아준다
            while ((count = bis.read(buffer)) != -1) {

                String data = new String(buffer, 0, count);

                result = result + data;
            }
            // 4. 스트림을 닫는다

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 파일에쓰기
    public static void write(Context context, String filename, String content) {

        // 1. 스트림을 열거나 파일을 생성하거나
        try {
            FileOutputStream fos = context.openFileOutput(filename, MODE_PRIVATE);
            fos.write(content.getBytes());
            fos.close();
        } catch (Exception e) {
            Log.e("DetailActivity", e.toString());
        }

        // 2. 스트림을 통해서 데이터를 쓰고


        // 3. 스트림을 닫아준다


    }

}
