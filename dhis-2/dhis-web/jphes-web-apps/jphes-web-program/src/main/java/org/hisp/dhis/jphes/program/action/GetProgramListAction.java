package org.hisp.dhis.jphes.program.action;

import org.hisp.dhis.jphes.program.Program;
import org.hisp.dhis.jphes.program.ProgramService;
import org.hisp.dhis.paging.ActionPagingSupport;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Created by afya on 06/12/16.
 */

public class GetProgramListAction extends ActionPagingSupport<Program>
{
    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------

    private ProgramService programService;

    public void setProgramService(ProgramService programService) {
        this.programService = programService;
    }
// -------------------------------------------------------------------------
    // Input & Output
    // -------------------------------------------------------------------------

    private List<Program> programs;

    public List<Program> getPrograms() {
        return programs;
    }

    private String key;

    public void setKey( String key )
    {
        this.key = key;
    }

    public String getKey()
    {
        return key;
    }

    // -------------------------------------------------------------------------
    // Action implementation
    // -------------------------------------------------------------------------

    @Override
    public String execute()
        throws Exception
    {
        // Filter on key only if set
        if ( isNotBlank( key ) )
        {
            this.paging = createPaging(programService.getProgramCountByName(key));

            programs = programService.getProgramsBetweenByName(key, paging.getStartPos(), paging.getPageSize() );
        }
        else
        {
            int count = programService.getProgramCount();

            this.paging = createPaging(count);

            programs = programService.getProgramsBetween( paging.getStartPos(), paging.getPageSize() );
        }

        return SUCCESS;
    }
}