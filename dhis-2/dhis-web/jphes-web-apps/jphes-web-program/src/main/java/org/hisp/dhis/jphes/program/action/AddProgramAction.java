package org.hisp.dhis.jphes.program.action;

import com.opensymphony.xwork2.Action;
import org.apache.commons.logging.Log;
import org.hisp.dhis.dataelement.DataElement;
import org.hisp.dhis.dataelement.DataElementGroup;
import org.hisp.dhis.dataelement.DataElementService;
import org.hisp.dhis.indicator.Indicator;
import org.hisp.dhis.indicator.IndicatorGroup;
import org.hisp.dhis.indicator.IndicatorService;
import org.hisp.dhis.jphes.program.Program;
import org.hisp.dhis.jphes.program.ProgramElement;
import org.hisp.dhis.jphes.program.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by afya on 06/12/16.
 */
public class AddProgramAction implements Action
{
    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------

    private ProgramService programService;

    public void setProgramService(ProgramService programService) {
        this.programService = programService;
    }

    @Autowired
    private IndicatorService indicatorService;

    @Autowired
    private DataElementService dataElementService;

    // -------------------------------------------------------------------------
    // Input
    // -------------------------------------------------------------------------

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


    private List<String> deSelected = new ArrayList<>();

    public void setDeSelected(List<String> deSelected) {
        this.deSelected = deSelected;
    }

    private List<String> indSelected = new ArrayList<>();

    public void setIndSelected(List<String> indSelected) {
        this.indSelected = indSelected;
    }


    // -----------------------------------------------------------------
    // Action implementation
    // -------------------------------------------------------------------------


    @Override public String execute() throws Exception
    {
        Program program = new Program();
        DataElementGroup dataElementGroup = new DataElementGroup();
        IndicatorGroup indicatorGroup = new IndicatorGroup();

        program.setDisplayName(displayName);
        program.setName(name);
        program.setCode(code);
        program.setStartDate(new Date());
        program.setEndDate(null);

        Set<ProgramElement> programElements = new HashSet<>();
        Set<DataElement> dataElementGroupMembers = new HashSet<>();
        Set<Indicator> indicators = new HashSet<>();

      if (deSelected.size() > 0){
          for (String id:deSelected){
              programElements.add(program.addProgramElement(dataElementService.getDataElement(id)));
              dataElementGroupMembers.add(dataElementService.getDataElement(id));
          }
          program.setProgramElements(programElements);
      }

      if (indSelected.size() > 0){
          for (String id:indSelected){
              indicators.add(indicatorService.getIndicator(id));
          }
          program.setIndicators(indicators);
      }

        System.out.println(indicators.size());

        dataElementGroup.setName(displayName);
        dataElementGroup.setCode(code);
        dataElementGroup.setShortName(name);
        dataElementGroup.setMembers(dataElementGroupMembers);

        indicatorGroup.setName(name);
        indicatorGroup.setCode(code);
        indicatorGroup.setMembers(indicators);

        programService.addProgram(program);
        dataElementService.addDataElementGroup(dataElementGroup);
        indicatorService.addIndicatorGroup(indicatorGroup);

        return SUCCESS;
    }
}
