package org.hisp.dhis.jphes.program.action;

import org.hisp.dhis.indicator.Indicator;
import org.hisp.dhis.jphes.program.Program;
import org.hisp.dhis.jphes.program.ProgramService;
import org.hisp.dhis.paging.ActionPagingSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by afya on 25/01/17.
 */
public class ViewProgramIndicatorList extends ActionPagingSupport<Indicator>
{
    @Autowired
    private ProgramService programService;

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

    private List<Indicator> indicators;

    public List<Indicator> getIndicators()
    {
        return indicators;
    }

    private Program program;

    public Program getProgram(){ return program; }

    // -------------------------------------------------------------------------
    // Action implementation
    // -------------------------------------------------------------------------

    @Override public String execute() throws Exception
    {
        program = programService.getProgram( id );

        indicators = new ArrayList<>( program.getIndicators() );

        Collections.sort( indicators );

        this.paging = createPaging( indicators.size() );

        indicators = indicators.subList( paging.getStartPos(), paging.getEndPos() );

        return SUCCESS;
    }
}
