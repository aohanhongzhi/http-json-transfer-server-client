import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class ShopManagerJsonTest {

	public static void main(String[] args) {
		
		//这里是传输过来的json数据，下面是字符串
		String result = "[{\"version\":\"0.0.1\" ,\"shop_name\":\"xindian\",\"user_name\": \"houxyi\",\"user_data\": {\"normal\":[{\"goodsid\":\"6656656466\",\"goodsname\":\"可乐\",\"price\":\"3元/瓶\"},{\"goodsid\":\"6654444466\",\"goodsname\":\"饼干\",\"price\":\"13元/斤\"}], \"caijia\": \"your nickname\"}}]";

		// 根据字符串生成JSON对象
		JSONArray resultArray = new JSONArray(result);
		JSONObject resultObj = resultArray.optJSONObject(0);

		// 获取数据项
		String version = resultObj.getString("version");
		String user_name = resultObj.getString("user_name");

		System.out.format("version:%s\nusername:%s\n", version, user_name);

		// 获取数据对象
		JSONObject user_data = resultObj.getJSONObject("user_data");
		JSONArray normal = user_data.getJSONArray("normal");
		// 获取数组的数据

		ArrayList<Object> aList = (ArrayList<Object>) normal.toList();

		for (int i = 0; i < aList.size(); ++i) {
			HashMap<String, Object> goods_datas = (HashMap<String, Object>) aList.get(i);
			Object goods_id = goods_datas.get("goodsid");
			String goods_name = (String) goods_datas.get("goodsname");
			String price = (String)goods_datas.get("price");
			System.out.format("goodsid:%s\t goodsname:%s\t price:%s\n", goods_id, goods_name,price);

		}
	}
}
