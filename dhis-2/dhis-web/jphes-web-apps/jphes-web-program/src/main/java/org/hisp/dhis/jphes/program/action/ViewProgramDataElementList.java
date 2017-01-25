package org.hisp.dhis.jphes.program.action;

import org.hisp.dhis.dataelement.DataElement;
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
public class ViewProgramDataElementList extends ActionPagingSupport<DataElement>
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

    private List<DataElement> dataElements;

    public List<DataElement> getDataElements()
    {
        return dataElements;
    }

    private Program program;

    public Program getProgram(){ return program; }

    // -------------------------------------------------------------------------
    // Action implementation
    // -------------------------------------------------------------------------

    @Override public String execute() throws Exception
    {
        program = programService.getProgram( id );

        dataElements = new ArrayList<>( program.getDataElements() );

        Collections.sort( dataElements );

        this.paging = createPaging( dataElements.size() );

        dataElements = dataElements.subList( paging.getStartPos(), paging.getEndPos() );

        return SUCCESS;
    }
}
