package com.nte.financedcore.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

@Slf4j
@Service
public class MyDataCommon {

    @Value("${mydata.key}")
    private String KEY;

    /**
     * Return String Type.
     * */
    public String read(Map<String, String> map) throws Exception {

        String baseUrl = "https://apis.data.go.kr/1160100/service/GetStockSecuritiesInfoService/getStockPriceInfo";
        String serviceKey = URLEncoder.encode(KEY, "UTF-8");
        String resultType = URLEncoder.encode("json", "UTF-8");

        baseUrl += "?" + "serviceKey=" + serviceKey + "&" + "resultType=" + resultType;

        for(Map.Entry<String, String> entry : map.entrySet() ){
            String key = entry.getKey();
            String value = URLEncoder.encode(entry.getValue(), "UTF-8");
            baseUrl += "&" + key + "=" + value;
        }

        URL url = new URL(baseUrl);
        BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
        String result = bf.readLine();

        return result;
    }

}