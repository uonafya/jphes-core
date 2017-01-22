package org.hisp.dhis.webapi.controller.jphes;

import org.hisp.dhis.jphes.program.Program;
import org.hisp.dhis.schema.descriptors.JphesProgramSchemaDescriptor;
import org.hisp.dhis.webapi.controller.AbstractCrudController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author bangadennis on 10/01/17.
 */

@Controller
@RequestMapping( value = JphesProgramSchemaDescriptor.API_ENDPOINT )
public class ProgramController extends AbstractCrudController<Program>
{
}
