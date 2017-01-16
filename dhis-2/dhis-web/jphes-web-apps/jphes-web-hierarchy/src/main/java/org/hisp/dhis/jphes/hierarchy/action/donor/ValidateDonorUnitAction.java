package org.hisp.dhis.jphes.hierarchy.action.donor;


import com.opensymphony.xwork2.Action;
import org.hisp.dhis.i18n.I18n;
import org.hisp.dhis.jphes.hierarchy.donor.DonorUnit;
import org.hisp.dhis.jphes.hierarchy.donor.DonorUnitService;
import org.hisp.dhis.user.UserGroupService;

/**
 * Created by afya on 12/01/17.
 */
public class ValidateDonorUnitAction implements Action
{    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------

    private DonorUnitService donorUnitService;

    public void setDonorUnitService(DonorUnitService donorUnitService){
        this.donorUnitService=donorUnitService;
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
            DonorUnit match = donorUnitService.getDonorUnitByName( name );

            int userGroupCount = userGroupService.getUserGroupCountByName( name );

            if ( (match != null && (id == null || match.getId() != id)) || (userGroupCount>0 && id==null) )
            {
                message = i18n.getString( "name_in_use" );

                return ERROR;
            }
        }

        if ( shortName != null )
        {
            DonorUnit match2 = donorUnitService.getDonorUnitByShortName( shortName );

            if ( match2 != null && (id == null || match2.getId() != id) )
            {
                message = i18n.getString( "name_in_use" );

                return ERROR;
            }
        }

        if ( code != null )
        {
            DonorUnit match3 = donorUnitService.getDonorUnitByCode( code );

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
