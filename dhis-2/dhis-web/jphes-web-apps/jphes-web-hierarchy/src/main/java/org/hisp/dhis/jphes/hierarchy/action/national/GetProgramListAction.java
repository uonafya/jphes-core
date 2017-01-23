package org.hisp.dhis.jphes.hierarchy.action.national;

import org.hisp.dhis.paging.ActionPagingSupport;
import org.hisp.dhis.jphes.program.Program;
import org.hisp.dhis.jphes.program.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by afya on 07/01/17.
 */
public class GetProgramListAction extends ActionPagingSupport<Program>
{

    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------

    @Autowired
    private ProgramService programService;

    // -------------------------------------------------------------------------
    // Input & Output
    // -------------------------------------------------------------------------

    private List<Program> programs;

    public List<Program> getPrograms()
    {
        return programs;
    }

    // -------------------------------------------------------------------------
    // Action implementation
    // -------------------------------------------------------------------------

    @Override
    public String execute()
        throws Exception
    {
        programs = new ArrayList<>( programService.getAllPrograms() );

        Collections.sort( programs );

        if ( usePaging )
        {
            this.paging = createPaging( programs.size() );

            programs = programs.subList( paging.getStartPos(), paging.getEndPos() );
        }

        return SUCCESS;
    }
}
