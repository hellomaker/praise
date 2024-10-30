package io.github.hellomaker.praise.mvc.view;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.View;

import java.util.Map;

/**
 * @author xianzhikun
 */
public class RestView implements View {

    public RestView(JSONObject result) {
        if (result == null) {
            throw new NullPointerException("result is null");
        }
        this.result = result;
    }

    JSONObject result;

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().print(JSONObject.toJSONString(result, JSONWriter.Feature.WriteLongAsString));
    }
}
