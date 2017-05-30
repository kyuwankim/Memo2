package com.kyuwankim.android.memo2.domain;

import android.content.Context;

import com.kyuwan.android.util.FileUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by kimkyuwan on 2017. 5. 30..
 */

public class Loader {
    // 파일이 저장되는 디렉토리(internal Storage) 구조 = /data/data/패키지명/files
    public static final String DIR = "/data/data/com.kyuwankim.android.memo2/files";

    // 메모를 저장한 디렉토리를 리스팅해서
    // 파일 목록과
    // 해당 파일의 내용 첫줄
    // 해당 파일의 마지막 수정일자를 담아서 리턴한다
    public static ArrayList<Memo> getData(Context context) {
        ArrayList<Memo> datas = new ArrayList<>();
        // 1.1 목록을 가져올 디렉토리경로를 파일 클래스 생성
        File dir = new File(DIR);
        // 1.2 파일 클래스에 정의된 listFiles 함수를 이용해서 파일 목록을 가져온다
        File files[] = dir.listFiles();
        // 1.3 파일이 하나도 없으면 그냥 리턴한다
        if (files == null) {
            return datas;
        }


        // 2.1 반복문을 돌면서 파일의 내용을 Memo 객체에 담은 후 datas에 add 한다
        for (File file : files) {

            // 2.2 파일이면 (디렉토리일 경우는 안함)
            if (file.isFile()) {
                Memo memo = new Memo();
                // 2.2.1 파일명을 가져와서 담는다
                memo.setId(file.getName());
                // 2.2.2 작성일자를 formatting 해서 문자열로 담는다
                memo.setDate(convertLongToDate(file.lastModified()));
                // 2.2.3 파일에 첫줄만 가져와서 담는다
                String firstLine = FileUtil.readFirstLine(context, file.getName());

                memo.setContent(firstLine);

                datas.add(memo);
            }
        }

        return datas;
    }

    public static String convertLongToDate(long value) {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd HH/:mm:ss");
        return sdf.format(value);
    }
}
