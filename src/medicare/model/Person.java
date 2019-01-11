package medicare.model;

import java.util.Date;

public class Person {

	public Person() {
		// TODO Auto-generated constructor stub
	}

	private String id;
	private String citizenIDNo;
	private String gender;
	private Date birthday;
	private String medicalPersonType;
	private String workplaceID;
	private String workplaceName;
	private int hospitalizationCountForThisYear;
	private Date previousHospitalizationDate;
	private String previousDiagnosisID;
	private String reimbursementAccKotoshi;
	private String totalPaidBySelfKotoshi;
	private String totalMedicalFeeAccKotoshi;

}
