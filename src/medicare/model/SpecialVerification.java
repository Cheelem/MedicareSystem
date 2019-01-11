package medicare.model;

import java.util.Date;

public class SpecialVerification extends Verification {

	private int id;

	private int patientID;

	private VerificationType verificationType;

	private Date startDate;

	private Date endDate;

	private int medicineID;

	private String verificationNote;

	private String verifier;

	private Date verificationDate;

	private boolean isVerified;

	private Medicine medicine;

}
