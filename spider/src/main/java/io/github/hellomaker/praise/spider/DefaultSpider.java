package io.github.hellomaker.praise.spider;

import io.github.hellomaker.praise.spider.regex.RegexUtils;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;

/**
 * @author hellomaker
 */
public class DefaultSpider implements Spider{

    // 创建 httpClient 对象
    CloseableHttpClient httpClient = HttpClients.createDefault();

    @Override
    public String crawling(String url, Object... params) {
        // 创建 httpGet 对象，设置访问 URL
        HttpGet httpGet = new HttpGet(RegexUtils.parse(url, params));
        CloseableHttpResponse response = null;
        try {
            // 发送请求
            response = httpClient.execute(httpGet);
            // 根据状态码判断是否响应成功（一般是 200）
            if (response.getCode() == 200) {
                // 解析响应
                HttpEntity entity = response.getEntity();
                // 打印响应内容
//                System.out.println(html);
                return EntityUtils.toString(entity, Charset.forName("utf-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 关闭资源
            try {
//                httpClient.close();
                if (Objects.nonNull(response)) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void close() {
        try {
            httpClient.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
