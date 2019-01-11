package medicare;

import javafx.fxml.FXML;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import medicare.model.Person;
import netscape.javascript.JSObject;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker.State;

public class MainController {

	public static final String DEFAULT_RUNTIME_DIR = "runtime";

	@FXML
	protected WebView mainViewer;
	@FXML
	protected TabPane tabs;
	
	public class PersonPage {

	}

	public void loadMedicineQuery() {
		newViewEngine(newTab("药品信息查询"), "dataTableFromJSON.html",
				"loadTable(\"" + formatForJS(Storage.getStarted().retrieveWithFormattedKeys("medicine").toString())
						+ "\");" + "setClassName(\"medicine\");" + "setTitle(\"药品信息录入\");"
						+ "setSubTitle(\"填写下列基本信息录入药品\");");
	}

	public void loadMedicineInsert() {
		newViewEngine(newTab("药品信息录入"), "formFromJSON.html",
				"loadForm(\"" + generateCreateForm("medicine") + "\");" + "setClassName(\"medicine\");"
						+ "setTitle(\"药品信息录入\");" + "setSubTitle(\"填写下列基本信息录入药品\");" + "setFormMode('create')");
	}

	public void loadPersonQuery() {
		newViewEngine(newTab("人员信息查询"), "dataTableFromJSON.html",
				"loadTable(\"" + formatForJS(Storage.getStarted().retrieveWithFormattedKeys("person").toString())
						+ "\");" + "setClassName(\"person\");" + "setTitle(\"人员信息\");"
						+ "setSubTitle(\"可选择需要的项目进行编辑\");");
	}

	public void loadPersonInsert() {
		newViewEngine(newTab("人员信息录入"), "formFromJSON.html",
				"loadForm(\"" + generateCreateForm("person") + "\");" + "setClassName(\"person\");"
						+ "setTitle(\"人员信息录入\");" + "setSubTitle(\"填写下列基本信息录入人员\");" + "setFormMode('create')");
	}
	
	
	public void loadDiagnosisQuery() {
		newViewEngine(newTab("诊疗项目浏览"), "dataTableFromJSON.html",
				"loadTable(\"" + formatForJS(Storage.getStarted().retrieveWithFormattedKeys("diagnosis").toString())
						+ "\");" + "setClassName(\"diagnosis\");" + "setTitle(\"诊疗项目\");"
						+ "setSubTitle(\"可选择需要的项目进行编辑\");");
	}

	public void loadDiagnosisInsert() {
		newViewEngine(newTab("诊疗项目录入"), "formFromJSON.html",
				"loadForm(\"" + generateCreateForm("diagnosis") + "\");" + "setClassName(\"diagnosis\");"
						+ "setTitle(\"诊疗项目录入\");" + "setSubTitle(\"填写下列基本信息录入诊疗项目\");" + "setFormMode('create')");
	}
	
	
	public void loadRecordQuery() {
		newViewEngine(newTab("住院记录浏览"), "dataTableFromJSON.html",
				"loadTable(\"" + formatForJS(Storage.getStarted().retrieveWithFormattedKeys("record").toString())
						+ "\");" + "setClassName(\"record\");" + "setTitle(\"住院记录信息\");"
						+ "setSubTitle(\"可选择需要的项目进行编辑\");");
	}

	public void loadRecordInsert() {
		newViewEngine(newTab("住院记录录入"), "formFromJSON.html",
				"loadForm(\"" + generateCreateForm("record") + "\");" + "setClassName(\"record\");"
						+ "setTitle(\"住院记录录入\");" + "setSubTitle(\"填写下列基本信息录入住院记录\");" + "setFormMode('create')");
	}

	public void loadServiceQuery() {
		newViewEngine(newTab("服务设施浏览"), "dataTableFromJSON.html",
				"loadTable(\"" + formatForJS(Storage.getStarted().retrieveWithFormattedKeys("service").toString())
						+ "\");" + "setClassName(\"service\");" + "setTitle(\"服务设施信息\");"
						+ "setSubTitle(\"可选择需要的项目进行编辑\");");
	}

