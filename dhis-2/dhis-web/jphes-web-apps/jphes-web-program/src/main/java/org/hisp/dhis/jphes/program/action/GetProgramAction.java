package org.hisp.dhis.jphes.program.action;

import com.opensymphony.xwork2.Action;
import org.hisp.dhis.dataelement.DataElement;
import org.hisp.dhis.dataelement.DataElementService;
import org.hisp.dhis.indicator.Indicator;
import org.hisp.dhis.indicator.IndicatorService;
import org.hisp.dhis.jphes.program.Program;
import org.hisp.dhis.jphes.program.ProgramService;

import java.util.List;

/**
 * Created by xenial on 1/13/17.
 */
public class GetProgramAction implements Action {

    private ProgramService programService;

    public void setProgramService(ProgramService programService) {
        this.programService = programService;
    }

    private DataElementService dataElementService;

    public void setDataElementService(DataElementService dataElementService) {
        this.dataElementService = dataElementService;
    }

    private IndicatorService indicatorService;

    public void setIndicatorService(IndicatorService indicatorService) {
        this.indicatorService = indicatorService;
    }

//  ---------------------------------
//  input
//  ---------------------------------
    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

//  ----------------------------------
//  output
//  ----------------------------------
    private Program program;

    public Program getProgram() {
        return program;
    }

    private List<DataElement> dataElements;

    public List<DataElement> getDataElements() {
        return dataElements;
    }

    private List<Indicator> indicators;

    public List<Indicator> getIndicators() {
        return indicators;
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
        program = programService.getProgram(id);
        dataElements = (List<DataElement>) program.getDataElements();
        indicators = (List<Indicator>) program.getIndicators();

        return SUCCESS;
    }
}
