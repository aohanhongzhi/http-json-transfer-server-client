
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSONObject;


/*
 * 系统测试环境
 * Mac
 * eclipse
 * Dynamic Web Project
 * Tomcat9.0
 * jdk1.8
 * 
 */


/**
 * Servlet implementation class JsonServerTest
 */
//@WebServlet("/JsonServerTest")
public class JsonServerTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public JsonServerTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Servlet has inited！");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 *      protected void service(HttpServletRequest request, HttpServletResponse
	 *      response) throws ServletException, IOException { // TODO Auto-generated
	 *      method stub System.out.println("客户端访问中");
	 * 
	 *      //
	 *      response.getWriter().append("Served at: ").append(request.getContextPath());
	 *      }
	 */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Get方法响应");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Post方法响应");
		synYxGoods(response, request);
	}

	public void synYxGoods(HttpServletResponse response, HttpServletRequest request) throws IOException {
		// String json = request.getParameter("param"); //这是通过通过get方式去url
		// 拼接的键值对，post方式取不到值。
		request.setCharacterEncoding("UTF-8"); // 返回页面防止出现中文乱码
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));// post方式传递读取字符流
		String jsonStr = null;
		StringBuilder result = new StringBuilder();
		try {
			while ((jsonStr = reader.readLine()) != null) {
				result.append(jsonStr);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		reader.close();// 关闭输入流
		JSONObject jsonObject = JSONObject.parseObject(result.toString()); // 取一个json转换为对象
		System.out.format("version:%s\nusername:%s\n", jsonObject.get("version"), jsonObject.get("user_name"));

		// 获取数据对象
		JSONObject user_data = jsonObject.getJSONObject("user_data");
		com.alibaba.fastjson.JSONArray normal = user_data.getJSONArray("normal");

		// 遍历方式2
		for (Object obj : normal) {
			JSONObject goods_datas = (JSONObject) obj;
			Object goods_id = goods_datas.get("goodsid");
			String goods_name = (String) goods_datas.get("goodsname");
			String price = (String) goods_datas.get("price");
			System.out.format("goodsid:%s\t goodsname:%s\t price:%s\n", goods_id, goods_name, price);
		}
		response.getWriter().append("successful!\tServed at: ").append(request.getContextPath());
		// 获取数组的数据

//    ArrayList<Object> aList =  normal.toList();

//    for (int i = 0; i < aList.size(); ++i) {
//      HashMap<String, Object> goods_datas = (HashMap<String, Object>) aList.get(i);
//      Object goods_id = goods_datas.get("goodsid");
//      String goods_name = (String) goods_datas.get("goodsname");
//      String price = (String)goods_datas.get("price");
//      System.out.format("goodsid:%s\t goodsname:%s\t price:%s\n", goods_id, goods_name,price);
//
//    }

		// logger.info(jsonObject);
//	GoodsInfo goodsInfo = new GoodsInfo();
//	Date date = new Date();
//	goodsInfo.setAccess_code1("001");
//	                goodsInfo.setAccess_code1("001");
//	                goodsInfo.setGoodsName(jsonObject.getString("goodsName"));     //通过键取到值，再将值封装到类里面。
//	               goodsInfo.setType(Integer.parseInt(jsonObject.getString("type")));
//	List<ResultData<String>> data = yxGoodsService.synYxGoodsInfo(goodsInfo);
//	String json_str = JSON.toJSONString(data);
//	return write(response, json_str);
	}

}
