package org.hisp.dhis.jphes.hierarchy.action.agency;

import com.opensymphony.xwork2.Action;
import org.hisp.dhis.jphes.hierarchy.donor.DonorUnit;
import org.hisp.dhis.jphes.hierarchy.donor.DonorUnitService;
import org.hisp.dhis.paging.ActionPagingSupport;
import org.hisp.dhis.jphes.program.Program;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by @bangadennis on 13/01/17.
 */
public class GetDonorProgramListAction extends ActionPagingSupport<Program>
{
    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------

    @Autowired
    private DonorUnitService donorUnitService;

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
        DonorUnit donorUnit = donorUnitService.getDonorUnit( id );

        programs = new ArrayList<>( donorUnit.getPrograms() );

        Collections.sort( programs );

        if ( usePaging )
        {
            this.paging = createPaging( programs.size() );

            programs = programs.subList( paging.getStartPos(), paging.getEndPos() );
        }

        return SUCCESS;
    }
}
