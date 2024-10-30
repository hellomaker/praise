package io.github.hellomaker.praise.mvc.view;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;

/**
 * @author xianzhikun
 */
public class R<T> extends RestView{

    public R(JSONObject result) {
        super(result);
    }

    public static class Builder<T> {
        private String id;
        private Boolean success;
        private String message;
        private Integer code; // 使用字符串作为code
        private T data;
        private final JSONObject jsonObject = new JSONObject();

        public Builder<T> id(String id) {
            jsonObject.put("id", id);
            this.id = id;
            return this;
        }

        public Builder<T> message(String message) {
            jsonObject.put("message", message);
            return this;
        }

        public Builder<T> code(Integer code) {
            jsonObject.put("code", code);
            this.code = code;
            return this;
        }

        public Builder<T> data(T data) {
            jsonObject.put("data", data);
            this.data = data;
            return this;
        }

        public Builder<T> append(String name, Object data) {
            jsonObject.put(name, data);
            return this;
        }

        public R<T> buildPlain() {
            return new R<>(jsonObject);
        }
    }

    public static <T> Builder<T> builder() {
        return new Builder<T>();
    }

    public static <T> R<T> ok(T data) {
        return R.<T>builder().code(2000).message("success").data(data).buildPlain();
    }

    public static <T> R<T> ok() {
        return R.<T>builder().code(2000).message("success").data(null).buildPlain();
    }

}
