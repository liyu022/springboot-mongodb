package com.hdsx.lwgl.statanalysis.service.serviceImpl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdsx.lwgl.statanalysis.service.IRemoteService;
import com.hdsx.lwgl.statanalysis.util.HttpHelper;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
@ConfigurationProperties(prefix="mapdata.http") //接收application.yml中的roadQuery下面的属性
public class HttpServiceImpl implements IRemoteService {

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //路线图层名称
    String url;


    //路线图层名称
    String lxLayerName;

    public String getXzqhLayerName() {
        return xzqhLayerName;
    }

    public void setXzqhLayerName(String xzqhLayerName) {
        this.xzqhLayerName = xzqhLayerName;
    }

    //路线图层名称
    String xzqhLayerName;

    public String getLxLayerName() {
        return lxLayerName;
    }

    public void setLxLayerName(String lxLayerName) {
        this.lxLayerName = lxLayerName;
    }

    ObjectMapper mapper = new ObjectMapper();

    /**
     * http://10.228.2.73:8080/mapdata/rest/mapdata/getSegment?layerName=GIS_LX_M&where=LXDM%253D'G40'&startPos=1459.8&endPos=1465.8
     * @param lxdm 路线代码
     * @param roadEnd 桩号
     * @param roadstart 桩号
     * @return
     */
    @Override
    public String getSegment(String lxdm, BigDecimal roadstart, BigDecimal roadEnd) {
        String roadWkt = null;
        Map<String,String> params = new HashMap<String,String>();
        params.put("layerName",this.lxLayerName);
        StringBuilder wereSqlBuilder = new StringBuilder("LXDM='").append(lxdm).append("'");
        params.put("where",wereSqlBuilder.toString());
        params.put("startPos",roadstart.toString());
        params.put("endPos",roadEnd.toString());
        try {
            String jsonResult = HttpHelper.post(params,"http://" + this.url + "/mapdata/rest/mapdata/getSegment");
            JsonNode jsonNode =  mapper.readTree(jsonResult);
            if(jsonNode!=null){
                jsonNode = jsonNode.get("features");
                if(jsonNode!=null && jsonNode.size()> 0){
                    jsonNode = jsonNode.get(0).get("geometry");
                    if(jsonNode!=null){
                        roadWkt = jsonNode.asText();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return roadWkt;
    }
}
