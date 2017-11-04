import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ZhengFang {
    private static String idCode = "04163216";
    private static String password = "waxi1998JTT.";
    private static String loginUrl = "http://222.24.62.120/default2.aspx";
    private static String checkCode = "";
    private String viewState;

    private String cookie = "";
    HttpResponse loginResponse;
    private CloseableHttpResponse response;
    private CloseableHttpClient httpClient;

    private boolean getCookie() {

//        HttpParams params = httpClient.getParams();
//        params.setParameter(ClientPNames.HANDLE_REDIRECTS, false);
        RequestConfig config = RequestConfig.custom().setRedirectsEnabled(false).build();//不允许重定向
        httpClient = HttpClients.custom().setDefaultRequestConfig(config).build();

        HttpGet get=new HttpGet("http://222.24.62.120/default2.aspx");
        get.setConfig(RequestConfig.custom()
                .setSocketTimeout(5000)
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .setRedirectsEnabled(false)
                .build());
        CloseableHttpResponse response= null;//提交请求获得响应
        try {
            response = httpClient.execute(get);
            System.out.println("获取cookie" + response);

        } catch (IOException e) {
            e.printStackTrace();
        }
        cookie = response.getFirstHeader("Set-Cookie").getValue().substring(0, response.getFirstHeader("Set-Cookie").getValue().indexOf(";") + 1);
        System.out.println("获取cookie的sessionId" + cookie);
        return true;
    }


    private boolean getViewState() {

        HttpResponse response;
        HttpGet httpGet = new HttpGet("http://222.24.62.120/default2.aspx");

        httpGet.setConfig(RequestConfig.custom()
                .setSocketTimeout(5000)
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .setRedirectsEnabled(false)
                .build());

        httpGet.setHeader("Host", "222.24.62.120");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0");
        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate");
        httpGet.setHeader("Cookie", cookie);
        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setHeader("Update-Insecure-Requests", "1");
//        httpGet.setHeader("Cache-Control", "max-age=0");
//        httpGet.setHeader("Referer", "http://222.24.62.120/");
        System.out.println(cookie);
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("获取ViewStat" + response);
        String htmlCode = "";
        try {
            htmlCode = EntityUtils.toString(response.getEntity(),"GB2312");
//            System.out.println(htmlCode);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        sessionId = firstResponse.getFirstHeader("Set-Cookie").getValue().substring(0, firstResponse.getFirstHeader("Set-Cookie").getValue().indexOf(";"));
//        System.out.println(sessionId);
        int indexS = htmlCode.indexOf("__VIEWSTATE");
        int indexE = 0;
//        System.out.println(htmlCode.substring(indexS, indexS + 80));
        for (int i = indexS;; i++) {
            if (htmlCode.substring(i, i + 7).equals("value=\"")){
                indexS = i + 7;
                break;
            }
        }
        for (int i = indexS;; i++) {
            if (htmlCode.substring(i, i + 4).equals("\" />")) {
                indexE = i;
                break;
            }
        }
        viewState = htmlCode.substring(indexS, indexE);
        System.out.println("获取到的viewstate" + viewState);

        return true;
    }

    private boolean getCheckCode() {
        HttpResponse imageResponse = null;
        HttpGet httpGet = new HttpGet("http://222.24.62.120/CheckCode.aspx");

        httpGet.setConfig(RequestConfig.custom()
                .setSocketTimeout(5000)
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .setRedirectsEnabled(false)
                .build());

        httpGet.setHeader("Host", "222.24.62.120");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0");
        httpGet.setHeader("Accept", "*/*");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate");
        httpGet.setHeader("Referer", "http://222.24.62.120/default2.aspx");
        httpGet.setHeader("Cookie", cookie);
//        System.out.println(sessionId);
        httpGet.setHeader("Connection", "keep-alive");
        try {
            imageResponse = httpClient.execute(httpGet);
//            sessionId = imageResponse.getFirstHeader("Set-Cookie").getValue().substring(0, imageResponse.getFirstHeader("Set-Cookie").getValue().indexOf(";"));
//            System.out.println("img : " + cookie);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(imageResponse);
        byte[] responseBody = null;
        try {
            responseBody = EntityUtils.toByteArray(imageResponse.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(cookie);

//        System.out.println("************************");
//        System.out.println(responseBody);
        downloadPicture(responseBody);
        return true;
    }


    private static void downloadPicture(byte[] img) {
        URL url = null;
        int imageNumber = 0;

        try {
            String imageName =  "F:/CheckCode.aspx.gif";
            FileOutputStream fileOutputStream = new FileOutputStream(new File(imageName));
            fileOutputStream.write(img);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean login() {
        HttpPost httpPost = new HttpPost(loginUrl);
        httpPost.setConfig(RequestConfig.custom()
                .setRedirectsEnabled(false)
                .build());
        httpPost.setHeader("Host", "222.24.62.120");
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0");
        httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        httpPost.setHeader("Accept-Encoding", "gzip, deflate");
        httpPost.setHeader("Referer", "http://222.24.62.120/");
//        httpPost.setHeader("Referer", "http://222.24.62.120/xs_main.aspx?xh=04163216");
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
//        httpPost.setHeader("Content-Length", "195");
        httpPost.setHeader("Cookie", cookie);
        httpPost.setHeader("Connection", "keep-alive");
        httpPost.setHeader("Update-Insecure-Requests", "1");

        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("_VIEWSTATE", viewState));
        nvps.add(new BasicNameValuePair("txtUserName", idCode));
        nvps.add(new BasicNameValuePair("Textbox1", ""));
        nvps.add(new BasicNameValuePair("TextBox2", password));
        nvps.add(new BasicNameValuePair("txtSecretCode", checkCode));
        nvps.add(new BasicNameValuePair("RadioButtonList1", "%D1%A7%C9%FA"));
        nvps.add(new BasicNameValuePair("Button1", ""));
        nvps.add(new BasicNameValuePair("lbLanguage", ""));
        nvps.add(new BasicNameValuePair("hidPdrs", ""));
        nvps.add(new BasicNameValuePair("hidsc", ""));

        System.out.println(cookie);

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            loginResponse = httpClient.execute(httpPost) ;//执行获得response
            System.out.println("登录response" + loginResponse);
//            System.out.println("loginR   " + EntityUtils.toString(loginResponse.getEntity()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;

    }

    /**
     * 获取登录后重定向的url
     * @return 重定向url
     */
    private String getRedirectLocation() {

        Header header = loginResponse.getFirstHeader("Location");
        System.out.println("重定向的url" + header);
        if (header == null) {
            return null;
        } else {
            return header.getValue();
        }
    }

    private boolean test() {
        HttpResponse imageResponse = null;
        HttpGet httpGet = new HttpGet("http://222.24.62.120/xs_main.aspx?xh=04163216");
        httpGet.setHeader("Host", "222.24.62.120");

        httpGet.setHeader("Referer", "http://222.24.62.120/default2.aspx");
        httpGet.setHeader("Cookie", cookie);
//        System.out.println(sessionId);
        httpGet.setHeader("Connection", "keep-alive");
        System.out.println("1");
        try {
            imageResponse = httpClient.execute(httpGet);
            System.out.println("1");
//            sessionId = imageResponse.getFirstHeader("Set-Cookie").getValue().substring(0, imageResponse.getFirstHeader("Set-Cookie").getValue().indexOf(";"));
//            System.out.println("img : " + cookie);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(imageResponse);
        return true;
    }

    public static void main(String[] args) {
        ZhengFang zhengFang = new ZhengFang();
        zhengFang.getCookie();
        zhengFang.getViewState();
        zhengFang.getCheckCode();
        checkCode = new Scanner(System.in).nextLine().substring(0, 4);
        zhengFang.login();
        zhengFang.getRedirectLocation();
//        zhengFang.test();
    }

}
