package org.hisp.dhis.jphes.hierarchy.action.mechanism;

import org.hisp.dhis.jphes.hierarchy.mechanism.MechanismUnit;
import org.hisp.dhis.jphes.hierarchy.mechanism.MechanismUnitService;
import org.hisp.dhis.jphes.program.Program;
import org.hisp.dhis.paging.ActionPagingSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by afya on 25/01/17.
 */
public class viewMechanismProgramList extends ActionPagingSupport<Program>
{
    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------

    @Autowired
    private MechanismUnitService mechanismUnitService;

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
        MechanismUnit unit = mechanismUnitService.getMechanismUnit( id );

        programs = new ArrayList<>( unit.getPrograms() );

        Collections.sort( programs );

        this.paging = createPaging( programs.size() );

        programs = programs.subList( paging.getStartPos(), paging.getEndPos() );

        return SUCCESS;
    }
}
