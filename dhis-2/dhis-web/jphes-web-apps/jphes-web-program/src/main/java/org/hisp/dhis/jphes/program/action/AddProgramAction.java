package org.hisp.dhis.jphes.program.action;

import com.opensymphony.xwork2.Action;
import org.hisp.dhis.dataelement.DataElementService;
import org.hisp.dhis.jphes.program.Program;
import org.hisp.dhis.jphes.program.ProgramElement;
import org.hisp.dhis.jphes.program.ProgramService;
import org.hisp.dhis.user.UserService;
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
    private DataElementService dataElementService;

    // -------------------------------------------------------------------------
    // Input
    // -------------------------------------------------------------------------

    private String name;


    private String code;


    private String displayName;

    private List<String> deSelected = new ArrayList<>();

    private List<String> iSelected = new ArrayList<>();

    public void setDeSelected(List<String> deSelected) {
        this.deSelected = deSelected;
    }

    public void setiSelected(List<String> iSelected) {
        this.iSelected = iSelected;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    // -----------------------------------------------------------------
    // Action implementation
    // -------------------------------------------------------------------------


    @Override public String execute() throws Exception
    {
        Program program = new Program();

        program.setDisplayName(displayName);
        program.setName(name);
        program.setCode(code);
        program.setStartDate(new Date());
        program.setEndDate(null);

        Set<ProgramElement> programElements = new HashSet<>();

        for (String id:deSelected){
            programElements.add(program.addProgramElement(dataElementService.getDataElement(id)));
        }
        program.setProgramElements(programElements);

        programService.addProgram(program);

        return SUCCESS;
    }
}
