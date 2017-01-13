package org.hisp.dhis.jphes.hierarchy.action.donor;

import org.hisp.dhis.jphes.hierarchy.national.NationalUnit;
import org.hisp.dhis.jphes.hierarchy.national.NationalUnitService;
import org.hisp.dhis.paging.ActionPagingSupport;
import org.hisp.dhis.program.Program;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by afya on 13/01/17.
 */
class GetNationalProgramListAction extends ActionPagingSupport<Program>
{
    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------

    @Autowired
    private NationalUnitService nationalUnitService;

    // -------------------------------------------------------------------------
    // Input
    // -------------------------------------------------------------------------

    private Integer id;

    public void setId(Integer id){
        this.id = id;
    }

    // -------------------------------------------------------------------------
    // Output
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
        NationalUnit nationalUnit = nationalUnitService.getNationalUnit( id );

        programs = new ArrayList<>( nationalUnit.getPrograms() );

        Collections.sort( programs );

        if ( usePaging )
        {
            this.paging = createPaging( programs.size() );

            programs = programs.subList( paging.getStartPos(), paging.getEndPos() );
        }

        return SUCCESS;
    }
}