	public void loadServiceInsert() {
		newViewEngine(newTab("服务设施录入"), "formFromJSON.html",
				"loadForm(\"" + generateCreateForm("service") + "\");" + "setClassName(\"service\");"
						+ "setTitle(\"服务设施录入\");" + "setSubTitle(\"填写下列基本信息录入服务设施\");" + "setFormMode('create')");
	}
	
	public void loadUpperLimitQuery() {
		newViewEngine(newTab("封顶线"), "dataTableFromJSON.html",
				"loadTable(\"" + formatForJS(Storage.getStarted().retrieveWithFormattedKeys("upperLimit").toString())
						+ "\");" + "setClassName(\"upperLimit\");" + "setTitle(\"封顶线参数设置\");"
						+ "setSubTitle(\"可选择需要的项目进行编辑\");");
	}
	
	public void loadIndivRatioQuery() {
		newViewEngine(newTab("个人分段自费比例"), "dataTableFromJSON.html",
				"loadTable(\"" + formatForJS(Storage.getStarted().retrieveWithFormattedKeys("individualSplitRate").toString())
						+ "\");" + "setClassName(\"individualSplitRate\");" + "setTitle(\"个人分段自费比例参数设置\");"
						+ "setSubTitle(\"可选择需要的项目进行编辑\");");
	}
	
	public void loadStartingLineQuery() {
		newViewEngine(newTab("起付标准"), "dataTableFromJSON.html",
				"loadTable(\"" + formatForJS(Storage.getStarted().retrieveWithFormattedKeys("startingLine").toString())
						+ "\");" + "setClassName(\"startingLine\");" + "setTitle(\"起付标准参数设置\");"
						+ "setSubTitle(\"可选择需要的项目进行编辑\");");
	}
	
	public void loadInstitutionQuery() {
		newViewEngine(newTab("定点医疗机构 - 浏览与管理"), "dataTableFromJSON.html",
				"loadTable(\"" + formatForJS(Storage.getStarted().retrieveWithFormattedKeys("institution").toString())
						+ "\");" + "setClassName(\"institution\");" + "setTitle(\"浏览所有的定点医疗机构并进行管理操作\");"
						+ "setSubTitle(\"可选择需要的项目进行编辑\");");
	}
	
	public void loadInstitutionInsert() {
		newViewEngine(newTab("新增定点医疗机构"), "formFromJSON.html",
				"loadForm(\"" + generateCreateForm("institution") + "\");" + "setClassName(\"institution\");"
						+ "setTitle(\"定点医疗机构录入\");" + "setSubTitle(\"填写下列基本信息录入定点医疗机构\");" + "setFormMode('create')");
	}
	
