package org.hisp.dhis.webapi.controller.jphes;

import org.hisp.dhis.jphes.hierarchy.national.NationalUnit;
import org.hisp.dhis.schema.descriptors.NationalUnitSchemaDescriptor;
import org.hisp.dhis.webapi.controller.AbstractCrudController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author bangadennis on 10/01/17.
 */

@Controller
@RequestMapping( value = NationalUnitSchemaDescriptor.API_ENDPOINT )
public class NationalUnitController extends AbstractCrudController<NationalUnit>
{
}
