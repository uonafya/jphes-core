package org.hisp.dhis.jphes.program.action;

import com.opensymphony.xwork2.Action;
import org.hisp.dhis.dataelement.DataElementGroup;
import org.hisp.dhis.dataelement.DataElementService;
import org.hisp.dhis.i18n.I18n;
import org.hisp.dhis.indicator.IndicatorService;
import org.hisp.dhis.jphes.program.Program;
import org.hisp.dhis.jphes.program.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by mfl on 1/24/17.
 */
public class ValidateProgramAction implements Action {

    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------
    @Autowired
    private ProgramService programService;

    @Autowired
    private DataElementService dataElementService;


    private I18n i18n;

    public void setI18n( I18n i18n )
    {
        this.i18n = i18n;
    }

    // -------------------------------------------------------------------------
    // Input
    // -------------------------------------------------------------------------

    private Integer id;

    public void setId( Integer id )
    {
        this.id = id;
    }

    private String name;

    public void setName( String name )
    {
        this.name = name;
    }

    private String code;

    public void setCode( String code )
    {
        this.code = code;
    }



    // -------------------------------------------------------------------------
    // Output
    // -------------------------------------------------------------------------

    private String message;

    public String getMessage()
    {
        return message;
    }

    // -------------------------------------------------------------------------
    // Action implementation
    // -------------------------------------------------------------------------

    @Override
    public String execute()
            throws Exception
    {

        if ( name != null )
        {
            Program match = programService.getProgramByName(name);

            DataElementGroup dataElementGroup = dataElementService.getDataElementGroupByName(name);

            if ( (match != null && (id == null || match.getId() != id)) ||
                    (dataElementGroup != null && (id == null || dataElementGroup.getId() != id)) )
            {
                message = i18n.getString( "name_in_use" );

                return ERROR;
            }
        }

        if ( code != null )
        {
            Program match3 = programService.getProgramByCode( code );

            if ( match3 != null && (id == null || match3.getId() != id) )
            {
                message = i18n.getString( "code_in_use" );

                return ERROR;
            }
        }

        message = i18n.getString( "everything_is_ok" );

        return SUCCESS;
    }
}