	public String generateEditForm(String className, String id) {
		try{
		Storage storage = Storage.getStarted();
		//System.out.println(className+" ID "+id);
		JSONObject jsObj = storage.retrieve(className, id);
		
		if (jsObj == null) {
			return null;
		}
		ArrayList<String> keyList = new ArrayList<String>(jsObj.keySet());
		JSONObject parameters = storage.root().getJSONObject("parameter");
		JSONObject generatedForm = new JSONObject();
		for (String singleKey : keyList) {
			JSONArray parameterOptions = parameters.optJSONArray(singleKey);
			String label = storage.root().getJSONObject("label").optString(singleKey);
			if (label.equals("")) {
				label = singleKey;
			}
			if (parameterOptions != null) {
				generatedForm.append("elements",
						new JSONObject("{\"type\":\"combox\",\"value\":\"" + jsObj.getString(singleKey)
								+ "\",\"required\":1,\"placeholder\":\"键入内容\",\"label\":\"" + label + "\",\"id\":\""
								+ singleKey + "\",\"options\":" + parameterOptions.toString() + "}"));
			} else {
				generatedForm.append("elements",
						new JSONObject("{\"type\":\"text\",\"value\":\"" + jsObj.getString(singleKey)
								+ "\",\"required\":1,\"placeholder\":\"键入内容\",\"label\":\"" + label + "\",\"id\":\""
								+ singleKey + "\"}"));
			}
		}
		//System.out.println("GENERATED FORM : "+formatForJS(generatedForm.toString(2)));
		return formatForJS(generatedForm.toString());
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * 生成新建类型表单JSON指令
	 * 
	 * @param className
	 *            对象存储类型名称
	 * @return 适用于引擎的JSON模板
	 */
	public String generateCreateForm(String className) {
		Storage storage = Storage.getStarted();
		JSONObject jsObj = storage.retrieve(className).getJSONObject(0);
		if (jsObj == null) {
			return null;
		}
		ArrayList<String> keyList = new ArrayList<String>(jsObj.keySet());
		JSONObject parameters = storage.root().getJSONObject("parameter");
		JSONObject generatedForm = new JSONObject();

		for (String singleKey : keyList) {
			String label = storage.root().optJSONObject("label").optString(singleKey);
			if (label == null) {
				label = singleKey;
			}
			JSONArray parameterOptions = parameters.optJSONArray(singleKey);
			if (parameterOptions != null) {
				generatedForm.append("elements",
						new JSONObject("{\"type\":\"combox\",\"value\":\""
								+ "\",\"required\":1,\"placeholder\":\"\",\"label\":\"" + label + "\",\"id\":\""
								+ singleKey + "_new" + "\",\"options\":" + parameterOptions.toString() + "}"));
			} else {
				generatedForm.append("elements",
						new JSONObject("{\"type\":\"text\",\"value\":\""
								+ "\",\"required\":1,\"placeholder\":\"\",\"label\":\"" + label + "\",\"id\":\""
								+ singleKey + "\"}"));
			}
		}
		// System.out.println(generatedForm.toString().replace("\"","\\\""));
		return formatForJS(generatedForm.toString());// 格式化输出
	}

	/**
	 * 渲染新选项卡
	 * 
	 * @param tabTitle
	 *            定义选项卡标题
	 * @return 返回视图供引擎调用
	 */
	public WebView newTab(String tabTitle) {
		Tab newTab = new Tab();
		WebView newViewer = new WebView();
		newTab.setText(tabTitle);
		newTab.setContent(newViewer);
		ObservableList<Tab> obTabs = tabs.getTabs();
		obTabs.add(newTab);
		SingleSelectionModel<Tab> selectionModel = tabs.getSelectionModel();
		selectionModel.select(obTabs.size() - 1);
		return newViewer;
	}

	/**
	 * 自定义 WebKit UI渲染引擎 加载视图UI布局到页面指定容器
	 * 
	 * @param viewer
	 *            视图控制器
	 * @param pagePath
	 *            视图文件路径 (相对runtime)
	 * @param className
	 *            视图控制类
	 */
	private void newViewEngine(WebView viewer, String pageName, String preScript) {
		// 获取容器引擎
		viewer.setContextMenuEnabled(false);
		WebEngine uiEngine = viewer.getEngine();
		// 加载视图文件
		uiEngine.load("file:///" + System.getProperty("user.dir") + "/" + DEFAULT_RUNTIME_DIR + "/" + pageName);
		uiEngine.getLoadWorker().stateProperty()
				.addListener((ObservableValue<? extends State> ov, State oldState, State newState) -> {
					JSObject win = (JSObject) uiEngine.executeScript("window");
					try {// 设置成员使控制器对视图引擎可见
						win.setMember("app", this);
						uiEngine.executeScript(preScript);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
	}

	/**
	 * 转义供JavaScript读取的字符串
	 * 
	 * @param input
	 *            输入RAW字符
	 * @return 转义后输出
	 */
	public String formatForJS(String input) {
		return input.replace("\"", "\\\"");

	}

	/*******************************************************
	 * WebKit 视图引擎调用方法(Actions)
	 ********************************************************/
	public void testJS(String inp) {
		System.out.println("test seong gong! inp:" + inp);

	}

	public void dataReceiver(String json) {
		try {
			System.out.println("Receieved: " + json);
			JSONObject jsonObj = new JSONObject(json);
			Storage storage = Storage.getStarted();
			boolean result = false;
			if (jsonObj.getString("mode").equals("create")) {
				result = storage.create(jsonObj.getString("className"), jsonObj.getJSONObject("object"));
			} else if (jsonObj.getString("mode").equals("edit")) {
				JSONObject objTemp = jsonObj.getJSONObject("object");
				result = storage.update(jsonObj.getString("className"), objTemp.getString("id"), objTemp);
			}
			System.out.println("received Result :" + result);
			if (jsonObj.getString("className").equals("prescription")) {
				refreshDataTable(jsonObj.getString("className"), jsonObj.getJSONObject("object").getString("recordID"));
			} else {
				refreshDataTable(jsonObj.getString("className"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 根据对象存储数据生成并显示修改页面
	 * 
	 * @param className
	 *            对象存储类型名称
	 * @param id
	 *            指定对象存储ID
	 */
	public void editObject(String className, int id) {
		System.out.println(generateEditForm(className, String.valueOf(id)));
		newViewEngine(newTab("编辑"), "formFromJSON.html",
				"loadForm(\"" + generateEditForm(className, String.valueOf(id)) + "\");setTitle(\"编辑\");"
						+ "setSubTitle(\"编辑基本属性\");" + "setFormMode('edit');" + "setClassName('" + className + "');");
	}

	/**
	 * 根据对象存储数据生成并显示删除页面
	 * 
	 * @param className
	 * @param id
	 */
	public void deleteObject(String className, int id) {
		try{
		Storage storage=Storage.getStarted();
		JSONObject jsonObj=storage.retrieve(className, String.valueOf(id));
		boolean result =storage.delete(className, String.valueOf(id));
		System.out.println("Delete result: "+result);
		if(result){
			storage.save();
		}
		if (className.equals("prescription")) {
			refreshDataTable(className, jsonObj.getString("recordID"));
		} else {
			refreshDataTable(className);
		}
	} catch (Exception ex) {
		ex.printStackTrace();
	}
	}

	public void reimPersons() {
		Person pr = new Person();
		newViewEngine(newTab("流程报销-所有人员信息"), "dataTableFromJSON.html",
				"loadTable(\""
						+ formatForJS(Storage.getStarted()
								.retrieveWithExtraOperationCol("person", "继续报销操作", "info", "app.reimRecords", "user",
										"选择此人员")
								.toString())
						+ "\");" + "setTitle(\"流程报销 - 选择人员\");setSubTitle(\"选择一个人员以继续报销操作\");");
	}

	public void reimRecords(String personID) {
		Storage storage = Storage.getStarted();
		String name = storage.retrieve("person", personID).getString("name");
		System.out.println(formatForJS(storage.retrieveWithExtraOperationColWithCondition("record", "personID",
				personID, "继续报销操作", "primary", "app.reimRx", "user", "选择此住院记录").toString()));
		newViewEngine(newTab("流程报销 - " + name + "的住院记录"), "dataTableFromJSON.html",
				"loadTable(\""
						+ formatForJS(storage.retrieveWithExtraOperationColWithCondition("record", "personID", personID,
								"继续报销操作", "primary", "app.reimRx", "user", "选择此住院记录").toString())
						+ "\");" + "setTitle(\"选择" + name + "的一条住院记录指定为处理对象以继续报销\");setSubTitle(\"选择一个住院记录以继续报销操作\");");
	}
	
	public void reimAllRecords() {
		Storage storage = Storage.getStarted();
		//String name = storage.retrieve("person", personID).getString("name");
		System.out.println(formatForJS(storage.retrieveWithExtraOperationCol("record", "继续报销操作", "primary", "app.reimRx", "user", "选择此住院记录").toString()));
		newViewEngine(newTab("流程报销 -  通过住院记录报销"), "dataTableFromJSON.html",
				"loadTable(\""
						+ formatForJS(storage.retrieveWithExtraOperationCol("record",
								"继续报销操作", "primary", "app.reimRx", "user", "选择此住院记录").toString())
						+ "\");" + "setTitle(\"从所有数据中选择一条住院记录指定为处理对象以继续报销\");"
								+ "setSubTitle(\"选择一个住院记录以继续报销操作\");");

	}

	public void reimRx(String recordID) {
		try {
			Storage storage = Storage.getStarted();
			JSONObject record = storage.retrieve("record", recordID);
			String name = storage.retrieve("person", record.getString("personID")).getString("name");
			newViewEngine(newTab("流程报销 - 处方明细 - " + name + "的住院记录 (ID/" + record.getString("id") + ")"),
					"prescriptionDataTableFromJSON.html",
					"loadTable(\""
							+ formatForJS(storage.retrieveWithExtraOperationColWithCondition("prescription", "recordID",
									recordID, "操作", "danger", "onDeleteObject", "trash-o", "删除").toString())
							+ "\");" + "setTitle(\"" + name + " 当前住院记录的处方明细\");" + "setSubTitle(\"正在查看患者 " + name
							+ " 在 " + record.getString("hospitalizeDate") + " 至 " + record.getString("leaveDate")
							+ "期间的住院记录(ID/" + record.getString("id") + ") 的处方明细" + "\");" + "setRecordID(" + recordID
							+ ");");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void preReim(String recordID) {
		Storage storage = Storage.getStarted();
		JSONArray prescriptions = storage.retrieveWithExtraOperationColWithCondition("prescription", "recordID",
				recordID, "备注", "primary", "app.reimRx", "user", "选择此住院记录");
		int i = 0;
		JSONArray processed = new JSONArray();
		int len = prescriptions.length();
		double total = 0.0;
		double reim = 0.0;
		for (i = 0; i < len; i++) {
			StringBuilder note = new StringBuilder("");
			JSONObject tempPres = prescriptions.getJSONObject(i);
			double currentAmount = 0.0;
			currentAmount = tempPres.optDouble("金额");
			total += currentAmount;
			if (tempPres.getString("是否在录药品").equals("是")) {
				String chargeType = tempPres.getString("收费类别");
				switch (chargeType) {
				case "药品":
					JSONObject medicine = storage.retrieve("medicine", tempPres.getString("收费项目ID"));
					if (medicine.getString("chargeTypeClass").equals("甲类")) {
						reim += currentAmount;
						note.append("<span class='label label-success '>甲类药品，全额预报销 (" + currentAmount + "元); </span>");
					} else if (medicine.getString("chargeTypeClass").equals("乙类")) {
						reim += currentAmount / 2.0;
						note.append("<span class='label label-warning'>乙类药品，预报销50% (合计" + currentAmount / 2.0
								+ "元); </span>");
					} else if (medicine.getString("chargeTypeClass").equals("丙类")) {
						note.append("<span class='label label-danger'>丙类药品，不予报销; </span>");
					}
					break;
				case "诊疗项目":
					JSONObject diagnosis = storage.retrieve("diagnosis", tempPres.getString("收费项目ID"));
					if (diagnosis.getString("chargeTypeClass").equals("甲类")) {
						reim += currentAmount;
						note.append("<span class='label label-success '>甲类诊疗项目，全额预报销 (" + currentAmount + "元); </span>");
					} else if (diagnosis.getString("chargeTypeClass").equals("乙类")) {
						reim += currentAmount / 2.0;
						note.append("<span class='label label-warning'>乙类诊疗项目，预报销50% (折算" + currentAmount / 2.0
								+ "元); </span>");
					} else if (diagnosis.getString("chargeTypeClass").equals("丙类")) {
						note.append("<span class='label label-danger'>丙类诊疗项目，不予报销; </span>");
					}
					break;
				case "服务设施":
					reim += currentAmount;
					note.append("<span class='label label-success '>服务设施全额预报销 (" + currentAmount + "元); </span>");
				
					break;
				}

			} else {
				note.append("<span class='label label-danger'>非在录药品，不予报销; </span>");
			}

			tempPres.put("备注", note.toString());
			processed.put(tempPres);
		}
		System.out.println("total:: " + total + "    reim::" + reim);
		newViewEngine(newTab("预结算"), "preReimResultFromJSON.html",
				"loadTable(\"" + formatForJS(processed.toString()) + "\");" + "setClassName(\"reim\");"
						+ "setTitle(\"预结算结果预览\");" + "setRecordID(" + recordID + ");" + "setAmount(" + total + ","
						+ reim + ");");
	}

	public void refreshDataTable(String className) {
		SingleSelectionModel<Tab> selectionModel = tabs.getSelectionModel();
		tabs.getTabs().remove(selectionModel.getSelectedIndex());
		tabs.getTabs().remove(selectionModel.getSelectedIndex());
		switch (className) {
		case "person":
			loadPersonQuery();
			break;
		case "medicine":
			loadMedicineQuery();
			break;
		case "service":
			loadServiceQuery();
			break;
		case "diagnosis":
			loadDiagnosisQuery();
			break;
		case "record":
			loadRecordQuery();
			break;
		default:
			
			break;
		}
	}

	public void refreshDataTable(String className, String id) {
		SingleSelectionModel<Tab> selectionModel = tabs.getSelectionModel();
		tabs.getTabs().remove(selectionModel.getSelectedIndex());
		tabs.getTabs().remove(selectionModel.getSelectedIndex());
		switch (className) {
		case "prescription":
			reimRx(id);
			break;
		case "medicine":
			loadMedicineQuery();
			break;
		default:
			;
			break;
		}
	}

	public void newPrescription(String recordID) {
		System.out.println("RECEIVED RECORD ID: " + recordID);
		newViewEngine(newTab("新增处方"), "prescriptionFromJSON.html",
				"loadTable1(\""
						+ formatForJS(Storage.getStarted()
								.retrieveWithExtraOperationCol("medicine", "选择操作", "primary", "setChargeName1", "check",
										"选择此药品")
								.toString())
						+ "\");"
				+"loadTable2(\""
				+ formatForJS(Storage.getStarted()
						.retrieveWithExtraOperationCol("diagnosis", "选择操作", "danger", "setChargeName2", "check",
								"选择此诊疗项目")
						.toString())
				+ "\");"
						+"loadTable3(\""
						+ formatForJS(Storage.getStarted()
								.retrieveWithExtraOperationCol("service", "选择操作", "warning", "setChargeName3", "check",
										"选择此服务设施")
								.toString())
						+ "\");"
								+ "setClassName(\"prescription\");" + "setTitle(\"新增处方明细\");" + "setRecordID("
						+ recordID + ")");
	}

	public void closeThisTab() {
		SingleSelectionModel<Tab> selectionModel = tabs.getSelectionModel();
		if(selectionModel.getSelectedIndex()>=0){
		tabs.getTabs().remove(selectionModel.getSelectedIndex());
		}

	}
	
	public void closeAllTabs() {
		//SingleSelectionModel<Tab> selectionModel = tabs.getSelectionModel();
		tabs.getTabs().removeAll();

	}
	
	public void closeOthersTabs() {
		SingleSelectionModel<Tab> selectionModel = tabs.getSelectionModel();
		ObservableList<Tab> obl=tabs.getTabs();
		int limit=obl.size();
		int i=0;
		int skip=selectionModel.getSelectedIndex();
		for(i=0;i<limit;i++){
			//System.out.println("Skip:"+skip+"/Limit:"+limit);
			if(i<skip){
				obl.remove(0);
			}else if(i>skip){
				obl.remove(1);
			}
			
		}
	}

}