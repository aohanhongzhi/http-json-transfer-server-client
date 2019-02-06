package snippet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *http client post method
 * 
 */
public class Demo {

	public static String sendPost(String url, String param) {

		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	public static void main(String[] args) {
		/*
		 * 
		 * JSONObject jsonobject = new JSONObject(); jsonobject.put("goodsName",
		 * "2018/07/06 18:30:26"); jsonobject.put("pageNo", 1);
		 * jsonobject.put("pageSize", 10); jsonobject.put("fyId", 2400);
		 * jsonobject.put("ajlx", 1);
		 */

		// 这里是传输过来的json数据，下面是字符串
		String result = "[{\"version\":\"0.0.1\" ,\"shop_name\":\"xindian\",\"user_name\": \"houxyi\",\"user_data\": {\"normal\":[{\"goodsid\":\"6656656466\",\"goodsname\":\"可乐\",\"price\":\"3元/瓶\"},{\"goodsid\":\"6654444466\",\"goodsname\":\"饼干\",\"price\":\"13元/斤\"}], \"caijia\": \"your nickname\"}}]";

		// 根据字符串生成JSON对象
		JSONArray resultArray = new JSONArray(result);
		JSONObject jsonobject = resultArray.optJSONObject(0);

		// 发送 POST 请求
		String sr = Demo.sendPost("http://192.168.0.104:8080/JsonSeverTest/post", jsonobject.toString());
		System.out.println(sr);
	}
}
