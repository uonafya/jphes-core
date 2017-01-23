package org.hisp.dhis.webapi.controller.jphes;

import org.hisp.dhis.jphes.hierarchy.donor.DonorUnit;
import org.hisp.dhis.schema.descriptors.DonorUnitSchemaDescriptor;
import org.hisp.dhis.webapi.controller.AbstractCrudController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author bangadennis on 11/01/17.
 */
@Controller
@RequestMapping( value = DonorUnitSchemaDescriptor.API_ENDPOINT )
public class DonorUnitController extends AbstractCrudController<DonorUnit>
{
}
