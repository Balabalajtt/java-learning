import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class RenRen {
    private static String userName = "18792898826";
    private static String password = "waxi1998JTT.";
    private static String redirectURL = "http://blog.renren.com/blog/304317577/449470467";
    private static String renRenLoginURL = "http://www.renren.com/PLogin.do";

    private CloseableHttpResponse response;
    private CloseableHttpClient httpClient = HttpClients.createDefault();

    /**
     * 处理参数
     * 登录post请求
     * @return 是否登录成
     */
    private boolean login() {
        HttpPost httpPost = new HttpPost(renRenLoginURL);
        //参数
        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("originURL", redirectURL));
        nvps.add(new BasicNameValuePair("domain", "renren.com"));
        nvps.add(new BasicNameValuePair("isplogin", "true"));
        nvps.add(new BasicNameValuePair("formName", ""));
        nvps.add(new BasicNameValuePair("method", ""));
        nvps.add(new BasicNameValuePair("submit", "登录"));
        nvps.add(new BasicNameValuePair("email", userName));
        nvps.add(new BasicNameValuePair("password", password));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            response = httpClient.execute(httpPost);//执行获得response
            System.out.println("uuuuuuuuuuuuuu" + response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            httpPost.abort();
        }
        return true;

    }

    /**
     * 获取登录后重定向的url
     * @return 重定向url
     */
    private String getRedirectLocation() {
        System.out.println(response);
        Header header = response.getFirstHeader("Location");
        System.out.println(header);
        System.out.println(header);
        if (header == null) {
            return null;
        } else {
            return header.getValue();
        }
    }

    /**
     * Get请求重定向网页
     * @param redirectLocation 重定向的url
     * @return responseBody
     */
    private String getText(String redirectLocation) {
        HttpGet httpGet = new HttpGet(redirectLocation);
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String responseBody = "";
        try {
            responseBody = httpClient.execute(httpGet, responseHandler);
        } catch (IOException e) {
            e.printStackTrace();
            responseBody = null;
        } finally {
            httpGet.abort();
        }
        return responseBody;
    }

    public void printText() {
        if (login()) {
            String redirectLocation = getRedirectLocation();
            if (redirectLocation != null) {
                System.out.println(getText(redirectLocation));
            }
        }
    }

    public static void main(String[] args) {
        RenRen renRen = new RenRen();
        renRen.printText();
    }

}