package org.hisp.dhis.webapi.controller.jphes;

import org.hisp.dhis.jphes.hierarchy.agency.AgencyUnit;
import org.hisp.dhis.schema.descriptors.AgencyUnitSchemaDescriptor;
import org.hisp.dhis.webapi.controller.AbstractCrudController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author bangadennis on 11/01/17.
 */
@Controller
@RequestMapping( value = AgencyUnitSchemaDescriptor.API_ENDPOINT )
public class AgencyUnitController  extends AbstractCrudController<AgencyUnit>
{
}
