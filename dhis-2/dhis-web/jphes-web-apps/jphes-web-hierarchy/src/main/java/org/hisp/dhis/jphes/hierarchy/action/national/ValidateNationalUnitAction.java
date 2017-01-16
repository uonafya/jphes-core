package org.hisp.dhis.jphes.hierarchy.action.national;

import com.opensymphony.xwork2.Action;
import org.hisp.dhis.i18n.I18n;
import org.hisp.dhis.jphes.hierarchy.national.NationalUnit;
import org.hisp.dhis.jphes.hierarchy.national.NationalUnitService;
import org.hisp.dhis.user.UserGroup;
import org.hisp.dhis.user.UserGroupService;

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

    private UserGroupService userGroupService;

    public void setUserGroupService(UserGroupService userGroupService){
        this.userGroupService = userGroupService;
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

    private String shortName;

    public void setShortName( String shortName )
    {
        this.shortName = shortName;
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
            NationalUnit match = nationalUnitService.getNationalUnitByName( name );

            int userGroupCount = userGroupService.getUserGroupCountByName( name );

            if ( (match != null && (id == null || match.getId() != id)) || (userGroupCount>0 && id==null) )
            {
                message = i18n.getString( "name_in_use" );

                return ERROR;
            }
        }

        if ( shortName != null )
        {
            NationalUnit match2 = nationalUnitService.getNationalUnitByShortName( shortName );

            if ( match2 != null && (id == null || match2.getId() != id) )
            {
                message = i18n.getString( "name_in_use" );

                return ERROR;
            }
        }

        if ( code != null )
        {
            NationalUnit match3 = nationalUnitService.getNationalUnitByCode( code );

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
