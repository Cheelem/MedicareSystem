package medicare.model;

import java.util.Date;

public class Patient {

	private int id;

	private int treatmentID;

	private int designatedMedicalInstitutionID;

	private MedicalType medicalType;

	private Date hospitalizeDate;

	private Date leaveDate;

	private int diseaseID;

	private HospitalClass hospitalClass;

	private int idOfDiseaseDiagnosiedWhenHospitalize;

	private String nameOfDiseaseDiagnosiedWhenHospitalize;

	private String leaveReason;

	private Reimbursement reimbursement;

	private DesignatedMedicalInstitution designatedMedicalInstitution;

	private Disease disease;

	private Verification verification;

}
