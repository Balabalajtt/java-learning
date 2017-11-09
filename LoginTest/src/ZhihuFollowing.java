import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ZhihuFollowing {
    private String url = "https://www.zhihu.com/api/v4/members/excited-vczh/followees?include=data[*].answer_count,articles_count,gender,follower_count,is_followed,is_following,badge[?(type=best_answerer)].topics&offset=0&limit=20https://www.zhihu.com/api/v4/members/excited-vczh/followees?include=data[*].answer_count,articles_count,gender,follower_count,is_followed,is_following,badge[?(type=best_answerer)].topics&offset=0&limit=20";

    private CloseableHttpClient httpC = HttpClients.custom().build();

    private String getText() {

        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Host", "www.zhihu.com");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0");
        httpGet.setHeader("Accept", "application/json, text/plain, */*");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate, br");
        httpGet.setHeader("Referer", "https://www.zhihu.com/people/jjmoe/following");
        httpGet.setHeader("authorization", "oauth c3cef7c66a1843f8b3a9e6a1e3160e20");
        httpGet.setHeader("x-udid", "ABBCwIlUfQyPTrpjg6Mo-7n_JB8To-1OlOU=");
        httpGet.setHeader("origin", "https://www.zhihu.com");

        httpGet.setHeader("Cookie", "q_c1=52982f8b821b44e79e6f761d93fc2357|1506525709000|1506525709000; q_c1=639c94471633436a84cea1deb07d8a72|1509804514000|1506525709000; _zap=ced7bf40-a461-42e0-8897-ed8d1a6eba12; l_cap_id=\"OGU1MGMzMWM3YjRiNDNhNmIxMWRjNTQ4ZmQ1NDBlNjU=|1510239602|96872b2a7e4904703a5372203476e2f857cb0c83\"; r_cap_id=\"YzM2NzcyZGE3ZTVmNDcwNjhjM2Y3YzExNGYyMjYzYzg=|1510239602|8ed26df51e898b6f6b7713d4e5360906c4ee40ef\"; cap_id=\"YzNlZmM4ZjcwYzYzNGE1OWE0N2MxNTA4M2FkZjBkNzU=|1510239602|8abbf803269d5b7edf81cffc05276b07951bc7c4\"; d_c0=\"ABBCwIlUfQyPTrpjg6Mo-7n_JB8To-1OlOU=|1507349199\"; __utma=51854390.77523427.1508236687.1508236687.1510238658.2; __utmz=51854390.1510238658.2.2.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; __utmv=51854390.000--|3=entry_date=20170927=1; aliyungf_tc=AQAAAIWVEV/U9wMAHNggdVt8MSV/RbfP; _xsrf=9a81a1a6-11fa-4d1d-9f0a-4643af5bd9e2; __utmb=51854390.0.10.1510238658; __utmc=51854390; s-q=%E6%A1%A5%E9%80%97%E9%BA%BB%E8%A2%8B533; s-i=1; sid=koos0018; s-t=autocomplete");
        httpGet.setHeader("Connection", "keep-alive");
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String s = "";
        try {
            s = httpC.execute(httpGet, responseHandler);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpGet.abort();
        }

        String rtn = "";
        try {
            rtn = new String(s.getBytes("gb2312"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return rtn;
    }

    public static String unicode2String(String unicode) {
        StringBuffer string = new StringBuffer();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            int data = Integer.parseInt(hex[i], 16);
            string.append((char) data);
        }
        return string.toString();
    }

    public static void main(String[] args) {
        ZhihuFollowing zhihuFollowing = new ZhihuFollowing();

        String jsonArray = zhihuFollowing.getText();
        System.out.println(jsonArray);

        String[] jss = new String[10];

        for(int i = 0; i < 10; i++) {
            jsonArray = jsonArray.substring(jsonArray.indexOf("\"name\": \""), jsonArray.length());
//            System.out.println(jsonArray.indexOf("\"name\": \"") + 9);
//            System.out.println(jsonArray.indexOf("\", \""));
//            System.out.println(jsonArray.length());
            jss[i] = jsonArray.substring(jsonArray.indexOf("\"name\": \"") + 9, jsonArray.indexOf("\", \""));
//            System.out.println(jss[i]);
            if(jss[i].contains("\\u")) {
                System.out.println("关注的第" + (i+1) + "个人：" + unicode2String(jss[i]));
            } else {
                System.out.println("关注的第" + (i+1) + "个人：" + jss[i]);
            }
            jsonArray = jsonArray.substring(jsonArray.indexOf("\"name\": \"") + 10, jsonArray.length());
        }


    }



}
