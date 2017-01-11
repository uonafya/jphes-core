package org.hisp.dhis.webapi.controller.jphes;

import org.hisp.dhis.jphes.hierarchy.mechanism.MechanismUnit;
import org.hisp.dhis.schema.descriptors.MechanismUnitSchemaDescriptor;
import org.hisp.dhis.webapi.controller.AbstractCrudController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author bangadennis on 11/01/17.
 */
@Controller
@RequestMapping( value = MechanismUnitSchemaDescriptor.API_ENDPOINT )
public class MechanismUnitController  extends AbstractCrudController<MechanismUnit>
{
}
