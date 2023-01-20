package list;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.Agreement;

public class AgreementList implements Serializable {
	
	private static final long serialVersionUID = 5000L;
	private List<Agreement> agreementList;

	public AgreementList() {
		agreementList = new ArrayList<Agreement>();
	}

	public List<Agreement> getAgreements() {
		return agreementList;
	}

	public void addAgreement(Agreement agreement) {
		agreementList.add(agreement);
	}

	public int getSize() {
		return agreementList.size();
	}
}
