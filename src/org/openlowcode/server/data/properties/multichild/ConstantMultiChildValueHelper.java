/********************************************************************************
 * Copyright (c) 2020 [Open Lowcode SAS](https://openlowcode.com/)
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0 .
 *
 * SPDX-License-Identifier: EPL-2.0
 ********************************************************************************/

package org.openlowcode.server.data.properties.multichild;

import java.util.function.BiConsumer;
import java.util.function.Function;

import org.apache.poi.ss.usermodel.Cell;
import org.openlowcode.module.system.data.choice.ApplocaleChoiceDefinition;
import org.openlowcode.server.data.ChoiceValue;
import org.openlowcode.server.data.DataObject;
import org.openlowcode.server.data.properties.MultidimensionchildInterface;
import org.openlowcode.server.data.properties.UniqueidentifiedInterface;
import org.openlowcode.tools.misc.TriConsumer;
import org.openlowcode.tools.misc.TriFunction;

/**
 * @author <a href="https://openlowcode.com/" rel="nofollow">Open Lowcode
 *         SAS</a>
 *
 * @param <E> type of child object
 * @param <F> payload of the field
 * @param <G> type of the parent object (or any other object to be used)
 */
public class ConstantMultiChildValueHelper<
E extends DataObject<E> & UniqueidentifiedInterface<E> & MultidimensionchildInterface<E, G>,
F extends Object,
G extends DataObject<G> & UniqueidentifiedInterface<G>> extends MultichildValueHelper<E,F,G>  {

	
	
	private F[] minimumvalues;
	private F[] maximumvalues;
	private boolean allowothervalues;
	private F defaultforotherdata;
	
	public ConstantMultiChildValueHelper(String fieldname,F[] minimumvalues,BiConsumer<E, F> setter, Function<E, F> getter,TriConsumer<Cell,String[], F> cellfiller,
			TriFunction<Object,ChoiceValue<ApplocaleChoiceDefinition>,String[], F> payloadparser,Function<F, String> printer) {
		super(fieldname,setter, getter,cellfiller,payloadparser,printer);
		this.minimumvalues=minimumvalues;
		this.maximumvalues=null;
		this.allowothervalues=true;
		this.defaultforotherdata=null;
	}
	public ConstantMultiChildValueHelper(String fieldname,F[] minimumvalues,F defaultforotherdata,BiConsumer<E, F> setter, Function<E, F> getter,TriConsumer<Cell,String[], F> cellfiller,
			TriFunction<Object,ChoiceValue<ApplocaleChoiceDefinition>,String[], F> payloadparser,Function<F, String> printer) {
		super(fieldname,setter, getter,cellfiller,payloadparser,printer);
		this.minimumvalues=minimumvalues;
		this.maximumvalues=null;
		this.allowothervalues=false;
		this.defaultforotherdata=defaultforotherdata;
	}
	

	@Override
	public F[] getMandatoryValues() {
		return minimumvalues;
	}

	@Override
	public F[] getOptionalValues() {
		if (maximumvalues!=null) return maximumvalues;
		return minimumvalues;
	}

	@Override
	public boolean allowUserValue() {
		return false;
	}

	@Override
	public boolean allowothervalues() {
		return allowothervalues;
	}

	@Override
	public F getDefaultValueForOtherData() {
		return defaultforotherdata;
	}
	@Override
	public void setContext(G parent) {
		// do nothing
	}
	@Override
	public boolean filterForPayloadTotal(F value) {
		return true;
	}

}
