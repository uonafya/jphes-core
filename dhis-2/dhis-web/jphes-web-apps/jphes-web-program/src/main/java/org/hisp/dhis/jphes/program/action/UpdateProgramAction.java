package org.hisp.dhis.jphes.program.action;

import com.opensymphony.xwork2.Action;
import org.apache.commons.lang3.StringUtils;
import org.hisp.dhis.dataelement.DataElementService;
import org.hisp.dhis.indicator.Indicator;
import org.hisp.dhis.indicator.IndicatorService;
import org.hisp.dhis.jphes.program.Program;
import org.hisp.dhis.jphes.program.ProgramElement;
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

        program.setName(StringUtils.trimToNull(name));
        program.setCode(StringUtils.trimToNull(code));
        program.setDisplayName(StringUtils.trimToNull(displayName));

        program.getProgramElements().clear();
        program.getIndicators().clear();

        Set<ProgramElement> programElements = new HashSet<>();
        Set<Indicator> indicators = new HashSet<>();

        for (String id:deSelectedList){
            programElements.add(program.addProgramElement(dataElementService.getDataElement(id)));
        }
        program.setProgramElements(programElements);

        for (String id:indSelectedList){
            indicators.add(indicatorService.getIndicator(id));
        }
        program.setIndicators(indicators);

        programService.updateProgram(program);

        return SUCCESS;
    }
}
