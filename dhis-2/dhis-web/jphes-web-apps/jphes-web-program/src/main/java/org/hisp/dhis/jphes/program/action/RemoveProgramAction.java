package org.hisp.dhis.jphes.program.action;

import com.opensymphony.xwork2.Action;
import org.hisp.dhis.common.DeleteNotAllowedException;
import org.hisp.dhis.i18n.I18n;
import org.hisp.dhis.jphes.program.Program;
import org.hisp.dhis.jphes.program.ProgramService;

/**
 * Created by xenial on 1/25/17.
 */
public class RemoveProgramAction implements Action {

    private ProgramService programService;

    public void setProgramService(ProgramService programService) {
        this.programService = programService;
    }

    private int id;

    public void setId(int id) {
        this.id = id;
    }

    private I18n i18n;

    public void setI18n(I18n i18n) {
        this.i18n = i18n;
    }

    private String message;

    public String getMessage() {
        return message;
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
        try {
            Program program = programService.getProgram(id);
            programService.deleteProgram(program);

        }catch (DeleteNotAllowedException ex){

            if ( ex.getErrorCode().equals( DeleteNotAllowedException.ERROR_ASSOCIATED_BY_OTHER_OBJECTS ) )
            {
                message = i18n.getString( "object_not_deleted_associated_by_objects" ) + " " + ex.getMessage();

                return ERROR;
            }
            
        }
        return SUCCESS;
    }
}
