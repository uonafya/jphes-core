package org.hisp.dhis.jphes.hierarchy.action.national;

import com.opensymphony.xwork2.Action;
import org.hisp.dhis.i18n.I18n;
import org.hisp.dhis.jphes.hierarchy.national.NationalUnit;
import org.hisp.dhis.jphes.hierarchy.national.NationalUnitService;

/**
 * @author  bangadennis on 05/01/17.
 */
public class ValidateNationalUnitAction implements Action
{
    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------

    private NationalUnitService nationalUnitService;

    public void setNationalUnitService(NationalUnitService nationalUnitService){
        this.nationalUnitService=nationalUnitService;
    }

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
        name = name.trim();

        if ( name != null )
        {
            NationalUnit match = nationalUnitService.getNationalUnitByName( name );

            if ( match != null && (id == null || match.getId() != id) )
            {
                message = i18n.getString( "name_in_use" );

                return ERROR;
            }
        }

        message = i18n.getString( "everything_is_ok" );

        return SUCCESS;
    }
}
