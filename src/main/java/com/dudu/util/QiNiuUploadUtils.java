package com.dudu.util;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.io.IOException;
import java.util.Date;


/**
 * @author: huyuanzhi
 * @version: 1.0
 * @date: 2016/10/9
 * @project: duduMaster
 * @packageName: com.dudu.util
 * @description: XXXXXX
 */

public class QiNiuUploadUtils {

    static String ACCESS_KEY = "nVrUPXxjcb_fPXXue7p6Ou7XGqk6LofTssy2q6TH";
    static String SECRET_KEY = "IRwefmkY2ztDb7mWFCIAUTm9txnSwcYvyMBb-SFh";
    static String bucketname = "soundblogpic";
    static String domain="http://7xp0v5.com1.z0.glb.clouddn.com/";



    private static String getUpToken(Auth auth) {
        return auth.uploadToken(bucketname);
    }

    public static String upload(byte[] b) throws IOException {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        UploadManager uploadManager = new UploadManager();
        Long num = new Date().getTime();
        try {
            Response res = uploadManager.put(b, num+"", getUpToken(auth));
            System.out.println(res.bodyString());
        } catch (QiniuException e) {

        }
        return domain+num;
    }
}
