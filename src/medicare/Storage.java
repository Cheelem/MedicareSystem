package medicare;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.Set;

import org.json.*;
import medicare.model.*;

/**
 * JSON Storage Controller 提供 JSON 对象存储 以及 CRUD 基本操作接口
 * 
 * @author 李林根 / 20165254 / NEU
 *
 */
public class Storage {

	private JSONObject storageJSON;
	private static Storage instance;

	/**
	 * 默认磁盘存储路径文件
	 */
	public static final String DEFAULT_STORAGE_PATH = "runtime/storage.json";

	private Storage() {
		loadFromFile();
	}

	public JSONObject root() {
		return storageJSON;
	}

	/**
	 * 打印当前库内容到控制台
	 */
	public void var_dump() {
		System.out.println(storageJSON.toString());
	}

	/**
	 * 从默认文件加载库
	 * 
	 * @param path
	 *            文件路径
	 * @return 是否加载成功
	 */
	public boolean loadFromFile() {
		try {
			storageJSON = new JSONObject(readFile(DEFAULT_STORAGE_PATH));
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;

		}
	}

	/**
	 * 从文件加载库
	 * 
	 * @param path
	 *            文件路径
	 * @return 是否加载成功
	 */
	public boolean loadFromFile(String path) {
		try {
			storageJSON = new JSONObject(readFile(path));
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean loadFromJSON(String json) {
		try {
			storageJSON = new JSONObject(json);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * @return 单例模式对象
	 */
	public static Storage getStarted() {
		if (instance == null) {
			instance = new Storage();
		}
		return instance;
	}

	/**
	 * 增加
	 * 
	 * @param className
	 *            存储类型名称
	 * @param id
	 *            存储对象ID
	 * @return 指示是否新增成功的布尔值
	 */
	public boolean create(String className, Object obj) {
		try {
			JSONObject json = (JSONObject) obj;
			System.out.println("CREATE: \n  " + json.toString());
			// 自动生成新ID
			json.put("id", storageJSON.getJSONArray(className).length() + 1);
			storageJSON.append(className, json);
			saveAndRefresh();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * 修改
	 * 
	 * @param className
	 *            存储类型名称
	 * @param id
	 *            存储对象ID
	 * @param obj
	 *            新用于替换的对象
	 * @return 指示是否更改成功的布尔值
	 */
	public boolean update(String className, String id, Object obj) {
		int location = searchInArray(className, id);
		if (location != -1) {
			JSONArray tempArr = storageJSON.getJSONArray(className);
			tempArr.remove(location);
			storageJSON.append(className, obj);
			return true;
		}
		return false;
	}

	/**
	 * 删除
	 * 
	 * @param className
	 *            存储类型名称
	 * @param id
	 *            存储对象ID
	 * @return 指示是否删除成功的布尔值
	 */
	public boolean delete(String className, String id) {
		int location = searchInArray(className, id);
		if (location != -1) {
			storageJSON.getJSONArray(className).remove(location);
			return true;
		}
		return false;
	}

	/**
	 * 查找
	 * 
	 * @param className
	 *            存储类型名称
	 * @param id
	 *            存储对象ID
	 * @param classFullName
	 *            对象化指定类完整路径
	 * @return 一个携带搜索结果的指定对象，无结果则返回null
	 */
	public Object retrieve(String className, String id, String classFullName) {
		JSONArray temp = storageJSON.getJSONArray(className);
		int i = 0;
		for (i = 0; i < temp.length(); i++) {
			JSONObject tempObj = temp.getJSONObject(i);
			if (tempObj.optInt("id") == Integer.valueOf(id)) {
				try {
					return tempObj.toJavaBean(Class.forName(classFullName));
				} catch (ClassNotFoundException e) {
					// 指定对象存储类名称不存在
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 通过ID 搜索指定类型的对象存储 并返回 JSONObject
	 * 
	 * @param className
	 * @param id
	 * @return
	 */
	public JSONObject retrieve(String className, String id) {
		JSONArray temp = storageJSON.getJSONArray(className);
		int i = 0;

		for (i = 0; i < temp.length(); i++) {
			JSONObject tempObj = temp.getJSONObject(i);
			if (tempObj.optString("id").equals(id)) {
				return tempObj;
			}
		}
		return null;
	}

	/**
	 * 对象存储列出对象操作
	 * 
	 * @param className
	 *            存储类型名称
	 * @return 一个携带搜索结果的 JSONArray 结果集，无结果则返回null
	 */
	public JSONArray retrieve(String className) {
		JSONArray temp = storageJSON.getJSONArray(className);
		return temp;
	}

	/**
	 * 对象存储列出对象操作
	 * 
	 * @param className
	 *            存储类型名称
	 * @return 一个携带搜索结果的 JSONArray 结果集，无结果则返回null
	 */
	public JSONArray retrieveWithFormattedKeys(String className) {
		return formatLabel(storageJSON.getJSONArray(className), className);
	}

	public JSONArray formatLabel(JSONArray jsonArr, String className) {
		JSONArray newJSONArray = new JSONArray();
		for (Object object : jsonArr) {
			JSONObject jsonObj = formatLabelForObject((JSONObject) object, className);
			newJSONArray.put(jsonObj);
		}
		return newJSONArray;
	}

	public JSONObject formatLabelForObject(JSONObject jsonObj, String className) {
		Iterator<String> keys = jsonObj.keys();
		JSONObject newJSONObj = new JSONObject();
		while (keys.hasNext()) {
			String key = keys.next();
			String label = storageJSON.getJSONObject("label").optString(key);
			if (label.equals("")) {
				label = key;
			}
			newJSONObj.put(label, jsonObj.get(key));
		}
		newJSONObj.put("操作",
				"<a class='btn btn-sm btn-default' onclick='onEditObject(" + jsonObj.optString("id")
						+ ");'><i class='fa fa-pencil'></i> 修改</a>"
						+ "<a class='btn btn-sm btn-danger' onclick='onDeleteObject(" + jsonObj.optString("id")
						+ ");'><i class='fa fa-trash-o'></i> 删除</a>");
		//System.out.println(newJSONObj.toString(2));
		return newJSONObj;
	}

	public JSONArray retrieveWithExtraOperationCol(String className, String exColName, String btnColor, String onClick,
			String FAIcon, String btnText) {
		JSONArray newJSONArray = new JSONArray();
		JSONArray jsonArr = storageJSON.getJSONArray(className);
		for (Object object : jsonArr) {
			JSONObject oldJSONObj = (JSONObject) object;
			Iterator<String> keys = oldJSONObj.keys();
			JSONObject newJSONObj = new JSONObject();
			while (keys.hasNext()) {
				String key = keys.next();
				String label = storageJSON.getJSONObject("label").optString(key);
				if (label.equals("")) {
					label = key;
				}
				newJSONObj.put(label, oldJSONObj.get(key));
			}
			newJSONObj.put(exColName, "<a class='btn btn-sm btn-" + btnColor + "' onclick='" + onClick + "("
					+ oldJSONObj.optString("id") + ");'><i class='fa fa-" + FAIcon + "'></i> " + btnText + "</a>");

			newJSONArray.put(newJSONObj);
		}
		// System.out.println(newJSONArray.toString(2));
		return newJSONArray;
	}

	public JSONArray retrieveWithExtraOperationColWithCondition(String className, String conditionKey,
			String conditionValue, String exColName, String btnColor, String onClick, String FAIcon, String btnText) {
		try {
			JSONArray newJSONArray = new JSONArray();
			JSONArray jsonArr = storageJSON.getJSONArray(className);
			for (Object object : jsonArr) {

				JSONObject oldJSONObj = (JSONObject) object;
				Iterator<String> keys = oldJSONObj.keys();
				JSONObject newJSONObj = new JSONObject();
				while (keys.hasNext()) {

					String key = keys.next();
					String label = storageJSON.getJSONObject("label").optString(key);
					if (label.equals("")) {
						label = key;
					}
					newJSONObj.put(label, oldJSONObj.get(key));
				}
				newJSONObj.put(exColName, "<a class='btn btn-sm btn-" + btnColor + "' onclick='" + onClick + "("
						+ oldJSONObj.optString("id") + ");'><i class='fa fa-" + FAIcon + "'></i> " + btnText + "</a>");
				// System.out.println("\n
				// Result:"+oldJSONObj.optString(conditionKey)+"\n WHERE
				// "+conditionKey+" = "+conditionValue);
				// 执行检索过滤条件
				if (oldJSONObj.optString(conditionKey).equals(conditionValue)) {
					newJSONArray.put(newJSONObj);
				}

			}
			// System.out.println(newJSONArray.toString(2));
			return newJSONArray;
		} catch (Exception ex) {
			ex.printStackTrace();
			return new JSONArray();
		}
	}

	/**
	 * 对象库中搜索指定id的对象
	 * 
	 * @param className
	 * @param id
	 * @return 在数组中的索引
	 */
	public int searchInArray(String className, String id) {

		JSONArray temp = storageJSON.getJSONArray(className);
		int i = 0;
		for (i = 0; i < temp.length(); i++) {
			JSONObject tempObj = temp.getJSONObject(i);
			if (tempObj.optInt("id") == Integer.valueOf(id)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 保存对象存储库到默认文件
	 * 
	 * @return
	 */
	public boolean save() {
		String content = this.storageJSON.toString();
		try {
			return writeFile(DEFAULT_STORAGE_PATH, content);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 保存对象存储库到指定路径文件
	 * 
	 * @param path
	 * @return
	 */
	public boolean save(String path) {
		// Convert JSON to string
		String content = this.storageJSON.toString();
		try {
			return writeFile(path, content);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static String readFile(String path) {
		BufferedReader reader = null;
		String laststr = "";
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				laststr = laststr + tempString;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return laststr;
	}

	public static boolean writeFile(String filePath, String fileContent) throws IOException {
		try {
			File f = new File(filePath);
			if (!f.exists()) {
				f.createNewFile();
			}
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(fileContent);
			writer.close();
			return true;
		} catch (Exception e) {
			System.out.println("[Exception] 写入文件内容操作出错");
			e.printStackTrace();
			return false;
		}
	}

	public void saveAndRefresh() {
		save();
		loadFromFile();
	}

}
