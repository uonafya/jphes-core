package org.hisp.dhis.jphes.hierarchy.action.national;

import com.opensymphony.xwork2.Action;
import org.hisp.dhis.dataelement.CategoryOptionGroupSet;
import org.hisp.dhis.jphes.hierarchy.national.NationalUnit;
import org.hisp.dhis.jphes.hierarchy.national.NationalUnitService;
import org.hisp.dhis.jphes.program.Program;
import org.hisp.dhis.user.UserGroup;
import org.hisp.dhis.user.UserGroupService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author bangadennis on 06/01/17.
 */
public class GetNationalUnitAction implements Action
{
    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------
    private NationalUnitService nationalUnitService;

    public void setNationalUnitService(NationalUnitService nationalUnitService)
    {
        this.nationalUnitService =nationalUnitService;
    }

    private UserGroupService userGroupService;

    public void setUserGroupService(UserGroupService userGroupService){
        this.userGroupService = userGroupService;
    }

    // -------------------------------------------------------------------------
    // Input
    // -------------------------------------------------------------------------

    private Integer id;

    public void setId( Integer id )
    {
        this.id = id;
    }

    // -------------------------------------------------------------------------
    // Output
    // -------------------------------------------------------------------------

    private NationalUnit nationalUnit;

    public NationalUnit getNationalUnit(){return nationalUnit;}

    private List<Program> programList;

    public List<Program> getProgramList(){return programList;}

    private UserGroup userGroup;

    public UserGroup getUserGroup(){return userGroup;}

    private CategoryOptionGroupSet categoryOptionGroupSet;

    public CategoryOptionGroupSet getCategoryOptionGroupSet(){return categoryOptionGroupSet;}


    // -------------------------------------------------------------------------
    // Action Implementation
    // -------------------------------------------------------------------------

    @Override public String execute() throws Exception
    {
        nationalUnit = nationalUnitService.getNationalUnit( id );

        userGroup = nationalUnit.getUserGroup();

        categoryOptionGroupSet = nationalUnit.getCategoryOptionGroupSet();

        programList = new ArrayList<>(nationalUnit.getPrograms());

        Collections.sort( programList );

        return SUCCESS;

    }
}
