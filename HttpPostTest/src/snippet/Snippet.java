package snippet;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Snippet {
	@Test
	public void synYxGoodsInfoTest() {
	try {
	String url = "http://192.168.0.104:8080/JsonSeverTest";
	GoodsInfo goodsInfo = new GoodsInfo();
	goodsInfo.setGoods_id(111);
	goodsInfo.setGoodsName("1231213");
	goodsInfo.setBrand(1);
	goodsInfo.setType(1);
	DefaultHttpClient httpClient = new DefaultHttpClient();
	HttpPost httpPost = new HttpPost(url);
	httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
	String jsonstr = JSON.toJSONString(goodsInfo);
	StringEntity se = new StringEntity(jsonstr);
	                        se.setContentType("text/json");
	                       se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
	                       httpPost.setEntity(se);
	                       HttpResponse response=httpClient.execute(httpPost);
	//输出调用结果
	if(response != null && response.getStatusLine().getStatusCode() == 200) {
	String result=EntityUtils.toString(response.getEntity());
	 
	// 生成 JSON 对象
	JSONObject obj = JSONObject.parseObject(result);
	
	String errorcode = obj.getString("errorcode");
	
	if("000".equals(errorcode)) {
	System.out.println("addHkfishOrder_request_success");
	}
	}else {
		System.out.println(response.getStatusLine().getStatusCode()+"服务器不可达");
	}
	 
	} catch (Exception e) {
	System.out.println("======回不来了=======" );
	}
	
	} 
	
}

