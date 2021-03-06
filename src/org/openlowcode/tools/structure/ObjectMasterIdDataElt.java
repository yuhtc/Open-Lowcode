/********************************************************************************
 * Copyright (c) 2019-2020 [Open Lowcode SAS](https://openlowcode.com/)
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0 .
 *
 * SPDX-License-Identifier: EPL-2.0
 ********************************************************************************/

package org.openlowcode.tools.structure;

import java.io.IOException;

import org.openlowcode.tools.messages.MessageFieldSpec;
import org.openlowcode.tools.messages.MessageFieldTypeString;
import org.openlowcode.tools.messages.MessageReader;
import org.openlowcode.tools.messages.MessageWriter;
import org.openlowcode.tools.messages.OLcRemoteException;

/**
 * a master id for an object. The master id is the id common to all versions of
 * an object
 * 
 * @author <a href="https://openlowcode.com/" rel="nofollow">Open Lowcode
 *         SAS</a>
 *
 */
public class ObjectMasterIdDataElt extends SimpleDataElt {
	private String id;
	private String objectid;

	/**
	 * @return the id of the instance of the object
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @return the id of the type of object
	 */
	public String getObjectId() {
		return this.objectid;
	}

	/**
	 * creates an empty data element
	 * 
	 * @param name name of the data element
	 */
	public ObjectMasterIdDataElt(String name) {
		super(name, new ObjectMasterIdDataEltType());
	}

	/**
	 * @param name     name of the data element
	 * @param id       id of the instance of the object
	 * @param objectid id of the type of object
	 */
	public ObjectMasterIdDataElt(String name, String id, String objectid) {
		super(name, new ObjectMasterIdDataEltType());
		this.id = id;
		this.objectid = objectid;
	}

	/**
	 * @param name name of the data element
	 * @param id   id of the instance of the object
	 */
	public ObjectMasterIdDataElt(String name, String id) {
		super(name, new ObjectMasterIdDataEltType());
		this.id = id;
	}

	/**
	 * @param name     name of the data element
	 * @param objectid id of the type of object
	 */
	public ObjectMasterIdDataElt(String name, ObjectIdInterface objectid) {
		super(name, new ObjectMasterIdDataEltType());
		this.id = objectid.getId();
		this.objectid = objectid.getObjectId();
	}

	private ObjectMasterIdDataElt(String name, ObjectMasterIdDataEltType type, String id) {
		super(name, type);
		this.id = id;
	}

	@Override
	public void writePayload(MessageWriter writer) throws IOException {
		writer.addStringField("PLD", this.id);
		writer.addStringField("OBI", this.objectid);

	}

	@Override
	public void addPayload(MessageReader reader) throws OLcRemoteException, IOException {
		this.id = reader.returnNextStringField("PLD");
		this.objectid = reader.returnNextStringField("OBI");

	}

	@Override
	public String defaultTextRepresentation() {
		return this.id;
	}

	@Override
	public ObjectMasterIdDataElt cloneElt() {
		return new ObjectMasterIdDataElt(this.getName(), (ObjectMasterIdDataEltType) this.getType(), this.id);
	}

	@Override
	public void forceContent(String constraintvalue) {
		throw new RuntimeException("not yet implemented");

	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (!(other instanceof ObjectMasterIdDataElt))
			return false;
		ObjectMasterIdDataElt parseddataelt = (ObjectMasterIdDataElt) other;
		if (!this.id.equals(parseddataelt.id))
			return false;
		if (!this.objectid.equals(parseddataelt.objectid))
			return false;
		return true;
	}

	@Override
	protected MessageFieldSpec getMessageFieldSpec() {
		return new MessageFieldSpec(this.getName().toUpperCase(), MessageFieldTypeString.singleton);
	}

	@Override
	protected Object getMessageArrayValue() {
		return this.id;
	}
}
