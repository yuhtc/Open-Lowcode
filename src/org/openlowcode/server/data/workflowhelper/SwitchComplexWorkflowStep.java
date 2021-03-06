/********************************************************************************
 * Copyright (c) 2020 [Open Lowcode SAS](https://openlowcode.com/)
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0 .
 *
 * SPDX-License-Identifier: EPL-2.0
 ********************************************************************************/

package org.openlowcode.server.data.workflowhelper;

import org.openlowcode.module.system.data.Workflow;
import org.openlowcode.server.data.DataObject;
import org.openlowcode.server.data.TransitionFieldChoiceDefinition;
import org.openlowcode.server.data.properties.DataObjectId;
import org.openlowcode.server.data.properties.LifecycleInterface;

/**
 * A step in a complex workflowhaving a switch. After the execution of this
 * step, several workflow steps may be triggered
 * 
 * @author <a href="https://openlowcode.com/" rel="nofollow">Open Lowcode
 *         SAS</a>
 *
 * @param <E> data object the worfklow is about
 * @param <F> transition choice used for the lifecycle of the object
 */
public abstract class SwitchComplexWorkflowStep<
		E extends DataObject<E> & LifecycleInterface<E, F>,
		F extends TransitionFieldChoiceDefinition<F>>
		extends
		ComplexWorkflowStep<E, F> {
	/**
	 * gets the step to select next depending on context. This is the actual switch
	 * logic
	 * 
	 * @param workflowid id of the workflow
	 * @param object     data object
	 * @return the next step to execute
	 */
	public abstract ComplexWorkflowStep<E, F> getstepselection(DataObjectId<Workflow> workflowid, E object);

	/**
	 * creates a swich complex workflow
	 */
	public SwitchComplexWorkflowStep() {

	}

	@Override
	public void execute(DataObjectId<Workflow> workflowid, E object, ComplexWorkflowHelper<E, F> workflowhelper) {
		ComplexWorkflowStep<E, F> nextstep = getstepselection(workflowid, object);
		nextstep.execute(workflowid, object, workflowhelper);

	}

}
