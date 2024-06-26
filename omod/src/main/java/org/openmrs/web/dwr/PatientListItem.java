/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.web.dwr;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.PatientIdentifier;

import java.util.ArrayList;
import java.util.List;

public class PatientListItem extends PersonListItem {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	private Integer patientId;
	
	private String identifier = "";
	
	private String identifierTypeName = "";
	
	private String otherIdentifiers = "";
	
	
	private List<String> clientRegistryUId = new ArrayList<>();

	private String clientRegistryId = "";

	public PatientListItem() {
	}
	
	public PatientListItem(Patient patient) {
		this(patient, null);
	}
	
	public PatientListItem(Patient patient, String searchName) {
		super(patient, searchName);
		
		if (patient != null) {
			
			patientId = patient.getPatientId();
			
			PatientIdentifier patientIdentifier = patient.getPatientIdentifier();
			if (patientIdentifier != null) {
				identifier = patientIdentifier.getIdentifier();
				// get patient's identifiers
				for (PatientIdentifier pi : patient.getIdentifiers()) {
					if (pi.getIdentifierType().getUuid().equals("43a6e699-c2b8-4d5f-9e7f-cf19448d59b7") && pi.getIdentifier() != null) {
						clientRegistryUId.add(pi.getIdentifier());
					}
					
					if (!pi.getIdentifier().equals(identifier)) {
						if (!"".equals(otherIdentifiers)) {
							otherIdentifiers += ",";
						}
						otherIdentifiers += " " + pi.getIdentifier();
					}
				}
			}
		}
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof PatientListItem) {
			PatientListItem pi = (PatientListItem) obj;
			if (pi.getPatientId() == null || patientId == null) {
				return false;
			}
			return pi.getPatientId().equals(patientId);
		}
		return false;
	}
	
	public int hashCode() {
		if (patientId == null) {
			return super.hashCode();
		}
		return patientId.hashCode();
	}
	
	public String getIdentifier() {
		return identifier;
	}
	
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	public String getOtherIdentifiers() {
		return otherIdentifiers;
	}
	
	public void setOtherIdentifiers(String otherIdentifiers) {
		this.otherIdentifiers = otherIdentifiers;
	}
	
	public Integer getPatientId() {
		return patientId;
	}
	
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	
	/**
	 * @param identifierTypeName the identifierTypeName to set
	 */
	public void setIdentifierTypeName(String identifierTypeName) {
		this.identifierTypeName = identifierTypeName;
	}
	
	/**
	 * @return the identifierTypeName
	 */
	public String getIdentifierTypeName() {
		return identifierTypeName;
	}
	
	/**
	 * @param clientRegistryUId the clientRegistryId to set
	 */
 	public void setClientRegistryId(String clientRegistryId) {
		this.clientRegistryId = clientRegistryId;
	} 
	
		/**
	 * @return the clientRegistryId
	 */
	public String getClientRegistryId() {
		return clientRegistryId;
	}

	/**
	 * @return the clientRegistryUId
	 */
	public List<String> getclientRegistryUId() {
		return clientRegistryUId;
	}
	
}
