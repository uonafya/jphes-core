package org.hisp.dhis.jphes.hierarchy.action.mechanism;

import com.opensymphony.xwork2.Action;
import org.hisp.dhis.i18n.I18n;
import org.hisp.dhis.jphes.hierarchy.mechanism.MechanismUnit;
import org.hisp.dhis.jphes.hierarchy.mechanism.MechanismUnitService;
import org.hisp.dhis.user.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by afya on 13/01/17.
 */
public class ValidateMechanismUnitAction implements Action
{
    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------
    @Autowired
    private MechanismUnitService mechanismUnitService;

    @Autowired
    private UserGroupService userGroupService;


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
            MechanismUnit match = mechanismUnitService.getMechanismUnitByName( name );

            int userGroupCount = userGroupService.getUserGroupCountByName( name );

            if ( (match != null && (id == null || match.getId() != id)) || (userGroupCount>0 && id==null) )
            {
                message = i18n.getString( "name_in_use" );

                return ERROR;
            }
        }

        if ( shortName != null )
        {
            MechanismUnit match2 = mechanismUnitService.getMechanismUnitByShortName( shortName );

            if ( match2 != null && (id == null || match2.getId() != id) )
            {
                message = i18n.getString( "name_in_use" );

                return ERROR;
            }
        }

        if ( code != null )
        {
            MechanismUnit match3 = mechanismUnitService.getMechanismUnitByCode( code );

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
