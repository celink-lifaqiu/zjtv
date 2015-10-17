package com.magic.commons.http;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: yunchunnan
 * Date: 14-4-8
 * Time: 下午2:06
 * To change this template use File | Settings | File Templates.
 */
public class HttpClientHelper {
    public static String doGet(String url){
        if (url == null) {
            return "";
        }
        HttpClient httpClient = new HttpClient();
        HttpMethod method = new GetMethod(url);
        try {
            int statusCode = httpClient.executeMethod(method);
            if (statusCode == 200){
                return method.getResponseBodyAsString();
            } else {
                return "";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } catch (Exception e){
            e.printStackTrace();
            return "";
        } finally {
            method.releaseConnection();
        }
    }

    public static void main(String[] args) {
        String response = HttpClientHelper.doGet("http://hbzh-images.qiniudn.com/FhBCqQssPdxvmSrYy7NgccfPFBeG?imageInfo");
        System.out.println(response);
    }
}
