package org.hisp.dhis.jphes.program.action;

import com.opensymphony.xwork2.Action;
import org.apache.commons.lang3.StringUtils;
import org.hisp.dhis.dataelement.DataElement;
import org.hisp.dhis.dataelement.DataElementGroup;
import org.hisp.dhis.dataelement.DataElementService;
import org.hisp.dhis.indicator.Indicator;
import org.hisp.dhis.indicator.IndicatorGroup;
import org.hisp.dhis.indicator.IndicatorService;
import org.hisp.dhis.jphes.program.Program;
import org.hisp.dhis.jphes.program.ProgramService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by xenial on 1/13/17.
 */
public class UpdateProgramAction implements Action {

    private ProgramService programService;

    public void setProgramService(ProgramService programService) {
        this.programService = programService;
    }

    private IndicatorService indicatorService;

    public void setIndicatorService(IndicatorService indicatorService) {
        this.indicatorService = indicatorService;
    }

    private DataElementService dataElementService;

    public void setDataElementService(DataElementService dataElementService) {
        this.dataElementService = dataElementService;
    }

    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    private String code;

    public void setCode(String code) {
        this.code = code;
    }

    private String displayName;

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    private List<String> deSelectedList = new ArrayList<>();

    public void setDeSelectedList(List<String> deSelectedList) {
        this.deSelectedList = deSelectedList;
    }

    private List<String> indSelectedList = new ArrayList<>();

    public void setIndSelectedList(List<String> indSelectedList) {
        this.indSelectedList = indSelectedList;
    }

    /**
     * Where the logic of the action is executed.
     *
     * @return a string representing the logical result of the execution.
     * See constants in this interface for a list of standard result values.
     * @throws Exception thrown if a system level exception occurs.
     *                   <b>Note:</b> Application level exceptions should be handled by returning
     *                   an error value, such as <code>Action.ERROR</code>.
     */
    @Override
    public String execute() throws Exception {
        Program program = programService.getProgram(id);
        DataElementGroup dataElementGroup = program.getDataElementGroup();
        IndicatorGroup indicatorGroup = program.getIndicatorGroup();

        if( deSelectedList.size() >0 || indSelectedList.size()>0)
        {
            program.setName( StringUtils.trimToNull( name ) );
            program.setCode( StringUtils.trimToNull( code ) );
            program.setDisplayName( StringUtils.trimToNull( displayName ) );
            program.setShortName( StringUtils.abbreviate(name,40));

            program.getDataElements().clear();
            program.getIndicators().clear();

            dataElementGroup.getMembers().clear();
            indicatorGroup.getMembers().clear();

            for ( String id : deSelectedList )
            {
                DataElement dataElement = dataElementService.getDataElement( id );

                dataElementGroup.getMembers().add( dataElement );
                program.getDataElements().add( dataElement );
            }

            for ( String id : indSelectedList )
            {

                Indicator indicator = indicatorService.getIndicator( id );

                indicatorGroup.getMembers().add( indicator );
                program.getIndicators().add( indicator );

            }

            //updating DataElementGroup and IndicatorGroup
            dataElementGroup.setName( StringUtils.trimToNull( name ) );
            dataElementGroup.setCode( StringUtils.trimToNull( code ) );
            dataElementGroup.setShortName( StringUtils.abbreviate(name, 40) );

            indicatorGroup.setName( StringUtils.trimToNull( name ) );
            indicatorGroup.setCode( StringUtils.trimToNull( code ) );

            programService.updateProgram( program );

            dataElementService.updateDataElementGroup( dataElementGroup );

            indicatorService.updateIndicatorGroup( indicatorGroup );
        }

        return SUCCESS;
    }
}
