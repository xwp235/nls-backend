package com.nlsapi.core.business.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nlsapi.core.business.req.TestReq;
import com.nlsapi.core.common.resp.JsonResp;
import com.nlsapi.core.common.utils.JsonUtil;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final ObjectMapper objectMapper;


    @GetMapping
    public JsonResp index(@RequestParam String username) {
        return JsonResp.ok();
    }

    @GetMapping("2")
    public JsonResp test2(@Valid TestReq req) {
        System.out.println(req);
        return JsonResp.ok();
    }

    @PostMapping
    public String index2(@RequestBody User data) {
        return "Hello, youmi api!";
    }

    @GetMapping("date1")
    public ZonedDateTime date1() {
        String json1 = "{\"name\": \"John\", \"age\": 30, \"address\": {\"city\": \"New York\"}}";
        String json2 = "{\"age\": 25, \"address\": {\"street\": \"Broadway\"}, \"email\": \"john@example.com\"}";
        String json3 = "{\"phone\": \"123-456-7890\", \"address\": {\"zipcode\": \"10001\"}}";
        JsonNode node1 = null;
        JsonNode node2 = null;
        JsonNode node3 = null;
        try {
            node1 = objectMapper.readTree(json1);
            node2 = objectMapper.readTree(json2);
            node3 = objectMapper.readTree(json3);
        } catch (Exception e) {

        }
        JsonUtil.json2Obj(json3,Object.class);
        // 合并多个 JSON 对象
        JsonNode mergedNode = JsonUtil.mergeMultiple(node1, node2, node3);
        System.out.println(">>>>" + mergedNode);
//        int a = 10/0;

        return null;
    }

    @PostMapping("upload")
    public void test3(@RequestBody List<MultipartFile> files) {
        System.out.println(files);
    }

    @PostMapping("str1")
    public void test4(@RequestBody List<String> strs) {

    }

    @PostMapping("file1")
    public void file1(MultipartFile file,String strs) {
        System.out.println(file);
    }


    @Data
    public static class User {
        private String username;
        private String password;
    }

}
