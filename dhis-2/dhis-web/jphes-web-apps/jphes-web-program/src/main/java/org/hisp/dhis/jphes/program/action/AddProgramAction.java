package org.hisp.dhis.jphes.program.action;

import com.opensymphony.xwork2.Action;
import org.hisp.dhis.jphes.program.Program;
import org.hisp.dhis.jphes.program.ProgramService;
import org.hisp.dhis.user.UserService;

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

    // -------------------------------------------------------------------------
    // Input
    // -------------------------------------------------------------------------

    private String name;


    private String code;


    private String formName;

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDisplayName(String displayName) {
        this.formName = displayName;
    }
// -------------------------------------------------------------------------
    // Action implementation
    // -------------------------------------------------------------------------


    @Override public String execute() throws Exception
    {
        Program program = new Program();

        program.setFormName(formName);
        program.setName(name);
        program.setCode(code);

        programService.addProgram(program);

        return SUCCESS;
    }
}
