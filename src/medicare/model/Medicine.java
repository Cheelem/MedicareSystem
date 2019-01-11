package medicare.model;

import org.json.JSONObject;

public class Medicine extends JSONObject {

	private int id;

	private String name;

	private String engName;

	private String unit;

	private String measurementUnit;

	private ChargeType chargeType;

	private double priceUpperLimit;

	private ChargeClass chargeClass;

	private boolean isRx;

	private boolean isInnerHospitalProduced;

	private boolean isNeedVerification;

	private String hospitalClass;

	private String medicineForm;

	private String usageFrequency;

	private String medicineUsage;

	private String specs;

	private int daysOfLimit;

	private String tradeName;

	private String factoryName;

	private String nationalDrugCertification;

	private String limitedRangeOfUsage;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEngName() {
		return engName;
	}

	public void setEngName(String engName) {
		this.engName = engName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getMeasurementUnit() {
		return measurementUnit;
	}

	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}

	public ChargeType getChargeType() {
		return chargeType;
	}

	public void setChargeType(ChargeType chargeType) {
		this.chargeType = chargeType;
	}

	public double getPriceUpperLimit() {
		return priceUpperLimit;
	}

	public void setPriceUpperLimit(double priceUpperLimit) {
		this.priceUpperLimit = priceUpperLimit;
	}

	public ChargeClass getChargeClass() {
		return chargeClass;
	}

	public void setChargeClass(ChargeClass chargeClass) {
		this.chargeClass = chargeClass;
	}

	public boolean isRx() {
		return isRx;
	}

	public void setRx(boolean isRx) {
		this.isRx = isRx;
	}

	public boolean isInnerHospitalProduced() {
		return isInnerHospitalProduced;
	}

	public void setInnerHospitalProduced(boolean isInnerHospitalProduced) {
		this.isInnerHospitalProduced = isInnerHospitalProduced;
	}

	public boolean isNeedVerification() {
		return isNeedVerification;
	}

	public void setNeedVerification(boolean isNeedVerification) {
		this.isNeedVerification = isNeedVerification;
	}

	public String getString() {
		return hospitalClass;
	}

	public void setString(String hospitalClass) {
		this.hospitalClass = hospitalClass;
	}

	public String getMedicineForm() {
		return medicineForm;
	}

	public void setMedicineForm(String medicineForm) {
		this.medicineForm = medicineForm;
	}

	public String getUsageFrequency() {
		return usageFrequency;
	}

	public void setUsageFrequency(String usageFrequency) {
		this.usageFrequency = usageFrequency;
	}

	public String getMedicineUsage() {
		return medicineUsage;
	}

	public void setMedicineUsage(String medicineUsage) {
		this.medicineUsage = medicineUsage;
	}

	public String getSpecs() {
		return specs;
	}

	public void setSpecs(String specs) {
		this.specs = specs;
	}

	public int getDaysOfLimit() {
		return daysOfLimit;
	}

	public void setDaysOfLimit(int daysOfLimit) {
		this.daysOfLimit = daysOfLimit;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public String getNationalDrugCertification() {
		return nationalDrugCertification;
	}

	public void setNationalDrugCertification(String nationalDrugCertification) {
		this.nationalDrugCertification = nationalDrugCertification;
	}

	public String getLimitedRangeOfUsage() {
		return limitedRangeOfUsage;
	}

	public void setLimitedRangeOfUsage(String limitedRangeOfUsage) {
		this.limitedRangeOfUsage = limitedRangeOfUsage;
	}

	public String getPlaceOfProduction() {
		return placeOfProduction;
	}

	public void setPlaceOfProduction(String placeOfProduction) {
		this.placeOfProduction = placeOfProduction;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSpecialVerification() {
		return specialVerificationID;
	}

	public void setSpecialVerification(String specialVerification) {
		this.specialVerificationID = specialVerification;
	}

	private String placeOfProduction;

	private String remark;

	private String specialVerificationID;

}
